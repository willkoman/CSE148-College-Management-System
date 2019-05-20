package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NameFactory {
	public List<String> names;
	public List<String> lastNames;
	public String temp = "";
	private Scanner scanner;
	private Random rand = new Random();

	public void init() throws Exception {
		names = new ArrayList<String>();
		lastNames = new ArrayList<String>();
		importNames();
	}

	private void importNames() throws Exception {

		File boys = new File("bin/input_data/boys_names.txt");
		scanner = new Scanner(boys);
		while (scanner.hasNextLine()) {
			String i = scanner.nextLine();
			if (i != null && i != "")
				temp += i;
		}
		names.addAll(Arrays.asList(temp.split("([^A-Za-z]+\\s)")));
		scanner.close();
		temp = "";

		File girls = new File("bin/input_data/girls_names.txt");
		scanner = new Scanner(girls);
		while (scanner.hasNextLine()) {
			String i = scanner.nextLine();
			if (i != null && i != "")
				temp += i;
		}
		scanner.close();
		names.addAll(Arrays.asList(temp.split("\\s")));
		temp = "";

		File general = new File("bin/input_data/First Names.txt");
		scanner = new Scanner(general);
		while (scanner.hasNextLine()) {
			String i = scanner.nextLine();
			if (i != null && i != "")
				temp += i + "\r\n";
		}
		scanner.close();
		names.addAll(Arrays.asList(temp.split("\\r\\n")));
		temp = "";

		File lastnames = new File("bin/input_data/Last Names.txt");
		scanner = new Scanner(lastnames);
		while (scanner.hasNextLine()) {
			String i = scanner.nextLine();
			if (i != null && i != " ")
				temp += i + "\r\n";
		}
		scanner.close();
		lastNames.addAll(Arrays.asList(temp.split("\\r\\n")));
		temp = "";

		Thread.sleep(100);
	}

	public String emitFirstName() {
		return names.get(rand.nextInt(names.size()));
	}

	public String emitLastName() {
		return lastNames.get(rand.nextInt(lastNames.size()));
	}

	public String emitName() {
		return emitFirstName() + " " + emitLastName();
	}

}
