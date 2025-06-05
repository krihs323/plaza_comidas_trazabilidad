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

    public TraceabilityEntity(String id, Long idOrder, Long idCustomer, String statusBefore, String status, Date dateTime) {
        this.id = id;
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.statusBefore = statusBefore;
        this.status = status;
        this.dateTime = dateTime;
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
}
