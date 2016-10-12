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

    public void onClick(ActionEvent e) {

        // Variables auxiliares
        MenuItem itemAux;
        CheckMenuItem checkItemAux;
        Button btnAun;
        String option;
        Class classReference = e.getSource().getClass();
        double sizeFont = texto.getFont().getSize();
        String familyFont = texto.getFont().getFamily();

        CheckMenuItem familyList [] = {
                Calibri,
                Consolas,
                Courier
        };

        CheckMenuItem sizeList [] = {
                size12,
                size14,
                size24
        };

        // Verificar tipo del objecto devuelto
        if (classReference == Button.class) {
            btnAun = (Button) e.getSource();
            option = btnAun.getText();
        } else if(classReference == CheckMenuItem.class) {
            checkItemAux = (CheckMenuItem) e.getSource();
            option = checkItemAux.getText();
        } else {
            itemAux = (MenuItem) e.getSource();
            option = itemAux.getText();
        }

        System.out.println(option);

        switch (option) {
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
                texto.setFont(Font.font("Calibri", sizeFont));
                for (CheckMenuItem item: familyList) {
                    if (!item.getId().equalsIgnoreCase("Calibri") && item.isSelected()) {
                        item.setSelected(false);
                        return;
                    }
                }
                break;
            case "Courier New":
                texto.setFont(Font.font("Courier New", sizeFont));
                for (CheckMenuItem item: familyList) {
                    if (!item.getId().equalsIgnoreCase("Courier") && item.isSelected()) {
                        item.setSelected(false);
                        return;
                    }
                }
                break;
            case "Consolas":
                texto.setFont(Font.font("Consolas", sizeFont));
                for (CheckMenuItem item: familyList) {
                    if (!item.getId().equalsIgnoreCase("Consolas") && item.isSelected()) {
                        item.setSelected(false);
                        return;
                    }
                }
                break;
            case "12px":
                texto.setFont(Font.font(familyFont, 12));
                for (CheckMenuItem item: sizeList) {
                    if (!item.getId().equalsIgnoreCase("size12") && item.isSelected()) {
                        item.setSelected(false);
                        return;
                    }
                }
                break;
            case "14px":
                texto.setFont(Font.font(familyFont, 14));
                for (CheckMenuItem item: sizeList) {
                    if (!item.getId().equalsIgnoreCase("size14") && item.isSelected()) {
                        item.setSelected(false);
                        return;
                    }
                }
                break;
            case "24px":
                texto.setFont(Font.font(familyFont, 24));
                for (CheckMenuItem item: sizeList) {
                    if (!item.getId().equalsIgnoreCase("size24") && item.isSelected()) {
                        item.setSelected(false);
                        return;
                    }
                }
                break;
            case "About":
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setContentText("Aquest es un editor de text simple y senzill programat amb JavaFX.");
                info.setTitle("About");
                info.setHeaderText("Editor JavaFX");
                info.show();
        }
    }

    public void onMouseIn(MouseEvent mouseEvent) {
        texto.selectionProperty().addListener(observable -> {
            if (texto.getSelectedText().equalsIgnoreCase("")) {
                tBarCopiar.setDisable(true);
                tBarCortar.setDisable(true);
            } else {
                tBarCopiar.setDisable(false);
                tBarCortar.setDisable(false);
            }
        });
    }

    public void onShowing() {
        if (texto.getSelectedText().equalsIgnoreCase("")) {
            Copiar.setDisable(true);
            Cortar.setDisable(true);
        } else {
            Copiar.setDisable(false);
            Cortar.setDisable(false);
        }
    }

    public void initialize() {
        tBarCopiar.setDisable(true);
        tBarCortar.setDisable(true);
    }
}
