/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package delay_pic;

import java.io.FileNotFoundException;
import java.util.Formatter;
import javax.swing.JOptionPane;

/**
 *
 * @author XIZUTH
 */
public class EscrituraTXT {

    Formatter salida;//------->

    public void abrirArchivo(String ruta) {
        try {
            salida = new Formatter(ruta);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
            System.exit(1);//cerrar solo ese archivo y no el programa si fuera 0 cerraria la aplicacion
        }
    }

    public void cerrarArchivo() {

        if (salida != null) {
            salida.close();
        }

    }

    public void escribir(String instru) {

        salida.format(instru);
        //salida.format("%d %s %s %s\n", reg.getId(),reg.getNombre(),reg.getApPaterno(),reg.getApMaterno());

    }//fin del metodo escribit

}
