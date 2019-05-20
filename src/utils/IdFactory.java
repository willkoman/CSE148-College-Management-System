package utils;

import java.io.Serializable;

public class IdFactory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String generateID(String rawId) {
		String id = rawId;
		int originalLength = rawId.length();
		for (int i = originalLength; i < 8; i++) {
			id = "0" + id;
		}
		return id;
	}
}
