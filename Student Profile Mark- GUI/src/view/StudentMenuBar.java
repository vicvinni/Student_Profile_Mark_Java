package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;


public class StudentMenuBar extends MenuBar {

	//declared for access throughout class, as handlers are now attached via methods - see towards end of class
	private MenuItem loadItem, saveItem, exitItem, aboutItem, creatorItem , blueTheme, DefaultTheme, DefaultSize,  goldTheme, SmallSize, LargeSize;

	public StudentMenuBar() {

		//temp vars for menus and menu items within this MenuBar
		Menu menu;

		menu = new Menu("_File");

		loadItem = new MenuItem("_Load");
		loadItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+L"));
		menu.getItems().add(loadItem);

		saveItem = new MenuItem("_Save");
		saveItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+S"));
		menu.getItems().add(saveItem);

		menu.getItems().add( new SeparatorMenuItem());

		exitItem = new MenuItem("E_xit");
		exitItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+X"));
		menu.getItems().add(exitItem);

		this.getMenus().add(menu);

		menu = new Menu("_Change theme");

		blueTheme = new MenuItem("Blue");
		blueTheme.setAccelerator(KeyCombination.keyCombination("SHORTCUT+B"));
		menu.getItems().add(blueTheme);

		goldTheme = new MenuItem("Gold");
		goldTheme.setAccelerator(KeyCombination.keyCombination("SHORTCUT+G"));
		menu.getItems().add(goldTheme);

		menu.getItems().add( new SeparatorMenuItem());

		DefaultTheme = new MenuItem("Default");
		DefaultTheme.setAccelerator(KeyCombination.keyCombination("SHORTCUT+D"));
		menu.getItems().add(DefaultTheme);


		this.getMenus().add(menu);



		menu = new Menu("_Help");

		aboutItem = new MenuItem("_About");
		aboutItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+A"));
//		aboutItem.setOnAction(new aboutHandler());

		menu.getItems().add(aboutItem);

		creatorItem = new MenuItem("_Creator");
		creatorItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+C"));
//		creatorItem.setOnAction(new creatorHandler());
		menu.getItems().add(creatorItem);
		this.getMenus().add(menu);

	menu = new Menu("_Change size");

	SmallSize = new MenuItem("small");
//	SmallSize.setAccelerator(KeyCombination.keyCombination("SHORTCUT+"));
	menu.getItems().add(SmallSize);

	LargeSize = new MenuItem("large");
//	LargeSize.setAccelerator(KeyCombination.keyCombination("SHORTCUT+L"));
	menu.getItems().add(LargeSize);

	DefaultSize = new MenuItem("Default");
//	DefaultTheme.setAccelerator(KeyCombination.keyCombination("SHORTCUT+D"));
	menu.getItems().add(DefaultSize);




	this.getMenus().add(menu);
	}
	//these methods allow handlers to be externally attached to this view by the controller
	public void addLoadHandler(EventHandler<ActionEvent> handler) {
		loadItem.setOnAction(handler);
	}

	public void addSaveHandler(EventHandler<ActionEvent> handler) {
		saveItem.setOnAction(handler);
	}

	public void addExitHandler(EventHandler<ActionEvent> handler) {
		exitItem.setOnAction(handler);
	}

	public void addAboutHandler(EventHandler<ActionEvent> handler) {
		aboutItem.setOnAction(handler);
	}

	public void addCreatorHandler(EventHandler<ActionEvent> handler) {
		creatorItem.setOnAction(handler);
	}

	public void addGoldTheme(EventHandler<ActionEvent> handler) {
		goldTheme.setOnAction(handler);
	}

	public void addBlueTheme(EventHandler<ActionEvent> handler) {
		blueTheme.setOnAction(handler);
	}

	public void addDefaultTheme(EventHandler<ActionEvent> handler) {
		DefaultTheme.setOnAction(handler);
	}
	public void addDefaultSize(EventHandler<ActionEvent> handler) {
		DefaultSize.setOnAction(handler);
	}
	public void addSmallSize(EventHandler<ActionEvent> handler) {
		SmallSize.setOnAction(handler);
	}
	public void addLargeSize(EventHandler<ActionEvent> handler) {
		LargeSize.setOnAction(handler);
	}


}
