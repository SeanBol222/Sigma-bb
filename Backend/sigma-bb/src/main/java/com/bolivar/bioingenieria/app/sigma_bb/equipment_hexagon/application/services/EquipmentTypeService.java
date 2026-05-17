package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input.EquipmentTypeServicePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.EquipmentTypePersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.equipment_type_services.commands.CreateEquipmentTypeCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.equipment_type_services.commands.DeleteEquipmentTypeCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.equipment_type_services.commands.UpdateEquipmentTypeCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.metrological_data_services.commands.MetrologicalDataCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.EquipmentType;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.MetrologicalData;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.errors.EquipmentTypeNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.shared.application.ports.output.EventDispatcherPort;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.events.DomainEvent;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.events.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EquipmentTypeService implements EquipmentTypeServicePort {
    private final EquipmentTypePersistencePort equipmentTypePersistencePort;
    private final EventDispatcherPort eventDispatcherPort;

    @Autowired
    public EquipmentTypeService(EquipmentTypePersistencePort equipmentTypePersistencePort,
                                @Qualifier(value = "springDispatcher") EventDispatcherPort eventDispatcherPort) {
        this.equipmentTypePersistencePort = equipmentTypePersistencePort;
        this.eventDispatcherPort = eventDispatcherPort;
    }

    @Override
    public List<EquipmentType> findAll() {
        return equipmentTypePersistencePort.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EquipmentType findById(String id) {
        return this.equipmentTypePersistencePort.findById(id)
                .orElseThrow(() -> new EquipmentTypeNotFoundException(id));
    }

    @Override
    public EquipmentType save(CreateEquipmentTypeCommand command) {
        EquipmentType et = EquipmentType.create(
                command.equipmentTypeName(),
                command.technicalDefinition(),
                command.careRecommendations(),
                command.voltage(),
                command.amperage(),
                command.predominantTechnology(),
                command.verifiable(),
                command.unitMaintenanceValue(),
                command.metrologicalData());

        equipmentTypePersistencePort.save(et);
        dispatchEvents(et);
        return et;
    }

    @Override
    public EquipmentType update(String id, UpdateEquipmentTypeCommand command) {
        EquipmentType et = equipmentTypePersistencePort.findById(id)
                .orElseThrow(() -> new EquipmentTypeNotFoundException(id));
        et.updateEquipmentType(
                command.equipmentTypeName(), command.technicalDefinition(),
                command.careRecommendations(), command.voltage(), command.amperage(),
                command.predominantTechnology(), command.verifiable(), command.unitMaintenanceValue());
        equipmentTypePersistencePort.update(id, et);
        dispatchEvents(et);
        return et;
    }

    @Override
    public void delete(DeleteEquipmentTypeCommand command) {
        EquipmentType et = equipmentTypePersistencePort.findById(command.id())
                .orElseThrow(() -> new EquipmentTypeNotFoundException(command.id()));
        et.deleteEquipmentType();
        equipmentTypePersistencePort.delete(command.id());
        dispatchEvents(et);
    }

    @Override
    public EquipmentType addMetrologicalData(String equipmentTypeId, MetrologicalDataCommand command) {
        EquipmentType et = equipmentTypePersistencePort.findById(equipmentTypeId)
                .orElseThrow(() -> new EquipmentTypeNotFoundException(equipmentTypeId));

        et.addMetrologicalData(
                MetrologicalData.builder()
                        .value(command.value())
                        .type(command.type())
                        .build());
        equipmentTypePersistencePort.save(et);
        dispatchEvents(et);
        return et;
    }

    @Override
    public EquipmentType removeMetrologicalData(String equipmentTypeId, MetrologicalDataCommand command) {
        EquipmentType et = equipmentTypePersistencePort.findById(equipmentTypeId)
                .orElseThrow(() -> new EquipmentTypeNotFoundException(equipmentTypeId));

        et.removeMetrologicalData(
                MetrologicalData.builder()
                        .value(command.value())
                        .type(command.type())
                        .build());
        equipmentTypePersistencePort.update(equipmentTypeId, et);
        dispatchEvents(et);
        return et;
    }

    @Override
    public EquipmentType updateMetrologicalData(String equipmentTypeId,
                                                MetrologicalDataCommand oldCommand,
                                                MetrologicalDataCommand newCommand) {
        EquipmentType et = equipmentTypePersistencePort.findById(equipmentTypeId)
                .orElseThrow(() -> new EquipmentTypeNotFoundException(equipmentTypeId));

        et.updateMetrologicalData(
                MetrologicalData.builder()
                        .value(oldCommand.value())
                        .type(oldCommand.type())
                        .build(),
                MetrologicalData.builder()
                        .value(newCommand.value())
                        .type(newCommand.type())
                        .build());
        equipmentTypePersistencePort.update(equipmentTypeId, et);
        dispatchEvents(et);
        return et;
    }

    @Override
    public EquipmentType addMetrologicalDataList(String equipmentTypeId,
                                                 List<MetrologicalDataCommand> command) {
        EquipmentType et = equipmentTypePersistencePort.findById(equipmentTypeId)
                .orElseThrow(() -> new EquipmentTypeNotFoundException(equipmentTypeId));

        for(MetrologicalDataCommand c: command) {
            et.addMetrologicalData(
                    MetrologicalData.builder()
                            .value(c.value())
                            .type(c.type())
                            .build());
        }

        equipmentTypePersistencePort.save(et);
        dispatchEvents(et);
        return et;
    }

    @Override
    public EquipmentType removeMetrologicalDataList(String equipmentTypeId, List<MetrologicalDataCommand> command) {
        EquipmentType et = equipmentTypePersistencePort.findById(equipmentTypeId)
                .orElseThrow(() -> new EquipmentTypeNotFoundException(equipmentTypeId));

        for(MetrologicalDataCommand c: command) {
            et.removeMetrologicalData(
                    MetrologicalData.builder()
                            .value(c.value())
                            .type(c.type())
                            .build());
        }

        equipmentTypePersistencePort.update(equipmentTypeId, et);
        dispatchEvents(et);
        return et;
    }

    @Override
    public EquipmentType updateMetrologicalDataList(String equipmentTypeId,
                                                    List<MetrologicalDataCommand> oldCommand,
                                                    List<MetrologicalDataCommand> newCommand) {


        EquipmentType et = equipmentTypePersistencePort.findById(equipmentTypeId)
                .orElseThrow(() -> new EquipmentTypeNotFoundException(equipmentTypeId));

        if(oldCommand.size() != newCommand.size()) {
            throw new RuntimeException("Number of old commands does not match number of new commands");
        }

        for(int i = 0; i < oldCommand.size(); i++) {
            MetrologicalDataCommand oldC = oldCommand.get(i);
            MetrologicalDataCommand newC = newCommand.get(i);
            et.updateMetrologicalData(
                    MetrologicalData.builder()
                            .value(oldC.value())
                            .type(oldC.type())
                            .build(),
                    MetrologicalData.builder()
                            .value(newC.value())
                            .type(newC.type())
                            .build());
        }

        equipmentTypePersistencePort.update(equipmentTypeId, et);
        dispatchEvents(et);
        return et;
    }

    private void dispatchEvents(EquipmentType aggregate) {
        List<DomainEvent<? extends Payload>> events = aggregate.pullEvents();
        events.forEach(eventDispatcherPort::dispatch);
    }
}
