package lk.ijse.dep11.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;

import java.io.*;


public class TextFormController {

    public AnchorPane root;
    public HTMLEditor htmlEditor;
    public MenuBar menuBar;
    public Menu menuFile;
    public MenuItem menuItemNew;
    public MenuItem menuItemOpen;
    public MenuItem menuItemSave;
    public MenuItem menuItemSaveAs;
    public MenuItem menuItemClose;
    public Menu menuHelp;
    public MenuItem menuItemAbout;
    public Label lblFooter;

    public File fileAddress;
    public TextArea txtBody;
    private static boolean isEdited = false;

    public void menuItemNewOnAction(ActionEvent actionEvent) {


    }

    public void menuItemOpenOnAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open a text file");

        File file = fileChooser.showOpenDialog(txtBody.getScene().getWindow());

        if(file == null) return;

        fileAddress = file;
        String fileName = String.valueOf(file);
        AppInitializer.observableTitle.set(fileName.substring(fileName.lastIndexOf('/')+1));
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = fis.readAllBytes();
        fis.close();
        String content = new String(bytes);
        txtBody.setText(new String(bytes));
    }

    public void menuItemSaveOnAction(ActionEvent actionEvent) {

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save a text File");

            File file =fileChooser.showSaveDialog(txtBody.getScene().getWindow());
            if(file == null)return;
            fileAddress =file;
            FileOutputStream fos = new FileOutputStream(file,false);

            String text = txtBody.getText();
            byte[] bytes = text.getBytes();
            fos.write(bytes);
            fos.close();
            String fileName = String.valueOf(file);
            AppInitializer.observableTitle.set(fileName.substring(fileName.lastIndexOf('/')+1));
            isEdited = false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void menuItemSaveAsOnAction(ActionEvent actionEvent) {
    }
}
