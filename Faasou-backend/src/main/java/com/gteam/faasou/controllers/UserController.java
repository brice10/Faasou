package com.gteam.faasou.controllers;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gteam.faasou.services.UsersService;
import com.gteam.faasou.utils.Util;
import com.gteam.faasou.beans.Users;
import com.gteam.faasou.exceptions.InvalidInputException;
import com.gteam.faasou.models.UserPasswordChangeModel;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UsersService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUser(@RequestBody Users user) throws InvalidInputException {
		if (userService.findByEmail(user.getEmail()) != null) {
			throw new InvalidInputException("Email already used");
		} else if (userService.findByPseudo(user.getPseudo()) != null) {
			throw new InvalidInputException("Pseudo already used");
		}
		Users userCreated = userService.createUser(user);
		return new ResponseEntity<>(userCreated, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUser(@RequestBody Users user) throws InvalidInputException {
		Users userByMail = userService.findByEmail(user.getEmail());
		Users userByPseudo = userService.findByPseudo(user.getPseudo());
		if (userByMail != null) {
			if (userByMail.getUserId() != user.getUserId())
				throw new InvalidInputException("The new mail adress you have chosoen already used");
		} else if (userByPseudo != null) {
			if (userByPseudo.getUserId() != user.getUserId())
				throw new InvalidInputException("The new pseudo you have choosen already used");
		}
		Users userUpdated = userService.updateUser(user);
		return new ResponseEntity<>(userUpdated, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/deleteUser/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId) throws InvalidInputException {
		Users userDeleted = userService.deleteUser(userId);
		if (userDeleted != null) {
			return new ResponseEntity<>(userDeleted, HttpStatus.OK);
		} else {
			throw new InvalidInputException("User to delete not found");
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/findUserById/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findUserById(@PathVariable("userId") int userId) {
		Users userFound = userService.findUserById(userId);
		return new ResponseEntity<>(userFound, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateUserPassword", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUserPassword(@RequestBody UserPasswordChangeModel user)
			throws InvalidInputException {
		Users userChanged = userService.updateUserPassword(user.getUserId(), user.getOldPassword(),
				user.getNewPassword());
		if (userChanged != null) {
			return new ResponseEntity<>(userChanged, HttpStatus.OK);
		} else {
			throw new InvalidInputException("Votre ancien mot de passe est incorrect.");
		}
	}
}
