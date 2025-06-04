package com.plaza.traceability.application.dto;

import com.plaza.traceability.domain.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TraceabilityResponse {

    private Long idOrder;

    private Status statusBefore;

    private String status;

}
