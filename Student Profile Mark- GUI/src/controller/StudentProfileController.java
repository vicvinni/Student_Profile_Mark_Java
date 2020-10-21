package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import model.Course;
import model.Module;
import model.StudentProfile;

import view.InputMarkTxtField;
import view.ModuleMarksTab;
import view.OverviewResultsTab;
import view.ProfileTab;
import view.StudentMenuBar;
import view.StudentProfileRootPane;

public class StudentProfileController {

	//Fields that will be used throughout the class
	private ProfileTab pt;
	private ModuleMarksTab mmt;
	private OverviewResultsTab ort;
	private StudentProfile modelProfile;
	private Module modelModule;
	private InputMarkTxtField markTxtField;
	private StudentProfileRootPane view;
	private StudentMenuBar smb;
	private StudentProfileRootPane sprp;
	private Collection<Module> collection;
	private ArrayList<Module> list  = new ArrayList<>();
	//
	public StudentProfileController( StudentProfile modelProfile, StudentProfileRootPane view ) throws IOException {
		this.modelProfile = modelProfile;
		this.view = view;

		pt = view.getProfileTab();
		mmt = view.getModuleMarksTab();
		ort = view.getOverviewResultsTab();
		smb = view.getStudentMenuBar();
		this.attachEventHandlers();

		pt.populateComboBox(setupAndGetCourses());
	}

	private ComboBox<Course> cboCourses;

	public void populateComboBox(Course[] courses) {
		cboCourses.getItems().addAll(courses);
		cboCourses.getSelectionModel().select(0);
	}

	//attach event handlers
	private void attachEventHandlers() throws IOException{
		//menuBar Buttons
		//load
		smb.addSaveHandler(new saveHandler());
		smb.addLoadHandler(new LoadMenuHandler());

		//exitButton
		smb.addExitHandler(e -> System.exit(0));
		/*about button */
		smb.addAboutHandler( e -> this.alertDialogBuilder("About", null,"Student Mark Submission Tool v2.2 \n I.T edition"));
		smb.addCreatorHandler( e -> this.alertDialogBuilder("Author", null, "Author: P15232864 \n contact details- P15232864@my365.dmu.ac.uk"));

		//change theme buttons
		smb.addGoldTheme(new goldThemeHandler());
		smb.addBlueTheme(new blueThemeHandler());
		smb.addSmallSize(new SmallSizeHandler());
		smb.addLargeSize(new LargeSizeHandler());
		smb.addDefaultSize(new defaultSizeHandler());
		smb.addDefaultTheme(new defaultThemeHandler());

		//create profile pane buttons
		pt.addCreateProfileListener(new createProfileHandler());
		pt.addPTClearListener(new PTclearHandler());
		pt.createBind(pt.isAnyFieldEmpty());

		//module marks buttons
		mmt.addSubmitListener(new SubmitHandler());
		mmt.addClearListener(new clearHandler());

		//overview Buttons
		ort.addStartAgainListener(new startAgainHandler());
		ort.addSaveOverviewListener(new saveOverviewHandler());

	}

	private Course[] setupAndGetCourses() {
		Module ctec2121 = new Module("CTEC2121", "- Organisations, Project Management and Research", true);
		Module ctec2122 = new Module("CTEC2122", "- Forensics and Security", false);
		Module ctec2602 = new Module("CTEC2602", "- OO Software Design and Development", false);
		Module ctec2701 = new Module("CTEC2701", "- Multi-tier Web Applications", true);
		Module ctec2901 = new Module("CTEC2901", "- Data Structures and Algorithms", false);
		Module lawg2003 = new Module("LAWG2003", "- Issues in Criminal Justice", false);
		Module ctec2903 = new Module("CTEC2903", "- System Defence Strategies", true);


		Course compSci = new Course("Computer Science");
		compSci.addModule(ctec2121);
		compSci.addModule(ctec2602);
		compSci.addModule(ctec2701);
		compSci.addModule(ctec2901);

		Course softEng = new Course("Software Engineering");
		softEng.addModule(ctec2121);
		softEng.addModule(ctec2602);
		softEng.addModule(ctec2701);
		softEng.addModule(ctec2901);

		Course compSecu = new Course("Computer Security");
		compSecu.addModule(ctec2121);
		compSecu.addModule(ctec2122);
		compSecu.addModule(ctec2701);
		compSecu.addModule(ctec2903);

		Course forenComp = new Course("Forensic Computing");
		forenComp.addModule(ctec2121);
		forenComp.addModule(ctec2122);
		forenComp.addModule(ctec2701);
		forenComp.addModule(lawg2003);

		Course[] courses = new Course[5];
		//		courses [0] = defaultCourse;
		courses[0] = compSci;
		courses[1] = softEng;
		courses[2] = compSecu;
		courses[3] = forenComp;

		return courses;
	}

