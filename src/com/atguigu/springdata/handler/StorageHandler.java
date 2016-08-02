package com.atguigu.springdata.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.springdata.service.StorageService;
import com.atguigu.springdata.utils.Servlets;

@RequestMapping("/storage")
@Controller
public class StorageHandler {

	@Autowired
	private StorageService storageService;
	
	@RequestMapping("/list")
	public String showList(Map<String,Object> map,HttpServletRequest request,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		Map<String, Object> reqParams = Servlets.getParametersStartingWith(request, "search_");
		
		map.put("page", storageService.getPage(pageNo,reqParams));
		
		String paramStr = Servlets.encodeParameterStringWithPrefix(reqParams, "search_");
		map.put("searchParams", paramStr);
		return "list";
	}

}
