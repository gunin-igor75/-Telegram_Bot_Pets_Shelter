package pro.sky.telegram_bot_pets_shelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegram_bot_pets_shelter.entity.Pet;

/**
 *  Pet Repository - Репозиторий для сущности <b>pet</b>
 **/
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
