package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TextbookBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Textbook> textbookArray;
	int nElems;
	private Random rand = new Random();

	public TextbookBag(int maxSize) {
		textbookArray = new ArrayList<Textbook>();
		nElems = textbookArray.size();
	}

	public void insert(Textbook textbook) {
		textbookArray.add(textbook);
	}

	public Textbook emitTextbook() {
		return textbookArray.get(rand.nextInt(textbookArray.size()));
	}

	public Textbook findByISBN(String isbn) {
		for (Textbook t : textbookArray) 
			if (t.getISBN().equals(isbn))
				return t;
		
		return null;
	}
	public void deleteByISBN(String id) {
		for (Iterator<Textbook> iterator = textbookArray.iterator(); iterator.hasNext(); ) {
		    Textbook g = iterator.next();
			if (g.getIsbn().equals(id)) {
				iterator.remove();
			}
		}

	}
}
