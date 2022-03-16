package ca.mcgill.ecse321.grocerystoresystem.controller;
import ca.mcgill.ecse321.grocerystoresystem.dto.StoreHourDto;
import ca.mcgill.ecse321.grocerystoresystem.model.StoreHour;
import ca.mcgill.ecse321.grocerystoresystem.model.Weekdays;
import ca.mcgill.ecse321.grocerystoresystem.service.StoreHourService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = {"/storehours/get/id/", "/storehours/get/id"})
    public StoreHourDto getStoreHourDto(@RequestParam int id){
        try{
            return convertToDto(storeHourService.getStoreHour(id));
        }
        catch (NullPointerException n){
            return null;
        }
    }

    @PostMapping(value = {"/storehours/get/id/", "/storehours/get/id"})
    public StoreHourDto createStoreHourDto(@RequestParam LocalTime startTime, @RequestParam LocalTime endTime,
                                         @RequestParam Weekdays weekday, @RequestParam int storeHourID){
        return convertToDto(storeHourService.createStoreHour(startTime, endTime, weekday, storeHourID));
    }

    @DeleteMapping (value = {"/storehour/delete/", "/storehour/delete" })
    public boolean deleteStoreHourDto(@RequestParam int storeHourID){
        try {
            return storeHourService.deleteStoreHour(storeHourID);
        }
        catch (NullPointerException n){
            return false;
        }

    }

    private StoreHourDto convertToDto(StoreHour hour) throws NullPointerException{
        if(hour == null) {
            throw new NullPointerException("Store hour is null");
        }
        return new StoreHourDto(hour.getStoreHourID(), hour.getStartTime(), hour.getEndTime(), hour.getWeekday());
    }


    



}