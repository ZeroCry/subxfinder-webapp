package com.finder.service;

import java.util.List;

import com.finder.dto.SubtitleDTO;

public interface FinderService {
	
	List<SubtitleDTO> findSubtitles(String title, String[] description) throws Exception;
}