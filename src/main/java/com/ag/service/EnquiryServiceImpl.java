package com.ag.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ag.db.CoursesEntity;
import com.ag.db.CoursesRepository;
import com.ag.db.EnqStatus;
import com.ag.db.EnqStatusRepository;
import com.ag.db.StudentEnqReposiroty;
import com.ag.db.StudentEnquiaryEntity;
import com.ag.db.UserDetailsEntity;
import com.ag.model.DashboardResponse;
import com.ag.model.EnquiryForm;
import com.ag.model.EnquirySearchCriteria;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	StudentEnqReposiroty enqReposiroty;

	@Autowired
	CoursesRepository courseRepo;

	@Autowired
	EnqStatusRepository enqStatus;
	
	@Autowired
	HttpSession session;

	@Override
	public DashboardResponse getDashboardResp(Integer userId) {
		UserDetailsEntity entity = new UserDetailsEntity();
		entity.setUserId(userId);
		List<StudentEnquiaryEntity> allStudent = enqReposiroty.findByUserId(entity);

		String totalStudent = "0";
		String enrolled = "0";
		String lost = "0";

		if (allStudent != null && allStudent.size() > 0) {
			totalStudent = "" + allStudent.size();
			long enrolledCount = allStudent.stream().filter(student -> student.getEnqStatus().equals("Enrolled"))
					.count();
			long lostCount = allStudent.stream().filter(student -> student.getEnqStatus().equals("Lost")).count();
			enrolled = "" + enrolledCount;
			lost = "" + lostCount;
		}

		DashboardResponse dashboardResponse = new DashboardResponse();
		dashboardResponse.setEnrolled(enrolled);
		dashboardResponse.setLost(lost);
		dashboardResponse.setTotalEnq(totalStudent);

		return dashboardResponse;
	}

	@Override
	public String addEnquiry(EnquiryForm formData) {
		StudentEnquiaryEntity studentEnquiaryEntity = new StudentEnquiaryEntity();
		BeanUtils.copyProperties(formData, studentEnquiaryEntity);

		if(formData.getEnqId() != null && formData.getEnqId().length() >0) {
			studentEnquiaryEntity.setEnqId(Integer.valueOf(formData.getEnqId()));
		}
		System.out.println("StudentEnquiaryEntity data" + studentEnquiaryEntity);

		UserDetailsEntity userDetail = new UserDetailsEntity();
		userDetail.setUserId(formData.getUserId());

		studentEnquiaryEntity.setUserId(userDetail);

		enqReposiroty.save(studentEnquiaryEntity);

		return "SUCCESS";
	}

	@Override
	public List<EnquiryForm> getEnquries(int pageNumber, Integer userId, EnquirySearchCriteria enqSearchCryt) {
		
		Pageable pageable =  PageRequest.of(pageNumber, 5);
		
		UserDetailsEntity entity = new UserDetailsEntity();
		entity.setUserId(userId);
		List<StudentEnquiaryEntity> allStudent = enqReposiroty.findByUserId(entity,pageable);
		List<EnquiryForm> enq = new ArrayList<>();
		allStudent.forEach(data -> {

			String pattern = "dd-MM-yyyy";

			// String pattern = "yyyy-MM-dd hh:mm:ss";

			EnquiryForm enquiryForm = new EnquiryForm();
			enquiryForm.setEnqId(String.valueOf(data.getEnqId()));
			enquiryForm.setClassMode(data.getClassMode());
			enquiryForm.setClassName(data.getClassName());
			enquiryForm.setStudentName(data.getStudentName());
			enquiryForm.setEnqStatus(data.getEnqStatus());
			enquiryForm.setPhoneNumber(data.getPhoneNumber());
			enquiryForm.setEnqDate(new SimpleDateFormat(pattern).format(data.getCreatedDate()).toString());
			enq.add(enquiryForm);
		});
		System.out.println("All Student Data" + enq);
		return enq;
	}

	@Override
	public List<String> getAllEnqStatus() {
		List<EnqStatus> enqStatusData = StreamSupport.stream(enqStatus.findAll().spliterator(), false)
				.collect(Collectors.toList());
		List<String> enqName = enqStatusData.stream().map(statuData -> statuData.getEnqName())
				.collect(Collectors.toList());
		return enqName;
	}

	@Override
	public List<String> getAllCourseList() {
		List<CoursesEntity> courseData = StreamSupport.stream(courseRepo.findAll().spliterator(), false)
				.collect(Collectors.toList());
		List<String> courseName = courseData.stream().map(course -> course.getCourseName())
				.collect(Collectors.toList());
		return courseName;
	}

	@Override
	public EnquirySearchCriteria getStudentById(Integer studentId) {

		EnquirySearchCriteria enquirySearchCriteria = new EnquirySearchCriteria();

		Optional<StudentEnquiaryEntity> studentData = enqReposiroty.findById(studentId);
		if (studentData.isPresent()) {
			StudentEnquiaryEntity studentObj = studentData.get();
			BeanUtils.copyProperties(studentObj, enquirySearchCriteria);
		}
		System.out.println("Student Data" + enquirySearchCriteria);
		return enquirySearchCriteria;
	}

	@Override
	public List<EnquiryForm> getEnquriesByFilter(String clsMode, String status, String course) {
		Integer userId = Integer.valueOf(session.getAttribute("userId").toString());
		UserDetailsEntity entity = new UserDetailsEntity();
		entity.setUserId(userId);
		
		if(clsMode.length()==0) {
			clsMode = null;
		}
		
		if(status.length()==0) {
			status = null;
		}
		
		if(course.length()==0) {
			course = null;
		}
		
		
		StudentEnquiaryEntity studentEnquiaryEntity =  new StudentEnquiaryEntity();
		studentEnquiaryEntity.setUserId(entity);
		studentEnquiaryEntity.setClassMode(clsMode);
		studentEnquiaryEntity.setEnqStatus(status);
		studentEnquiaryEntity.setClassName(course);
	
		Example<StudentEnquiaryEntity> exampleOfStudentEnqFilter = Example.of(studentEnquiaryEntity);

		List<StudentEnquiaryEntity> getAllFilteredData = enqReposiroty.findAll(exampleOfStudentEnqFilter);
		
		List<EnquiryForm> filteredData = new ArrayList<>();
		getAllFilteredData.forEach(data -> {

			String pattern = "dd-MM-yyyy";

			// String pattern = "yyyy-MM-dd hh:mm:ss";

			EnquiryForm enquiryForm = new EnquiryForm();
			enquiryForm.setEnqId(String.valueOf(data.getEnqId()));
			enquiryForm.setClassMode(data.getClassMode());
			enquiryForm.setClassName(data.getClassName());
			enquiryForm.setStudentName(data.getStudentName());
			enquiryForm.setEnqStatus(data.getEnqStatus());
			enquiryForm.setPhoneNumber(data.getPhoneNumber());
			enquiryForm.setEnqDate(new SimpleDateFormat(pattern).format(data.getCreatedDate()).toString());
			filteredData.add(enquiryForm);
		});
		return filteredData;
	}

}
