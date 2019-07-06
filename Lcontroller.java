package application;

import java.net.URL;


import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.sql.*;

public class Lcontroller implements Initializable,ControlledStage {
	private static String num;
	private int loginflag=0;//0 is a patient
	private String password;
	@FXML
	private TextField numinput;
	@FXML
	private PasswordField passwordin;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Label label;
	@FXML
	private Button loginbutton1;
	@FXML
	private Button loginbutton2;
	@FXML
	private Button exitbutton;
	@FXML
	private Button DBbutton;
	StageController myController;
    public void setStageController(StageController stageController) {
		this.myController=stageController;
	}
    public void initialize(URL url, ResourceBundle rb) {
    	this.toString();
    } 
    @FXML
    private void exitAction(ActionEvent event) {
    	Stage stage=(Stage)anchorPane.getScene().getWindow();
    	stage.close();
    	DocController.timerp.cancel();
    }
    @FXML
    private void ploginAction(ActionEvent event) {
    	loginflag=0;
    	num=numinput.getText();
    	System.out.println(numinput.getText());
    	password=passwordin.getText();
    	System.out.println(passwordin);
    	judge("BRBH","DLKL","dbo.T_BRXX");
    }
    @FXML
    private void dloginAction(ActionEvent event) {
    	loginflag=1;
    	num=numinput.getText();
    	System.out.println(numinput.getText());
    	password=passwordin.getText();
    	System.out.println(passwordin);
    	judge("YSBH","DLKL","dbo.T_KSYS");
    }
    @FXML
    private void DBAction(ActionEvent event) {
    	gotonewDB();
    }
    private void gotonewDB() {
    	myController.setStage(Main.dbviewid, Main.loginviewid);
	}
    private void gotoGuahao() {
    	myController.setStage(Main.guahaoviewid,Main.loginviewid);
    }
    private void gotoDoctor() {
    	myController.setStage(Main.yishengviewid,Main.loginviewid);
    }
    private void judge(String bianhao, String mima, String table_name) {
    	Statement statement = null;
    	String queryString=null;// ˝æ›ø‚≤È—Ø”Ôæ‰
    	ResultSet resultSet=null;
    	String insertString=null;
		Connection dbConn = null;
        try{
            Class.forName(Main.driverName);//º”‘ÿ«˝∂Ø≥Ã–Ú
            dbConn = DriverManager.getConnection(Main.dbURL, Main.userName, Main.userPwd);//Ω®¡¢ ˝æ›ø‚µƒ¡¥Ω”
            label.setText("¡¨Ω” ˝æ›ø‚≥…π¶");
        }catch (Exception e){
        	e.printStackTrace();
        	label.setText("¡¨Ω” ˝æ›ø‚ ß∞‹");
        }
        try {//÷¥––œ‡”¶µƒSQL”Ôæ‰
    		statement=dbConn.createStatement();//¥¥Ω®”Ôæ‰
    		queryString="select "+bianhao+","+mima+" from "+table_name;//SQL”Ôæ‰
    		resultSet=statement.executeQuery(queryString);//÷¥––”Ôæ‰
    		while(resultSet.next()) {
    			String brbh=resultSet.getString(bianhao).trim();
    			String dlkl=resultSet.getString(mima).trim();
    			if(brbh.equals(num)) {
    				if(dlkl.equals(password)) {
    					label.setText("µ«¬º≥…π¶£¨«Î…‘µ»£°");
    					insertString="update "+table_name+" set DLRQ=getdate() where "+bianhao+"='"+brbh+"'";
    					PreparedStatement pStatement=dbConn.prepareStatement(insertString);
    					pStatement.executeUpdate();
    					if(loginflag==1) {
    						gotoDoctor();
    					}
    					else gotoGuahao();
    				}
    				else {
    					label.setText("«Î÷ÿ–¬ ‰»Î");
    				}
    			}
    			else {
    				label.setText("«Î÷ÿ–¬ ‰»Î");
    			}
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Main.closedb(resultSet, statement, dbConn);//πÿ±’¡¥Ω”
    }
    public static String getnum() {
		return num;
	}
}