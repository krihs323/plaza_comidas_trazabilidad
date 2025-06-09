package com.plaza.traceability.infraestructure.output.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

@Document(collection = "logs")
public class TraceabilityEntity {

    @Id
    private  String id;

    private Long idOrder;

    private Long idCustomer;

    private String statusBefore;

    private String status;

    private Date dateTime;

    private String nameCustomer;

    private String restaurantName;

    private Long idRestaurant;

    private String employeeName;

    private Long idEmployee;

    public TraceabilityEntity(String id, Long idOrder, Long idCustomer, String statusBefore, String status, Date dateTime, String nameCustomer, String restaurantName, Long idRestaurant, String employeeName, Long idEmployee) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatusBefore() {
        return statusBefore;
    }

    public void setStatusBefore(String statusBefore) {
        this.statusBefore = statusBefore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
}
