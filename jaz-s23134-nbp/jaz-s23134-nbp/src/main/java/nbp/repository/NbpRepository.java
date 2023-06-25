package nbp.repository;

import nbp.model.SaveData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NbpRepository extends JpaRepository<SaveData,Long> {
}
