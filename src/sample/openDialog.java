package sample;

import javafx.scene.Parent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

class openDialog {

    /**
     * Mostrar diálogo para seleccionar un fichero.
     * @return String contenido del fichero seleccionado.
     * @param root Stage.
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
     * @param root Stage.
     */
    static void showDialogToSave(String content, Parent root) {

        FileChooser fileToSave = new FileChooser();

        // Set extención del fichero.
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Arxiu de text","*.txt");
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
     private static void saveFile(File f1, String content) {
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
    private static String openFile(File selectedFile) {
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
}

