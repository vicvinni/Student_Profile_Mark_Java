package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Module;

public class ModuleMarksTab  extends VBox{

	private Label lblModule, lblCWKMark, lblEXAMLabel;
	private ArrayList<InputMarkTxtField> modules;
	private Button btnSubmitButton, btnClearButton;
	private int examMark;
	private int cwkMark;

	public  ModuleMarksTab() {

		this.setPadding(new Insets(80, 80, 80, 80));
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);

		lblModule = new Label("Modules: ");
		lblModule.setFont(Font.font(null, FontWeight.BOLD, 15));
		lblCWKMark = new Label("CourseWork Mark: ");
		lblCWKMark.setFont(Font.font(null, FontWeight.BOLD, 15));
		lblEXAMLabel = new Label("Exam Mark: ");
		lblEXAMLabel.setFont(Font.font(null, FontWeight.BOLD, 15));

		btnSubmitButton = new Button("Submit");
		btnClearButton = new Button("Clear");

		modules = new ArrayList<InputMarkTxtField>();

		modules.add(new InputMarkTxtField("Module 1: "));
		modules.add(new InputMarkTxtField("Module 2: "));
		modules.add(new InputMarkTxtField("Module 3: "));
		modules.add(new InputMarkTxtField("Module 4: "));

		HBox titles = new HBox();
		titles.setSpacing(15);

		titles.getChildren().addAll(lblCWKMark, lblEXAMLabel);

		BorderPane bp = new BorderPane();

		bp.setCenter(lblModule);
		bp.setRight(titles);


		HBox h1  = new HBox();
		HBox h2  = new HBox();
		HBox h3  = new HBox();
		HBox h4  = new HBox();

		h1.getChildren().add(modules.get(0));
		h2.getChildren().add(modules.get(1));
		h3.getChildren().add(modules.get(2));
		h4.getChildren().add(modules.get(3));

		//h1.HGap().
		this.getChildren().addAll(bp, modules.get(0), modules.get(1), modules.get(2), modules.get(3), btnSubmitButton, btnClearButton);

	}

	public ArrayList<InputMarkTxtField> getarray(){
		return modules;
	}


	public int getExamMark() {
		return examMark;
	}

	public void setExamMark(int examMark) {
		this.examMark = examMark;
	}

	public int getCwkMark() {
		return cwkMark;
	}

	public void setCwkMark(int cwkMark) {
		this.cwkMark = cwkMark;
	}

	//events
	public void addSubmitListener (EventHandler<ActionEvent> handler) {
		btnSubmitButton.setOnAction(handler);
	}

	public void clear() {
		modules.clear();
	}

	public void addClearListener (EventHandler<ActionEvent> handler) {
		btnClearButton.setOnAction(handler);

	}


}