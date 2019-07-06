package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

//
import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DocController implements Initializable,ControlledStage{
	StageController myController;
	@FXML
	private Tab BRLB;
	@FXML
	private Tab SRLB;
	@FXML
	private Tab SZSJ;
	@FXML
	private TableView<BRINFO> BRtable;
	@FXML
	private TableView<SRINFO> SRtable;
	@FXML
	private TableColumn<BRINFO, String> brghbh;
	@FXML
	private TableColumn<BRINFO, String> brbrmc;
	@FXML
	private TableColumn<BRINFO, String> brghrq;
	@FXML
	private TableColumn<BRINFO, String> brhzlb;
	@FXML
	private TableColumn<SRINFO, String> srksmc;
	@FXML
	private TableColumn<SRINFO, String> srysbh;
	@FXML
	private TableColumn<SRINFO, String> srysmc;
	@FXML
	private TableColumn<SRINFO, String> srhzlb;
	@FXML
	private TableColumn<SRINFO, String> srghrc;
	@FXML
	private TableColumn<SRINFO, String>	srsrhj;
	@FXML
	private TextField startTime;
	@FXML
	private TextField endTime;
	@FXML
	private Button setbutton;
	@FXML
	private Button exitbutton;
	@FXML
	private Label Timelabel;
	private Timestamp sTime;
	private Timestamp eTime;
	public Timestamp nowTime;
	static public Timer timerp=new Timer();
	
	public void setStageController(StageController stageController) {
		// TODO Auto-generated method stub
		this.myController=stageController;
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.toString();
		
		brghbh.setCellValueFactory(new PropertyValueFactory<>("BGHBH"));
		brbrmc.setCellValueFactory(new PropertyValueFactory<>("BBRMC"));
		brghrq.setCellValueFactory(new PropertyValueFactory<>("BGHSJ"));
		brhzlb.setCellValueFactory(new PropertyValueFactory<>("BSFZJ"));
		
		srksmc.setCellValueFactory(new PropertyValueFactory<>("SKSMC"));
		srysbh.setCellValueFactory(new PropertyValueFactory<>("SYSBH"));
		srysmc.setCellValueFactory(new PropertyValueFactory<>("SYSMC"));
		srhzlb.setCellValueFactory(new PropertyValueFactory<>("SHZLB"));
		srghrc.setCellValueFactory(new PropertyValueFactory<>("SGHRC"));
		srsrhj.setCellValueFactory(new PropertyValueFactory<>("SSRHJ"));
		initialtime();
		
		selectBRLB(new Event(null));
		try {
			timerp.schedule(new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					freshTime();
				}
			},0,1000);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	private void freshTime() {
		Statement statement = null;
    	String queryString=null;
    	ResultSet resultSet=null;
		Connection dbConn = null;
        try{
            Class.forName(Main.driverName);
            dbConn = DriverManager.getConnection(Main.dbURL, Main.userName, Main.userPwd);  
        }catch (Exception e){
        	e.printStackTrace();  
        }
        try {
        	statement=dbConn.createStatement();
        	statement=dbConn.createStatement();
        	queryString="select getdate() as nowtime";
        	resultSet=statement.executeQuery(queryString);
        	while(resultSet.next()) {
        		nowTime=resultSet.getTimestamp("nowtime");
        	}
        	if(nowTime!=null) {
        		//Timelabel.setText(String.valueOf(nowTime));

        		Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
	            		String tString="";
	            		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
	            		tString=sdf.format(nowTime);
//	            		System.out.println(tString);
						Timelabel.setText(tString);
					}
				}
        		);
        	}
        	else {
        		timerp.cancel();
        	}
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
        Main.closedb(resultSet, statement, dbConn);
	}
	@FXML
	public void selectBRLB(Event event) {
		Statement statement = null;
    	String queryString=null;
    	ResultSet resultSet=null;
		Connection dbConn = null;
        try{
            Class.forName(Main.driverName);
            dbConn = DriverManager.getConnection(Main.dbURL, Main.userName, Main.userPwd);  
        }catch (Exception e){
        	e.printStackTrace();  
        }
        try {
        	statement=dbConn.createStatement();
        	queryString="select GHBH,BRMC,RQSJ,SFZJ from T_GHXX,T_BRXX,T_HZXX where T_GHXX.YSBH = '"
        			+ Lcontroller.getnum()
        			+ "' and T_GHXX.THBZ = 0 "
        			+ "and T_GHXX.BRBH=T_BRXX.BRBH "
        			+ "and T_GHXX.HZBH = T_HZXX.HZBH order by GHBH";
        	ObservableList<BRINFO> observableList = FXCollections.observableArrayList();
        	resultSet=statement.executeQuery(queryString);
        	while (resultSet.next()) {
				BRINFO brinfo=new BRINFO();
				brinfo.setBGHBH(resultSet.getString("GHBH"));
				brinfo.setBBRMC(resultSet.getString("BRMC"));
				brinfo.setBGHSJ(String.valueOf(resultSet.getTimestamp("RQSJ").toString()));
				brinfo.setBSFZJ(resultSet.getBoolean("SFZJ")?"◊®º“∫≈":"∆’Õ®∫≈");
				observableList.add(brinfo);
        	}
        	BRtable.setItems(observableList);
        	System.out.println("œ‘ æ≤°»À¡–±Ì");
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        Main.closedb(resultSet, statement, dbConn);
	}
	@FXML
	public void selectSRLB(Event event) {
		PreparedStatement statement = null;
    	String queryString=null;
    	ResultSet resultSet=null;
		Connection dbConn = null;
        try
        {
            Class.forName(Main.driverName);
            dbConn = DriverManager.getConnection(Main.dbURL, Main.userName, Main.userPwd);
            
        }catch (Exception e)
        {
        	e.printStackTrace();
            
        }
        try {
        	if(eTime==null) {
        		queryString="select KSMC,T_GHXX.YSBH,YSMC,T_HZXX.SFZJ,count(*) RC,sum(T_GHXX.GHFY) SR"
            			+ " from T_GHXX,T_KSXX,T_KSYS,T_HZXX"
            			+ " where T_GHXX.YSBH=T_KSYS.YSBH and T_KSYS.KSBH=T_KSXX.KSBH and T_GHXX.HZBH = T_HZXX.HZBH"
            			+ " and T_GHXX.RQSJ>= ? and T_GHXX.RQSJ< getdate() and T_GHXX.THBZ=0"
            			+ " group by T_GHXX.YSBH,T_KSXX.KSMC,T_KSYS.YSMC,T_HZXX.SFZJ "
            			+ " order by T_GHXX.YSBH";
            	
            	statement=dbConn.prepareStatement(queryString);
            	statement.setTimestamp(1, sTime);
        	}
        	else {
        		queryString="select KSMC,T_GHXX.YSBH,YSMC,T_HZXX.SFZJ,count(*) RC,sum(T_GHXX.GHFY) SR"
            			+ " from T_GHXX,T_KSXX,T_KSYS,T_HZXX"
            			+ " where T_GHXX.YSBH=T_KSYS.YSBH and T_KSYS.KSBH=T_KSXX.KSBH and T_GHXX.HZBH = T_HZXX.HZBH"
            			+ " and T_GHXX.RQSJ>= ? and T_GHXX.RQSJ< ? and T_GHXX.THBZ=0"
            			+ " group by T_GHXX.YSBH,T_KSXX.KSMC,T_KSYS.YSMC,T_HZXX.SFZJ "
            			+ " order by T_GHXX.YSBH";
            	statement=dbConn.prepareStatement(queryString);
            	statement.setTimestamp(1, sTime);
            	statement.setTimestamp(2, eTime);
        	}
        	ObservableList<SRINFO> observableList = FXCollections.observableArrayList();
        	resultSet=statement.executeQuery();
        	while (resultSet.next()) {
				SRINFO srinfo=new SRINFO();
				srinfo.setSKSMC(resultSet.getString("KSMC"));
				srinfo.setSYSBH(resultSet.getString("YSBH"));
				srinfo.setSYSMC(resultSet.getString("YSMC"));
				srinfo.setSHZLB(resultSet.getBoolean("SFZJ")?"◊®º“∫≈":"∆’Õ®∫≈");
				srinfo.setSGHRC(String.valueOf(resultSet.getBigDecimal("RC")));
				srinfo.setSSRHJ(String.valueOf(resultSet.getInt("SR")));
				observableList.add(srinfo);
        	}
        	SRtable.setItems(observableList);
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        Main.closedb(resultSet, statement, dbConn);
	}
	@FXML
	private void sclicked(ActionEvent event) {
		// TODO Auto-generated method stub
		String starttemp=startTime.getText().trim();
		String endtemp=endTime.getText().trim();
		if(starttemp==null||starttemp.equals("")||endtemp==null||endtemp.equals("")) {
			initialtime();
			JOptionPane.showMessageDialog(null,"”…”⁄√ª”–…Ë÷√£¨ª÷∏¥ƒ¨»œ ±º‰");
		}
		else {
			try {
				if(starttemp.length()!=0) {
					sTime = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(starttemp).getTime());
				}
				else {
					initialtime();
				}
				if(endtemp.length()!=0) {
					eTime=new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endtemp).getTime());
				}
				else {
					eTime=null;
				}
				JOptionPane.showMessageDialog(null,"±£¥Ê≥…π¶");
			}catch (ParseException e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "«ÎºÏ≤È ±º‰∏Ò Ω «∑Ò’˝»∑");
			}
		}
	}
	@FXML
	private void cclicked(ActionEvent event) {
		GotoMain();
//		DocController.timerp.cancel();
	}
	private void initialtime() {
		Statement statement = null;
    	String queryString=null;
    	ResultSet resultSet=null;
		Connection dbConn = null;
        try
        {
            Class.forName(Main.driverName);
            dbConn = DriverManager.getConnection(Main.dbURL, Main.userName, Main.userPwd);
            
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            
        }
        try {
        	statement=dbConn.createStatement();
        	queryString="select cast(cast( getdate() as date) as datetime) as starttime";
//        	queryString=="select cast(getdate() as datetime) as starttime";
        	resultSet=statement.executeQuery(queryString);
        	if(resultSet.next()) {
        		sTime=resultSet.getTimestamp("starttime");
        		eTime=null;
        		System.out.println(" ±º‰≥ı ºªØ≥…π¶");
        	}
        	else {
        		JOptionPane.showMessageDialog(null, " ˝æ›ø‚∑¢…˙¥ÌŒÛ");
        	}
        } catch (SQLException e) {
			// TODO: handle exception
        	e.printStackTrace();
        }
        Main.closedb(resultSet, statement, dbConn);
	}
	private void GotoMain() {
		myController.setStage(Main.loginviewid,Main.yishengviewid);
	}
}
