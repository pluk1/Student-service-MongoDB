package telran.studentservicemongo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private int id;
    private String name;
    private String password;

}
