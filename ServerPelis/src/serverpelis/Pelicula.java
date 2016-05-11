/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverpelis;

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

    Pelicula(int _ID, String _title, String _gender, int _releaseYear, float _rating, String _addedOn) {
        this.ID = _ID;
        this.title = _title;
        this.gender = _gender;
        this.releaseYear = _releaseYear;
        this.rating = _rating;
        this.addedOn = _addedOn;
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

}
