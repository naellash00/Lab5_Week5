package com.example.lab5.Controller;

import com.example.lab5.ApiResponse.ApiResponse;
import com.example.lab5.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    ArrayList<Student> students = new ArrayList<>();

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student) {
        students.add(student);
        return new ApiResponse("Student added successfully");
    }

    @GetMapping("/display")
    public ArrayList<Student> displayStudents() {
        return students;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateStudent(@PathVariable int index, @RequestBody Student student) {
//        if (!students.contains(student)) {
//            return new ApiResponse("Student not found");
//        } else
        students.set(index, student);
        return new ApiResponse("Student updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteStudent(@PathVariable int index) {
        students.remove(index);
        return new ApiResponse("Student deleted successfully");
    }

    @GetMapping("/honor") //* dont send in args the whole student object but just the id
    public ApiResponse studentHonors(@RequestBody Student student) {
        if (student.getGpa() > 4.6)
            return new ApiResponse(student.getName() + ": First Class Honor");
        else if (student.getGpa() > 4.3)
            return new ApiResponse(student.getName() + ": Second Class Honor");

        return new ApiResponse(student.getName() + ": Dose Not Have Honor Class");
    }

//    // using index
//    @GetMapping("/honor/{index}")
//    public ApiResponse studentHonors(@PathVariable int index) {
//        if (students.get(index).getGpa() > 4.6) {
//            return new ApiResponse("First Class Honor");
//        } else if (students.get(index).getGpa() > 4.3) {
//            return new ApiResponse("Second Class Honor");
//        }
//
//        return new ApiResponse("Student Dose Not Have Honor Class");
//    }

    //using id

    @GetMapping("/honor/{id}")
    public ApiResponse studentHonors(@PathVariable String id){
        for(Student student : students){
            if(student.getId().equals(id)){
                if(student.getGpa() > 4.6)
                    return new ApiResponse("First Class Honor");
                else if(student.getAge() > 4.3)
                    return new ApiResponse("Second Class Honor");
            }
        }
        return new ApiResponse("Student ID Not Found");
    }

    @GetMapping("/average") //*outside the loop
    public ArrayList<Student> aboveAverageStudents() {
        ArrayList<Student> aboveAvg = new ArrayList<>();
        double sum = 0, avg = 0;
        for (Student student : students) {
            sum += student.getGpa();
        }
        for (Student student : students) {
            if (student.getGpa() > sum / students.size()) {
                aboveAvg.add(student);
            }
        }
        return aboveAvg;
    }

    //another solution:
//    @GetMapping("/average/{index}")
//    public ApiResponse aboveAverage(@PathVariable int index) {
//        int sum = 0;
//        for (Student student : students) {
//            sum += student.getGpa();
//        }
//        if (students.get(index).getGpa() > sum / students.size())
//            return new ApiResponse(students.get(index).getName() + ": GPA Is Above Average");
//
//        return new ApiResponse(students.get(index).getName() + ": GPA Is Not Above Average");
//
//    }

}
