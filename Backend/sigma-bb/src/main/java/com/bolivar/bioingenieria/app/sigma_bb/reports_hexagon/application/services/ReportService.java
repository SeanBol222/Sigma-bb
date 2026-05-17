package com.bolivar.bioingenieria.app.sigma_bb.reports_hexagon.application.services;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.input.model.report.EquipmentDomainReportDTO;
import com.bolivar.bioingenieria.app.sigma_bb.location_hexagon.infrastructure.input.model.report.CountryDomainReportDTO;
import com.bolivar.bioingenieria.app.sigma_bb.shared.application.ports.input.ReportDataProviderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ReportService {

    private final ReportDataProviderPort<EquipmentDomainReportDTO> equipmentReportProvider;
    private final ReportDataProviderPort<CountryDomainReportDTO> countryReportProvider;

    @Autowired
    public ReportService(
            @Qualifier("equipmentReportProvider") ReportDataProviderPort<EquipmentDomainReportDTO> equipmentReportProvider,
            @Qualifier("countryReportProvider") ReportDataProviderPort<CountryDomainReportDTO> countryReportProvider) {
        this.equipmentReportProvider = equipmentReportProvider;
        this.countryReportProvider = countryReportProvider;
    }

    public Map<String, Object> generateReport(String reportId, String modelId) {
        Map<String, Object> report = new LinkedHashMap<>();
        report.put("reportId", reportId);

        var equipmentData = equipmentReportProvider.provideReportData(modelId);
        report.put(equipmentReportProvider.domainName(), equipmentData);

        String countryId = null;
        if (equipmentData != null && equipmentData.getManufacturer() != null) {
            countryId = equipmentData.getManufacturer().getCountryId();
        }

        var countryData = (countryId != null)
                ? countryReportProvider.provideReportData(countryId)
                : null;
        report.put(countryReportProvider.domainName(), countryData);
        System.out.println("Country Data: " + countryData);
        System.out.println("Generated report: " + report);
        return report;
    }
}
