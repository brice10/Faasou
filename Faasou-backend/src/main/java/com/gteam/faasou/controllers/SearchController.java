package com.gteam.faasou.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gteam.faasou.services.SearchService;
import com.gteam.faasou.models.SearchModel;
import com.gteam.faasou.exceptions.InvalidInputException;

@RestController
@CrossOrigin("*")
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping(method = RequestMethod.POST, value = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> search(@RequestBody SearchModel searchModel) throws InvalidInputException {
		String result = searchService.search(searchModel);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
