package co.istad.elearningapi.base;


import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class BaseError<T> {
    private String code;
    private T description;
}
