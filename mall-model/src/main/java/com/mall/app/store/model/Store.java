package com.mall.app.store.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mall.app.segment.model.Segment;

@Entity
@Table(name = "store")
public class Store implements Serializable {

	private static final long serialVersionUID = 6444231386271377817L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 14)
	private String cnpj;

	@NotNull
	@Size(min = 2, max = 150)
	private String name;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date init;
	
	@Temporal(TemporalType.DATE)
	private Date end;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String floor;
	
	@NotNull
	@Size(min = 1, max = 10)
	private String number;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "store_segment", joinColumns = @JoinColumn(name = "store_id"), inverseJoinColumns = @JoinColumn(name = "segment_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"store_id", "segment_id" }))
	@JoinColumn(name = "segment_id")
	@OrderBy("name")
	@NotNull
	@Size(min = 1)
	private List<Segment> segments;
	
	public Store() {
		
	}
	
	public Store(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getInit() {
		return init;
	}

	public void setInit(Date init) {
		this.init = init;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<Segment> getSegments() {
		return segments;
	}

	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", cnpj=" + cnpj + ", name=" + name + ", init=" + init + ", end=" + end + ", floor="
				+ floor + ", number=" + number + ", segments=" + segments + "]";
	}

}
