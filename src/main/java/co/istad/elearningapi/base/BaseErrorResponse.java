package co.istad.elearningapi.base;


import lombok.*;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class BaseErrorResponse {

    private BaseError baseError;

}
