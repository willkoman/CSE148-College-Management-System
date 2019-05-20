package view;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import model.College;
import model.Course;
import model.MiniFacultyCourseBag;
import model.MiniFacultyCourseInfo;
import model.MiniFacultyCourseInfo.TeachStatus;
import model.MiniStudentCourseInfo.CourseStatus;
import model.MiniStudentCourseInfo.LetterGrade;
import model.Student.Major;
import model.Faculty;
import model.Faculty.Title;

public class FacultyPane {
	private GridPane grid;

	public FacultyPane(College college) {

		grid = new GridPane();
		grid.setVgap(20);
		grid.setHgap(50);
		
		grid.setPadding(new Insets(50));
		TextField firstNameField = new TextField();
		firstNameField.setPromptText("First Name");

		TextField lastNameField = new TextField();
		lastNameField.setPromptText("Last Name");

		TextField titleField = new TextField();
		titleField.setPromptText("Title");
		titleField.setEditable(false);
		TextField phoneField = new TextField();
		phoneField.setPromptText("Phone Number");

		TextField IDField = new TextField();
		IDField.setPromptText("ID #");
		IDField.setEditable(false);
		TextField salaryField = new TextField();
		salaryField.setPromptText("Salary");

		TextField departmentField = new TextField();
		departmentField.setPromptText("Department");

		grid.add(firstNameField, 0, 0);
		grid.add(lastNameField, 0, 1);
		grid.add(IDField, 0, 2);

		grid.add(titleField, 0, 3);
		grid.add(phoneField, 0, 4);
		grid.add(salaryField, 0, 5);
		grid.add(departmentField, 0, 6);
		ObservableList<String> names = FXCollections.observableArrayList();

		ListView<String> listView = new ListView<String>(names);
		listView.setPrefWidth(450);
		listView.setPrefHeight(200);

		grid.add(listView, 4, 6);
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
		alert.setContentText("Could not find faculty! (check that your information is correct)");

		Alert done = new Alert(AlertType.CONFIRMATION);
		done.setTitle("Done");
		done.setHeaderText("Success!");
		done.setContentText("Task Successful");

		searchBtn.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("ID NUMBER");
			dialog.setContentText("Please enter a faculty ID");
			dialog.setHeaderText("Faculty ID Please");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				if (college.pb.findFacultyByID(idNumber) != null
						&& college.pb.findFacultyByID(idNumber).getFirstName() != null) {

					firstNameField.setText(college.pb.findFacultyByID(idNumber).getFirstName());
					lastNameField.setText(college.pb.findFacultyByID(idNumber).getLastName());
					titleField.setText(college.pb.findFacultyByID(idNumber).getTitle().toString());
					IDField.setText(college.pb.findFacultyByID(idNumber).getId());
					phoneField.setText(college.pb.findFacultyByID(idNumber).getOfficePhone());
					salaryField.setText(String.format("%.2f", college.pb.findFacultyByID(idNumber).getSalary()));
					departmentField.setText(college.pb.findFacultyByID(idNumber).getDepartment().toString());

					names.clear();
					names.addAll(college.pb.findFacultyByID(idNumber).miniFacultyCourseBag.getCourses());
				} else {
					alert.showAndWait();
				}
			});

		});

		searchBtn2.setOnAction(e -> {

			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Name");
			dialog.setContentText("Please enter a faculty name <first last>");
			dialog.setHeaderText("Faculty Name");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(name -> {
				if (college.pb.findFacultyByName(name) != null
						&& college.pb.findFacultyByName(name).getFirstName() != null) {

					firstNameField.setText(college.pb.findFacultyByName(name).getFirstName());
					lastNameField.setText(college.pb.findFacultyByName(name).getLastName());
					titleField.setText(college.pb.findFacultyByName(name).getTitle().toString());
					IDField.setText(college.pb.findFacultyByName(name).getId());
					phoneField.setText(college.pb.findFacultyByName(name).getOfficePhone());
					salaryField.setText(String.format("%.2f", college.pb.findFacultyByName(name).getSalary()));

					names.clear();
					names.addAll(college.pb.findFacultyByName(name).miniFacultyCourseBag.getCourses());
				} else {
					alert.showAndWait();
				}
			});

		});

		insertBtn.setOnAction(e -> {
			MiniFacultyCourseBag temp = new MiniFacultyCourseBag(6);
			for (int i = 0; i < 6; i++) {
				Course t = college.cb.emitCourse();
				MiniFacultyCourseInfo msi = new MiniFacultyCourseInfo(t.getCourseNumber(), t.getCourseTitle(),
						t.getClassroom());
				msi.courseStatus = TeachStatus.getRandomStatus();
				temp.insert(msi);
			}
			Faculty s;
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Import Faculty");
			dialog.setContentText("Please enter the first name of the faculty");
			dialog.setHeaderText("Faculty Import");
			Optional<String> firstName = dialog.showAndWait();
			firstName.ifPresent(name -> System.out.println("FIRSTNAME: " + name));

			TextInputDialog dialog2 = new TextInputDialog();
			dialog2.setTitle("Import Faculty");
			dialog2.setContentText("Please enter the last name of the faculty");
			dialog2.setHeaderText("Faculty Import");
			Optional<String> lastname = dialog2.showAndWait();
			lastname.ifPresent(name -> System.out.println("LASTNAME: " + name));

			TextInputDialog dialog3 = new TextInputDialog();
			dialog3.setTitle("Import Faculty");
			dialog3.setContentText("Please enter the phone number of the faculty");
			dialog3.setHeaderText("Faculty Import");
			Optional<String> phoneNumber = dialog3.showAndWait();
			phoneNumber.ifPresent(name -> System.out.println("PHONE: " + name));

			List<String> enumNames = Stream.of(Major.values()).map(Enum::name).collect(Collectors.toList());

			ChoiceDialog<String> dialog4 = new ChoiceDialog<>("NONE", enumNames);
			dialog4.setTitle("Import Faculty");
			dialog4.setHeaderText("Please select the department of this faculty");
			dialog4.setContentText("Faculty Import");
			Optional<String> major = dialog4.showAndWait();
			major.ifPresent(name -> System.out.println("dept: " + name));

			Major m = Major.valueOf(major.get());

			List<String> emuntitles = Stream.of(Title.values()).map(Enum::name).collect(Collectors.toList());

			ChoiceDialog<String> dialog5 = new ChoiceDialog<>("Prof", emuntitles);
			dialog5.setTitle("Import Faculty");
			dialog5.setHeaderText("Please select the title of this faculty");
			dialog5.setContentText("Faculty Import");
			Optional<String> title = dialog5.showAndWait();
			major.ifPresent(name -> System.out.println("tiitle: " + name));

			Title t = Title.valueOf(title.get());

			TextInputDialog dialog6 = new TextInputDialog();
			dialog6.setTitle("Import Faculty");
			dialog6.setContentText("Please enter the salary of the faculty ($)");
			dialog6.setHeaderText("Faculty Import");
			Optional<String> salary = dialog6.showAndWait();
			phoneNumber.ifPresent(name -> System.out.println("salary: " + name));

			s = new Faculty(firstName.get(), lastname.get(), t, Double.parseDouble(salary.get()), m, phoneNumber.get(),
					temp, Integer.parseInt(college.pb.personArray.get(college.pb.personArray.size() - 1).getId()) + 1);

			college.pb.insert(s);

			done.showAndWait();
		});

		deleteBtn.setOnAction(e -> {

			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Name");
			dialog.setContentText("Please enter an id number to delete");
			dialog.setHeaderText("Faculty id");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				if (college.pb.findFacultyByID(idNumber) != null
						&& college.pb.findFacultyByID(idNumber).getFirstName() != null) {
					college.pb.deleteByID(idNumber);
					done.showAndWait();
				} else {
					alert.showAndWait();
				}
			});

		});

		updateBtn.setOnAction(e -> {
			if (IDField.getText() != "") {
				String id = IDField.getText();
				Faculty s = college.pb.findFacultyByID(id);
				s.setFirstName(firstNameField.getText());
				s.setLastName(lastNameField.getText());
				s.setDepartment(Major.valueOf(departmentField.getText()));
				s.setOfficePhone(phoneField.getText());
				s.setTitle(Title.valueOf(titleField.getText()));
				s.setSalary(Double.parseDouble(salaryField.getText()));
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