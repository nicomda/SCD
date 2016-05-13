package interrmi;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Definimos la interfaz que tendrá nuestro objeto remoto.
 * Ésta será la comunicación entre nuestro cliente y el server
 * 
 */
import java.io.FileNotFoundException;
import java.rmi.*;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
 
public interface Api extends Remote {
    public boolean addFilm(Pelicula peli) throws RemoteException;
    public boolean rent(Pelicula peli,int userID) throws RemoteException;
    public void loadDBFromFile(LinkedBlockingDeque<Pelicula> DB) throws RemoteException, FileNotFoundException;
    public ArrayList<Pelicula> listDB(String sorting) throws RemoteException;
    public boolean watchFilm(Pelicula peli,int userId) throws RemoteException;
    public ArrayList<Pelicula> listRent(int userId) throws RemoteException;
}