package pro.sky.telegram_bot_pets_shelter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity(name="owner")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id",unique = true,nullable = false)
    private long chatId;

    @Column(name = "name",unique = true,nullable = false)
    private String name;

    @Column(name = "registered_at",nullable = false)
    private LocalDate registeredAt;
    @Column(name = "last_action")
    private String lastAction;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cat_id")
    private Cat cat;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dog_id")
    private Dog dog;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
