package com.ag.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "AIT_ENQ_STS")
public class EnqStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENQ_STS_ID")
	private Integer enqId;
	
	@Column(name = "ENQ_STS_NAME")
	private String enqName;
}
