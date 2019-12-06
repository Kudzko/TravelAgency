package by.andersen.kudko.webapp.jpaentity;

import javax.persistence.Entity;


public class Topic {
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
