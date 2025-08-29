package com.tec.attendenceTracker.dto;


import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceDTO {
    public AttendanceDTO(Long id, Long employeeId, LocalDate date, LocalTime time, Double latitude, Double longitude) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public AttendanceDTO() {

    }
    private Long id;

    private Long employeeId; // Reference by ID only

    private LocalDate date;

    private LocalTime time;

    private Double latitude;

    private Double longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}