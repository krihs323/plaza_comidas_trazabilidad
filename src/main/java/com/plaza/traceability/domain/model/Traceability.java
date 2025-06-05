package com.plaza.traceability.domain.model;

import java.util.Date;

public class Traceability {

    private Long idOrder;

    private String idCustomer;

    private Status statusBefore;

    private Status status;

    private Date dateTime;

    public Traceability(Long idOrder, String idCustomer, Status statusBefore, Status status, Date dateTime) {
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.statusBefore = statusBefore;
        this.status = status;
        this.dateTime = dateTime;
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
}
