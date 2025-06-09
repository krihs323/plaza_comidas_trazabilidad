package com.plaza.traceability.application.handler;

import com.plaza.traceability.application.dto.TraceabilityEmployeeReportResponse;
import com.plaza.traceability.application.dto.TraceabilityOrderReportResponse;
import com.plaza.traceability.application.dto.TraceabilityRequest;
import com.plaza.traceability.application.dto.TraceabilityResponse;
import com.plaza.traceability.domain.model.PageResult;

import java.util.List;

public interface ITraceabilityHandler {

    void saveTraceability(TraceabilityRequest traceabilityRequest);

    PageResult<TraceabilityResponse> getAllLogs(int page, int size, String sortBy, String sortDir);

    List<TraceabilityOrderReportResponse> getReportByOrder();

    List<TraceabilityEmployeeReportResponse> getReportByEmployee();
}
