package model;

public class Species {

    private String name;
    private String type;
    private String SpeciesPhoto;
    private int specimensNumber;

    public Species(String SpeciesPhoto, String name, int specimensNumber, String type) {
        this.SpeciesPhoto = SpeciesPhoto;
        this.name = name;
        this.specimensNumber = specimensNumber;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSpeciesPhoto() {
        return SpeciesPhoto;
    }

    public int getSpecimensNumber() {
        return specimensNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSpeciesPhoto(String speciesPhoto) {
        SpeciesPhoto = speciesPhoto;
    }

    public void setSpecimensNumber(int specimensNumber) {
        this.specimensNumber = specimensNumber;
    }

}
