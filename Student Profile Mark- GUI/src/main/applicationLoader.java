package main;

import java.io.IOException;

import controller.StudentProfileController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.StudentProfile;
import view.StudentProfileRootPane;

public class applicationLoader extends Application{

	private StudentProfileRootPane view; 
	public void init() throws IOException { 
		
		StudentProfile model = new StudentProfile(); 
		view = new StudentProfileRootPane(); 
		new StudentProfileController(model, view); 
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setMinHeight(510);
		stage.setMinWidth(520);
		stage.setTitle("Student Profile");
		stage.setScene(new Scene(view));
		stage.show();	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
