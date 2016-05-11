
package interRMI;
import serverpelis.Pelicula;
import java.util.ArrayList;

/**
 *
 * Los objetos que formarán parte del proceso de invocación remota
 * deberán implementar la interfaz Serializable para poder ser transmitidos
 * por la red.
 * 
 */
import java.io.*;

public class Data implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Pelicula> DB;

    public Data(ArrayList<Pelicula> DB) {
        this.DB = DB;
    }

    public ArrayList<Pelicula> getDB() {
        return this.DB;
    }

    public void setDB(ArrayList<Pelicula> DB){
        this.DB = DB;
    }
}
