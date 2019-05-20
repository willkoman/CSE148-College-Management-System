package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClassroomBag;
import model.College;
import model.CourseBag;
import model.PersonBag;
import model.TextbookBag;
import utils.Exporter;
import utils.Setup;
import view.BottomPane;
import view.ClassroomPane;
import view.CoursePane;
import view.FacultyPane;
import view.StudentPane;
import view.TextbookPane;
import view.TopPane;

public class Main extends Application {
	static Setup setup = new Setup();
	public static ClassroomBag crb;
	public static TextbookBag tb;
	public static CourseBag cb;
	public static PersonBag pb;
	public static Exporter ex;
	static College college;

	public static void main(String[] args) throws Exception {

		setup.init();
		crb = setup.importClassroomBag();
		tb = setup.importTextbookBag();
		cb = setup.importCourseBag();
		pb = setup.importPersonBag();

		ex = new Exporter(crb, cb, pb, tb);
		college = new College(crb, cb, pb, tb);

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		BottomPane bottomPane = new BottomPane();
		TopPane topPane = new TopPane();
		root.setTop(topPane.getBtnBox());
		root.setBottom(bottomPane.getBtnBox());
		Button studentBtn = bottomPane.getStudentBtn();
		Button textbookBtn = bottomPane.getTextbookBtn();
		Button facultyBtn = bottomPane.getFacultyBtn();
		Button courseBtn = bottomPane.getCourseBtn();
		Button classroomBtn = bottomPane.getClassroomBtn();

		Button saveBtn = topPane.getSaveBtn();
		Button loadBtn = topPane.getLoadBtn();
		Button exitBtn = topPane.getExitBtn();

		studentBtn.setOnAction(e -> {
			root.setCenter(new StudentPane(college).getGrid());
		});
		facultyBtn.setOnAction(e -> {
			root.setCenter(new FacultyPane(college).getGrid());
		});
		textbookBtn.setOnAction(e -> {
			root.setCenter(new TextbookPane(college).getGrid());
		});
		courseBtn.setOnAction(e -> {
			root.setCenter(new CoursePane(college).getGrid());
		});
		classroomBtn.setOnAction(e -> {
			root.setCenter(new ClassroomPane(college).getGrid());
		});

		saveBtn.setOnAction(e -> {
			try {
				ex.write();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		loadBtn.setOnAction(e -> {
			setup.importClassroomBag();
			setup.importTextbookBag();
			setup.importCourseBag();
			setup.importPersonBag();
		});
		exitBtn.setOnAction(e -> {
			try {
				ex.write();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
		});

		VBox centerBox = new VBox(20);
		root.setCenter(centerBox);

		Scene scene = new Scene(root, 800, 720);
		scene.getStylesheets().add("bootstrap3.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("College Management System");
		primaryStage.show();
		primaryStage.setOnHiding(event -> {

			try {
				ex.write();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}

}
