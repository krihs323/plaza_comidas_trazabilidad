package com.plaza.traceability.domain.model;

import java.time.Duration;

public class TraceabilityOrderReport {


    private Long idOrder;

    private Long idCustomer;

    private String nameCustomer;

    private String restaurantName;

    private Long idRestaurant;

    private Duration time;


    public TraceabilityOrderReport(Long idOrder, Long idCustomer, String nameCustomer, String restaurantName, Long idRestaurant, Duration time) {
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.restaurantName = restaurantName;
        this.idRestaurant = idRestaurant;
        this.time = time;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getnameCustomer() {
        return nameCustomer;
    }

    public void setnameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration time) {
        this.time = time;
    }
}
