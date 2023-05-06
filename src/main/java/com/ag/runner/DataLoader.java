package com.ag.runner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ag.db.CoursesEntity;
import com.ag.db.CoursesRepository;
import com.ag.db.EnqStatus;
import com.ag.db.EnqStatusRepository;

@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	CoursesRepository coursesRepository;
	
	@Autowired
	EnqStatusRepository enqStatusRepository;
	
	String[] classMode =  {"Online","Offline"};
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		CoursesEntity c1 =  new CoursesEntity();
		c1.setCourseName("Java Fullstack");
		
		CoursesEntity c2 =  new CoursesEntity();
		c2.setCourseName("DevOps");
		
		CoursesEntity c3 =  new CoursesEntity();
		c3.setCourseName("AWS");
		
		EnqStatus enqStatus =  new EnqStatus();
		EnqStatus enqStatus2 =  new EnqStatus();
		EnqStatus enqStatus3 =  new EnqStatus();
		enqStatus.setEnqName("New");
		enqStatus2.setEnqName("Enrolled");
		enqStatus3.setEnqName("Lost");
	
		List<EnqStatus> enqList =  new ArrayList<>();
		enqList.add(enqStatus);
		enqList.add(enqStatus2);
		enqList.add(enqStatus3);
		
		
		List<CoursesEntity> courseList =  new ArrayList<>();
		courseList.add(c1);
		courseList.add(c2);
		courseList.add(c3);
		
		//enqStatusRepository.saveAll(enqList);
		
		//coursesRepository.saveAll(courseList);
	}

	public String[] getClassMode() {
		return classMode;
	}	

}
