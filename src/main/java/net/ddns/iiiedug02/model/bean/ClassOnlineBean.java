package net.ddns.iiiedug02.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "class_online")
@Component
public class ClassOnlineBean {
	
	@GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "class"))
	@Id
	@Column
	@GeneratedValue(generator = "generator")
	private int cid;
	
	private int online;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

}
