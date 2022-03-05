package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.CalendarRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    @Transactional
    public Calendar createCalendar() {
        Calendar calendar = new Calendar();
        calendarRepository.save(calendar);

        return calendar;
    }

    @Transactional
    public Calendar deleteCalendar(int calendarID) {
        Calendar calendar = calendarRepository.findCalendarByCalendarID(calendarID);

        if(calendar == null) throw new NullPointerException("Calendar not found");
        calendarRepository.delete(calendar);

        return calendar;
    }

    @Transactional
    public Calendar findCalendarByID(int calendarID) {
        Calendar calendar = calendarRepository.findCalendarByCalendarID(calendarID);

        if(calendar == null) throw new NullPointerException("Calendar not found");

        return calendar;
    }

    @Transactional
    public boolean isCalendarByID(int calendarID) {
        return calendarRepository.existsByCalendarID(calendarID);
    }


}
