package net.ddns.iiiedug02.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ddns.iiiedug02.model.bean.ArticleBean;

                                                         //對應檔名     //對應PK型別
public interface ArticalRepository extends JpaRepository<ArticleBean, Integer> {

}
