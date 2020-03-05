package by.andersen.kudko.model.jpaentity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@Table
@NamedQuery(name = "find user by name", query = "SELECT u FROM User u WHERE u.name = :name")
public class User extends BEntity {
    private String name;
    private String surname;
    private String nickName;


    public User() {
    }

    public User(int id, String name, String surname, String nickName) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.nickName = nickName;
    }

    public User(String name, String surname, String nickName) {
        this.name = name;
        this.surname = surname;
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(nickName, user.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, nickName);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nickName='" + nickName + '\'' +
                ", id=" + id +
                '}';
    }
}
