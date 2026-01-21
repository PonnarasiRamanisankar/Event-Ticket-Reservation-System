package com.wipro.etr.util;

public class InvalidUserException extends Exception {
	 
	    @Override
	    public String toString() {
	        return "InvalidUserException: User does not exist.";
	    }

}
