package com.example.lab5eventsystem.Controller;

import com.example.lab5eventsystem.ApiResponse.ApiResponse;
import com.example.lab5eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody Event event){
        events.add(event);
        return new ApiResponse("Event added successfully");

    }

    @GetMapping("/display")
    public ArrayList<Event> displayEvents(){
        return events;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateEvent(@PathVariable int index, @RequestBody Event event){
        events.set(index, event);
        return new ApiResponse("Event updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteEvent(@PathVariable int index){
        events.remove(index);
        return new ApiResponse("Event deleted successfully");
    }

    @PutMapping("/capacity/{index}/{capacity}")
    public ApiResponse changeCapacity(@PathVariable int index, @PathVariable int capacity){
        events.get(index).setCapacity(capacity);
        return new ApiResponse("Event capacity changed successfully");
    }

    @GetMapping("/search/id/{id}")
    public ApiResponse searchByID(@PathVariable String id){
        for(Event event : events){
            if(event.getId().equalsIgnoreCase(id)){
                return new ApiResponse("Event found");
            }
        }
        return new ApiResponse("Event not found");
    }


}
