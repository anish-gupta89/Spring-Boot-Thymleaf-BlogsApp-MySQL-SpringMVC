package com.ag.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ag.AppConst;
import com.ag.model.DashboardResponse;
import com.ag.model.EnquiryForm;
import com.ag.model.EnquirySearchCriteria;
import com.ag.model.LoginForm;
import com.ag.runner.DataLoader;
import com.ag.service.EnquiryService;

@Controller
public class EnquiryController {

	@Autowired
	EnquiryService enquiryService;

	@Autowired
	DataLoader dataLoader;

	@Autowired
	HttpSession session;

	@GetMapping("/dashboard")
	public String dashboardLoader(Model model) {
		DashboardResponse dashboardResp = enquiryService
				.getDashboardResp(Integer.valueOf(session.getAttribute("userId").toString()));
		System.out.print("User ID For Dashboard"+Integer.valueOf(session.getAttribute("userId").toString()));
		model.addAttribute("dashboarddata", dashboardResp);
		return AppConst.DASHBOARD;
	}

	@GetMapping("/enquiry")
	
	public String addEnqLoader(@RequestParam(required = false) String id, Model model) {
		if (id != null && !id.isEmpty()) {
			EnquirySearchCriteria studentById = enquiryService.getStudentById(Integer.valueOf(id));
			studentById.setEnqId(id);
			System.out.println("Student Data" + studentById);
			model.addAttribute("enqdata", studentById);
		} else {
			model.addAttribute("enqdata", new EnquiryForm());
		}
		List<String> enqStatus = enquiryService.getAllEnqStatus();
		List<String> allCourseList = enquiryService.getAllCourseList();
		List<String> classModes = Arrays.asList(dataLoader.getClassMode());
		model.addAttribute("classMode", classModes);
		model.addAttribute("enqStatus", enqStatus);
		model.addAttribute("courseList", allCourseList);
		return AppConst.ADD_ENQ;
	}

	@PostMapping(value = "/enquiry")
	public String addStudent(@ModelAttribute("enqdata") EnquiryForm enqData, Model model) {
		System.out.println(enqData);
		enqData.setUserId(Integer.valueOf(model.getAttribute("userId").toString()));
		enquiryService.addEnquiry(enqData);
		return "redirect:" + AppConst.DASHBOARD;
	}

	@GetMapping(value = "/logout")
	public String logout(Model model) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping(value = "/viewenq")
	public String getEnq(@RequestParam(defaultValue = "0",required = false) String pageId , Model model) {
		List<String> enqStatus = enquiryService.getAllEnqStatus();
		List<String> allCourseList = enquiryService.getAllCourseList();
		List<String> classModes = Arrays.asList(dataLoader.getClassMode());
		model.addAttribute("classMode", classModes);
		model.addAttribute("viewenq", new EnquirySearchCriteria());
		model.addAttribute("enqStatus", enqStatus);
		model.addAttribute("courseList", allCourseList);
		List<EnquiryForm> enquries = enquiryService
				.getEnquries(Integer.valueOf(pageId), Integer.valueOf(session.getAttribute("userId").toString()), null);
		System.out.println("View Enq" + enquries);
		model.addAttribute("allData", enquries);
		return AppConst.VIEW_ENQ;
	}
	
	@GetMapping(value = "/filterenq")
	public String getEnqByFilter(@RequestParam(required = false) String clsMode,@RequestParam(required = false) String status,@RequestParam(required = false) String course, Model model) {

		System.out.println("clsMode" + clsMode + "status "+ status+ "course "+course);
		
		List<EnquiryForm> enquries = enquiryService.getEnquriesByFilter(clsMode, status, course);
		model.addAttribute("allData", enquries);
		return AppConst.ENQ_TABLES;
	}
	
	@GetMapping(value = "/loginformdata")
	@ResponseBody
	public LoginForm getLoginData() {
		
		LoginForm l =  new LoginForm();
		l.setEmail("aks@gmail.com");
		l.setPassword("AKS@123");
		
		return l;
	}
}
