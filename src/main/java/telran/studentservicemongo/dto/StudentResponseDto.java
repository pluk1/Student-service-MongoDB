package telran.studentservicemongo.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDto {
    private String name;
    private int id;
    private Map<String, Integer> scores;
}
