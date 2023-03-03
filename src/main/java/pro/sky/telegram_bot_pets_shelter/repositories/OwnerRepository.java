package pro.sky.telegram_bot_pets_shelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegram_bot_pets_shelter.entity.Owner;
/**
 *  Owner Repository - Репозиторий для сущности <b>owner</b>
 **/
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}