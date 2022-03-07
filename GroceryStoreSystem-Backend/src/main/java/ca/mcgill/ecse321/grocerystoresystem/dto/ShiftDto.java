package ca.mcgill.ecse321.grocerystoresystem.dto;

import ca.mcgill.ecse321.grocerystoresystem.model.ShiftStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShiftDto {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private ShiftStatus shiftStatus;

    private EmployeeDto employeeDto;

    public ShiftDto(LocalDate date, LocalTime startTime, LocalTime endTime, ShiftStatus shiftStatus) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftStatus = shiftStatus;
    }

    public ShiftDto(LocalDate date, LocalTime startTime, LocalTime endTime, ShiftStatus shiftStatus, EmployeeDto employeeDto) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftStatus = shiftStatus;
        this.employeeDto = employeeDto;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setShiftStatus(ShiftStatus shiftStatus) {
        this.shiftStatus = shiftStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public ShiftStatus getShiftStatus() {
        return shiftStatus;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }
}
