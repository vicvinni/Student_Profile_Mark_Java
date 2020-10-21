package view;

import javafx.animation.FadeTransition;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class StudentProfileRootPane  extends VBox{

	private ProfileTab pt;
	private ModuleMarksTab mmt;
	private OverviewResultsTab ort;
	private TabPane tabPane;
	private StudentMenuBar smb;

	public Tab t2;
	public Tab t3;



	public StudentProfileRootPane() {

		this.setStyle("-fx-background-color: #d3d3d3; -fx-font-size: 12");

		pt = new ProfileTab();
		mmt = new ModuleMarksTab();
		ort = new OverviewResultsTab();
		tabPane = new TabPane();
		smb = new StudentMenuBar();

		Tab t1 = new Tab ("Create Profile", pt);
		 t2 = new Tab ("Module Marks",mmt);
		 t3 = new Tab ("View Overview ", ort);

		tabPane.getTabs().addAll(t1, t2, t3);

		this.getChildren().addAll(smb, tabPane);

		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	}

	public ProfileTab getProfileTab () {
		return pt;
	}
	public ModuleMarksTab getModuleMarksTab() {
		return mmt;
	}
	public OverviewResultsTab getOverviewResultsTab() {
		return ort;
	}
	public StudentMenuBar getStudentMenuBar() {
		return smb;
	}

	public void changeTab(int index) {
		tabPane.getSelectionModel().select(index);
	}


	public void fadeAnimation() {
		FadeTransition ft = new FadeTransition(Duration.millis(100), this);
		ft.setFromValue(1.0);
		ft.setToValue(0.1);
		ft.setCycleCount(2);
		ft.setAutoReverse(true);
		ft.play();
	}

	public void setEnabled(int i, boolean b) {

	}


	}
