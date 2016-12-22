package ui.presentation.util;

import init.LoopedStreams;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import javax.swing.text.Document;
import java.io.*;

/**
 * Created by island on 2016/12/21.
 */
public class ServerMainViewController {
    private String resource = "ServerMainView.fxml";

    private Stage stage;

    private FXMLLoader loader;

    private String s = "";

    @FXML
    private TextField ipTextField;

    @FXML
    private TextArea textArea;

    @FXML
    private Button closeButton;

    public void setIP(String IP){
        ipTextField.setText(IP);
        startConsoleReaderThread();
    }


    @FXML
    private void  closeStage(){
        System.exit(0);
    }

    private void startConsoleReaderThread() {
        try {
            final LoopedStreams ls = new LoopedStreams();
            PrintStream ps = new PrintStream(ls.getOutputStream());
            InputStream inStream = ls.getInputStream();


            final BufferedReader br =
                    new BufferedReader(new InputStreamReader(inStream));
            new Thread(new Runnable() {
                public void run() {
                    StringBuffer sb = new StringBuffer();
                    try {
                        String s;
                        while ((s = br.readLine()) != null) {
                            boolean caretAtEnd = false;
                            sb.setLength(0);
                            s += sb.append(s).append('\n').toString();
                            textArea.setText(s);
                        }
                    } catch (IOException e) {
                        System.exit(1);
                    }
                }
            }).start();
        }catch (IOException e){
            e.printStackTrace();
        }
    } // startConsoleReaderThread()
}
