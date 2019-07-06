package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class BRINFO {
	private SimpleStringProperty BGHBH;
	private SimpleStringProperty BBRMC;
	private SimpleStringProperty BGHSJ;
	private SimpleStringProperty BSFZJ;
	public void setBGHBH(String value) {
		this.BGHBH=new SimpleStringProperty(value);
	}
	public void setBBRMC(String value) {
		this.BBRMC=new SimpleStringProperty(value);
	}
	public void setBGHSJ(String value) {
		this.BGHSJ=new SimpleStringProperty(value);
	}
	public void setBSFZJ(String value) {
		this.BSFZJ=new SimpleStringProperty(value);
	}
	
	public String getBGHBH() {
		return BGHBH.get();
	}
	public String getBBRMC() {
		return BBRMC.get();
	}
	public String getBSFZJ() {
		return BSFZJ.get();
	}
	public String getBGHSJ() {
		return BGHSJ.get();
	}

	public ObservableValue<String> BGHBHProperty() {
		return BGHBH;
	}
	public ObservableValue<String> BBRMCProperty() {
		return BBRMC;
	}
	public ObservableValue<String> BGHSJProperty(){
		return BGHSJ;
	}
	public ObservableValue<String> BSFJZProperty() {
		return BSFZJ;
	}
}
