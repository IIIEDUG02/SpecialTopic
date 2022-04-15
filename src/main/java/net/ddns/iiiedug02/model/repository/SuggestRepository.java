package net.ddns.iiiedug02.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.ddns.iiiedug02.model.bean.Suggest;

@Repository
public interface SuggestRepository extends JpaRepository<Suggest, Integer> {
    public List<Suggest> findByStatus(short status);
}
