package by.andersen.kudko.jpaentity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "travel_agency.order")
public class Order extends BEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    public Order() {
    }

    public Order(User user, Tour tour) {
        this.user = user;
        this.tour = tour;
    }

    public Order(int id, User user, Tour tour) {
        super(id);
        this.user = user;
        this.tour = tour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(user, order.user) &&
                Objects.equals(tour, order.tour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, tour);
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", tour=" + tour +
                ", id=" + id +
                '}';
    }
}
