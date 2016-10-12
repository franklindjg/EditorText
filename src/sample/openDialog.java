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
     * Mostrar diálogo para seleccionar fichero.
     * @return String contenido cargado del fichero seleccionado.
     * @param root Stage donde se mostrará el diálogo.
     */
    static String showDialoToOpen(Parent root) {

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
            // Mostrando nombre del fichero en el título del editor.
            mainStage.setTitle(selectedFile.getName());
            content = openFile(selectedFile);
        }
        return content;
    }

    /**
     * Mostrar diálogo para guardar fichero.
     * @param content String a guardar en fichero.
     * @param root Stage donde se mostrará el diálogo.
     */
    static void showDialogToSave(String content, Parent root) {

        FileChooser fileToSave = new FileChooser();

        // Set extención del fichero.
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Arxiu de text","*.txt");
        fileToSave.getExtensionFilters().add(filtro);
        Stage mainStage = (Stage) root.getScene().getWindow();
        File f = fileToSave.showSaveDialog(mainStage);

        if (f != null ) {
            // Guardando contenido.
            saveFile(f, content);
        }
    }

    /**
     * Guardar contenido en fichero de texto.
     * @param f fichero destino File.
     * @param content a guardar en fichero destino.
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
     * Cargar contenido de un fichero pasado por parámetro.
     * @param selectedFile fichero File.
     * @return String contenido del fichero cargado.
     */
    private static String openFile(File selectedFile) {
        String text = "";
        try {
            // Cargar contenido del fichero seleccionado.
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

