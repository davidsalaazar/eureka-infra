package com.experience.scloud.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class ProductModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private BigDecimal price;
	
	@Transient
	private Integer port;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", name=" + name + ", price=" + price + ", createAt=" + createAt + "]";
	}

}
