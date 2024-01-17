package org.alonso.rrhhapp.models.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private List<String> authorities;
    private Boolean isAdmin;
    private String username;
    private String email;
    private String sub;
    private long iat;
    private long exp;
}
