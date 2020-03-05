package by.andersen.kudko.model.entity;

import java.util.Objects;

public class Hotel extends BEntity {
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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
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
