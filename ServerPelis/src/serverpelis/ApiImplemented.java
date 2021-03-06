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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
    LinkedBlockingDeque<Pelicula> rented;

    public ApiImplemented() throws RemoteException {
        super();
        try {
            DB=new LinkedBlockingDeque<>();
            rented=new LinkedBlockingDeque<>();
            loadDBFromFile(DB);
            System.out.println("--------------------\nLa base de datos ha sido cargada correctamente.\n--------------------");
        } catch (FileNotFoundException ex) {
            System.out.println("Error al leer el fichero.");
        }
    }
    /**
     *
     * @author nicomda
     * @param peli
     * @return bool
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean addFilm(Pelicula peli) throws RemoteException {
        DB.add(peli);
        System.out.println("Alquilada "+peli.getTitle());
        boolean ret;
        return ret=true;  
    }
    /**
     *
     * @author nicomda
     * @param peli
     * @param userID
     * @return bool
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean rent(Pelicula peli, int userID) throws RemoteException {
            Pelicula aux=peli;
            aux.setRented(true);
            aux.setRentUserId(userID);
            rented.add(aux);
            return true;
    }
    /**
     *
     * @author nicomda
     * @param DB
     * @throws java.rmi.RemoteException
     * @throws java.io.FileNotFoundException
     */
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

    @Override
    public ArrayList<Pelicula> listDB(String sorting) throws RemoteException {
        ArrayList<Pelicula> list=new ArrayList<Pelicula>();
        Iterator<Pelicula> itDB=DB.iterator();
        for(int i=0;i<DB.size();i++){
            list.add(itDB.next());
        }

        switch(sorting){
            case "Title":
                Collections.sort(list, new Comparator<Pelicula>(){
                @Override public int compare(Pelicula p1, Pelicula p2){
                    return p1.getTitle().compareTo(p2.getTitle());}
                });
                
                break;
            case "Rate":
                Collections.sort(list, new Comparator<Pelicula>(){
                @Override public int compare(Pelicula p1, Pelicula p2){
                    float aux;
                    aux=p1.getRating()-p2.getRating();
                    if(aux<0) aux=-1;
                    if(aux>0) aux=1;
                    return (int) aux;
                                                            }
                });
                break;
            case "Gender":
                Collections.sort(list, new Comparator<Pelicula>(){
                @Override public int compare(Pelicula p1, Pelicula p2){
                    return p1.getGender().compareTo(p2.getGender());}
                });
                break;
            case "Year":
                Collections.sort(list, new Comparator<Pelicula>(){
                @Override public int compare(Pelicula p1, Pelicula p2){
                    return (int)(p1.getReleaseYear()-p2.getReleaseYear());}
                });
                break;
            default:
                Collections.sort(list, new Comparator<Pelicula>(){
                @Override public int compare(Pelicula p1, Pelicula p2){
                    return (int)(p1.getID()-p2.getID());}
                });
                
        }
        
        return list;
    }

    @Override
    public boolean watchFilm(Pelicula peli,int userID) throws RemoteException {
        Pelicula aux;
        aux=rented.pollFirst();
        if(aux!=null){
            return true;
        }
        else return false;
        

    }

    @Override
    public ArrayList<Pelicula> listRent(int userId) throws RemoteException {
        Iterator<Pelicula> itpeli=rented.iterator();
        ArrayList<Pelicula> rentedFilms=new ArrayList<>();
        while(itpeli.hasNext()){
            Pelicula aux=itpeli.next();
            if(aux.getRentUserId()==userId){
                rentedFilms.add(aux);
            }
        }
        return rentedFilms;
    }

    
    
}