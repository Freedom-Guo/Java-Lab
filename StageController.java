package application;
import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class StageController{
	//map store all stages
	private HashMap<String, Stage>stages=new HashMap<String,Stage>();
	//put stages into the map
	public void addStage(String name,Stage stage) {
		stages.put(name,stage);
	}
	
	public Stage getStage(String name) {
		return stages.get(name);
	}
	
	public void setPrimaryStage(String primaryStageName,Stage primaryStage) {
		this.addStage(primaryStageName, primaryStage);
	}
	
	public boolean loadStage(String name,String resources,StageStyle...styles) {
		try {
			
			FXMLLoader loader= new FXMLLoader(getClass().getResource(resources));
			Parent tempPane=(Parent)loader.load();
			//Stage
			ControlledStage controlledStage=(ControlledStage)loader.getController();
			controlledStage.setStageController(this);
			Scene tempScene = new Scene(tempPane);
			Stage tempStage=new Stage();
			tempStage.setScene(tempScene);
			
			for(StageStyle style:styles) tempStage.initStyle(style);
			
			this.addStage(name, tempStage);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public boolean setStage(String name) {
		this.getStage(name).show();
        this.getStage(name).setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub77
				if(name==Main.guahaoviewid) {
					System.out.println("º‡Ã˝µΩπ“∫≈¥∞ø⁄πÿ±’");
				}
				else if(name==Main.loginviewid) {
					System.out.println("º‡Ã˝µΩ÷˜¥∞ø⁄πÿ±’");
				}
				else if(name==Main.yishengviewid) {
					DocController.timerp.cancel();
				}
				DocController.timerp.cancel();
			}
		});
		return true;
	}
	public boolean setStage(String show,String close) {
		getStage(close).close();
		setStage(show);
		return true;
	}
	//delete Stage
	public boolean unloadStage(String name) {
		if(stages.remove(name)==null) {
			System.out.println("¥∞ø⁄≤ª¥Ê‘⁄£¨«ÎºÏ≤È√˚≥∆");
			return false;
		}
		else {
			System.out.println("¥∞ø⁄“∆≥˝≥…π¶");
			return true;
		}
	}
}
