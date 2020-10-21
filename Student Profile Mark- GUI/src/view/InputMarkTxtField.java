package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class InputMarkTxtField  extends BorderPane {
	private Label module ;
	private TextField courseWField, examField;

	//default constructor
	public InputMarkTxtField() {
		module=new Label("");
		module.setText(" default text ");
		courseWField = new TextField();
		courseWField.setText(" Text1");

		examField = new TextField();
		examField.setText(" Text2");
		//this.setSpacing(78);
		//this.getChildren().addAll(module,courseWField, examField);

		HBox right = new HBox();
		right.getChildren().addAll(courseWField, examField);
		this.setCenter(module);
		this.setRight(right);
	}

	//custom constructor

	public InputMarkTxtField(String name) {
		module=new Label("");
		module.setText(name);

		courseWField= new TextField();
		courseWField.setPromptText("0");
		courseWField.setPrefWidth(75);

		examField= new TextField();
		examField.setPromptText("0");
		examField.setPrefWidth(75);

		HBox h = new HBox();
		h.getChildren().addAll(courseWField, examField);

		BorderPane bp = new BorderPane();
		HBox hbox = new HBox();

		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(courseWField, examField);

		bp.setCenter(module);

		this.setCenter(bp);
		this.setRight(hbox);

	}

	public String getLabel() {
		return module.getText();
	}

	public Label getLabelField() {
		return module;
	}
	public void setLabel(String label) {
		module.setText(label);
	}
	public String getCwk() {
		return courseWField.getText();
	}
	public void setCWK(String CWK) {
		courseWField.setText(CWK);
	}

	public TextField getCwkField() {
		return courseWField;
	}
	public String getExam() {
		return examField.getText();
	}
	public void setExam(String exam) {
		examField.setText(exam);
	}

	public TextField getExamField() {
		return examField;
		}

	public void clear() {
		//getCwkField().setPromptText("0");
//		getExamField().setPromptText("0");
		courseWField.clear();
		examField.clear();

	}


//	public static void clear() {
//		examField.clear();
//		courseWField.clear();
//	}
}