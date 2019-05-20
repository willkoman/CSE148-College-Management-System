package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import model.College;
import model.Student.Major;
import model.Classroom;
import model.Classroom.RoomBuilding;

public class ClassroomPane {
	private GridPane grid;

	public ClassroomPane(College college) {
		grid = new GridPane();
		grid.setVgap(20);
		grid.setHgap(50);
		grid.setPadding(new Insets(50));

		TextField roomNumberField = new TextField();
		roomNumberField.setPromptText("Room Number");
		roomNumberField.setEditable(false);

		TextField buildingField = new TextField();
		buildingField.setPromptText("Building Name");

		TextField projectorField = new TextField();
		projectorField.setPromptText("Projector <Y/N>");

		TextField capacityField = new TextField();
		capacityField.setPromptText("Capacity");

		grid.add(roomNumberField, 0, 0);
		grid.add(buildingField, 0, 1);
		grid.add(projectorField, 0, 2);
		grid.add(capacityField, 0, 3);

		Button searchBtn = new Button("SEARCH BY ROOM #");
		Button insertBtn = new Button("INSERT");
		Button deleteBtn = new Button("DELETE");
		Button updateBtn = new Button("UPDATE");

		grid.add(searchBtn, 2, 0);
		grid.add(insertBtn, 2, 1);
		grid.add(deleteBtn, 2, 2);
		grid.add(updateBtn, 2, 3);

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("ERROR");
		alert.setContentText("Could not find classroom! (check that your information is correct)");

		Alert done = new Alert(AlertType.CONFIRMATION);
		done.setTitle("Done");
		done.setHeaderText("Success!");
		done.setContentText("Task Successful");

		searchBtn.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Room Number");
			dialog.setContentText("Please enter a Room Number");
			dialog.setHeaderText("Room Number Please");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				if (college.crb.getByRoomNumber(idNumber) != null && college.crb.getByRoomNumber(idNumber).getRoomNumber() != null) {
					roomNumberField.setText(college.crb.getByRoomNumber(idNumber).getRoomNumber());
					capacityField.setText(String.valueOf(college.crb.getByRoomNumber(idNumber).getCapacity()));
					projectorField.setText(String.valueOf(college.crb.getByRoomNumber(idNumber).isProjector()));
					buildingField.setText(String.valueOf(college.crb.getByRoomNumber(idNumber).getRoomBuilding().toString()));
					
				} else {
					alert.showAndWait();
				}
			});
		});

		insertBtn.setOnAction(e -> {
			Classroom s;
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Import Classroom");
			dialog.setContentText("Please enter the Room Number of the classroom");
			dialog.setHeaderText("Import Classroom");
			Optional<String> roomNum = dialog.showAndWait();
			roomNum.ifPresent(name -> System.out.println("Title: " + name));

			TextInputDialog dialog2 = new TextInputDialog();
			dialog2.setTitle("Import Classroom");
			dialog2.setContentText("Please enter the Capacity of the classroom");
			dialog2.setHeaderText("Import Classroom");
			Optional<String> cap = dialog2.showAndWait();
			cap.ifPresent(name -> System.out.println("isbn: " + name));

			List<String> enumBuilding = Stream.of(RoomBuilding.values()).map(Enum::name).collect(Collectors.toList());
			
			
			ChoiceDialog<String> dialog3 = new ChoiceDialog<>("Riverhead", enumBuilding);
			dialog3.setTitle("Import Classroom");
			dialog3.setHeaderText("Please select the Building of this Classroom");
			dialog3.setContentText("Classroom Import");
			Optional<String> building = dialog3.showAndWait();
			building.ifPresent(name -> System.out.println("Building: " + name));
			RoomBuilding m = RoomBuilding.valueOf(building.get());

			List<String> choices = new ArrayList<>();
			choices.add("true");
			choices.add("false");
			ChoiceDialog<String> dialog4 = new ChoiceDialog<>("true", choices);
			dialog4.setTitle("Import Classroom");
			dialog4.setHeaderText("Does this classroom have a projector?");
			dialog4.setContentText("Classroom Import");
			Optional<String> proj = dialog4.showAndWait();
			proj.ifPresent(name -> System.out.println("Building: " + name));
			
			s = new Classroom(m,roomNum.get(),Boolean.parseBoolean(proj.get()),Integer.parseInt(cap.get()));
			college.crb.insert(s);

			done.showAndWait();
		});
		deleteBtn.setOnAction(e -> {

			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Name");
			dialog.setContentText("Please enter a room number to delete");
			dialog.setHeaderText("Room Delete");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				if (college.crb.getByRoomNumber(idNumber) != null && college.crb.getByRoomNumber(idNumber).getRoomNumber() != null){
					college.crb.deleteByRoomNumber(idNumber);
					done.showAndWait();
				} else {
					alert.showAndWait();
				}
			});

		});

		updateBtn.setOnAction(e -> {
			if (roomNumberField.getText() != "") {
				String id = roomNumberField.getText();
				Classroom s = college.crb.getByRoomNumber(id);
				s.setRoomBuilding((RoomBuilding.valueOf(buildingField.getText())));
				s.setRoomNumber(id);
				s.setCapacity(Integer.parseInt(capacityField.getText()));
				s.setProjector(Boolean.parseBoolean(projectorField.getText()));
			}
		});
	}

	public GridPane getGrid() {
		return grid;
	}
}
