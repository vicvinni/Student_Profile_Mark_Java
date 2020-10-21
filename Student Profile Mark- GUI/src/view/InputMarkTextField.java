package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class InputMarkTextField  extends HBox{
	private Label module ; 
	private TextField courseWField, examField; 
	
	//default constructor 
	public InputMarkTextField() {
		module=new Label("");
		module.setText(" bbbbbb ");
		courseWField = new TextField(); 
		courseWField.setText(" Text1");
		
		examField = new TextField(); 
		examField.setText(" Text2");
		this.setSpacing(68);
		this.getChildren().addAll(module,courseWField, examField);
		}

	//custom constructor 
	
	public InputMarkTextField(String name) { 
		module=new Label("");
		module.setText(name);
		
		courseWField= new TextField(); 
		courseWField.setPromptText("0");
		courseWField.setPrefWidth(75);
		
		examField= new TextField(); 
		examField.setPromptText("0");
		examField.setPrefWidth(75);
		
		this.setSpacing(50);
		this.getChildren().addAll(module, courseWField, examField); 
	
	}
	
	public String getLabel() {
		return module.getText();
	}
	public void setLabel(String label) { 
		module.setText(label);
	}
	public String getCourseWField() { 
		return courseWField.getText(); 
	}
	public void setCWK(int CWK) { 
		courseWField.setText(""+ courseWField); 
	}
	
	public String getExam() { 
		return examField.getText();
	}
	public void setExam(int exam) { 
		examField.setText("" + examField);
	}
	
	public TextField getExamField() { 
		return examField; 
	}
	public void clear() { 
		examField.clear();
		courseWField.clear();
	}
	
	
}
