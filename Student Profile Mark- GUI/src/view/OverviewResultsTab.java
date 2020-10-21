package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;


public class OverviewResultsTab extends GridPane {

	public TextArea results;
	private Button btnSaveOverview, btnStartAgain;

	public OverviewResultsTab() {
		this.setPadding(new Insets(80, 80, 80, 80));
		this.setVgap(15);
		this.setHgap(20);

		btnSaveOverview = new Button("Save Overview...");
		btnStartAgain = new Button("Start again. ");

		results = new TextArea("Overview will appear here....");

		results.setEditable(false);


		this.setPadding(new Insets(10,10,10,10));

		this.add(results, 1,4);
		this.add(btnSaveOverview, 2, 7);
		this.add(btnStartAgain, 2, 8);

	}
	public void setResults(String overview) {
		results.setText(overview);
	}

	public String getResults() {
		return results.getText();
	}


	public void addStartAgainListener (EventHandler<ActionEvent> handler) {
		btnStartAgain.setOnAction(handler);

	}
	public void addSaveOverviewListener (EventHandler<ActionEvent> handler) {
		btnSaveOverview.setOnAction(handler);

	}
}
