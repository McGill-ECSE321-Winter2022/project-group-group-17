package ca.mcgill.ecse321.grocerystoresystem.controller;
import ca.mcgill.ecse321.grocerystoresystem.dto.StoreHourDto;
import ca.mcgill.ecse321.grocerystoresystem.model.StoreHour;
import ca.mcgill.ecse321.grocerystoresystem.model.Weekdays;
import ca.mcgill.ecse321.grocerystoresystem.service.StoreHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import java.time.*;


@CrossOrigin(origins = "*")
@RestController
public class StoreHourController {

    @Autowired
    private StoreHourService storeHourService;


    @GetMapping(value = {"/storehours/", "/storehours"})
    public List<StoreHourDto> getAllStoreHourDtos() {
        return storeHourService.getAllStoreHours().stream().map(this::convertToDto).collect(Collectors.toList());

    }

    @GetMapping(value = {"/storehour/get/id/", "/storehour/get/id"})
    public StoreHourDto getStoreHourDto(@RequestParam int storeHourID){
        try{
            return convertToDto(storeHourService.getStoreHour(storeHourID));
        }
        catch (NullPointerException n){
            return null;
        }
    }

//    @GetMapping(value = {"/storehour/get/day/", "/storehour/get/day"})
//    public StoreHourDto getStoreHourDtoByDay(@RequestParam Weekdays day){
//        try{
//            return convertToDto(storeHourService.getStoreHourByWeekday(day));
//        }
//        catch (NullPointerException n){
//            return null;
//        }
//    }


    @PostMapping(value = {"/storehour/create/", "/storehour/create"})
    public StoreHourDto createStoreHourDto(@RequestParam("startTime") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime startTime,
                                           @RequestParam("endTime") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime endTime,
                                           @RequestParam Weekdays weekday){
        return convertToDto(storeHourService.createStoreHour(startTime, endTime, weekday));
    }

    @DeleteMapping (value = {"/storehour/delete/", "/storehour/delete" })
    public boolean deleteStoreHourDto(@RequestParam int storeHourID){
        try {
            return this.storeHourService.deleteStoreHour(storeHourID);
        }
        catch (NullPointerException n){
            return false;
        }

    }

    @DeleteMapping (value = {"/storehour/deleteall/", "/storehour/deleteall"})
    public boolean deleteAllHoursDto(){

        return storeHourService.deleteAllHours();
    }

    @PostMapping (value = {"/storehour/update/", "/storehour/update"})
    public StoreHourDto updateStoreHourDtoById(@RequestParam int storeHourID,
                                               @RequestParam("startTime") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime startTime,
                                               @RequestParam("endTime") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime endTime ){
        try {
            return convertToDto(storeHourService.updateStoreHourById(storeHourID, startTime, endTime));
        }
        catch (IllegalArgumentException x) {
            System.out.println(x.getMessage());
            return null;

        }
    }

    @GetMapping(value = {"/storehour/check/id", "/storehour/check/id/"})
    public boolean isStoreHourById(@RequestParam int storeHourID) {
        return storeHourService.isStoreHourById(storeHourID);
    }


    private StoreHourDto convertToDto(StoreHour hour) throws NullPointerException{
        if(hour == null) {
            throw new NullPointerException("Store hour is null");
        }
        return new StoreHourDto(hour.getStoreHourID(), hour.getStartTime(), hour.getEndTime(), hour.getWeekday());
    }






}
