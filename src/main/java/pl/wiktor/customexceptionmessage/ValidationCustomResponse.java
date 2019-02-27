package pl.wiktor.customexceptionmessage;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ValidationCustomResponse {
    private String field;
    private String message;
}
