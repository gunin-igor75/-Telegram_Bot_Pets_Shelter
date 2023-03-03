package pro.sky.telegram_bot_pets_shelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegram_bot_pets_shelter.entity.Visitor;

/**
 *  Visitor Repository - Репозиторий для сущности <b>visitor</b>
 **/
@Repository
public interface VisitorRepository extends JpaRepository<Visitor,Long> {
}