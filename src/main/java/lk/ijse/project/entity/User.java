package lk.ijse.project.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private String userId;
    private String userName;
    private String userType;
    private String password;
}
