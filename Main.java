package application;
	
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import javax.security.auth.login.LoginContext;

import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.stage.StageStyle;


public class Main extends Application {
    public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String dbName="mydb";
    public static String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName="+dbName;
    public static String userName = "sa";
    public static String userPwd = "hao123qq999";
    public static String num=null;
    public static String password=null;
	public static String loginviewid="Login";
	public static String loginviewres="plogin.fxml";
	public static String guahaoviewid="Guahao";
	public static String guahaoviewres="guahao.fxml";
	public static String yishengviewid="Doctor";
	public static String yishengviewers="Doctor.fxml";
	public static String dbviewid="db";
	public static String dbviewer="DBcon.fxml";
	private StageController stageController;
	@Override
	public void start(Stage primaryStage) {
		try {
			stageController=new StageController();
			stageController.setPrimaryStage("primaryStage", primaryStage);
			stageController.loadStage(loginviewid, loginviewres);
			stageController.loadStage(guahaoviewid,guahaoviewres);
			stageController.loadStage(yishengviewid, yishengviewers);
			stageController.loadStage(dbviewid, dbviewer, StageStyle.UNDECORATED);
			stageController.setStage(loginviewid);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public static void closedb(ResultSet resultSet,Statement statement,Connection dbConn) {
        if(resultSet != null){   // πÿ±’º«¬ººØ   
            try{   
                resultSet.close() ;   
            }catch(SQLException e){   
                e.printStackTrace() ;   
            }   
         }   
         if(statement != null){   // πÿ±’…˘√˜   
            try{   
                statement.close() ;   
            }catch(SQLException e){   
                e.printStackTrace() ;   
            }   
         }
         if(dbConn != null){  // πÿ±’¡¨Ω”∂‘œÛ   
             try{   
                dbConn.close() ;   
             }catch(SQLException e){   
                e.printStackTrace() ;   
             }   
         }
	}
}
