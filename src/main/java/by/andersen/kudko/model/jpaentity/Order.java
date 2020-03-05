package by.andersen.kudko.model.jpaentity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "order.maxDate", query = "SELECT MAX(o.orderDate) FROM Order o")
})

@Data
@Entity
@Table(name = "travel_agency.order")
public class Order extends BEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Column(name = "order_date")
    private Date orderDate;

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
