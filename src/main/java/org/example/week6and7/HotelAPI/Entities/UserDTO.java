package org.example.week6and7.HotelAPI.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.week6and7.HotelAPI.Security.User;

import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private Set<String> roles;

    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRolesAsStrings();
    }
    public UserDTO(String username, Set<String> roleSet){
        this.username = username;
        this.roles = roleSet;
    }

}
