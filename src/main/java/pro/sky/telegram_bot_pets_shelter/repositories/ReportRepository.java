package pro.sky.telegram_bot_pets_shelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegram_bot_pets_shelter.entity.Report;

/**
 *  Report Repository - Репозиторий для сущности <b>report</b>
 **/
@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
}