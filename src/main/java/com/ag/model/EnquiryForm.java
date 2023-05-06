package com.ag.model;

import lombok.Data;

@Data
public class EnquiryForm {
	
	private String enqId;
	private String studentName;
	private String phoneNumber;
	private String classMode;
	private String className;
	private String enqStatus;
	private Integer userId;
	private String enqDate;

}
