package telran.studentservicemongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.studentservicemongo.entities.Student;

import java.util.List;

public interface IStudentRepository extends MongoRepository<Student, Integer> {
    List<Student> findByNameRegex(String regex);

}
