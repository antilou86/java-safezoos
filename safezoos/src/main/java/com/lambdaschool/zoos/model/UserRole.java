package com.lambdaschool.zoos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="userroles")
public class UserRole extends Auditable implements Serializable {
    @Id
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"userRoles","hibernateLazyInitializer"})
    @JoinColumn(name="userid")
    private User user;

    @Id
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"userRoles","hibernateLazyInitializer"})
    @JoinColumn(name="roleid")
    private Role role;

    public UserRole() { }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public UserRole(UserRole copy){
        this.user=copy.getUser();
        this.role=copy.getRole();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(!(obj instanceof UserRole))
            return false;
        UserRole userRole = (UserRole)obj;
        return getUser().equals(userRole.getUser()) && getRole().equals(userRole.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(),getRole());
    }
}