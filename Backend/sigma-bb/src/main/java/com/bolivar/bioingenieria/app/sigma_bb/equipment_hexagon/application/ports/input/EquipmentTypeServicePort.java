package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.input;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.EquipmentTypeService;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.equipment_type_services.commands.CreateEquipmentTypeCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.equipment_type_services.commands.DeleteEquipmentTypeCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.equipment_type_services.commands.UpdateEquipmentTypeCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.metrological_data_services.commands.MetrologicalDataCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.metrological_data_services.commands.MetrologicalDataCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.services.metrological_data_services.commands.MetrologicalDataCommand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.EquipmentType;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.MetrologicalData;

import java.util.List;

public interface EquipmentTypeServicePort {
    List<EquipmentType> findAll();
    EquipmentType findById(String id);
    EquipmentType save(CreateEquipmentTypeCommand command);
    EquipmentType update(String id, UpdateEquipmentTypeCommand command);
    void delete(DeleteEquipmentTypeCommand command);

    EquipmentType addMetrologicalData(String  equipmentTypeId,
                                             MetrologicalDataCommand command);

    EquipmentType removeMetrologicalData(String  equipmentTypeId,
                                                MetrologicalDataCommand command);

    EquipmentType updateMetrologicalData(String equipmentTypeId,
                                         MetrologicalDataCommand oldCommand,
                                         MetrologicalDataCommand newCommand);

    EquipmentType addMetrologicalDataList(String  equipmentTypeId,
                                                 List<MetrologicalDataCommand> command );

    EquipmentType removeMetrologicalDataList(String  equipmentTypeId,
                                                    List<MetrologicalDataCommand> command);

    EquipmentType updateMetrologicalDataList(String  equipmentTypeId,
                                             List<MetrologicalDataCommand> oldCommand
                                             ,List<MetrologicalDataCommand> newCommand);
}
