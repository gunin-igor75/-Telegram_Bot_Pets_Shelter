package pro.sky.telegram_bot_pets_shelter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Pet - Класс для сущности питомец
 *
 **/

@Entity(name="pet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 *  Pet - Класс для сущности питомец со свойствами
 *  <b>id</b>, <b>name</b>, <b>adopted</b>, <b>report</b>.
 **/
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",unique = true,nullable = false)
    private String name;
    @Column(name = "adopted",nullable = false)
    private boolean adopted;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id")
    private Report report;
}