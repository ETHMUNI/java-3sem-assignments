package org.example.week6and7.HotelAPI.Security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User implements ISecurityUser {

    @Id
    @Basic(optional = false)
    @Column(name = "user_name", length = 25)
    private String username;

    @Basic(optional = false)
    @Column(name = "user_password", length = 255, nullable = false)
    private String userPassword;

    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
            @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roleList = new LinkedHashSet<>();

    public User(String username, String userPassword) {
        this.username = username;
        this.userPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());
    }

    @Override
    public Set<String> getRolesAsStrings() {
        if (roleList.isEmpty()) {
            return null;
        }
        Set<String> rolesAsStrings = new LinkedHashSet<>();
        roleList.forEach((role) -> {
            rolesAsStrings.add(role.getRoleName());
        });
        return rolesAsStrings;
    }
    @Override
    public boolean verifyPassword(String pw) {
        return BCrypt.checkpw(pw, userPassword);
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void addRole(Role role) {
        roleList.add(role);
    }

    @Override
    public void removeRole(String role) {
        roleList.remove(role);
    }

    public String getPassword() {
        return this.userPassword;
    }
}
