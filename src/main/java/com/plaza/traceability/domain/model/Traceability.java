package com.plaza.traceability.domain.model;

import java.util.Date;

public class Traceability {

    private Long idOrder;

    private String idCustomer;

    private Status statusBefore;

    private Status status;

    private Date dateTime;

    private String nameCustomer;

    private String restaurantName;

    private Long idRestaurant;

    private String employeeName;

    private Long idEmployee;

    public Traceability(Long idOrder, String idCustomer, Status statusBefore, Status status, Date dateTime, String nameCustomer, String restaurantName, Long idRestaurant, String employeeName, Long idEmployee) {
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.statusBefore = statusBefore;
        this.status = status;
        this.dateTime = dateTime;
        this.nameCustomer = nameCustomer;
        this.restaurantName = restaurantName;
        this.idRestaurant = idRestaurant;
        this.employeeName = employeeName;
        this.idEmployee = idEmployee;
    }

    private Traceability(Builder builder) {
        this.idOrder = builder.idOrder;
        this.idCustomer = builder.idCustomer;
        this.statusBefore = builder.statusBefore;
        this.status = builder.status;
        this.dateTime = builder.dateTime;
        this.nameCustomer = builder.nameCustomer;
        this.restaurantName = builder.restaurantName;
        this.idRestaurant = builder.idRestaurant;
        this.employeeName = builder.employeeName;
        this.idEmployee =builder.idEmployee;
    }


    public Long getIdOrder() {
       return idOrder;
    }

    public void setIdOrder(Long idOrder) {
            this.idOrder = idOrder;
    }

    public String getIdCustomer() {
            return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
            this.idCustomer = idCustomer;
    }

    public Status getStatusBefore() {
            return statusBefore;
    }

    public void setStatusBefore(Status statusBefore) {
            this.statusBefore = statusBefore;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public static class Builder {
        private Long idOrder;
        private String idCustomer;
        private Status statusBefore;
        private Status status;
        private Date dateTime;
        private String nameCustomer;
        private String restaurantName;
        private Long idRestaurant;
        private String employeeName;
        private Long idEmployee;

        public Builder idOrder(Long idOrder) {
            this.idOrder = idOrder;
            return this; // Retorna el propio Builder para encadenar llamadas
        }

        public Builder idCustomer(String idCustomer) {
            this.idCustomer = idCustomer;
            return this;
        }

        public Builder statusBefore(Status statusBefore) {
            this.statusBefore = statusBefore;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder dateTime(Date dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder nameCustomer(String nameCustomer) {
            this.nameCustomer = nameCustomer;
            return this;
        }

        public Builder restaurantName(String restaurantName) {
            this.restaurantName = restaurantName;
            return this;
        }

        public Builder idRestaurant(Long idRestaurant) {
            this.idRestaurant = idRestaurant;
            return this;
        }

        public Builder employeeName(String employeeName) {
            this.employeeName = employeeName;
            return this;
        }

        public Builder idEmployee(Long idEmployee) {
            this.idEmployee = idEmployee;
            return this;
        }

        public Traceability build() {
            return new Traceability(this);
        }
    }
}
