package com.gteam.faasou.services.impl;

import java.util.List;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gteam.faasou.beans.Users;
import com.gteam.faasou.repositories.UsersRepository;
import com.gteam.faasou.services.UsersService;

/**
 *
 * @author Arleon Zemtsop
 */

@Service
@Transactional
public class UserServiceImpl implements UsersService {

	@Autowired
	UsersRepository usersRepository;

	@Override
	public Users createUser(Users user) {
		return usersRepository.save(user);
	}

	@Override
	public Users updateUser(Users user) {
		if (user.getUserId() != null) {
			user.setLastUpdateOn(new Date());
			return usersRepository.save(user);
		}
		return null;
	}

	@Override
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	@Override
	public Users deleteUser(int userId) {
		Users userToDelete = usersRepository.findById(userId).get();
		if (userToDelete != null) {
			usersRepository.delete(userToDelete);
		}
		return null;
	}

	@Override
	public Users findByPseudo(String pseudo) {
		return usersRepository.findByPseudo(pseudo);
	}

	@Override
	public Users findByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	@Override
	public Users findUserById(int userId) {
		return usersRepository.findById(userId).get();
	}

	@Override
	public Users updateUserPassword(int userId, String olpPassword, String newPassword) {
		Users userToUpdate = usersRepository.findById(userId).get();
		if (userToUpdate != null) {
			if (olpPassword.equals(userToUpdate.getPassword())) {
				userToUpdate.setPassword(newPassword);
				userToUpdate.setLastUpdateOn(new Date());
				return userToUpdate;
			}
			return null;
		} else {
			return null;
		}
	}
}
