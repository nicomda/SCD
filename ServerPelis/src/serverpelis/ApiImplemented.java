/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverpelis;

import interrmi.Pelicula;
import interrmi.Api;
import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;


/**
 * Esta clase implementará el método definido previavente en la interfaz remota
 * para poder realizar la invocación remota.
 * 
 * @author pedroj
 */
public class ApiImplemented extends UnicastRemoteObject implements Api {
    private static final long serialVersionUID = 1L;
    private static final String DB_FILE = "moviedb.txt";
    //Clase básica de contenedor de pelis para usar como DB.
    LinkedBlockingDeque<Pelicula> DB;

    public ApiImplemented() throws RemoteException {
        super();
        try {
            loadDBFromFile(DB);
        } catch (FileNotFoundException ex) {
            System.out.println("Error al leer el fichero.");
        }
    }

    @Override
    public boolean addFilm(Pelicula peli) throws RemoteException {
        DB.add
        
    }

    @Override
    public Pelicula rent(Pelicula peli) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     *
     * @author nicomda
     * @param DB
     * @throws java.rmi.RemoteException
     * @throws java.io.FileNotFoundException
     */
    @Override
    public void loadDBFromFile(LinkedBlockingDeque<Pelicula> DB) throws RemoteException, FileNotFoundException {
        String lineBuffer;
        String name, gender, date;
        int id, year;
        float rating;
        File file = new File(DB_FILE);
        Scanner sc_file = new Scanner(file);
        while (sc_file.hasNext()) {
            lineBuffer = sc_file.nextLine();
            Scanner sc_line = new Scanner(lineBuffer);
            sc_line.useDelimiter(",");
            id = sc_line.nextInt();
            name = sc_line.next();
            gender = sc_line.next();
            year = sc_line.nextInt();
            rating = Float.parseFloat(sc_line.next());
            date = sc_line.next();
            DB.add(new Pelicula(id,name,gender,year,rating,date));
            System.out.println("Cargado "+id+" - "+name);
        }
    }
    
    
}