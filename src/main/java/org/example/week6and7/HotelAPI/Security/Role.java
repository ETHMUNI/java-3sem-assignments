package org.example.week6and7.HotelAPI.Security;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roles")
@Getter
@NoArgsConstructor
public class Role implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "role_name", length = 20)
    private String roleName;

    @ManyToMany(mappedBy = "roleList")
    private List<User> userList;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }
}