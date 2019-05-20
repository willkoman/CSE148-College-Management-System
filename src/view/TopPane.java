package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TopPane {
	private HBox btnBox;
	private Button saveBtn;
	private Button loadBtn;
	private Button exitBtn;
	public TopPane() {
		btnBox = new HBox(30);
		saveBtn = new Button("SAVE");
		loadBtn = new Button("LOAD");
		exitBtn = new Button("EXIT");

		
		btnBox.getChildren().addAll(saveBtn, loadBtn, exitBtn);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.setPadding(new Insets(30));

	}
	public void setBtnBox(HBox btnBox) {
		this.btnBox = btnBox;
	}
	public void setSaveBtn(Button saveBtn) {
		this.saveBtn = saveBtn;
	}
	public void setLoadBtn(Button loadBtn) {
		this.loadBtn = loadBtn;
	}
	public void setExitBtn(Button exitBtn) {
		this.exitBtn = exitBtn;
	}
	public HBox getBtnBox() {
		return btnBox;
	}
	public Button getSaveBtn() {
		return saveBtn;
	}
	public Button getLoadBtn() {
		return loadBtn;
	}
	public Button getExitBtn() {
		return exitBtn;
	}
}
