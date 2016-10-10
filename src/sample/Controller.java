package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Controller {

    @FXML
    private TextArea texto;
    @FXML
    private MenuItem Cortar;
    @FXML
    private MenuItem Copiar;
    @FXML
    private CheckMenuItem Calibri;
    @FXML
    private CheckMenuItem Courier;
    @FXML
    private CheckMenuItem Consolas;
    @FXML
    private CheckMenuItem size12;
    @FXML
    private CheckMenuItem size14;
    @FXML
    private CheckMenuItem size24;
    @FXML
    Parent root;


    // Compartiendo evento onAction.
    public void onClick(ActionEvent e) {

        // Auxiliares
        MenuItem itemAux;
        CheckMenuItem checkItemAux;
        Button btn;
        String x;
        Class classReference = e.getSource().getClass();

        // Clase?
        if (classReference == Button.class) {
            btn = (Button) e.getSource();
            x = btn.getText();
        } else if(classReference == CheckMenuItem.class) {
            checkItemAux = (CheckMenuItem) e.getSource();
            x = checkItemAux.getText();
        } else {
            itemAux = (MenuItem) e.getSource();
            x = itemAux.getText();
        }

        System.out.println(x);

        switch (x) {
            case "Tancar":
                Platform.exit();
                break;
            case "Obre":
                texto.setText(importarTexto());
                break;
            case "Copiar":
                texto.copy();
                break;
            case "Tallar":
                texto.cut();
                break;
            case "Enganxar":
                texto.paste();
                break;
            case "Desfer":
                texto.undo();
                break;
            case "Calibri":
                texto.setFont(Font.font("Calibri", texto.getFont().getSize()));
                if (Consolas.isSelected()) Consolas.setSelected(false);
                else Courier.setSelected(false);
                break;
            case "Courier New":
                texto.setFont(Font.font("Courier New", texto.getFont().getSize()));
                if (Calibri.isSelected())Calibri.setSelected(false);
                else Consolas.setSelected(false);
                break;
            case "Consolas":
                texto.setFont(Font.font("Consolas", texto.getFont().getSize()));
                if (Courier.isSelected()) Courier.setSelected(false);
                else Calibri.setSelected(false);
                break;
            case "12px":
                texto.setFont(Font.font(texto.getFont().getFamily(), 12));
                if (size14.isSelected())  size14.setSelected(false);
                else size24.setSelected(false);
                break;
            case "14px":
                texto.setFont(Font.font(texto.getFont().getFamily(), 14));
                if (size12.isSelected())  size12.setSelected(false);
                else size24.setSelected(false);
                break;
            case "24px":
                texto.setFont(Font.font(texto.getFont().getFamily(), 24));
                if (size12.isSelected())  size12.setSelected(false);
                else size14.setSelected(false);
                break;
            case "About":
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setContentText("Aquest es un editor de text simple y senzill programat amb JavaFX.");
                info.setTitle("About");
                info.setHeaderText("Editor JavaFX");
                info.show();
        }
    }

    /**
     * Deshabilitar opción Copiar y Cortar si el usuario no selecciona ningún texto.
     */
    public void onShowing() {
        if (texto.getSelectedText().equalsIgnoreCase("")) {
            Copiar.setDisable(true);
            Cortar.setDisable(true);
        } else {
            Copiar.setDisable(false);
            Cortar.setDisable(false);
        }
    }

    /**
     * Mostar diálogo para seleccionar un fichero.
     * @return String contenido del fichero seleccionado.
     */
    private String importarTexto() {

        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Arxiu de Text","*.txt");
        chooser.setTitle("Selecciona un fitxer");
        chooser.getExtensionFilters().add(filtro);
        Stage mainStage = (Stage) root.getScene().getWindow();
        // Abriendo diálogo en nuestro contexto -> "Stage"
        File selectedFile = chooser.showOpenDialog(mainStage);

        String text = "";

        if (selectedFile != null) {
            try {
                // Cargar contenido del fichero seleccionado.
                BufferedReader f1 = new BufferedReader(new FileReader(selectedFile));
                while (f1.ready()) {
                    text += f1.readLine()+"\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return text;
    }
}
