package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

public class Controller {

    @FXML
    private TextArea texto;
    @FXML
    public Button tBarCopiar;
    @FXML
    public Button tBarCortar;
    @FXML
    private MenuItem Copiar;
    @FXML
    private MenuItem Cortar;
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
    private Parent root;

    // Compartiendo evento onClick para botones, items de menú y item seleccionable de menú.
    public void onClick(ActionEvent e) {

        // Auxiliares
        MenuItem itemAux;
        CheckMenuItem checkItemAux;
        Button btn;
        String x;
        Class classReference = e.getSource().getClass();

        // Qué tipo de clase devuelve getSource?
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
            case "Obre":
                texto.setText(showDialoToOpen());
                break;
            case "Desar":
                showDialogToSave(texto.getText());
            break;
            case "Tancar":
                Platform.exit();
                break;
            case "Copiar":
                texto.copy();
                texto.requestFocus();
                break;
            case "Tallar":
                texto.cut();
                texto.requestFocus();
                break;
            case "Enganxar":
                texto.paste();
                texto.requestFocus();
                break;
            case "Desfer":
                texto.undo();
                texto.requestFocus();
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
     * Deshabilitar opción Copiar y Cortar de la barra de herramienta
     * si el usuario no selecciona ningún texto.
     * @param mouseEvent callback.
     */
    public void mouseIn(MouseEvent mouseEvent) {
        texto.selectionProperty().addListener((observable, oldValue, newValue) -> {
            if (texto.getSelectedText().equalsIgnoreCase("")) {
                tBarCopiar.setDisable(true);
                tBarCortar.setDisable(true);
            } else {
                tBarCopiar.setDisable(false);
                tBarCortar.setDisable(false);
            }
        });
    }

    /**
     * Deshabilitar opción Copiar y Cortar del menú Editar si el usuario no selecciona ningún texto.
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
     * Mostrar diálogo para seleccionar un fichero.
     * @return String contenido del fichero seleccionado.
     */
    private String showDialoToOpen() {

        FileChooser chooser = new FileChooser();

        // Set extención del fichero.
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Arxiu de Text","*.txt");
        chooser.setTitle("Selecciona un fitxer");
        chooser.getExtensionFilters().add(filtro);
        Stage mainStage = (Stage) root.getScene().getWindow();
        // Abriendo diálogo en nuestro contexto -> "Stage"
        File selectedFile = chooser.showOpenDialog(mainStage);

        String content = "";

        if (selectedFile != null) {
            // Mostrando nombre del fichero en el título del editor.
            mainStage.setTitle(selectedFile.getName());
            content = openFile(selectedFile);
        }
        return content;
    }

    /**
     * Mostrar diálogo para guardar fichero.
     * @param content String a guardar en fichero.
     */
    private void showDialogToSave(String content) {

        FileChooser fileToSave = new FileChooser();

        // Set extención del fichero.
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Arxiu de Text","*.txt");
        fileToSave.getExtensionFilters().add(filtro);
        Stage mainStage = (Stage) root.getScene().getWindow();
        File f1 = fileToSave.showSaveDialog(mainStage);

        if (f1 != null ) {
            // Guardando contenido.
            saveFile(f1, content);
        }
    }

    /**
     * Guardar contenido en fichero de texto.
     * @param f1 fichero destino File.
     * @param content a guardar en destino.
     */
    private void saveFile(File f1, String content) {
        try {
            FileWriter newFile = new FileWriter(f1);
            newFile.write(content);
            newFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cargar contenido de un fichero pasado por parámetro.
     * @param selectedFile fichero File.
     * @return String contenido del fichero.
     */
    private String openFile(File selectedFile) {
        String text = "";
        try {
            // Cargar contenido del fichero seleccionado.
            BufferedReader f1 = new BufferedReader(new FileReader(selectedFile));
            // Mientras el buffer no este vacío, leo linea.
            while (f1.ready()) {
                text += f1.readLine()+"\n";
            }
            f1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * Deshabilitar Copiar y Cortar de la barra de herramienta al iniciar la aplicación.
     */
    public void initialize() {
        tBarCopiar.setDisable(true);
        tBarCortar.setDisable(true);
    }
}
