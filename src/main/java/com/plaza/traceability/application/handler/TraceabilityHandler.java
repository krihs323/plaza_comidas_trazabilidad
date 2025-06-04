package com.plaza.traceability.application.handler;

import com.plaza.traceability.application.dto.TraceabilityRequest;
import com.plaza.traceability.application.dto.TraceabilityResponse;
import com.plaza.traceability.application.mapper.TraceabilityRequestMapper;
import com.plaza.traceability.domain.api.ITraceabilityServicePort;
import com.plaza.traceability.domain.model.PageResult;
import com.plaza.traceability.domain.model.Traceability;
import org.springframework.stereotype.Service;

@Service
public class TraceabilityHandler implements ITraceabilityHandler {

    private final ITraceabilityServicePort traceabilityServicePort;
    private final TraceabilityRequestMapper traceabilityRequestMapper;

    public TraceabilityHandler(ITraceabilityServicePort traceabilityServicePort, TraceabilityRequestMapper traceabilityRequestMapper) {
        this.traceabilityServicePort = traceabilityServicePort;
        this.traceabilityRequestMapper = traceabilityRequestMapper;
    }

    @Override
    public void saveTraceability(TraceabilityRequest traceabilityRequest) {
        Traceability traceability = traceabilityRequestMapper.toTraceability(traceabilityRequest);

        traceabilityServicePort.saveTraceability(traceability);
    }

    @Override
    public PageResult<TraceabilityResponse> getAllLogs(int page, int size, String sortBy, String sortDir) {
        PageResult<Traceability> traceabilityList = traceabilityServicePort.getAllTraceabilityByCustomer(page, size, sortBy, sortDir);

        return traceabilityRequestMapper.toPageResultResponse(traceabilityList);
    }
}
