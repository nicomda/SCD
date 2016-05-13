/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpelis;

import interrmi.Api;
import interrmi.Pelicula;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author nicomda
 */
public class AdminPelis {
    private static final String HOST = "localhost";
    private static final int PORT = 1099;
    private static Registry registry;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //Connecting to API
        registry = LocateRegistry.getRegistry(HOST, PORT);
        Api remoteApi = (Api) registry.lookup(Api.class.getSimpleName());
        // Adding films
        Pelicula peli=new Pelicula(12,"La Bruja","Terror",2015, (float) 6.6,"13/5/2016");
        Pelicula peli2=new Pelicula(13,"La matanza de Texas","Terror",1974, (float) 6.7,"13/5/2016");
        Pelicula peli3=new Pelicula(14,"Rec","Terror",2007, (float) 6.5,"13/5/2016");

        remoteApi.addFilm(peli);
        remoteApi.addFilm(peli2);
        remoteApi.addFilm(peli3);
        System.out.println("Base de datos actualizada.");
    }
    
}
