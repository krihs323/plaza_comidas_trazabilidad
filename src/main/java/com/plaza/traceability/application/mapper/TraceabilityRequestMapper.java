package com.plaza.traceability.application.mapper;

import com.plaza.traceability.application.dto.TraceabilityEmployeeReportResponse;
import com.plaza.traceability.application.dto.TraceabilityOrderReportResponse;
import com.plaza.traceability.application.dto.TraceabilityRequest;
import com.plaza.traceability.application.dto.TraceabilityResponse;
import com.plaza.traceability.domain.model.PageResult;
import com.plaza.traceability.domain.model.Traceability;
import com.plaza.traceability.domain.model.TraceabilityEmployeeReport;
import com.plaza.traceability.domain.model.TraceabilityOrderReport;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TraceabilityRequestMapper {


    Traceability toTraceability(TraceabilityRequest traceabilityRequest);

    PageResult<TraceabilityResponse> toPageResultResponse(PageResult<Traceability> pageResult);

    List<TraceabilityOrderReportResponse> toPageResultTraceabilityOrderReport(List<TraceabilityOrderReport> traceabilityList);

    List<TraceabilityEmployeeReportResponse> toTraceabilityEmployeeReportResponse(List<TraceabilityEmployeeReport> traceabilityList);
}
