package com.plaza.traceability.domain.model;

public class Traceability {

    private Long idOrder;

    private String idCustomer;

    private Status statusBefore;

    private Status status;

    public Traceability(Long idOrder, String idCustomer, Status statusBefore, Status status) {
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.statusBefore = statusBefore;
        this.status = status;
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
}
