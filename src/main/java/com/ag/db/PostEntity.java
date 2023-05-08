package com.ag.db;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "TBL_POST")
public class PostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer post_id;

	private String description;
	private String title;
	
	private boolean deleted;

	@Lob
	private String content;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdOn;

	@UpdateTimestamp
	@Column(updatable = true)
	private LocalDate updatedOn;

	@ManyToOne
	@JoinColumn(name = "user_id")
	UserEntity user;
}
