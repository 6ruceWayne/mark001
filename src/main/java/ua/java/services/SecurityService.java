package ua.java.services;

public interface SecurityService {
	String findLoggedInUsername();

	void autologin(String username, String password);

	String getName();
	
	void logout();
}
