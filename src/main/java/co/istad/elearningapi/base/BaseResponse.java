package co.istad.elearningapi.base;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class BaseResponse<T> {
    private T payload;
}
