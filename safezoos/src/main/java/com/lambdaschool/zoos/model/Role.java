package com.lambdaschool.zoos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="roles")
public class Role extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    @Column(nullable = false,
            unique = true)
    String name;

    @OneToMany( mappedBy = "role",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("role")
    private List<UserRole> userRoles=new ArrayList<>();

    public Role(){}

    public Role(String name) {
        this.name = name;
    }

    public Role(Role copy){
        this.roleid=copy.getRoleid();
        this.name=copy.getName();
        if(copy.userRoles.size()>0){
            for(UserRole ur :copy.getUserRoles()){
                this.getUserRoles().add(new UserRole(ur));
            }
        }
    }

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}