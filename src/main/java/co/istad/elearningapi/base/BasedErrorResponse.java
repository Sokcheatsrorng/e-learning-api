package co.istad.elearningapi.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasedErrorResponse {
    // make flexible error
    private BaseError error;
}
