package com.sparta.malik.JSFLoginProject.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "jsfloginapplication")
@NamedQueries({
        @NamedQuery(name = "user.getAll", query = "SELECT users FROM UserEntity users"),
        @NamedQuery(name = "user.getById", query = "SELECT users FROM UserEntity users WHERE users.id = :id"),
        @NamedQuery(name = "user.getByUsername", query = "SELECT users FROM UserEntity users WHERE users.username = :username"),
        @NamedQuery(name = "user.updatePermission", query = "UPDATE UserEntity users SET users.userType = :role"),
        @NamedQuery(name = "user.getHighestIdUser", query = "SELECT users FROM UserEntity users ORDER BY users.id DESC")
})
public class UserEntity {

    private int id;
    private String username;
    private String password;
    private String userType;

    @Id
//    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "userType")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(userType, that.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, userType);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
