package sample;

import javafx.scene.Parent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

/**
 * Clase con métodos estáticos para su utilización en el Controller.
 * @author Franklin De Jesús Gómez
 * @version 1.0
 */
class openDialog {

    /**
     * Abrir diálogo para abrir fichero.
     * @param root Stage
     * @return String
     */
    static String showDialogToOpen(Parent root) {

        FileChooser chooser = new FileChooser();
        // Set extención del fichero.
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Arxiu de text","*.txt");
        chooser.setTitle("Selecciona un fitxer");
        chooser.getExtensionFilters().add(filtro);
        Stage mainStage = (Stage) root.getScene().getWindow();
        // Abriendo diálogo en nuestro contexto -> "Stage"
        File selectedFile = chooser.showOpenDialog(mainStage);

        String content = "";

        if (selectedFile != null) {
            mainStage.setTitle(selectedFile.getName());
            content = openFile(selectedFile);
        }
        return content;
    }

    /**
     * Abrir diálogo para guardar fichero.
     * @param content String
     * @param root Stage
     */
    static void showDialogToSave(String content, Parent root) {

        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Arxiu de text","*.txt");
        chooser.getExtensionFilters().add(filtro);
        Stage mainStage = (Stage) root.getScene().getWindow();
        File f = chooser.showSaveDialog(mainStage);

        if (f != null ) {
            saveFile(f, content);
        }
    }

    /**
     * Guardar fichero.
     * @param f fichero
     * @param content String
     */
     private static void saveFile(File f, String content) {
        try {
            FileWriter newFile = new FileWriter(f);
            newFile.write(content);
            newFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Abrir fichero.
     * @param selectedFile fichero
     * @return String
     */
    private static String openFile(File selectedFile) {
        String text = "";
        try {
            BufferedReader f = new BufferedReader(new FileReader(selectedFile));
            // Mientras el buffer no este vacío, leo linea.
            while (f.ready()) {
                text += f.readLine()+"\n";
            }
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}

