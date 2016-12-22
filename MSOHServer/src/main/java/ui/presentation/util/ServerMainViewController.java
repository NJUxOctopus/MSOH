package ui.presentation.util;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Created by island on 2016/12/21.
 */
public class ServerMainViewController {

    @FXML
    private TextField ipTextField;

    @FXML
    private TextArea textArea;

    @FXML
    private Button closeButton;

    public void setIP(String IP){
        ipTextField.setText(IP);
    }


    @FXML
    private void  closeStage(){
        System.exit(0);
    }

}
