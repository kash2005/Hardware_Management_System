package lk.ijse.project.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserTM {
    private String userId;
    private String userName;
    private String userType;
    private String password;
}
