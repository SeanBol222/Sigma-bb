package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.mapper;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.TechnicalVerification;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.request.TechnicalVerificationRequest;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.response.TechnicalVerificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TechnicalVerificationRestMapper {
    TechnicalVerification toTechnicalVerification(TechnicalVerificationRequest technicalVerificationRequest);
    TechnicalVerificationResponse toTechnicalVerificationResponse(TechnicalVerification technicalVerification);
    List<TechnicalVerificationResponse> toTechnicalVerificationResponseList(List<TechnicalVerification> technicalVerificationList);
}
