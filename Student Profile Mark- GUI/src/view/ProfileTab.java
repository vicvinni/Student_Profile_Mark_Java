package view;

import java.time.LocalDate;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Course;
import model.Name;

public class ProfileTab  extends GridPane {

	private ComboBox <Course> courseBox ;
	public TextField pNumber;
	public TextField email;
	private TextField firstName;
	public TextField surname;
	private Button btnCreateProfile, btnClear;
	private DatePicker date;

	public ProfileTab() {

		this.setPadding(new Insets(80, 80, 80, 80));
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);
//		 HBox.setHgrow(field, Priority.ALWAYS);
		Label lblTitle = new Label("Select Course: ");
		lblTitle.setFont(Font.font(null, FontWeight.BOLD, 15));
		Label lblPNumber = new Label("Enter P Number: ");
		lblPNumber.setFont(Font.font(null, FontWeight.BOLD, 15));
		Label lblFirstName = new Label("Enter First Name: ");
		lblFirstName.setFont(Font.font(null, FontWeight.BOLD, 15));
		Label lblSurname = new Label("Enter Surname: ");
		lblSurname.setFont(Font.font(null, FontWeight.BOLD, 15));
		Label lblEmail = new Label("Enter Email: ");
		lblEmail.setFont(Font.font(null, FontWeight.BOLD, 15));
		Label lblDate = new Label("Input Date: ");
		lblDate.setFont(Font.font(null, FontWeight.BOLD, 15));

		//setup comboBox
		courseBox = new ComboBox<Course>();

		//setup textFields
		setFirstName(new TextField());
		getFirstName().setPromptText("FirstName");

		surname = new TextField();
		surname.setPromptText("Surname");
		pNumber = new TextField();
		pNumber.setPromptText("P98765432");
		email = new TextField();
		email.setPromptText("xyz@email.com");
		date = new DatePicker();
		date.setPromptText("Date");
		date.setValue(LocalDate.now());
		date.setEditable(false);

		//initilise submit button
		btnCreateProfile = new Button("Create Profile!");

		btnClear = new Button("Start Again...");
		//		btnClear.setOnAction(new ClearButton);

		//add controls
		this.add(courseBox, 1,0);
		this.add(lblTitle, 0, 0);

		this.add(lblPNumber, 0 , 1) ;
		this.add(pNumber, 1,1);

		this.add(lblFirstName, 0 , 2) ;
		this.add(getFirstName(), 1,2);

		this.add(lblSurname, 0 , 3) ;
		this.add(surname, 1,3);

		this.add(lblEmail, 0 , 4) ;
		this.add(email, 1,4);

		this.add(lblDate, 0 , 5) ;
		this.add(date, 1,5);

		this.add(new HBox(), 0, 10 );
		this.add(btnCreateProfile, 2, 7);

		this.add(new HBox(), 0, 10 );
		this.add(btnClear, 1, 7);
	}
	public void populateComboBox(Course[] courses) {
		courseBox.getItems().addAll(courses);
		courseBox.getSelectionModel().select(0);
	}
	//the get methods to retrieve the data entered
	public void setPNumber(String Text) {
		pNumber.setText(Text);
	}

	public String getPNumberInput() {

		return pNumber.getText() ;
	}

	public void setFirstName(String Text) {
		firstName.setText(Text);
	}

	public String getFirstNameInput() {

		return getFirstName().getText() ;
	}

	public void setSurname(String Text) {
		surname.setText(Text);
	}
	public String getSurnameInput() {

		return surname.getText() ;
	}

	public void setEmail(String Text) {
		email.setText(Text);
	}

	public String getEmailInput() {

		return email.getText() ;
	}
	public void setDate(LocalDate local) {
		date.setValue(local);
	}

	public LocalDate getDateInput() {

		return date.getValue() ;
	}
	public Name getFullName() {
		return new Name (getFirstName().getText(), surname.getText());
	}

	public void setCourse(Course course) {
		courseBox.setValue(getSelectedCourse());
	}
	public Course getSelectedCourse() {
		return courseBox.getSelectionModel().getSelectedItem();
	}

public TextField getFirstName() {
		return firstName;
	}
	public void setFirstName(TextField firstName) {
		this.firstName = firstName;
	}
	//	public void addBtnDisableBind(BooleanBinding)
	//event handler
	public void addCreateProfileListener (EventHandler<ActionEvent> handler) {
		btnCreateProfile.setOnAction(handler);

	}

	public BooleanBinding isAnyFieldEmpty() {
		return pNumber.textProperty().isEmpty().or(getFirstName().textProperty().isEmpty().or(surname.textProperty().isEmpty().or(email.textProperty().isEmpty())));
		}

	public void createBind (BooleanBinding pty) {
		btnCreateProfile.disableProperty().bind(pty);
	}

	//clear Button
	public void clear() {
		pNumber.setText("");
		getFirstName().setText("");
		surname.setText("");
		email.setText("");
		date.getValue();
	}

	public void addPTClearListener (EventHandler<ActionEvent> handler) {
		btnClear.setOnAction(handler);

	}
}