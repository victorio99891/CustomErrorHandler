package pl.wiktor.customexceptionmessage;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomDTO {

    @Min(value = 0, groups = {CustomValidation.class})
    @NotNull(groups = {CustomValidation.class}, message = "Ohh bad ID! It should't be null!")
    private Integer id;

    @NotEmpty(groups = {CustomValidation.class})
    @NotNull(groups = {CustomValidation.class}, message = "Please set a name! It should't be null!")
    private String name;


    @Email(groups = {CustomValidation.class}, message = "Please type the correct email form!")
    @NotEmpty(groups = {CustomValidation.class}, message = "Email can't be empty!")
    @NotNull(groups = {CustomValidation.class}, message = "Email cannot be null!")
    private String email;


}
