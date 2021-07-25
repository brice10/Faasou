package com.gteam.faasou.services;

import java.util.List;
import com.gteam.faasou.beans.Users;

/**
 *
 * @author Arléon Zemtsop
 */
public interface UsersService {
	public Users createUser(Users user);

	public Users updateUser(Users user);

	public List<Users> getAllUsers();

	public Users deleteUser(int userId);

	public Users findByPseudo(String pseudo);

	public Users findByEmail(String email);

	public Users findUserById(int userId);

	public Users updateUserPassword(int userId, String olpPassword, String newPassword);
}
