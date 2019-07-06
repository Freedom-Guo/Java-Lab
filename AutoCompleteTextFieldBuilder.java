package application;

import java.util.List;

import javafx.scene.control.TextField;

public class AutoCompleteTextFieldBuilder {
	public static final AutoCompleteTextField build(TextField text,List<String> cachData) {
		return new AutoCompleteTextField(text,cachData);
	}
	public static final AutoCompleteTextField build(TextField textField) {
		// TODO Auto-generated constructor stub
		return new AutoCompleteTextField(textField);
	}
}
