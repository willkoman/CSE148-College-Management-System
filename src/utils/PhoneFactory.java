package utils;

import java.util.Random;

public class PhoneFactory {
	int num1, num2, num3; // 3 numbers in area code
	int set2, set3; // sequence 2 and 3 of the phone number

	Random rand = new Random();

	public String generatePhoneNumber() {

		num1 = rand.nextInt(7) + 1;
		num2 = rand.nextInt(8);
		num3 = rand.nextInt(8);

		set2 = rand.nextInt(643) + 100;

		set3 = rand.nextInt(8999) + 1000;

		return "(" + num1 + "" + num2 + "" + num3 + ") "  + set2 + "-" + set3;
	}
}
