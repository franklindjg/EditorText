package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/**
 * Controller para manejar eventos de un editor de texto simple
 * onClick, onShowing, onSelected y mouseIn.
 *
 * @author Franklin De Jesús Gómez
 * @version 2.2
 */
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
                texto.setText(openDialog.showDialoToOpen(root));
                break;
            case "Desar":
                openDialog.showDialogToSave(texto.getText(), root);
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
    public void onMouseIn(MouseEvent mouseEvent) {
        texto.selectionProperty().addListener((observable) -> {
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
     * Deshabilitar Copiar y Cortar de la barra de herramienta al iniciar la aplicación.
     */
    public void initialize() {
        tBarCopiar.setDisable(true);
        tBarCortar.setDisable(true);
    }
}
