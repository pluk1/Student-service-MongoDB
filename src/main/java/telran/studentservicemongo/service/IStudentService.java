package telran.studentservicemongo.service;

import telran.studentservicemongo.dto.*;
import telran.studentservicemongo.entities.Student;

import java.util.List;

public interface IStudentService {

    boolean addStudent(StudentDto studentDto);
    StudentResponseDto deleteStudent(int id);
    StudentDto editStudent(int id,StudentEditDto studentEditDto);
    StudentResponseDto getStudent(int id);
    boolean addScore(int id, ScoreDto score);
    List<Student> findByNameStartWith(String name);
}
