package com.plaza.traceability.infraestructure.imput.rest;

import com.plaza.traceability.application.dto.*;
import com.plaza.traceability.application.handler.ITraceabilityHandler;
import com.plaza.traceability.domain.model.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/trazabilidad")
public class TraceabilityRestController {

    private final ITraceabilityHandler traceabilityHandler;

    public TraceabilityRestController(ITraceabilityHandler traceabilityHandler) {
        this.traceabilityHandler = traceabilityHandler;
    }

    @Operation(summary = "Add a new log")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Log created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Log bad request", content = @Content)
    })
    @PostMapping("/create/")
    public ResponseEntity<?> saveTraceability(@RequestBody @Valid TraceabilityRequest traceabilityRequest) {
        traceabilityHandler.saveTraceability(traceabilityRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all logs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs All", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<PageResult<TraceabilityResponse>> getAllRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(traceabilityHandler.getAllLogs(page, size, sortBy, sortDir));
    }

    @Operation(summary = "Get reports by owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs All by orders", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping("/reports/orders")
    public ResponseEntity<List<TraceabilityOrderReportResponse>> getReportOrders() {
        return ResponseEntity.ok(traceabilityHandler.getReportByOrder());
    }

    @Operation(summary = "Get reports by employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs All by employees", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping("/reports/employees")
    public ResponseEntity<List<TraceabilityEmployeeReportResponse>> getReportemployees() {
        return ResponseEntity.ok(traceabilityHandler.getReportByEmployee());
    }

}