	//		//about button in the menu
	public class aboutHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("Header");
			alert.setContentText("Content");
			alert.showAndWait();
		}
	}
	public class goldThemeHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
			view.setStyle("-fx-background-color: #FFD700"); //Gold colour

		}
	}

	public class blueThemeHandler implements EventHandler<ActionEvent>{

		public void handle(ActionEvent e) {
			view.setStyle("-fx-background-color: #0080ff");
		}
	}

	public class defaultThemeHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
			view.setStyle("-fx-background-color: #d3d3d3; -fx-font-size: 12");
		}
	}

	public class defaultSizeHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
			view.setStyle("-fx-background-color: #d3d3d3; -fx-font-size: 12");
		}
	}


	public class SmallSizeHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
			view.setStyle("-fx-font-size: 10"); //Gold colour
		}
	}

	public class LargeSizeHandler implements EventHandler<ActionEvent>{

		public void handle(ActionEvent e) {
			view.setStyle("-fx-font-size: 18");
			view.setMinHeight(750);
			view.setMinWidth(750);

		}
	}

	//save button in the menu bar


	private class saveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Student.dat"));) {

				modelProfile.setCourse(pt.getSelectedCourse());
				modelProfile.setpNumber(pt.getPNumberInput());
				modelProfile.setStudentName(pt.getFullName());
				modelProfile.setEmail(pt.getEmailInput());
				modelProfile.setDate(pt.getDateInput());

				if(!list.isEmpty()){
					list.removeAll(collection);
				}
				collection = modelProfile.getCourse().getModulesOnCourse();
				list.addAll(collection);

//				int i= 0;
//				for(Module m : list){
//					mmt.getarray().get(i).setLabel(m.getModuleCode()+ ""+ m.getModuleName());
//
//					int cwk= Integer.parseInt(mmt.getarray().get(i).getCwkField().getText());
//					list.get(i).setCwkMark(cwk);
//
//					int exam= Integer.parseInt(mmt.getarray().get(i).getExamField().getText());
//					list.get(i).setExamMark(exam);
//
//
//				}
//
//				i++;

				oos.writeObject(modelProfile);

				System.out.println(modelProfile);

				oos.flush();
				oos.close();

				System.out.println("Save Successfull");

				view.fadeAnimation();
				alertDialogBuilder("Information Dialog", "Save success", "Student Profile saved to StudentProfile.dat");
			}
			catch (IOException ioExcep){
				System.out.println("Error saving");
				//Print the complete error message
				ioExcep.printStackTrace();
			}
		}
	}

	//LOAD HANDLER
	private class LoadMenuHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			//load in the data model
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Student.dat"));) {

				modelProfile = (StudentProfile) ois.readObject(); //reads the model object back from a file

				pt.setCourse(modelProfile.getCourse());
				pt.setPNumber(modelProfile.getpNumber());
				pt.setFirstName(modelProfile.getStudentName().getFirstName());
				pt.setSurname(modelProfile.getStudentName().getFamilyName());
				pt.setEmail(modelProfile.getEmail());
				pt.setDate(modelProfile.getDate());

				if(!list.isEmpty()){
					list.removeAll(collection);
				}
				collection = modelProfile.getCourse().getModulesOnCourse();
				list.addAll(collection);

