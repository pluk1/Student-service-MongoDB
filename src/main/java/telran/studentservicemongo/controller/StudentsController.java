package telran.studentservicemongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.studentservicemongo.dto.ScoreDto;
import telran.studentservicemongo.dto.StudentDto;
import telran.studentservicemongo.dto.StudentEditDto;
import telran.studentservicemongo.dto.StudentResponseDto;
import telran.studentservicemongo.entities.Student;
import telran.studentservicemongo.service.IStudentService;

import java.util.List;

@RestController
public class StudentsController {
    @Autowired
    IStudentService studentService;

    @PostMapping(StudentsURI.STUDENT)
    public boolean addStudent(@RequestBody StudentDto student) {
        return studentService.addStudent(student);
    }

    @DeleteMapping(StudentsURI.STUDENT)
    public StudentResponseDto removeStudent(@RequestParam int id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping(StudentsURI.STUDENT + "/{id}")
    public StudentDto editStudent(@PathVariable int id,
                                  @RequestBody StudentEditDto student) {
        return studentService.editStudent(id, student);
    }

    @GetMapping(StudentsURI.STUDENT + "/{id}")
    public StudentResponseDto getStudent(@PathVariable int id) {
        return studentService.getStudent(id);
    }

    @PutMapping(StudentsURI.TEACHER + "/{id}")
    public boolean addScore(@PathVariable int id,
                            @RequestBody ScoreDto score) {
        return studentService.addScore(id, score);

    }

    @GetMapping(StudentsURI.STUDENTS + "/{name}")
    public List<Student> getByName(@PathVariable String name) {
        return studentService.findByNameStartWith(name);
    }

}
