package com.plaza.traceability.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;


@Getter
@Setter
public class TraceabilityOrderReportResponse {

    private Long idOrder;

    private Long idCustomer;

    private String nameCustomer;

    private String restaurantName;

    private Long idRestaurant;

    private Duration time;

}
