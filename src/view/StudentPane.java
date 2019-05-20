package view;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import model.College;
import model.Course;
import model.MiniStudentCourseBag;
import model.MiniStudentCourseInfo;
import model.MiniStudentCourseInfo.CourseStatus;
import model.MiniStudentCourseInfo.LetterGrade;
import model.Student;
import model.Student.Major;

public class StudentPane {
	private GridPane grid;

	public StudentPane(College college) {

		grid = new GridPane();
		grid.setVgap(20);
		grid.setHgap(50);
		grid.setPadding(new Insets(50));
		TextField firstNameField = new TextField();
		firstNameField.setPromptText("First Name");

		TextField lastNameField = new TextField();
		lastNameField.setPromptText("Last Name");

		TextField gpaField = new TextField();
		gpaField.setPromptText("GPA");
		gpaField.setEditable(false);
		TextField phoneField = new TextField();
		phoneField.setPromptText("Phone Number");

		TextField IDField = new TextField();
		IDField.setPromptText("ID #");
		IDField.setEditable(false);
		TextField majorField = new TextField();
		majorField.setPromptText("Major");

		grid.add(firstNameField, 0, 0);
		grid.add(lastNameField, 0, 1);
		grid.add(IDField, 0, 2);

		grid.add(gpaField, 0, 3);
		grid.add(phoneField, 0, 4);
		grid.add(majorField, 0, 5);
		ObservableList<String> names = FXCollections.observableArrayList();

		ListView<String> listView = new ListView<String>(names);
		listView.setPrefWidth(450);
		listView.setPrefHeight(200);

		grid.add(listView, 4, 5);
		Button searchBtn = new Button("SEARCH BY ID");
		Button searchBtn2 = new Button("SEARCH BY NAME");
		Button insertBtn = new Button("INSERT");
		Button deleteBtn = new Button("DELETE");
		Button updateBtn = new Button("UPDATE");

		grid.add(searchBtn, 4, 0);
		grid.add(searchBtn2, 4, 1);
		grid.add(insertBtn, 4, 2);
		grid.add(deleteBtn, 4, 3);
		grid.add(updateBtn, 4, 4);

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("ERROR");
		alert.setContentText("Could not find student! (check that your information is correct)");

		Alert done = new Alert(AlertType.CONFIRMATION);
		done.setTitle("Done");
		done.setHeaderText("Success!");
		done.setContentText("Task Successful");

		searchBtn.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("ID NUMBER");
			dialog.setContentText("Please enter a student ID");
			dialog.setHeaderText("Student ID Please");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				if (college.pb.findStudentByID(idNumber) != null
						&& college.pb.findStudentByID(idNumber).getFirstName() != null) {

					firstNameField.setText(college.pb.findStudentByID(idNumber).getFirstName());
					lastNameField.setText(college.pb.findStudentByID(idNumber).getLastName());
					gpaField.setText(String.format("%.2f", college.pb.findStudentByID(idNumber).getGpa()));
					IDField.setText(college.pb.findStudentByID(idNumber).getId());
					phoneField.setText(college.pb.findStudentByID(idNumber).getPhone());
					majorField.setText(college.pb.findStudentByID(idNumber).getMajor().toString());

					names.clear();
					names.addAll(college.pb.findStudentByID(idNumber).getMiniStudentCourseBag().getCourses());
				} else {
					alert.showAndWait();
				}
			});

		});

		searchBtn2.setOnAction(e -> {

			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Name");
			dialog.setContentText("Please enter a student name <first last>");
			dialog.setHeaderText("Student Name");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(name -> {
				if (college.pb.findStudentByName(name) != null
						&& college.pb.findStudentByName(name).getFirstName() != null) {

					firstNameField.setText(college.pb.findStudentByName(name).getFirstName());
					lastNameField.setText(college.pb.findStudentByName(name).getLastName());
					gpaField.setText(String.format("%.2f", college.pb.findStudentByName(name).getGpa()));
					IDField.setText(college.pb.findStudentByName(name).getId());
					phoneField.setText(college.pb.findStudentByName(name).getPhone());
					majorField.setText(college.pb.findStudentByName(name).getMajor().toString());

					names.clear();
					names.addAll(college.pb.findStudentByName(name).getMiniStudentCourseBag().getCourses());
				} else {
					alert.showAndWait();
				}
			});

		});

		insertBtn.setOnAction(e -> {
			MiniStudentCourseBag temp = new MiniStudentCourseBag(6);
			for (int i = 0; i < 6; i++) {
				Course t = college.cb.emitCourse();
				MiniStudentCourseInfo msi = new MiniStudentCourseInfo(t.getCourseNumber(), t.getCredits(),
						t.getClassroom());
				msi.courseStatus = CourseStatus.getRandomStatus();
				msi.letterGrade = LetterGrade.getRandomGrade();
				temp.insert(msi);
			}
			Student s;
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Import Student");
			dialog.setContentText("Please enter the first name of the student");
			dialog.setHeaderText("Student Import");
			Optional<String> firstName = dialog.showAndWait();
			firstName.ifPresent(name -> System.out.println("FIRSTNAME: " + name));

			TextInputDialog dialog2 = new TextInputDialog();
			dialog2.setTitle("Import Student");
			dialog2.setContentText("Please enter the last name of the student");
			dialog2.setHeaderText("Student Import");
			Optional<String> lastname = dialog2.showAndWait();
			lastname.ifPresent(name -> System.out.println("LASTNAME: " + name));

			TextInputDialog dialog3 = new TextInputDialog();
			dialog3.setTitle("Import Student");
			dialog3.setContentText("Please enter the phone number of the student");
			dialog3.setHeaderText("Student Import");
			Optional<String> phoneNumber = dialog3.showAndWait();
			phoneNumber.ifPresent(name -> System.out.println("PHONE: " + name));

			List<String> enumNames = Stream.of(Major.values()).map(Enum::name).collect(Collectors.toList());

			ChoiceDialog<String> dialog4 = new ChoiceDialog<>("NONE", enumNames);
			dialog4.setTitle("Import Student");
			dialog4.setHeaderText("Please select the major of this student");
			dialog4.setContentText("Student Import");
			Optional<String> major = dialog4.showAndWait();
			major.ifPresent(name -> System.out.println("MAJOR: " + name));
			Major m = Major.valueOf(major.get());
			s = new Student(firstName.get(), lastname.get(), m, temp, phoneNumber.get(),
					Integer.parseInt(college.pb.personArray.get(college.pb.personArray.size() - 1).getId()) + 1);

			college.pb.insert(s);

			done.showAndWait();
		});

		deleteBtn.setOnAction(e -> {

			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Name");
			dialog.setContentText("Please enter an id number to delete");
			dialog.setHeaderText("Student id");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				if (college.pb.findStudentByID(idNumber) != null
						&& college.pb.findStudentByID(idNumber).getFirstName() != null) {
					college.pb.deleteByID(idNumber);
					done.showAndWait();
				} else {
					alert.showAndWait();
				}
			});

		});

		updateBtn.setOnAction(e->{
			if(IDField.getText()!="")
			{
				String id=IDField.getText();
				Student s = college.pb.findStudentByID(id);
				s.setFirstName(firstNameField.getText());
				s.setLastName(lastNameField.getText());
				s.setMajor(Major.valueOf(majorField.getText()));
				s.setPhone(phoneField.getText());
			}
		});
	}

	public GridPane getGrid() {
		return grid;
	}

	public void setGrid(GridPane grid) {
		this.grid = grid;
	}

}