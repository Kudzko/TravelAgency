package by.andersen.kudko.jpaentity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@Table
public class Hotel extends BEntity {
    @Column(name = "name")
    private String hotelName;
    private int stars;

    public Hotel() {
    }

    public Hotel(String hotelName, int stars) {
        this.hotelName = hotelName;
        this.stars = stars;
    }

    public Hotel(int id, String hotelName, int stars) {
        super(id);
        this.hotelName = hotelName;
        this.stars = stars;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hotel hotel = (Hotel) o;
        return stars == hotel.stars &&
                Objects.equals(hotelName, hotel.hotelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hotelName, stars);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelName='" + hotelName + '\'' +
                ", stars=" + stars +
                ", id=" + id +
                '}';
    }
}
