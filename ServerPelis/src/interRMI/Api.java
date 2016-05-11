/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interRMI;

/**
 * Definimos la interfaz que tendrá nuestro objeto remoto.
 * Ésta será la comunicación entre nuestro cliente y el server
 * 
 */
import java.rmi.*;
 
public interface Api extends Remote {
    public Data addFilm(Data DB) throws RemoteException;
    public Data rent(Data DB) throws RemoteException;
}