package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
// import javafx.scene.input.Clipboard;
// import javafx.scene.input.DataFormat;
import javafx.scene.text.Font;

public class Controller {

    @FXML
    private TextArea texto;
    @FXML
    private MenuItem Copiar, Enganxar, Cortar;
    @FXML
    private CheckMenuItem Calibri, Courier, Consolas, size12, size14, size24;

    /**
     * Capturar evento click del Ratón.
     * @param e callback
     */
    public void onClick(ActionEvent e) {

        MenuItem itemAux = (MenuItem) e.getSource();
        String x = itemAux.getText();
        System.out.println(x);

        switch (x) {
            case "Tancar":
                Platform.exit();
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
            case "About":
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setContentText("Aquest es un editor de text simple y senzill programat amb JavaFX.");
                info.setTitle("About");
                info.setHeaderText("Editor JavaFX");
                info.show();
                break;
        }
    }

    /**
     * Evento para detectar cuando se desplega el menú Editar
     *
     */
    public void showing() {

        /* Obteniendo un clipBoard del sistema */
        // Clipboard clipboard = Clipboard.getSystemClipboard();

        if (texto.getSelectedText().equalsIgnoreCase("")) {
            Copiar.setDisable(true);
            Cortar.setDisable(true);
        } else {
            Copiar.setDisable(false);
            Cortar.setDisable(false);
        }

        // TODO: 04/10/2016 Bloquear itemMenu Enganchar en caso de que no haya nada en el portapapeles
    }

    /**
     * Método para detectar cuando se ha selecionado una fuente o tamaño.
     * @param e callback
     */
    public void onSelected(ActionEvent e) {

        CheckMenuItem checkItemAux = (CheckMenuItem) e.getSource();
        String x = checkItemAux.getText();
        System.out.println(x);

        switch (x) {
            case "Calibri":
                texto.setFont(Font.font("Calibri", texto.getFont().getSize()));
                if (Consolas.isSelected()) Consolas.setSelected(false);
                if (Courier.isSelected()) Courier.setSelected(false);
                break;
            case "Courier New":
                texto.setFont(Font.font("Courier New", texto.getFont().getSize()));
                if (Calibri.isSelected()) Calibri.setSelected(false);
                if (Consolas.isSelected()) Consolas.setSelected(false);
                break;
            case "Consolas":
                texto.setFont(Font.font("Consolas", texto.getFont().getSize()));
                if (Courier.isSelected()) Courier.setSelected(false);
                if (Calibri.isSelected()) Calibri.setSelected(false);
                break;
            case "12px":
                texto.setFont(Font.font(texto.getFont().getFamily(), 12));
                if (size14.isSelected())  size14.setSelected(false);
                if (size24.isSelected())  size24.setSelected(false);
                break;
            case "14px":
                texto.setFont(Font.font(texto.getFont().getFamily(), 14));
                if (size12.isSelected())  size12.setSelected(false);
                if (size24.isSelected())  size24.setSelected(false);
                break;
            case "24px":
                texto.setFont(Font.font(texto.getFont().getFamily(), 24));
                if (size12.isSelected())  size12.setSelected(false);
                if (size14.isSelected())  size14.setSelected(false);
                break;
        }
    }
}
