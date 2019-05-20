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
import model.Course;

public class CoursePane {
	private GridPane grid;

	public CoursePane(College college) {
		grid = new GridPane();
		grid.setVgap(20);
		grid.setHgap(50);
		grid.setPadding(new Insets(50));

		TextField courseNumberField = new TextField();
		courseNumberField.setPromptText("Course Number");
		courseNumberField.setEditable(false);
		
		TextField titleField = new TextField();
		titleField.setPromptText("Title");
		
		TextArea descriptionField = new TextArea();
		descriptionField.setPromptText("Course Description");


		TextField creditsField = new TextField();
		creditsField.setPromptText("Credits");

		TextField classroomField = new TextField();
		classroomField.setPromptText("Classroom");

		grid.add(courseNumberField, 0, 0);
		grid.add(descriptionField, 0, 1);
		grid.add(titleField, 0, 2);
		grid.add(creditsField, 0, 3);
		grid.add(classroomField, 0, 4);

		Button searchBtn = new Button("SEARCH BY COURSE #");
		Button insercbtn = new Button("INSERT");
		Button deleteBtn = new Button("DELETE");
		Button updateBtn = new Button("UPDATE");

		grid.add(searchBtn, 2, 0);
		grid.add(insercbtn, 2, 2);
		grid.add(deleteBtn, 2, 3);
		grid.add(updateBtn, 2, 4);

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("ERROR");
		alert.setContentText("Could not find course! (check that your information is correct)");

		Alert done = new Alert(AlertType.CONFIRMATION);
		done.setTitle("Done");
		done.setHeaderText("Success!");
		done.setContentText("Task Successful");

		searchBtn.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Course Number");
			dialog.setContentText("Please enter a Course Number");
			dialog.setHeaderText("Course Number Please");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				if (college.cb.findByCourseID(idNumber) != null && college.cb.findByCourseID(idNumber).getCourseTitle() != null) {
					courseNumberField.setText(college.cb.findByCourseID(idNumber).getCourseNumber());
					descriptionField.setText(college.cb.findByCourseID(idNumber).getCourseDescription());
					titleField.setText(college.cb.findByCourseID(idNumber).getCourseTitle());
					classroomField.setText(college.cb.findByCourseID(idNumber).getClassroom());
					creditsField.setText(String.format("%.2f", college.cb.findByCourseID(idNumber).getCredits()));
				} else {
					alert.showAndWait();
				}
			});
		});

		insercbtn.setOnAction(e -> {
			Course s;
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Import Course");
			dialog.setContentText("Please enter the name of the course");
			dialog.setHeaderText("Import Course");
			Optional<String> title = dialog.showAndWait();
			title.ifPresent(name -> System.out.println("Title: " + name));

			TextInputDialog dialog2 = new TextInputDialog();
			dialog2.setTitle("Import Course");
			dialog2.setContentText("Please enter the Course Number of the course");
			dialog2.setHeaderText("Import Course");
			Optional<String> courseNumber = dialog2.showAndWait();
			courseNumber.ifPresent(name -> System.out.println("Course Number: " + name));

			TextInputDialog dialog3 = new TextInputDialog();
			dialog3.setTitle("Import Course");
			dialog3.setContentText("Please enter the description of the course");
			dialog3.setHeaderText("Course Import");
			Optional<String> description = dialog3.showAndWait();
			description.ifPresent(name -> System.out.println("description: " + name));

			TextInputDialog dialog4 = new TextInputDialog();
			dialog4.setTitle("Import Course");
			dialog4.setContentText("Please enter the room number of the course");
			dialog4.setHeaderText("Course Import");
			Optional<String> roomNum = dialog4.showAndWait();
			roomNum.ifPresent(name -> System.out.println("roomNum: " + name));

			TextInputDialog dialog5 = new TextInputDialog();
			dialog5.setTitle("Import Course");
			dialog5.setContentText("Please enter the credits of the course");
			dialog5.setHeaderText("Course Import");
			Optional<String> credits = dialog5.showAndWait();
			credits.ifPresent(name -> System.out.println("credits: " + name));
			
			s = new Course(courseNumber.get(),title.get(),description.get(),Double.parseDouble(credits.get()),roomNum.get());
			college.cb.insert(s);

			done.showAndWait();
		});
		deleteBtn.setOnAction(e -> {

			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Name");
			dialog.setContentText("Please enter an Course Number number to delete");
			dialog.setHeaderText("Course Number");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				if (college.cb.findByCourseID(idNumber) != null
						&& college.cb.findByCourseID(idNumber).getCourseTitle() != null) {
					college.cb.deleteByCourseID(idNumber);
					done.showAndWait();
				} else {
					alert.showAndWait();
				}
			});

		});

		updateBtn.setOnAction(e->{
			if(courseNumberField.getText()!="")
			{
				String id=courseNumberField.getText();
				Course s = college.cb.findByCourseID(id);
				s.setClassroom(classroomField.getText());
				s.setCourseDescription(descriptionField.getText());
				s.setCourseTitle(titleField.getText());
				s.setNumberOfCredits(Double.parseDouble(creditsField.getText()));
			}
		});
	}

	public GridPane getGrid() {
		return grid;
	}
}
