package net.ddns.iiiedug02.model.bean;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "article")
public class ArticleBean {

	  @Id
	  @Column(name = "Id")
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	  
	  @Column(name = "Title")
	  private String title;

	  @Column(name = "Content")
	  private String context; //text 型別?

	  @Column(name = "PublishTime")
	  private Date publishTime; //型別?

	  @Column(name = "PageViews")
	  private int pageViews;

	  @Column(name = "userid")
	  private int userid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getPublicTime() {
		return publishTime;
	}

	public void setPublicTime(Date publicTime) {
		this.publishTime = publicTime;
	}

	public int getPageViews() {
		return pageViews;
	}

	public void setPageViews(int pageViews) {
		this.pageViews = pageViews;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	 
}
