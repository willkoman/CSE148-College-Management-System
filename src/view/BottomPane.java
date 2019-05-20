package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class BottomPane {

	private HBox btnBox;
	private Button studentBtn;
	private Button facultyBtn;
	private Button classroomBtn;
	private Button courseBtn;
	private Button textbookBtn;

	public BottomPane() {
		btnBox = new HBox(30);
		studentBtn = new Button("STUDENT");
		facultyBtn = new Button("FACULTY");
		textbookBtn = new Button("TEXTBOOK");
		classroomBtn = new Button("CLASSROOM");
		courseBtn = new Button("COURSE");
		setBtnListener();
		btnBox.getChildren().addAll(studentBtn, facultyBtn, textbookBtn, classroomBtn, courseBtn);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.setPadding(new Insets(30));

	}

	private void setBtnListener() {
		studentBtn.setOnAction(e -> {
			System.out.println("Student button is clicked!");
		});
		facultyBtn.setOnAction(e -> {
			System.out.println("faculty button is clicked!");
		});
		classroomBtn.setOnAction(e -> {
			System.out.println("Classroom button is clicked!");
		});
		courseBtn.setOnAction(e -> {
			System.out.println("course button is clicked!");
		});
		textbookBtn.setOnAction(e -> {
			System.out.println("textbook button is clicked!");
		});
	}

	public HBox getBtnBox() {
		return btnBox;
	}

	public Button getStudentBtn() {
		return studentBtn;
	}

	public void setStudentBtn(Button studentBtn) {
		this.studentBtn = studentBtn;
	}

	public Button getFacultyBtn() {
		return facultyBtn;
	}

	public void setFacultyBtn(Button facultyBtn) {
		this.facultyBtn = facultyBtn;
	}

	public Button getClassroomBtn() {
		return classroomBtn;
	}

	public void setClassroomBtn(Button classroomBtn) {
		this.classroomBtn = classroomBtn;
	}

	public Button getCourseBtn() {
		return courseBtn;
	}

	public void setCourseBtn(Button courseBtn) {
		this.courseBtn = courseBtn;
	}

	public Button getTextbookBtn() {
		return textbookBtn;
	}

	public void setTextbookBtn(Button textbookBtn) {
		this.textbookBtn = textbookBtn;
	}

}