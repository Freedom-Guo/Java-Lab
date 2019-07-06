package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class SRINFO {
	private SimpleStringProperty SKSMC;
	private SimpleStringProperty SYSBH;
	private SimpleStringProperty SYSMC;
	private SimpleStringProperty SHZLB;
	private SimpleStringProperty SGHRC;
	private SimpleStringProperty SSRHJ;
	public void setSKSMC(String value) {
		this.SKSMC=new SimpleStringProperty(value);
	}
	public void setSYSBH(String value) {
		this.SYSBH=new SimpleStringProperty(value);
	}
	public void setSYSMC(String value) {
		this.SYSMC=new SimpleStringProperty(value);
	}
	public void setSHZLB(String value) {
		this.SHZLB=new SimpleStringProperty(value);
	}
	public void setSGHRC(String value) {
		this.SGHRC=new SimpleStringProperty(value);
	}
	public void setSSRHJ(String value) {
		this.SSRHJ=new SimpleStringProperty(value);
	}
	
	public String getSKSMC() {
		return SKSMC.get();
	}
	public String getSYSBH() {
		return SYSBH.get();
	}
	public String getSYSMC() {
		return SYSMC.get();
	}
	public String getSHZLB() {
		return SHZLB.get();
	}
	public String getSGHRC() {
		return SGHRC.get();
	}
	public String setSSRHJ() {
		return SSRHJ.get();
	}
	
	public ObservableValue<String> SKSMCProperty() {
		return SKSMC;
	}
	public ObservableValue<String> SYSBHProperty() {
		return SYSBH;
	}
	public ObservableValue<String> SYSMCProperty() {
		return SYSMC;
	}
	public ObservableValue<String> SHZLBProperty() {
		return SHZLB;
	}
	public ObservableValue<String> SGHRCProperty() {
		return SGHRC;
	}
	public ObservableValue<String> SSRHJProperty() {
		return SSRHJ;
	}
}
