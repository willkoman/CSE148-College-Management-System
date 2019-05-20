package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.Textbook;
import model.TextbookBag;

public class TextbookFactory {
	private TextbookBag textBag;
	public List<String> textbookNames;
	public List<String> textbookISBNs;
	public String temp = "";
	private Scanner scanner;
	private Random rand = new Random();
	public NameFactory nf;

	public TextbookFactory(TextbookBag textBag,NameFactory nf) {
		this.textBag = textBag;
		this.nf = nf;
		try {
			init();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	public void init() throws Exception {
		textbookNames = new ArrayList<String>();
		textbookISBNs = new ArrayList<String>();
		importNames();
	}

	public void importNames() throws Exception {
		File textnames = new File("bin/input_data/textbook_titles.txt");
		scanner = new Scanner(textnames);
		while (scanner.hasNextLine()) {
			String i = scanner.nextLine();
			if (i != null && i != "")
				temp += i + "\r\n";
		}
		scanner.close();
		textbookNames.addAll(Arrays.asList(temp.split("\\r\\n")));
		temp = "";

		File isbns = new File("bin/input_data/textbook_isbns.txt");
		scanner = new Scanner(isbns);
		while (scanner.hasNextLine()) {
			String i = scanner.nextLine();
			if (i != null && i != "")
				temp += i + "\r\n";
		}
		scanner.close();
		textbookISBNs.addAll(Arrays.asList(temp.split("\\r\\n")));
		temp = "";
	}

	public Textbook emitTextbook() {
		if (textbookNames.size() > 1 || textbookISBNs.size() > 1) {
			int tmp = rand.nextInt(textbookNames.size()) ;
			int tmp1 = rand.nextInt(textbookISBNs.size()) ;
			Textbook t = new Textbook(textbookNames.get(tmp), nf.emitName(), textbookISBNs.get(tmp1),
					rand.nextDouble() * 1000);
			textbookNames.remove(tmp);
			textbookISBNs.remove(tmp1);
			return t;
		} else
			return null;
	}

	public void importTextbooks(int amt) {
		for (int i = 0; i < amt; i++)
			textBag.insert(emitTextbook());
	}
}