//				int i = 0;
//
//				for (Module m: list) {
//					mmt.getarray().get(i).setLabel(m.getModuleCode()+" "+m.getModuleName());
//					if(m.isCwkOnly()) {
//						mmt.getarray().get(i).setCWK(Integer.toString(modelProfile.getCourse().getModuleByCode(m.getModuleCode()).getCwkMark()));
//						mmt.getarray().get(i).getExamField().setVisible(false);
//						System.out.println(modelProfile.getCourse().getModuleByCode(m.getModuleCode()).getCwkMark());
//					}
//					else {
//						mmt.getarray().get(i).setCWK(Integer.toString(m.getCwkMark()));
//						mmt.getarray().get(i).setExam(Integer.toString(m.getExamMark()));
//						mmt.getarray().get(i).getExamField().setVisible(true);
//					}
//					System.out.println(modelProfile.getCourse().getModuleByCode(m.getModuleCode()).getCwkMark());
//
//					i++;
//				}
					ois.close();

				alertDialogBuilder("Information Dialog", "Load success", "StudentProfile loaded from Student.dat");
				view.fadeAnimation(); //invokes an animation in the view to give the user visual feedback
				System.out.println("Load Successfull");
			}
			catch (IOException ioExcep){
				System.out.println("Error loading");
			}
			catch (ClassNotFoundException c) {
				System.out.println("Class Not found");
			}
		}
	}

	private void alertDialogBuilder(String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}


	//create profile button in the profile tab
	public class createProfileHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			//validation
			if(!pt.getPNumberInput().matches("(^[P[p]][0-9]+[0-9_xX])$")) {
				alertDialogBuilder(AlertType.ERROR, "Error", "Incorrect PNumber Entered", "Please make sure the Correct value is entered \n the input should be in the format p12345678 Or P1234567x");
			}
			else if(!pt.getFirstNameInput().matches("[a-zA-Z]+") || (!pt.getSurnameInput().matches("[a-zA-Z]+"))){
				alertDialogBuilder(AlertType.ERROR, "Error", "Invalid Characters found in the Name TextField", "Please make sureonly valid characters are entered");
			}
			else if (!pt.getFirstNameInput().matches("[a-zA-Z]+") || (!pt.getSurnameInput().matches("[a-zA-Z]+"))){
				alertDialogBuilder(AlertType.ERROR, "Error", "Invalid Characters found in the Name TextField", "Please make sureonly valid characters are entered");
			}
			else if (!(pt.getEmailInput().contains("@") && (pt.getEmailInput().contains(".")))){
					alertDialogBuilder(AlertType.ERROR, "Error", "Invalid Characters found in the Name TextField", "Please make sureonly valid characters are entered");
			}
			else
			{
				modelProfile.setpNumber(pt.getPNumberInput());
				modelProfile.setStudentName(pt.getFullName());
				modelProfile.setCourse(pt.getSelectedCourse());
				modelProfile.setEmail(pt.getEmailInput());
				modelProfile.setDate(pt.getDateInput());

				if(!list.isEmpty()){
					list.removeAll(collection);
				}

				collection = modelProfile.getCourse().getModulesOnCourse();
				list.addAll(collection);

				int i= 0;
				for (Module m: list) {
					mmt.getarray().get(i).setLabel(m.getModuleCode()+ ""+ m.getModuleName());
					mmt.getarray().get(i).getExamField().setVisible(true);

					if (m.isCwkOnly()) {
						mmt.getarray().get(i).getExamField().setVisible(false);
					}
					i++;
				}
				view.changeTab(1);
			}
		}

		private void alertDialogBuilder(AlertType error, String string, String string2, String string3) {
			// TODO Auto-generated method stub

		}
	}
	//the clear button in the Profile Tab
	public class PTclearHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			pt.clear();
			view.fadeAnimation();
		}
	}

	//the clear button in the module marks tab
	public class clearHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
			view.fadeAnimation();
			for (InputMarkTxtField marks: mmt.getarray()) {
				marks.clear();
			}
		}
	}

	//submit button in the Module Marks Pane
	public class SubmitHandler implements EventHandler<ActionEvent>{


		@Override
		public void handle(ActionEvent event) {

			int i = 0;
			for(Module module: list ){
				if ((mmt.getarray().get(i).getCwk().matches("^[1-9][0-9]?$|^100$")))
				{
					Integer result = Integer.valueOf(mmt.getarray().get(i).getCwk());
					module.setCwkMark(result);
				}
				if ((mmt.getarray().get(i).getExam().matches("^[1-9][0-9]?$|^100$"))) {
					Integer res = Integer.valueOf(mmt.getarray().get(i).getExam());
					module.setExamMark(res);
				}
				

					ort.setResults("Name:    " + modelProfile.getStudentName().getFullName()+
					"\r\n" + "PNumber:    " + modelProfile.getpNumber()  +
					"\r\n" + "Email:    " + modelProfile.getEmail()+
					"\r\n" + "Course:    " + modelProfile.getCourse() +
					"\r\n" + "Date:    " + modelProfile.getDate()+
					"\r\n" + "**********************************" +
					"\r\n" + "Average "+
					"\r\n" + "Credits Gained: " + modelProfile.getCourse().creditsPassed()+
					"\r\n" + "Year Average: " + modelProfile.getCourse().yearAverageMark());
					view.changeTab(2);
				}
			}
		}
	

	public class startAgainHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			view.changeTab(0);
			pt.clear();
			//			mmt.clear();
			view.fadeAnimation();
		}
	}
	public class saveOverviewHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("SaveOverview");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you would like to save?");

			alert.showAndWait();

			PrintWriter myWriter;
			try {
				myWriter = new PrintWriter("Overview.txt");
				myWriter.println("Name: " + modelProfile.getStudentName().getFullName()+
						"\r\n" + "PNumber " + modelProfile.getpNumber()  +
						"\r\n" + "Email: " + modelProfile.getEmail()+
						"\r\n" + "Course: " + modelProfile.getCourse() +
						"\r\n" + "Date: " + modelProfile.getDate()+
						"\r\n" + "**********************************" +
						"\r\n" + "Average "+
						"\r\n" + "Credits Gained: " + modelProfile.getCourse().creditsPassed()+
						"\r\n" + "Year Average: " + modelProfile.getCourse().yearAverageMark());
				myWriter.flush();
				myWriter.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

//			if (modelProfile.getCourse().yearAverageMark() >= 70) {
//	            ort.results.setStyle("-fx-text-fill: green; -fx-font-size: 16;");
//	        } else if (modelProfile.getCourse().yearAverageMark() >= 60) {
//	        	ort.results.setStyle("-fx-text-fill: orange; -fx-font-size: 16;");
//	        }else if (modelProfile.getCourse().yearAverageMark() <=40) {
//		        	ort.results.setStyle("-fx-text-fill: red; -fx-font-size: 16;");
//	        }
		}
	}
}

