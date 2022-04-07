package net.ddns.iiiedug02.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.CurriculumBean;


public interface CurriculumRepository extends JpaRepository<CurriculumBean, Integer> {
    List<CurriculumBean> findAllByClassbean(ClassBean cb);

    public CurriculumBean findByClassbeanAndChapter(ClassBean cb, String chapter);
}
