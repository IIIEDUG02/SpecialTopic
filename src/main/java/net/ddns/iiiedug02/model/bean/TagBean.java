package net.ddns.iiiedug02.model.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class TagBean {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String category;
	
	// Tag 這張表會去關聯到 article_tag 這張表(因為是多對多關係)
	// 目的是為了拿到有使用這個標籤的所有文章
	// 例如某個標籤叫做 "理想生活"，能夠拿到所有使用 "理想生活" 標籤的文章
	@ManyToMany(cascade = { CascadeType.REMOVE })
	@JoinTable(
		name = "article_tag",
		joinColumns = { @JoinColumn(name = "tag_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "article_id") }
	)
	Set<ArticleBean> articles = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public Set<ArticleBean> getArticles() {
		return articles;
	}

	public void setArticles(Set<ArticleBean> articles) {
		this.articles = articles;
	}
}
