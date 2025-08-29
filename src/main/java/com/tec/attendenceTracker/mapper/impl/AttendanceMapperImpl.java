package com.tec.attendenceTracker.mapper.impl;

import com.tec.attendenceTracker.dto.AttendanceDTO;
import com.tec.attendenceTracker.mapper.AttendanceMapper;
import com.tec.attendenceTracker.models.Attendance;
import com.tec.attendenceTracker.models.Employee;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapperImpl implements AttendanceMapper {

    @Override
    public AttendanceDTO toDto(Attendance attendance) {
        if (attendance == null) return null;

        AttendanceDTO dto = new AttendanceDTO();
        dto.setId(attendance.getId());

        if (attendance.getEmployee() != null) {
            dto.setEmployeeId(attendance.getEmployee().getId());
        }

        dto.setDate(attendance.getDate());
        dto.setTime(attendance.getTime());

        return dto;
    }

    @Override
    public Attendance toEntity(AttendanceDTO dto, Employee employee) {
        if (dto == null || employee == null) return null;

        Attendance attendance = new Attendance();
        attendance.setId(dto.getId());
        attendance.setEmployee(employee);
        attendance.setDate(dto.getDate());
        attendance.setTime(dto.getTime());

        return attendance;
    }

    @Override
    public void updateEntity(Attendance existing, AttendanceDTO dto) {
        if (existing == null || dto == null) return;

        existing.setDate(dto.getDate());
        existing.setTime(dto.getTime());
        // Employee reference not updated here
    }
}
