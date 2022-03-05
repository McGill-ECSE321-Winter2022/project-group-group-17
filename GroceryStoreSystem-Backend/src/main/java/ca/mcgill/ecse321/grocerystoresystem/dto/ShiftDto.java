package ca.mcgill.ecse321.grocerystoresystem.dto;

import ca.mcgill.ecse321.grocerystoresystem.model.ShiftStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShiftDto {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private ShiftStatus shiftStatus;

    public ShiftDto(LocalDate date, LocalTime startTime, LocalTime endTime, ShiftStatus shiftStatus) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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
}
