package net.ddns.iiiedug02.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity @Table(name = "YPTEACHER")
@Component
public class YPteacher {
	
	@Id @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "TEACHERID")
	private int teacherID;
	
	@Column(name = "YEAR")
	private int year;
	
	@Column(name = "YEARAMOUNT")
	private int yearAmount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYearAmount() {
		return yearAmount;
	}

	public void setYearAmount(int yearAmount) {
		this.yearAmount = yearAmount;
	}
	
	

	

}
