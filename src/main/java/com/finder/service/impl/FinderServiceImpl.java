package com.finder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.finder.dto.SubtitleDTO;
import com.finder.service.FinderService;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Service
public class FinderServiceImpl implements FinderService {
	
	private final Logger logger = LoggerFactory.getLogger(FinderServiceImpl.class);
	
	private static String searchPage = "http://www.subdivx.com/index.php?accion=5&buscar=";
	
	private List<SubtitleDTO> subtitleResults = new ArrayList<SubtitleDTO>();
	
	private WebClient webClient;
	
	public List<SubtitleDTO> findSubtitles(String title, String description) throws Exception {
		String searchURLWithParams = searchPage.concat(title);
		
		configureWebClient();
		
		HtmlPage searchResults = webClient.getPage(searchURLWithParams);
		
		List<DomElement> resultsAvailable = (List<DomElement>) searchResults.getByXPath("//div[@id='contenedor_izq']");
		
		if( resultsAvailable.size() > 0 ){
    	    int totalPages = getTotalPages(searchResults);
    	    
    	    for(int i = 1; i <= totalPages; i++){
    	    	logger.info("Fetching Page {} for title: {}", i, title);
    	    	
    	        searchResults = webClient.getPage(searchURLWithParams.concat("&pg=").concat(Integer.toString(i)));
    	    
    	        parseSubtitles(searchResults);
    	    }
	    }
		
		return subtitleResults;
	}
	
	public int getTotalPages(HtmlPage searchResults){
        List<DomElement> paginationDivs = (List<DomElement>) searchResults.getByXPath("//div[@id='contenedor_izq']/div[@class='pagination']/a[last()-1]");
        
        int totalPages = 1;
        
        //Check if there is any page
        if( paginationDivs.size() > 0 ){
        	totalPages = Integer.parseInt(paginationDivs.get(0).getTextContent());
        }
        
        return totalPages;
    }
	
	public void parseSubtitles(HtmlPage searchResults){
    	List<DomElement> subTitles = (List<DomElement>) searchResults.getByXPath("//div[@id='contenedor_izq']/div[@id='menu_detalle_buscador']");
    	List<DomElement> subDescriptions = (List<DomElement>) searchResults.getByXPath("//div[@id='contenedor_izq']/div[@id='buscador_detalle']");
    	
    	if( (subTitles.size() == subDescriptions.size()) && (subTitles.size() > 0 && subDescriptions.size() > 0) ){
	    	for( int i = 0; i < subTitles.size(); i++ ){
	    		SubtitleDTO subtitle = new SubtitleDTO();
	    		
	    		//Set Title
	    		subtitle.setTitle(subTitles.get(i).getTextContent().replace("Subtitulo de ", ""));
	    		
	    		List<DomElement> subDescriptionsDiv = (List<DomElement>) subDescriptions.get(i).getByXPath("div[@id='buscador_detalle_sub']");
	    		
	    		if( subDescriptionsDiv.size() > 0 ){
	    			//Set Description
	    			subtitle.setDescription(subDescriptionsDiv.get(0).getTextContent());
	    		}
	    		
	    		List<DomElement> subDescriptionDataLinks = (List<DomElement>) subDescriptions.get(i).getByXPath("div[@id='buscador_detalle_sub_datos']/a[last()]");
	    		
	    		if( subDescriptionDataLinks.size() > 0 ){
		    		//Set Download Link
		    		subtitle.setDownloadLink(subDescriptionDataLinks.get(0).getAttribute("href"));		    		
	    		}
	    		
	    		//Add to the subtitle list
	    		subtitleResults.add(subtitle);
	    	}
    	}
    }
	
	public WebClient configureWebClient(){
        webClient = new WebClient(BrowserVersion.CHROME);
        
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        
        return webClient;
    }
}