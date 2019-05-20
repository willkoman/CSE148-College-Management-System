package view;

import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import model.College;
import model.Textbook;


public class TextbookPane {
	private GridPane grid;

	public TextbookPane(College college) {
		grid = new GridPane();
		grid.setVgap(20);
		grid.setHgap(50);
		grid.setPadding(new Insets(50));

		TextField ISBN = new TextField();
		ISBN.setPromptText("ISBN");
		ISBN.setEditable(false);

		TextArea descriptionField = new TextArea();
		descriptionField.setPromptText("Course Description");

		TextField titleField = new TextField();
		titleField.setPromptText("Title");

		TextField authorField = new TextField();
		authorField.setPromptText("Author");

		TextField priceField = new TextField();
		priceField.setPromptText("Price");

		grid.add(ISBN, 0, 0);
		grid.add(descriptionField, 0, 1);
		grid.add(titleField, 0, 2);
		grid.add(authorField, 0, 3);
		grid.add(priceField, 0, 4);

		Button searchBtn = new Button("SEARCH BY ISBN");
		Button insertBtn = new Button("INSERT");
		Button deleteBtn = new Button("DELETE");
		Button updateBtn = new Button("UPDATE");

		grid.add(searchBtn, 2, 0);
		grid.add(insertBtn, 2, 2);
		grid.add(deleteBtn, 2, 3);
		grid.add(updateBtn, 2, 4);

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("ERROR");
		alert.setContentText("Could not find textbook! (check that your information is correct)");

		Alert done = new Alert(AlertType.CONFIRMATION);
		done.setTitle("Done");
		done.setHeaderText("Success!");
		done.setContentText("Task Successful");

		searchBtn.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("ISBN");
			dialog.setContentText("Please enter an ISBN");
			dialog.setHeaderText("ISBN Please");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				if (college.tb.findByISBN(idNumber) != null && college.tb.findByISBN(idNumber).getAuthor() != null) {
					ISBN.setText(college.tb.findByISBN(idNumber).getIsbn());
					descriptionField.setText(college.tb.findByISBN(idNumber).getDescription());
					titleField.setText(college.tb.findByISBN(idNumber).getBookTitle());
					authorField.setText(college.tb.findByISBN(idNumber).getAuthor());
					priceField.setText(String.format("%.2f", college.tb.findByISBN(idNumber).getPrice()));
				} else {
					alert.showAndWait();
				}
			});
		});

		insertBtn.setOnAction(e -> {
			Textbook s;
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Import Textbook");
			dialog.setContentText("Please enter the name of the textbook");
			dialog.setHeaderText("Import Textbook");
			Optional<String> title = dialog.showAndWait();
			title.ifPresent(name -> System.out.println("Title: " + name));

			TextInputDialog dialog2 = new TextInputDialog();
			dialog2.setTitle("Import Textbook");
			dialog2.setContentText("Please enter the ISBN of the textbook");
			dialog2.setHeaderText("Import Textbook");
			Optional<String> isbn = dialog2.showAndWait();
			isbn.ifPresent(name -> System.out.println("isbn: " + name));

			TextInputDialog dialog3 = new TextInputDialog();
			dialog3.setTitle("Import Textbook");
			dialog3.setContentText("Please enter the author of the textbook");
			dialog3.setHeaderText("Textbook Import");
			Optional<String> author = dialog3.showAndWait();
			author.ifPresent(name -> System.out.println("author: " + name));

			TextInputDialog dialog4 = new TextInputDialog();
			dialog4.setTitle("Import Textbook");
			dialog4.setContentText("Please enter the description of the textbook");
			dialog4.setHeaderText("Textbook Import");
			Optional<String> description = dialog4.showAndWait();
			description.ifPresent(name -> System.out.println("description: " + name));

			TextInputDialog dialog5 = new TextInputDialog();
			dialog5.setTitle("Import Textbook");
			dialog5.setContentText("Please enter the price of the textbook");
			dialog5.setHeaderText("Textbook Import");
			Optional<String> price = dialog5.showAndWait();
			price.ifPresent(name -> System.out.println("price: " + name));
			
			s = new Textbook(title.get(),author.get(),isbn.get(),Double.parseDouble(price.get()),description.get());
			college.tb.insert(s);

			done.showAndWait();
		});
		deleteBtn.setOnAction(e -> {

			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Name");
			dialog.setContentText("Please enter an ISBN number to delete");
			dialog.setHeaderText("ISBN");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				if (college.tb.findByISBN(idNumber) != null
						&& college.tb.findByISBN(idNumber).getBookTitle() != null) {
					college.tb.deleteByISBN(idNumber);
					done.showAndWait();
				} else {
					alert.showAndWait();
				}
			});

		});

		updateBtn.setOnAction(e->{
			if(ISBN.getText()!="")
			{
				String id=ISBN.getText();
				Textbook s = college.tb.findByISBN(id);
				s.setAuthor(authorField.getText());
				s.setBookTitle(titleField.getText());
				s.setDescription(descriptionField.getText());
				s.setPrice(Double.parseDouble(priceField.getText()));
			}
		});
	}

	public GridPane getGrid() {
		return grid;
	}
}
