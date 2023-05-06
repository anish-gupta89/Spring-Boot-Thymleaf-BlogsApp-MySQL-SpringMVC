package com.ag.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "AIT_USER_DTLS")
@EntityListeners(AuditingEntityListener.class)
public class UserDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "NAME")
	private String userName;
	
	@Column(name = "EMAIL", unique = true)
	private String userEmail;
	
	@Column(name = "PHNO")
	private String phoneNumber;
	
	@Column(name = "PWD")
	private String password;
	
	@Column(name = "ACC_STATUS",columnDefinition = "varchar(50) default 'LOCKED'")
	private String accountStatus;
	
	@CreationTimestamp
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
}
