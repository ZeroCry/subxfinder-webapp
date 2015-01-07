package com.finder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.finder.dto.SubtitleDTO;
import com.finder.service.FinderService;

@Controller
@RequestMapping(value = "/finder")
public class FinderController {
	
	private final Logger logger = LoggerFactory.getLogger(FinderController.class);
	
	@Resource
	FinderService service;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model,
							   @RequestParam(value = "title", required = true) String title,
							   @RequestParam(value = "description", required = true) String description) {
		
		List<SubtitleDTO> subtitles = new ArrayList<SubtitleDTO>();
		
		try{
			subtitles = service.findSubtitles(title, description);
		}catch(Exception e){
			logger.error("There was an error trying to get the subtitles", e);
		}
		
		model.put("subtitles", subtitles);
		
		return new ModelAndView("results");
	}
}