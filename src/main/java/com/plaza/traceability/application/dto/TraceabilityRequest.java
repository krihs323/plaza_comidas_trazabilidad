package com.plaza.traceability.application.dto;

import com.plaza.traceability.domain.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TraceabilityRequest {

        private Long idOrder;

        private Long idCustomer;

        private Status statusBefore;

        private String status;

        private Date dateTime;

}
