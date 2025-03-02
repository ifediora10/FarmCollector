package FarmCollector.repositories;

import FarmCollector.model.entities.Farm;
import FarmCollector.model.enums.Season;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmRepository extends JpaRepository<Farm, Long> {
    Page<Farm> findByName(String name, Pageable pageable);

    Optional<Farm> findByNameAndSeasonAndYear(String name, Season season, Integer year);

    boolean existsByNameAndSeason(String name, Season season);
}
