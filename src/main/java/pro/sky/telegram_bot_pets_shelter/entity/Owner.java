package pro.sky.telegram_bot_pets_shelter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import pro.sky.telegram_bot_pets_shelter.service.enums.UserState;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;

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
    private String firstname;
    private String lastname;
    private String username;
    @CreationTimestamp
    private LocalDateTime registeredAt;
    private String lastAction;
    private String phoneNumber;
    @Enumerated(value = STRING)
    private UserState state;
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
