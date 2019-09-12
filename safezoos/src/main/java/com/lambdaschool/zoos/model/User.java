package com.lambdaschool.zoos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable=false,
            unique=true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<UserRole> userRoles=new ArrayList<>();

    public User(){}

    public User(String username, String password, List<UserRole> userRoles) {
        this.username = username;
        this.password = password;

        if(userRoles.size()>0){
            for(UserRole ur : userRoles){
                ur.setUser(this);
            }
        }
        this.userRoles=userRoles;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void setPasswordNoEncrypt(String password){
        this.password=password;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        if(userRoles.size()>0)
            for(UserRole ur:userRoles)
                this.userRoles.add(new UserRole(ur));
    }

    public List<SimpleGrantedAuthority> getAuthority(){
        List<SimpleGrantedAuthority> temp=new ArrayList<>();

        for(UserRole ur:this.getUserRoles()){
            String myRole="ROLE_"+ur.getRole().getName().toUpperCase();
            temp.add(new SimpleGrantedAuthority(myRole));
        }
        return temp;
    }
}