package proj.emcegom.quality.assess.model.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;

    private String name;

    private String password;

    private String email;

}
