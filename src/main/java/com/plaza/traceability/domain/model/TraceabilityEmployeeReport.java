package com.plaza.traceability.domain.model;

import java.time.Duration;

public class TraceabilityEmployeeReport {


    private String employeeName;

    private Long idEmployee;

    private Long average;

    private Duration time;


    public TraceabilityEmployeeReport(String employeeName, Long idEmployee, Long average, Duration time) {
        this.employeeName = employeeName;
        this.idEmployee = idEmployee;
        this.average = average;
        this.time = time;
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

    public Long getAverage() {
        return average;
    }

    public void setAverage(Long average) {
        this.average = average;
    }

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration time) {
        this.time = time;
    }


}
