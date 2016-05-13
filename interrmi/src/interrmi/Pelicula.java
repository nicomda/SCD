package interrmi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author nicomda
 */
public class Pelicula {

    private final int ID; //Using it as a primary key.
    private final String title;
    private final String gender;
    private final int releaseYear;
    private final float rating;
    private final String addedOn;
    private boolean rented;
    private int rentUserId;

    public Pelicula(int _ID, String _title, String _gender, int _releaseYear, float _rating, String _addedOn) {
        this.ID = _ID;
        this.title = _title;
        this.gender = _gender;
        this.releaseYear = _releaseYear;
        this.rating = _rating;
        this.addedOn = _addedOn;
        this.rented=false;
        this.rentUserId=0;
    }
    
    public Pelicula(Pelicula peli) {
        this.ID = peli.getID();
        this.title = peli.getTitle();
        this.gender = peli.getGender();
        this.releaseYear = peli.getReleaseYear();
        this.rating = peli.getRating();
        this.addedOn = peli.getAddedOn();
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the releaseYear
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * @return the rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * @return the addedOn
     */
    public String getAddedOn() {
        return addedOn;
    }

    /**
     * @return the rented
     */
    public boolean isRented() {
        return rented;
    }

    /**
     * @param rented the rented to set
     */
    public void setRented(boolean rented) {
        this.rented = rented;
    }
    
    public int getRentUserId(){
        return this.rentUserId;
    }
    
    public void setRentUserId(int userID){
        this.rentUserId=userID;
    }


}
