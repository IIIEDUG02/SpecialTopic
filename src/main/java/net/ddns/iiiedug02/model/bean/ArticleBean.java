package net.ddns.iiiedug02.model.bean;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "publish_time")
	private Date publishTime;

	@Column(name = "page_views")
	private int pageViews;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "article_tag",
		joinColumns = { @JoinColumn(name = "article_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "tag_id") }
	)
	Set<TagBean> tags = new HashSet<>();
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "members_uid", referencedColumnName = "uid")
	private Member member;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public int getPageViews() {
		return pageViews;
	}

	public void setPageViews(int pageViews) {
		this.pageViews = pageViews;
	}

	public Set<TagBean> getTags() {
		return tags;
	}

	public void setTags(Set<TagBean> tags) {
		this.tags = tags;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
