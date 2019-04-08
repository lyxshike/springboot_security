package com.panda.security.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/ano")
public class AnonationController {
	
	@RequestMapping("VIP1")
	@Secured("ROLE_VIP1")
	public String Secured1() {
		return "VIP1 Secured";
	} 
	
	@RequestMapping("VIP2")
	@Secured("ROLE_VIP2")
	public String Secured2() {
		return "VIP2 Secured";
	} 
	
	@RequestMapping("VIP3")
	@RolesAllowed("ROLE_VIP3")
	public String RolesAllowed() {
		return "VIP3 RolesAllowed";
	} 
	
	@RequestMapping("preAuthorize")
	@PreAuthorize("hasRole('ROLE_VIP1')")
	public String preAuthorize() {
		return "you are my god";
	}
	
	@RequestMapping("preAuthorize_SPEL")
	@PreAuthorize("#request.getParameter('str').length() > 2")
	public String preAuthorize_SPEL(HttpServletRequest request) {
		return "you are my god";
	}
	
	@RequestMapping("postAuthorize_SPEL")
	@PostAuthorize("returnObject.length() > 2")
	public String postAuthorize_SPEL(HttpServletRequest request) {
		String str = request.getParameter("postA");
		return str;
	}
	
	@RequestMapping("postFilter")
	@PostFilter("filterObject>2")// filterObject 是使用@PreFilter, @PostFilter时的内置表达式，表示集合中的当前对象。
    public List<Integer> postFilter(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		return list;
	} 	
}
