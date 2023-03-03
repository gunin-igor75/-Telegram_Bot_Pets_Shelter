package pro.sky.telegram_bot_pets_shelter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity(name="owner")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 *  Owner - Класс для сущности хозяин питомца со свойствами
 *  <b>id</b>, <b>chatId</b>, <b>name</b>, <b>registeredAt</b>, <b>lastAction</b>, <b>pet</b>.
 **/
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "chat_id",unique = true,nullable = false)
    private long chatId;
    @Column(name = "name",unique = true,nullable = false)
    private String name;
    @Column(name = "registered_at",nullable = false)
    private LocalDateTime registeredAt;
    @Column(name = "last_action",nullable = false)
    private String lastAction;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
