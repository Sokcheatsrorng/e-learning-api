package co.istad.elearningapi.base;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class BaseMessage {
    private String message;
}
