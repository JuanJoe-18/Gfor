package model;

public class Department {

    private String name;
    private BiodiversePlace[] biodiversePlaces;

    public Department(String name) {
        this.name = name;
        this.biodiversePlaces = new BiodiversePlace[30];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BiodiversePlace[] getBiodiversePlaces() {
        return biodiversePlaces;
    }

    public void setBiodiversePlaces(BiodiversePlace[] biodiversePlaces) {
        this.biodiversePlaces = biodiversePlaces;
    }

    public boolean addBiodiversePlace(BiodiversePlace biodiversePlace) {
        for (int i = 0; i < biodiversePlaces.length; i++) {
            if (this.biodiversePlaces[i] == null) {
                this.biodiversePlaces[i] = biodiversePlace;
                return true;
            }
        }
        return false;
    }
}
