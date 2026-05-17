package com.bolivar.bioingenieria.app.sigma_bb.reports_hexagon.infrastructure.input.rest;

import com.bolivar.bioingenieria.app.sigma_bb.reports_hexagon.application.services.ReportService;
import com.bolivar.bioingenieria.app.sigma_bb.shared.domain.ReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> getReport(@RequestBody ReportRequest reportRequest) {
        Map<String, Object> result = reportService.generateReport(UUID.randomUUID().toString(), reportRequest.modelId());
        return ResponseEntity.ok(result);
    }
}

