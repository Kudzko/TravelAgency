package by.andersen.kudko.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@Table
public class Country extends BEntity {
    @Column(name = "country_name")
    private String countryName;

    public Country() {
    }

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public Country(int id, String countryName) {
        super(id);
        this.countryName = countryName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Country country = (Country) o;
        return Objects.equals(countryName, country.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countryName);
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryName='" + countryName + '\'' +
                ", id=" + id +
                '}';
    }
}
