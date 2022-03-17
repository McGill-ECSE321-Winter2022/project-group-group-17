package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dto.CalendarDto;
import ca.mcgill.ecse321.grocerystoresystem.model.Calendar;
import ca.mcgill.ecse321.grocerystoresystem.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    @GetMapping(value = {"/calendars/", "/calendars"})
    public List<CalendarDto> getCalendars() {
        return calendarService.getAllCalendars().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(value = {"/calendar/get/id", "/calendar/get/id/"})
    public CalendarDto getCalendarById(@RequestParam  int id) {
        try {
            return convertToDto(calendarService.findCalendarByID(id));
        }
        catch(NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/calendar/check/id/", "/calendar/check/id"})
    public boolean isCalendarById(@RequestParam int id) {
        return calendarService.isCalendarByID(id);
    }

    @PostMapping(value = {"/calendar/create/", "/calendar/create"})
    public CalendarDto createCalendar() {
        return convertToDto(calendarService.createCalendar());
    }

    @DeleteMapping(value = {"/calendar/delete/", "/calendar/delete"})
    public boolean deleteCalendar(@RequestParam int id) {
        return calendarService.deleteCalendar(id);
    }

    public CalendarDto convertToDto(Calendar c) {
        if(c == null) throw new NullPointerException("Calendar not found");

        return new CalendarDto(c.getCalendarID());
    }
}
