package com.tec.attendenceTracker.mapper;

import com.tec.attendenceTracker.dto.AttendanceDTO;
import com.tec.attendenceTracker.models.Attendance;
import com.tec.attendenceTracker.models.Employee;

public interface AttendanceMapper {

    AttendanceDTO toDto(Attendance attendance);

    Attendance toEntity(AttendanceDTO dto, Employee employee);

    void updateEntity(Attendance existing, AttendanceDTO dto);
}
