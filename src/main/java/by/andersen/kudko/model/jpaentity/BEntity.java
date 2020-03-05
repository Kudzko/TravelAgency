package by.andersen.kudko.model.jpaentity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table
@Inheritance( strategy = InheritanceType.JOINED )
public class BEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    protected int id;

    protected BEntity() {
    }

    protected BEntity(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BEntity entity = (BEntity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }


}
