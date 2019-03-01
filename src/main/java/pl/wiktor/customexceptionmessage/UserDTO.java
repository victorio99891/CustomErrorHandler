package pl.wiktor.customexceptionmessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    private Integer userId;

    private String emailLogin;

    private String firstName;

    private String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty("active")
    private boolean isActive;

    private String department;

    private Set<String> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return getUserId().equals(userDTO.getUserId()) &&
                getEmailLogin().equals(userDTO.getEmailLogin()) &&
                getFirstName().equals(userDTO.getFirstName()) &&
                getLastName().equals(userDTO.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getEmailLogin(), getFirstName(), getLastName());
    }
}
