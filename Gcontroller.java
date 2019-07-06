package application;

import java.math.BigDecimal;
import java.net.URL;
import java.security.spec.PSSParameterSpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.AutoCompleteTextField;
import application.AutoCompleteTextFieldBuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Gcontroller implements Initializable,ControlledStage {
	StageController myController;
	int mutux=1;//1 is error
    String KSBH=null;
    String KSMC=null;
    String KPYZS=null;
    String YSBH=null;
    String YSMC=null;
    String YPYZS=null;
    Boolean SFZJ=false;
    String HZBH=null;
    String HZMC=null;
    String HPYZS=null;
    private float rest_money=-1;
    boolean zhuanjia;
	@FXML
	private SplitPane splitpane;
	@FXML
	private TextField ksmcin;
	@FXML
	private TextField ysxmin;
	@FXML
	private TextField hzmcin;
	@FXML
	private TextField hzlbin;
	@FXML
	private TextField jkjein;
	@FXML
	private TextField yjjein;
	@FXML
	private TextField zljein;
	@FXML
	private TextField ghhmin;
	@FXML
	private Button clearbutton;
	@FXML
	private Button enterbutton;
	@FXML
	private Button exitbutton;
	@FXML
	private Button THbutton;
	@FXML
	private ContextMenu ksmenu;
	@FXML
	private Label tipslabel;
	AutoCompleteTextField ksmc=null;

	AutoCompleteTextField ysxm=null;

	AutoCompleteTextField hzmc=null;
	
	AutoCompleteTextField hzlb=null;
	float jk,yj,zl,saveback;
	private int GHBH;
	private int GHRC;
	@Override
	public void setStageController(StageController stageController) {
		// TODO Auto-generated method stub
		this.myController=stageController;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.toString();
		ksmcin.requestFocus();
		ksmc=AutoCompleteTextFieldBuilder.build(this.ksmcin);
		ysxm=AutoCompleteTextFieldBuilder.build(this.ysxmin);
		hzmc=AutoCompleteTextFieldBuilder.build(this.hzmcin);
		hzlb=AutoCompleteTextFieldBuilder.build(this.hzlbin);
		List<String> list = new ArrayList<String>();
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
            System.out.print("¡¨Ω” ß∞‹");
        }
        try {
			statement=dbConn.createStatement();
			queryString="select "+"KSBH,KSMC,PYZS"+" from "+"dbo.T_KSXX";
			resultSet=statement.executeQuery(queryString);
			while(resultSet.next()) {
				KSBH=resultSet.getString("KSBH").trim();
				KSMC=resultSet.getString("KSMC").trim();
				KPYZS=resultSet.getString("PYZS").trim();
				while(KSMC.length()<6) {
					KSMC=KSMC+" ";
				}
				list.add(KSBH+",\t"+KSMC+",\t"+KPYZS);
			}
			ksmc.setCacheDataList(list);

			list.clear();
			queryString="select "+"YSBH,YSMC,PYZS,SFZJ"+" from "+"dbo.T_KSYS";
			resultSet=statement.executeQuery(queryString);
			while(resultSet.next()) {
				YSBH=resultSet.getString("YSBH").trim();
				YSMC=resultSet.getString("YSMC").trim();
				YPYZS=resultSet.getString("PYZS").trim();
				SFZJ=resultSet.getBoolean("SFZJ");
				while(YSMC.length()<6) {
					YSMC=YSMC+" ";
				}
				String zhuanjia=null;
				if(SFZJ==true)
					zhuanjia="◊®º“";
				else zhuanjia="∆’Õ®";
				list.add(YSBH+",\t"+YSMC+",\t"+YPYZS+" "+zhuanjia);
			}
			ysxm.setCacheDataList(list);
			list.clear();
			queryString="select "+"HZBH,HZMC,PYZS"+" from "+"dbo.T_HZXX";
			resultSet=statement.executeQuery(queryString);
			while(resultSet.next()) {
				HZBH=resultSet.getString("HZBH").trim();
				HZMC=resultSet.getString("HZMC").trim();
				HPYZS=resultSet.getString("PYZS").trim();
				while(HZMC.length()<6) {
					HZMC=HZMC+" ";
				}
				list.add(HZBH+",\t"+HZMC+",\t"+HPYZS);
			}
			hzmc.setCacheDataList(list);
			list.clear();
			list.add("1,◊®º“");
			list.add("2,∆’Õ®");
			hzlb.setCacheDataList(list);
			list.clear();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        Main.closedb(resultSet, statement, dbConn);
    	ksmcin.setFocusTraversable(true);
    	ksmcin.requestFocus();
	}
    @FXML
    private void exitAction(ActionEvent event) {
    	GotoMain();
    }
    @FXML
    private void clearAction(ActionEvent event) {
    	Contentclear();
    }
    @FXML
    private void enterAction(ActionEvent event) {
    	if(mutux==0) {
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
    		String tempstring=null;
    		//String hZString=null;
    		try {
        		statement=dbConn.createStatement();
        		queryString="select YCJE from dbo.T_BRXX where BRBH='"+Lcontroller.getnum()+"'";
        		resultSet=statement.executeQuery(queryString);
        		saveback=0;
        		while(resultSet.next()) {
        			rest_money=resultSet.getFloat("YCJE");
        			System.out.println(rest_money);
        		}
        		System.out.println(queryString+" "+rest_money);
				jk=Float.parseFloat(jkjein.getText());
				yj=Float.parseFloat(yjjein.getText());
        		if(rest_money>0) {
        			if(jk>rest_money) {
        				tipslabel.setText("¥ÊøÓ≤ª◊„");
        			}
        			else if(jk<yj) {
        				tipslabel.setText("Ω…∑—Ω∂Ó≤ª◊„");
        			}
        			else {
        				//GHBH,HZBH,YSBH,BRBH
        				queryString="select * from T_GHXX where HZBH=? and YSBH=? and BRBH =?";
        				PreparedStatement pStatement=dbConn.prepareStatement(queryString);
        				pStatement.setString(1, hzmc.keyvalue);
        				pStatement.setString(2, ysxm.keyvalue);
        				pStatement.setString(3, Lcontroller.getnum());
        				resultSet=pStatement.executeQuery();
        				if(!resultSet.next()) {
        					int tempint=0;
            				int tempint2=0;
            				zl=jk-yj;
            				zljein.setText(String.valueOf(zl));
            				saveback=rest_money-yj;
            				queryString="select GHBH from T_GHXX group by GHBH order by GHBH asc";
            				resultSet=statement.executeQuery(queryString);
            				while(resultSet.next()) tempstring=resultSet.getString("GHBH");
            				if(tempstring!=null) {
            					GHBH=Integer.parseInt(tempstring);
            				}
            				else GHBH=0;
            				GHBH=GHBH+1;
            				queryString="select count(*) GHRC,GHRS from T_HZXX where HZBH= '"+hzmc.keyvalue+"' group by GHRS";
            				resultSet=statement.executeQuery(queryString);
            				while(resultSet.next()) { 
            					tempint=resultSet.getInt("GHRS");
            					tempint2=resultSet.getInt("GHRC");
            				}
            				if(tempint>tempint2) {
            					if(tempint2==1)	GHRC=1;
            					else GHRC=tempint2+1;
            				}
            				else {
            					Main.closedb(resultSet, statement, dbConn);
            					return;
            				}
            				queryString = "insert into T_GHXX(GHBH,HZBH,YSBH,BRBH,GHRC,THBZ,GHFY,RQSJ) values (?,?,?,?,?,?,?,getdate())";
            				pStatement=dbConn.prepareStatement(queryString);
            				pStatement.setString(1, String.format("%06d", GHBH));
            				System.out.println(hzmc.keyvalue);
            				System.out.println(ysxm.keyvalue);
            				pStatement.setString(2, hzmc.keyvalue);
            				pStatement.setString(3, ysxm.keyvalue);
            				pStatement.setString(4, Lcontroller.getnum());
            				pStatement.setInt(5, GHRC);
            				pStatement.setBoolean(6, false);
            				pStatement.setBigDecimal(7, new BigDecimal(jk));
            				pStatement.executeUpdate();
            				//dbConn.commit();
            				queryString="update T_BRXX set YCJE=? where BRBH = ?";
            				pStatement=dbConn.prepareStatement(queryString);
            				pStatement.setBigDecimal(1, new BigDecimal(saveback));
            				pStatement.setString(2, Lcontroller.getnum());
            				pStatement.executeUpdate();
            				ghhmin.setText(String.format("%06d", GHBH));
            				tipslabel.setText("π“∫≈≥…π¶");
            				mutux=1;
        				}
        				else {
        					JOptionPane.showMessageDialog(null, "∏√“Ω…˙”Î∫≈÷÷“—æ≠π“π˝");
        				}
        			}
        		}
        		else tipslabel.setText("Œﬁ¥ÊøÓ");

    		}catch (SQLException e) {
    	 		// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Main.closedb(resultSet, statement, dbConn);
    	}
    	else {
    		tipslabel.setText(" ‰»Î”–ŒÛ");
    		JOptionPane.showMessageDialog(null, " ‰»Î≤ª¬˙◊„“™«Û");
    	}

    }
    @FXML
    private void THAction(ActionEvent event) {
    	clearAction(new ActionEvent());
    	String ghbhstr = JOptionPane.showInputDialog("«Î ‰»Îπ“∫≈±‡∫≈").trim();
    	if(ghbhstr==null||ghbhstr.length()==0) {
    		return ;
    	}
    	Statement statement = null;
    	String queryString=null;
    	ResultSet resultSet=null;
		Connection dbConn = null;
		String thghhz=null;
		String thbrbh=null;
		String thysbh=null;
		boolean isth = false;
        try
        {
            Class.forName(Main.driverName);
            dbConn = DriverManager.getConnection(Main.dbURL, Main.userName, Main.userPwd);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            System.out.print("¡¨Ω” ß∞‹");
        }
        try {
        	
        	statement=dbConn.createStatement();
        	queryString="select YSBH,HZBH,BRBH,THBZ from T_GHXX where GHBH="+ghbhstr;
        	resultSet=statement.executeQuery(queryString);
        	while(resultSet.next()) {
        		thysbh=resultSet.getString("YSBH").trim();
        		thghhz=resultSet.getString("HZBH").trim();
        		thbrbh=resultSet.getString("BRBH").trim();
        		isth=resultSet.getBoolean("THBZ");
        	}
        	if(!isth) {
        		System.out.println(thbrbh+thghhz+thysbh);
            	if(thbrbh.equals(Lcontroller.getnum())) {
            		queryString = "select count(*) total from T_GHXX where HZBH = ? and YSBH=? and THBZ = 0 and RQSJ>=day(getdate()) and RQSJ<dateadd(day,day(getdate())+1,getdate())";
        			PreparedStatement pStatement = dbConn.prepareStatement(queryString);
        			pStatement.setString(1, thghhz);
        			pStatement.setString(2, thysbh);
        			resultSet=pStatement.executeQuery();
        			if(resultSet.next()) {
        				GHRC=resultSet.getInt("total")-1;
        				if(GHRC>=0) {
        					String updatestr="update T_GHXX set THBZ = 1 where HZBH = ? and BRBH = ?";
            				pStatement=dbConn.prepareStatement(updatestr);
            				pStatement.setString(1, thghhz);
            				pStatement.setString(2, thbrbh);
            				pStatement.executeUpdate();
            				updatestr="update T_GHXX set GHRC = ? "
            						+ "where HZBH = ? and RQSJ>=day(getdate()) and RQSJ<dateadd(day,day(getdate())+1,getdate())";
            				pStatement=dbConn.prepareStatement(updatestr);
            				pStatement.setInt(1, GHRC);
            				pStatement.setString(2, thghhz);
            				pStatement.executeUpdate();
            				JOptionPane.showMessageDialog(null, "ÕÀ∫≈≥…π¶");
        				}
        				else {
        					JOptionPane.showMessageDialog(null, "∏√≤°»ÀŒ¥”–∏√∫≈¬Î∂‘”¶µƒ∫≈÷÷");
        				}
        			}
        			else {
        				JOptionPane.showMessageDialog(null, "∏√≤°»ÀŒ¥”–∏√∫≈¬Î∂‘”¶µƒ∫≈÷÷");
        			}
            	}
            	else {
            		JOptionPane.showMessageDialog(null, "«ÎŒ ‰»ÎÀ˚»ÀÀ˘π“µƒ∫≈¬Î");
            		Main.closedb(resultSet, statement, dbConn);
            	}
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "∏√±‡∫≈“—æ≠ÕÀ∫≈");
        	}
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
        Main.closedb(resultSet, statement, dbConn);
    }
    @FXML
    private void ksshow() {
		// TODO Auto-generated method stub
    	ksmcin.setFocusTraversable(true);
    	ksmcin.requestFocus();
    	ksmc.showTipPop();
    	System.out.println("ø∆ “œ‘ æ");
	}
    @FXML
    private void ysshow() {
    	ysxmin.setFocusTraversable(true);
    	ysxmin.requestFocus();
    	if(ksmcin.getText()==null||ksmcin.getText().equals("")) {
    		ysxm.showTipPop();
    		System.out.println("“Ω…˙œ‘ æ");
    	}
    	else {
       		List<String> list = new ArrayList<String>();
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
    			queryString="select YSBH,YSMC,b.PYZS,SFZJ from T_KSXX a join T_KSYS b on a.KSMC='"+ksmcin.getText().trim()+"' and a.KSBH=b.KSBH";
    			resultSet=statement.executeQuery(queryString);
    			list.clear();
    			while(resultSet.next()) {
    				YSBH=resultSet.getString("YSBH").trim();
    				YSMC=resultSet.getString("YSMC").trim();
    				YPYZS=resultSet.getString("PYZS").trim();
    				SFZJ=resultSet.getBoolean("SFZJ");
    				while(YSMC.length()<6) {
    					YSMC=YSMC+" ";
    				}
    				String zhuanjia=null;
    				if(SFZJ==true)
    					zhuanjia="◊®º“";
    				else zhuanjia="∆’Õ®";
    				list.add(YSBH+",\t"+YSMC+",\t"+YPYZS+" "+zhuanjia);
    			}
    			ysxm.pflag=0;
    			ysxm.setCacheDataList(list);
    			ysxm.pflag=1;
    			System.out.println(list);
    			
    			ysxm.showTipPop();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            Main.closedb(resultSet, statement, dbConn);
    	}
    }
    @FXML
    private void hzshow() {
    	hzmcin.setFocusTraversable(true);
    	hzmcin.requestFocus();
    	hzmc.showTipPop();
    	System.out.println("∫≈÷÷œ‘ æ");
    }
    @FXML
    private void zjshow() {
    	hzlbin.setFocusTraversable(true);
    	hzlbin.requestFocus();
    	List<String> list = new ArrayList<String>();
    	String tempstring=hzmcin.getText();
        //œ‘ æ
    	if(hzmcin.getText()==null||hzmcin.getText().equals("")) {
        	hzlb.showTipPop();
    	}
    	else {
    		queryfy();
			if(tempstring.indexOf("◊®")!=-1) {
    			zhuanjia=true;
				list.clear();
				list.add("1,◊®º“");
				hzlb.setCacheDataList(list);
				hzlb.showTipPop();
    		}
			else if(tempstring.indexOf("∆’")!=-1) {
				zhuanjia=false;
				list.clear();
				list.add("1,∆’Õ®");
				hzlb.setCacheDataList(list);
				hzlb.showTipPop();
			}
			else {
				System.out.println("≤È’“¥ÌŒÛ");
			}
    	}
    	System.out.println("◊®º“œ‘ æ");
    	
    }
    @FXML
    private void queryfy() {
    	tipslabel.setText("");
    	Statement statement = null;
    	String queryString=null;
    	ResultSet resultSet=null;
		Connection dbConn = null;
		 try{
             Class.forName(Main.driverName);
             dbConn = DriverManager.getConnection(Main.dbURL, Main.userName, Main.userPwd);
         }
         catch (Exception e){
         	e.printStackTrace();
         }
		String tempstring=hzmcin.getText();
	 	try {
	 		statement=dbConn.createStatement();
			queryString="select HZBH,GHRS,GHFY from T_HZXX where HZMC= '"+tempstring+"'";
			resultSet=statement.executeQuery(queryString);
	 		while(resultSet.next()) {
	 			String hZString=resultSet.getString("HZBH");
	 			int ghrs=resultSet.getInt("GHRS");
	 			float ghfy=resultSet.getFloat("GHFY");
	 			System.out.println(hZString+ghrs+ghfy);
	 			if(ghrs>0) {
	 				yjjein.setText(String.valueOf(ghfy));
	 			}
	 			else {//
	 				Contentclear();
	 		    	tipslabel.setText("∏√∫≈÷÷π“∫≈»À ˝¥ÔµΩ…œœﬁ");
	 			}
			}

	 	} catch (SQLException e) {
	 		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	Main.closedb(resultSet, statement, dbConn);
	}
    @FXML
    private void zlchange(KeyEvent event) {
    	tipslabel.setText("");
    	if(event.getCode()==KeyCode.TAB) {
    		System.out.println("ÃΩ≤‚≥…π¶");
    		jisuan();
    	}
		String ksString=ksmcin.getText();
		String ysString=ysxmin.getText();
		String hzString=hzmcin.getText();
		String hzString2=hzlbin.getText();
		
    	if(ksString==null||ksString.equals("")
    		||ysString==null||ysString.equals("")
    		||hzString==null||hzString.equals("")
    		||hzString2==null||hzString2.equals(""))
    	{
        	System.out.println(" ‰»Î≤ª¬˙");
        	mutux=1;
    	}
    	else {

    		//String checkys=ysxm.keyvalue;
        	Statement statement = null;
        	String queryString=null;
        	ResultSet resultSet=null;
    		Connection dbConn = null;
    		 try{
                 Class.forName(Main.driverName);
                 dbConn = DriverManager.getConnection(Main.dbURL, Main.userName, Main.userPwd);
             }
             catch (Exception e){
             	e.printStackTrace();
             }
    		 try {
				statement=dbConn.createStatement();
				queryString="select YSMC,KSMC,T_KSYS.SFZJ from T_KSYS,T_HZXX,T_KSXX where T_KSYS.YSBH='"
						+ysxm.keyvalue
						+ "' and T_KSYS.KSBH=T_HZXX.KSBH and T_HZXX.KSBH=T_KSXX.KSBH ";
				resultSet=statement.executeQuery(queryString);
				if (resultSet.next()) {
					String ys=resultSet.getString("YSMC").trim();
					String ks=resultSet.getString("KSMC").trim();
					String isZJ=resultSet.getBoolean("SFZJ")?"◊®º“":"∆’Õ®";
					System.out.println(ys+ks+hzString);
					if(hzString.indexOf(ks)==-1) {
						mutux=1;
						tipslabel.setText(ys+"≤ª‘⁄"+ks);
					}
					else if(isZJ.equals("∆’Õ®")&&(hzString2.indexOf("◊®º“")!=-1)) {
						mutux=1;
						tipslabel.setText(ys+"≤ªƒ‹π“◊®º“∫≈");
					}
					else {
			    		if((hzString.indexOf("◊®")!=-1 && hzString2.indexOf("◊®")!=-1)||(hzString.indexOf("∆’")!=-1 && hzString2.indexOf("∆’")!=-1)) {
			    			mutux=0;// ‰»ÎÕÍ±œ
			    		}
			    		else {
			    			tipslabel.setText("∫≈÷÷¿‡±”Î∫≈÷÷√˚≥∆–Ë∆•≈‰");
			    			mutux=1;
			    		}
					}
				}
				else {
					mutux=1;
					JOptionPane.showMessageDialog(null, "“Ω…˙°¢ø∆ “°¢∫≈÷÷∆•≈‰¥ÌŒÛ");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    		 Main.closedb(resultSet, statement, dbConn);
    	}
    }
    @FXML
    private void jisuan() {
    	if(yjjein.getText()== null||yjjein.getText().equals("")
				&&jkjein.getText()==null||jkjein.getText().equals("")) {
			System.out.println("∑—”√ ‰»ÎŒ™ø’");;
		}
		else {
			if(Float.parseFloat(jkjein.getText())<Float.parseFloat(yjjein.getText())) {
				tipslabel.setText("Ω…∑—Ω∂Ó≤ª◊„");
			}
			else {
				System.out.println("º∆À„");
				jk=Float.parseFloat(jkjein.getText());
				yj=Float.parseFloat(yjjein.getText());
				zl=jk-yj;
				saveback=rest_money-yj;
				zljein.setText(String.valueOf(zl));
			}
		}
    }
    private void Contentclear() {
	    	ksmc.pflag=0;
	    	ysxm.pflag=0;
	    	hzmc.pflag=0;
	    	hzlb.pflag=0;
	    	ksmcin.clear();
	    	ysxmin.clear();
	    	hzmcin.clear();
	    	hzlbin.clear();
	    	jkjein.clear();
	    	yjjein.clear();
	    	zljein.clear();
	    	ghhmin.clear();
	    	hzlb.pflag=1;
	    	ksmc.pflag=1;
	    	ysxm.pflag=1;
	    	hzmc.pflag=1;
	    	mutux=1;
	}
	private void GotoMain() {
		myController.setStage(Main.loginviewid,Main.guahaoviewid);
	}
}