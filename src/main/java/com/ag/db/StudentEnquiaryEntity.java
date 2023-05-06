package com.ag.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "AIT_STUDENT_ENQ")
@EntityListeners(AuditingEntityListener.class)
public class StudentEnquiaryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENQ_ID")
	private Integer enqId;
	
	@Column(name = "STUDENT_NAME")
	private String studentName;
	
	@Column(name = "PHNO")
	private String phoneNumber;
	
	@Column(name = "CLASS_MODE")
	private String classMode;
	
	@Column(name = "CLASS_NAME")
	private String className;
	
	@Column(name = "ENQUIARY_STATUS", columnDefinition = "varchar(50) default 'NEW'")
	private String enqStatus;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserDetailsEntity userId;
	
	@CreationTimestamp
	@Column(name = "CREATED_DATE" , updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", updatable = true)
	private Date updatedDate;
}
