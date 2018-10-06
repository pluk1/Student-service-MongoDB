package telran.studentservicemongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.studentservicemongo.dao.IStudentRepository;
import telran.studentservicemongo.dto.*;
import telran.studentservicemongo.entities.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    IStudentRepository studentRepository;

    @Override
    public boolean addStudent(StudentDto studentDto) {
        Student student = studentRepository.findById(studentDto.getId()).orElse(null);
        if (student != null) {
            return false;
        }

        student = new Student(studentDto.getId(), studentDto.getName(), studentDto.getPassword());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentResponseDto deleteStudent(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }
        studentRepository.deleteById(id);
        return studentToStudentResponseDto(student);
    }

    @Override
    public StudentDto editStudent(int id, StudentEditDto studentEditDto) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }
        if (studentEditDto.getName() != null) {
            student.setName(studentEditDto.getName());
        }
        if (studentEditDto.getPassword() != null) {
            student.setPassword(studentEditDto.getPassword());
        }
        studentRepository.save(student);
        return studentToStudentDto(student);
    }

    @Override
    public StudentResponseDto getStudent(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }

        return studentToStudentResponseDto(student);
    }

    @Override
    public boolean addScore(int id, ScoreDto score) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            throw new NotFoundStudentException();
        }
        boolean res = student.addScore(score.getExamName(), score.getScore());
        studentRepository.save(student);
        return res;
    }

    @Override
    public List<Student> findByNameStartWith(String name) {
        if (name == null || name.equals("all")) {
            studentRepository.findAll();
        }
        return studentRepository.findByNameRegex(name + ".*");
    }

    private StudentResponseDto studentToStudentResponseDto(Student student) {
        return StudentResponseDto.builder()
                .id(student.getId())
                .name(student.getName())
                .scores(student.getScores())
                .build();
    }

    private StudentDto studentToStudentDto(Student student) {

        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .password(student.getPassword())
                .build();
    }

}
