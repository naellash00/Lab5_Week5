package com.example.lab5projecttracker.Controller;

import com.example.lab5projecttracker.ApiResponse.ApiResponse;
import com.example.lab5projecttracker.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project/tracker")
public class ProjectTrackerController {
    ArrayList<Project> projects = new ArrayList<>();

    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody Project project) {
        projects.add(project);
        return new ApiResponse("Project added successfully");
    }

    @GetMapping("/display")
    public ArrayList<Project> displayProjects() {
        return projects;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateProject(@PathVariable int index, @RequestBody Project project) {
        projects.set(index, project);
        return new ApiResponse("Project updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index) {
        projects.remove(index);
        return new ApiResponse("Project deleted successfully");
    }

    @PutMapping("/status/{index}")
    public ApiResponse changeStatus(@PathVariable int index) {
        if (projects.get(index).getStatus().equalsIgnoreCase("incomplete")) {
            projects.get(index).setStatus("complete");
        }
        return new ApiResponse("Project status changed successfully");
    }

    @GetMapping("/search/{title}")
    public ApiResponse getProjectByTitle(@PathVariable String title){
        for(Project project : projects){
            if(project.getTitle().equalsIgnoreCase(title))
                return new ApiResponse("Project Found");
        }
        return new ApiResponse("Project Not Found");
    }

    @GetMapping("/same/company/{companyName}")
    public ArrayList<Project> getPorjectsUsingCompanyName(@PathVariable String companyName){
        ArrayList<Project> sameCompanyProjects = new ArrayList<>();
        for(Project project : projects){
            if(project.getCompanyName().equalsIgnoreCase(companyName)){
                sameCompanyProjects.add(project);
            }
        }
        return sameCompanyProjects;
    }
}
