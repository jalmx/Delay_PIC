package delay_pic;

import java.io.FileNotFoundException;
import java.util.Formatter;

/**
 *
 * @author XIZUTH
 */
public class Escritura {

    Formatter salida;//------->

    public void abrirArchivo(String ruta) {
        try {
            salida = new Formatter(ruta);
        } catch (FileNotFoundException ex) {
            System.out.println("Error al abrir el archivo");
            System.exit(1);//cerrar solo ese archivo y no el programa si fuera 0 cerraria la aplicacion
        }
    }

    public void cerrarArchivo() {

        if (salida != null) {
            salida.close();
        }

    }

    public void escribir(String code) {

        salida.format(code);

    }//fin del metodo escribit

}
