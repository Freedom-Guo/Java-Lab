package application;

import javafx.stage.Window;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;

public class AutoCompleteTextField {
	private final static int LIST_MAX_SIZE=10;
	private final static int LIST_CELL_HEIGH=24;
	public int pflag=1;
	public String keyvalue=null;
	private TextField textField;
	private ObservableList<String> showCacheDataList=FXCollections.<String>observableArrayList();
	private List<String> cacheDataList=new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		@Override
		public int indexOf(Object searchContext) {
			showCacheDataList.clear();
			int size=cacheDataList.size();
			if(searchContext==null||searchContext.toString().equals("")) {
				if(pflag==1) {
					for(int i=0;i<size;i++) {
						showCacheDataList.add(cacheDataList.get(i));
					}
				}
			}
			else {
				for(int i=0;i<size;i++) {
					//∆•≈‰∂‘”¶ ˝æ›
					if(cacheDataList.get(i).contains(searchContext.toString())) {
						showCacheDataList.add(cacheDataList.get(i));
					}
				}
			}
			return -1;
		};
	};
	//º‡Ã˝ ‰»ÎøÚµƒƒ⁄»›
	private SimpleStringProperty inputContent =new SimpleStringProperty();
	// ‰»Îƒ⁄»›∫Ûœ‘ æµƒpop
	private Popup popShowList = new Popup();
	// ‰»Îƒ⁄»›∫Ûœ‘ æµƒÃ· æ–≈œ¢¡–±Ì
	private ListView<String> autoTipList = new ListView<String>();
	
	public AutoCompleteTextField(TextField textField, List<String> cacheDataList) {
		// TODO Auto-generated constructor stub
		if(textField==null) {
			throw new RuntimeException("textfield ≤ªƒ‹Œ™ø’");
		}
		this.textField=textField;
		if(cacheDataList!=null) {
			this.cacheDataList.addAll(cacheDataList);
			
		}
		configure();
		confListner();
	}
	public AutoCompleteTextField(TextField textField) {
		// TODO Auto-generated constructor stub
		this(textField,null);
	}
	public void setCacheDataList(List<String> cachDataList) {
		this.cacheDataList.clear();
		this.cacheDataList.addAll(cachDataList);
		showCacheDataList.clear();
		showCacheDataList.addAll(cachDataList);
	}
	private void confListner() {
		// TODO Auto-generated method stub
		textField.textProperty().bindBidirectional(inputContent);
		textField.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) {
				cacheDataList.add(inputContent.get());
				removeDuplicate(cacheDataList);
			}
		});
		inputContent.addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				configureListContext(newValue);
			}
		});
		autoTipList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				selectedItem();
			}
		});
		autoTipList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				selectedItem();
			}
		});
		autoTipList.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ENTER) {
					selectedItem();
				}
			}
		});
	}
	//—°»°Listµƒƒ⁄»›µΩ ‰»ÎøÚ
	private void selectedItem() {
		String temp=autoTipList.getSelectionModel().getSelectedItem();
		if(temp!=null) {
			String[] b=temp.split(",");
			inputContent.set(b[1].trim());
			keyvalue=b[0].trim();
			textField.end();
			popShowList.hide();
		}
	}
	//∏˘æ› ‰»Îµƒƒ⁄»›¿¥≈‰÷√Ã· æ–≈œ¢
	private void configureListContext(String tipContent)
	{
		cacheDataList.indexOf(tipContent);
		if(!showCacheDataList.isEmpty()) {
			showTipPop();
		} else {
			popShowList.hide();
		}
	}

	//≈‰÷√◊Èº˛
	private void configure()
	{
		popShowList.setAutoHide(true);
		popShowList.getContent().add(autoTipList);
  		autoTipList.setItems(showCacheDataList);
	}
	public void removeDuplicate(List<String> cacheDataList2) {
		HashSet<String> h  =   new  HashSet<String>(cacheDataList2); 
	    cacheDataList2.clear(); 
	    cacheDataList2.addAll(h); 
	}
	
	
	public final Scene getScene() {
		return textField.getScene();
	}
	public final Window getWindow() {
		return getScene().getWindow();
	}
	//œ‘ æpop
	public final void showTipPop() {
		// TODO Auto-generated method stub
		autoTipList.setPrefWidth(textField.getWidth()-3);
		//System.out.println(showCacheDataList);
		if(showCacheDataList.size()<LIST_MAX_SIZE) {
			autoTipList.setPrefHeight(showCacheDataList.size()*LIST_CELL_HEIGH+9);
		}
		else {
			autoTipList.setPrefHeight(LIST_CELL_HEIGH*LIST_MAX_SIZE+9);
		}
		Window window= getWindow();
		Scene scene =getScene();
		Point2D fieldPosition = textField.localToScene(0,0);
		popShowList.show(window,window.getX()+fieldPosition.getX()+scene.getX(),window.getY()+fieldPosition.getY()+scene.getY()+textField.getHeight());
		autoTipList.getSelectionModel().clearSelection();
		autoTipList.getFocusModel().focus(-1);
	}
	public final void hideTipPop() {
		popShowList.hide();
	}
}
