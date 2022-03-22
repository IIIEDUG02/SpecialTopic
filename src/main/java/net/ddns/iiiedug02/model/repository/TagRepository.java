package net.ddns.iiiedug02.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import net.ddns.iiiedug02.model.bean.TagBean;

public interface TagRepository extends JpaRepository<TagBean, Integer> {
	public List<TagBean> findByCategory(String category);
}
