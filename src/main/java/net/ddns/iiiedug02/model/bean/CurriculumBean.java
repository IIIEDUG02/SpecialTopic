package net.ddns.iiiedug02.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "curriculum")
@Component
public class CurriculumBean {
	@Id @Column(name = "CUID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cuid;
	private String chapter;
	private String videoPath;
	
	@Transient
	private int cid;
	
	private int cuTitle;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid")
	private ClassBean classbean;
	
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getCuTitle() {
		return cuTitle;
	}
	public void setCuTitle(int cuTitle) {
		this.cuTitle = cuTitle;
	}
	public ClassBean getClassbean() {
		return classbean;
	}
	public void setClassbean(ClassBean classbean) {
		this.classbean = classbean;
	}
	public int getCuid() {
		return cuid;
	}
	public void setCuid(int cuid) {
		this.cuid = cuid;
	}
	
}
