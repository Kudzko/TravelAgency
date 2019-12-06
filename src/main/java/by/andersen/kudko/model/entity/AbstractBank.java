package by.andersen.kudko.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AbstractBank<T> {
    private List<T> elements;

    public AbstractBank() {
        elements = new ArrayList<>();
    }

    public AbstractBank(List<T> elements) {
        this.elements = elements;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBank<?> that = (AbstractBank<?>) o;
        return Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }

    @Override
    public String toString() {
        return "AbstractBank{" +
                "list=" + elements +
                '}';
    }
}
