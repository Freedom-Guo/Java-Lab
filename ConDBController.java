package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ConDBController implements Initializable,ControlledStage {
	StageController myController;
	@FXML
	private TextField DBuser;
	@FXML
	private PasswordField DBpass;
	@FXML
	private TextField DBname;
	@FXML
	private Button yButton;
	@FXML
	private Button nButton;
	public void setStageController(StageController stageController) {
		this.myController=stageController;

	}
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.toString();
		DBuser.setText(Main.userName);
		DBpass.setText(Main.userPwd);
		DBname.setText(Main.dbName);
	}
	@FXML
	private void modifyAction(Event event) {
		String username,userpwd,dbname;
		username=DBuser.getText();
		userpwd=DBpass.getText();
		dbname=DBname.getText();
		if(username!=null)
			Main.userName=username;
		if(userpwd!=null)Main.userPwd=userpwd;
		if(dbname!=null)Main.dbName=dbname;
		Statement statement = null;
    	ResultSet resultSet=null;
		Connection dbConn = null;
        try
        {
            Class.forName(Main.driverName);
            dbConn = DriverManager.getConnection(Main.dbURL, Main.userName, Main.userPwd);
            JOptionPane.showMessageDialog(null, " ˝æ›ø‚–ﬁ∏ƒ≥…π¶");
        }catch (Exception e)
        {
        	e.printStackTrace();
        	JOptionPane.showMessageDialog(null, " ˝æ›ø‚–ﬁ∏ƒ ß∞‹");
        	Main.userName="sa";
        	Main.userPwd="hao123qq999";
        	Main.dbName="mydb";
        }
        Main.closedb(resultSet, statement, dbConn);
	}
	@FXML
	private void modPress(KeyEvent event) {
		if(this.yButton.isFocused()) {
			if(event.getCode()==KeyCode.ENTER) {
				modifyAction(new Event(null));
			}
		}
	}
	@FXML
	private void returnAction(Event event) {
		gotoGuahao();
	}
	@FXML
	private void retPress(KeyEvent event) {
		if(this.nButton.isFocused()) {
			if(event.getCode()==KeyCode.ENTER) {
				gotoGuahao();
			}
		}
	}
    private void gotoGuahao() {
    	myController.setStage(Main.loginviewid,Main.dbviewid);
    }
    
}
