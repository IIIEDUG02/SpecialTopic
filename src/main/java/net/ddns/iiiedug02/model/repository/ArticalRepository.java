package net.ddns.iiiedug02.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ddns.iiiedug02.model.bean.ArticleBean;

// 對應檔名 //對應PK型別
public interface ArticalRepository extends JpaRepository<ArticleBean, Integer> {
	public List<ArticleBean> findByUuid(String uuid);
	
	//由於 JPQL 是大小寫敏感的大小寫意思不一樣，且查詢的對象是 Entity 類別，不是資料表
	// 而 ArticleBean 類別上面的@Entity沒有指定名稱，所以 Entity 的名稱預設為類別名稱(要使用類別名稱去寫，不能用 Table 名稱去寫)，即為 ArticleBean
	// 欄位名稱也是
	// 這個 @Query 做的是寫 SQL 更新文章瀏覽次數
	// ArticleBean u: 幫 ArticleBean 取別名 u，為了方便
	@Modifying
	@Query("update ArticleBean u set u.pageViews = :pageViews where u.uuid = :uuid")
	void updatePageViews(@Param(value = "uuid") String uuid, @Param(value = "pageViews") int pageViews);
}
