package com.ag.model;

import lombok.Data;

@Data
public class CommentRequestAndResp {
	
	private String commentId;
	private String name;
	private String email;
	private String comment;
	private Integer blogId;
	private String commentedOn;
}
