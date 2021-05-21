package com.springbank.user.query.api.controllers;

import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.messaging.*;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springbank.user.query.api.dto.*;
import com.springbank.user.query.api.queries.FindAllUsersQuery;
import com.springbank.user.query.api.queries.FindUserByIdQuery;
import com.springbank.user.query.api.queries.SearchUsersQuery;

@RestController
@RequestMapping(path="/api/v1/userLookup")
public class UserLookupController {
	private final QueryGateway queryGateway;
	
	public UserLookupController(QueryGateway queryGateway) {
		this.queryGateway = queryGateway;
	}
	
	@GetMapping(path="/")
	public ResponseEntity<UserLookupResponse> getAllUsers() {
		try {
			var query = new FindAllUsersQuery();
			var response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();
			
			if ( response == null || response.getUsers()==null ) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception ex) {
			var safeErrorMessage = "Failed to complete get all  user request";
			System.out.println(ex.toString());
			return new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/byId/{id}")
	public ResponseEntity<UserLookupResponse> getUserById(@PathVariable(value="id") String id) {
		try {
			var query = new FindUserByIdQuery(id);
			var response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();
			
			if ( response == null || response.getUsers()==null ) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception ex) {
			var safeErrorMessage = "Failed to complete get user by Id request";
			System.out.println(ex.toString());
			return new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/byFilter/{filter}")
	public ResponseEntity<UserLookupResponse> searchUserByFilter(@PathVariable(value="filter") String filter) {
		try {
			var query = new SearchUsersQuery(filter);
			var response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();
			
			if ( response == null || response.getUsers()==null ) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception ex) {
			var safeErrorMessage = "Failed to complete get user search request";
			System.out.println(ex.toString());
			return new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
}
