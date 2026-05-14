package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.equipment_type.events.EquipmentTypeCreatedEvent;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.equipment_type.events.EquipmentTypeDeletedEvent;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.equipment_type.events.EquipmentTypePayload;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.equipment_type.events.EquipmentTypeUpdatedEvent;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.error.DomainException;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.metrological_data.events.MetrologicalDataCreatedEvent;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.metrological_data.events.MetrologicalDataDeletedEvent;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.metrological_data.events.MetrologicalDataUpdatedEvent;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.AggregateRoot;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.EventMetadata;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class EquipmentType extends AggregateRoot {
    private UUID id;
    private String equipmentTypeName;
    private String technicalDefinition;
    private String careRecommendations;
    private Integer voltage;
    private BigDecimal amperage;
    private String predominantTechnology;
    private Boolean verifiable;
    private Long unitMaintenanceValue;
    private List<MetrologicalData> metrologicalData = new ArrayList<>();

    public static EquipmentType create(
            String equipmentTypeName, String technicalDefinition, String careRecommendations,
            Integer voltage, BigDecimal amperage, String predominantTechnology,
            Boolean verifiable, Long unitMaintenanceValue, List<MetrologicalData> metrologicalData) {

        EquipmentType et = EquipmentType.builder()
                .id(UUID.randomUUID())
                .equipmentTypeName(equipmentTypeName)
                .technicalDefinition(technicalDefinition)
                .careRecommendations(careRecommendations)
                .voltage(voltage)
                .amperage(amperage)
                .predominantTechnology(predominantTechnology)
                .verifiable(verifiable)
                .unitMaintenanceValue(unitMaintenanceValue)
                .metrologicalData(metrologicalData)
                .build();

        EventMetadata metadata = new EventMetadata(
                UUID.randomUUID().toString(), "equipmentType.created", 1, Instant.now(), et.id.toString());

        et.registerEvent(new EquipmentTypeCreatedEvent(metadata, new EquipmentTypePayload(
                et.equipmentTypeName, et.technicalDefinition, et.careRecommendations,
                et.voltage, et.amperage, et.predominantTechnology, et.verifiable, et.unitMaintenanceValue)));
        return et;
    }

    public void updateEquipmentType(
            String equipmentTypeName, String technicalDefinition, String careRecommendations,
            Integer voltage, BigDecimal amperage, String predominantTechnology,
            Boolean verifiable, Long unitMaintenanceValue) {
        this.equipmentTypeName = equipmentTypeName;
        this.technicalDefinition = technicalDefinition;
        this.careRecommendations = careRecommendations;
        this.voltage = voltage;
        this.amperage = amperage;
        this.predominantTechnology = predominantTechnology;
        this.verifiable = verifiable;
        this.unitMaintenanceValue = unitMaintenanceValue;

        EventMetadata metadata = new EventMetadata(
                UUID.randomUUID().toString(), "equipmentType.updated", 1, Instant.now(), this.id.toString());

        registerEvent(new EquipmentTypeUpdatedEvent(metadata, new EquipmentTypePayload(
                this.equipmentTypeName, this.technicalDefinition, this.careRecommendations,
                this.voltage, this.amperage, this.predominantTechnology, this.verifiable, this.unitMaintenanceValue)));
    }

    public void deleteEquipmentType() {
        EventMetadata metadata = new EventMetadata(
                UUID.randomUUID().toString(), "equipmentType.deleted", 1, Instant.now(), this.id.toString());
        registerEvent(new EquipmentTypeDeletedEvent(metadata, new EquipmentTypePayload(
                this.equipmentTypeName, this.technicalDefinition, this.careRecommendations,
                this.voltage, this.amperage, this.predominantTechnology, this.verifiable, this.unitMaintenanceValue)));
    }

    public void addMetrologicalData(MetrologicalData md) {
        if (!this.verifiable)
            throw new DomainException("Cannot add metrological data to non-verifiable equipment type");

        boolean duplicated = this.metrologicalData.stream()
                .anyMatch(existing -> existing.getValue().equals(md.getValue())
                        && existing.getType().equals(md.getType()));
        if (duplicated) throw new DomainException("Metrological data already exists for this equipment type");

        this.metrologicalData.add(md);

        EventMetadata metadata = new EventMetadata(UUID.randomUUID().toString(), "metrologicalData.added", 1, Instant.now(), this.id.toString());
        registerEvent(new MetrologicalDataCreatedEvent(metadata, md));
    }

    public void updateMetrologicalData(MetrologicalData oldData, MetrologicalData newData) {
        if (!this.verifiable)
            throw new DomainException("Cannot update metrological data on non-verifiable equipment type");

        boolean duplicated = this.metrologicalData.stream()
                .anyMatch(existing -> !existing.equals(oldData)
                        && existing.getValue().equals(newData.getValue())
                        && existing.getType().equals(newData.getType()));
        if (duplicated) throw new DomainException("The new metrological data conflicts with an existing one");

        int index = this.metrologicalData.indexOf(oldData);
        if (index == -1) throw new DomainException("Metrological data to update does not exist");

        this.metrologicalData.set(index, newData);

        EventMetadata metadata = new EventMetadata(UUID.randomUUID().toString(), "metrologicalData.updated", 1, Instant.now(), this.id.toString());
        registerEvent(new MetrologicalDataUpdatedEvent(metadata, oldData));
    }

    public void removeMetrologicalData(MetrologicalData md) {
        if (!this.verifiable)
            throw new DomainException("Cannot remove metrological data from non-verifiable equipment type");

        boolean exists = this.metrologicalData.stream()
                .anyMatch(existing -> existing.getValue().equals(md.getValue())
                        && existing.getType().equals(md.getType()));
        if (!exists) throw new DomainException("Metrological data does not exist for this equipment type");

        this.metrologicalData.removeIf(p -> p.equals(md));

        EventMetadata metadata = new EventMetadata(UUID.randomUUID().toString(), "metrologicalData.removed", 1, Instant.now(), this.id.toString());
        registerEvent(new MetrologicalDataDeletedEvent(metadata, md));
    }

    public List<MetrologicalData> getMetrologicalData() {
        return metrologicalData;
    }
}
