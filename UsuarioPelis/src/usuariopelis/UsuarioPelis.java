/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuariopelis;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import interrmi.Api;
import interrmi.Pelicula;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author nicomda
 */
public class UsuarioPelis {
    private static final String HOST = "localhost";
    private static final int PORT = 1099;
    private static Registry registry;
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        //Connecting to API
        registry = LocateRegistry.getRegistry(HOST, PORT);
        Api remoteApi = (Api) registry.lookup(Api.class.getSimpleName());
        //Asking for remote film database
        ArrayList<Pelicula> filmList=remoteApi.listDB("Rate");
        
        Iterator<Pelicula>it=filmList.iterator();
        System.out.println("--------------------");
        while(it.hasNext()){
            System.out.println(it.next().getTitle());
        }
        System.out.println("--------------------");
        Pelicula peli=new Pelicula(12,"La Bruja","Terror",2015, (float) 6.6,"13/5/2016");
        remoteApi.addFilm(peli);
        filmList=remoteApi.listDB("Title");
        it=filmList.iterator();
        System.out.println("--------------------");
        while(it.hasNext()){
            System.out.println(it.next().getTitle());
        }
        System.out.println("--------------------");
        remoteApi.rent(peli, 1);
        filmList=remoteApi.listDB("Title");
        it=filmList.iterator();
        System.out.println("--------------------");
        Pelicula aux;
        while(it.hasNext()){
            aux=it.next();
            System.out.println(aux.getTitle()+" "+aux.getRentUserId());
        }
        //RENTED FILMS LISTING
        ArrayList<Pelicula> rentedList=remoteApi.listRent(1);
        System.out.println("RENTED BY USER 1");
        System.out.println("--------------------");
        for(int i=0;i<rentedList.size();i++){
            System.out.println(rentedList.get(i).getTitle());
        }
        System.out.println("--------------------");
        
  
        
    }
    
}
