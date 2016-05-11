/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverpelis;

import interRMI.Data;
import interRMI.Api;
import serverpelis.Pelicula;
import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Esta clase implementará el método definido previavente en la interfaz remota
 * para poder realizar la invocación remota.
 * 
 * @author pedroj
 */
public class ApiImplemented extends UnicastRemoteObject implements Api {
    private static final long serialVersionUID = 1L;
    private ArrayList<Pelicula> DB;

    public ApiImplemented() throws RemoteException {
        super();
    }

    @Override
    public synchronized Data incrementCounter(Data DB) throws RemoteException {
        counter += value.getValue();
        return new Data(counter);
    }

    @Override
    public synchronized Data addFilm(Data DB) throws RemoteException {
        //DB
    }

    @Override
    public Data rent(Data DB) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}