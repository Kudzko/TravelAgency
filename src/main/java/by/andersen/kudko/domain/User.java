package by.andersen.kudko.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "nickname")
    private String nickName;

    public User(int id, String name, String surname, String nickName) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.nickName = nickName;
    }
}
