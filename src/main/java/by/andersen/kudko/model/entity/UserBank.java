package by.andersen.kudko.model.entity;

import java.util.List;

public class UserBank extends AbstractBank<User>{
    public UserBank() {
    }

    public UserBank(List<User> elements) {
        super(elements);
    }
}
