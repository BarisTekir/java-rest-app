package rest.application.java_rest.rest;


import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.application.java_rest.entity.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    List<Student> theStudents = new ArrayList<>();
    @PostConstruct
    public void loadData() {

        theStudents.add(new Student("Munteanu", "Eugen"));
        theStudents.add(new Student("Ojog", "Maria"));
        theStudents.add(new Student("Gonzales", "Pedro"));
    }

    //definim endpoint-ul
    @GetMapping("/students/{studentsId}")
    public Student getStudents(@PathVariable int studentsId) {

        // veridicam din nou studentID si dimensiunea listei
        if(studentsId >= theStudents.size() || (studentsId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentsId);
        }

        return theStudents.get(studentsId);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex){

        //cererea StudentErroreResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(System.currentTimeMillis());

        // returnare
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex){

        //cererea StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setMessage("Id format error, please enter a valid student id");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(System.currentTimeMillis());

        // returnare
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
