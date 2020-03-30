package by.andersen.kudko.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@Table
public class Tour extends BEntity {

    @OneToOne
    @JoinColumn(name = "hotel")
    private Hotel hotel;

    @OneToOne
    @JoinColumn(name = "country")
    private Country country;
    private String review;

    public Tour() {
    }

    public Tour(Hotel hotel, Country country, String review) {
        this.hotel = hotel;
        this.country = country;
        this.review = review;
    }

    public Tour(int id, Hotel hotel, Country country, String review) {
        super(id);
        this.hotel = hotel;
        this.country = country;
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tour tour = (Tour) o;
        return Objects.equals(hotel, tour.hotel) &&
                Objects.equals(country, tour.country) &&
                Objects.equals(review, tour.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hotel, country, review);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "hotel=" + hotel +
                ", country=" + country +
                ", review='" + review + '\'' +
                ", id=" + id +
                '}';
    }
}
