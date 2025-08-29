package com.tec.attendenceTracker.models;


import jakarta.persistence.*;
;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "attendance")

public class Attendance {
    public Attendance(Long id, Employee employee, LocalDate date, LocalTime time) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.time = time;
    }
    public Attendance() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private LocalDate date;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    @Column(nullable = false)
    private LocalTime time;
}
