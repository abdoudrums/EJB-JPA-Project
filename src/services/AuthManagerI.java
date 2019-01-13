package services;

import java.util.List;

import entities.Activity;
import entities.Person;


public interface AuthManagerI {
	
	Person connexion(String email, String password);
	Person logout();
	
}
