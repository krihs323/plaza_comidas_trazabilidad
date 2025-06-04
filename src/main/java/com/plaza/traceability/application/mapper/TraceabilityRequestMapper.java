package com.plaza.traceability.application.mapper;

import com.plaza.traceability.application.dto.TraceabilityRequest;
import com.plaza.traceability.application.dto.TraceabilityResponse;
import com.plaza.traceability.domain.model.PageResult;
import com.plaza.traceability.domain.model.Traceability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TraceabilityRequestMapper {


    Traceability toTraceability(TraceabilityRequest traceabilityRequest);

    PageResult<TraceabilityResponse> toPageResultResponse(PageResult<Traceability> pageResult);

}
