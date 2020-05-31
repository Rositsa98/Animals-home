package fmi.course.hcmi.animalshome.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "photo", schema = "dko8s14veb65m")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "photo_name")
    private String photoName;

    public Photo(final long id, final String photoName) {
        this.id = id;
        this.photoName = photoName;
    }

    public Photo(final String photoName) {
        this.photoName = photoName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Photo photo = (Photo) o;
        return photoName.equals(photo.photoName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoName);
    }
}
