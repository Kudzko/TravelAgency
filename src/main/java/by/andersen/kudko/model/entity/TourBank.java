package by.andersen.kudko.model.entity;

import java.util.List;


public class TourBank extends AbstractBank<Tour> {

    public TourBank() {
    }

    public TourBank(List<Tour> elements) {
        super(elements);
    }

    @Override
    public String toString() {
        return "TourBank{" +
                "tours=" + getElements() +
                '}';
    }
}
