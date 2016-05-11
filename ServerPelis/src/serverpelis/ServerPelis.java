package serverpelis;

/**
 *
 * @author nicomda
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import serverpelis.Pelicula;
import interRMI.Api;

public class ServerPelis {


    //Clase básica de contenedor de pelis para usar como DB.


    private static ArrayList<Pelicula> DB;
    private static final String DB_FILE = "moviedb.txt";
    private static final int PORT = 1099;
    private static Registry registry;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DB = new ArrayList<>();
        try {
            loadDBFromFile(DB);
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo.");
        }
        for (int i=0; i<DB.size();i++){
            System.out.println(DB.get(i).getTitle());
        }
        try{
        startRegistry();
        registerObject(Api.class.getSimpleName(), new ApiImplemented());
        Thread.sleep(5 * 60 * 1000);
        System.out.println("Finalización del servidor....");
        } catch(Exception e){
            System.out.println("Error con el registro de objeto");
        }
            
    }

    /**
     * @author nicomda
     * @param DB
     * @throws java.io.FileNotFoundException
     */
    public static void loadDBFromFile(ArrayList<Pelicula> DB) throws FileNotFoundException {
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
    public static void startRegistry() throws RemoteException {
        // create in server registry
        registry = java.rmi.registry.LocateRegistry.createRegistry(PORT);
    }
    public static void registerObject(String name, Remote remoteObj)
        throws RemoteException, AlreadyBoundException {
        registry.bind(name, remoteObj);
        System.out.println("Registrando objeto remoto: " + name + " -> " +
            remoteObj.getClass().getName() + "[" + remoteObj + "]");
    }

}
