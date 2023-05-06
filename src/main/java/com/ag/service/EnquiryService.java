package com.ag.service;

import java.util.List;

import com.ag.model.DashboardResponse;
import com.ag.model.EnquiryForm;
import com.ag.model.EnquirySearchCriteria;

public interface EnquiryService {

	public DashboardResponse getDashboardResp(Integer userId);

	public String addEnquiry(EnquiryForm formData);

	public List<EnquiryForm> getEnquries(int pageNumber, Integer userId, EnquirySearchCriteria enqSearchCryt);

	public List<String> getAllEnqStatus();

	public List<String> getAllCourseList();

	public EnquirySearchCriteria getStudentById(Integer studentId);
	
	public List<EnquiryForm> getEnquriesByFilter(String clsMode, String status, String course);

}
