package by.andersen.kudko.webapp.model.entity;

import java.util.Objects;

public class User extends Entity {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
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
