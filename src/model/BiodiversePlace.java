package model;

import java.util.Date;

public class BiodiversePlace {

    private String placeName;
    private double placeSize;
    private String placePhoto;
    private Date openingDate;
    private Double resourcesNeeded;
    private Species[] species;
    private Community[] communities;

    public BiodiversePlace(String placeName, double placeSize, String placePhoto, Date openingDate,
            Double resourcesNeeded, Species[] species, Community[] communities) {
        this.placeName = placeName;
        this.placeSize = placeSize;
        this.placePhoto = placePhoto;
        this.openingDate = openingDate;
        this.resourcesNeeded = resourcesNeeded;
        this.species = species;
        this.communities = communities;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getPlaceSize() {
        return placeSize;
    }

    public void setPlaceSize(double placeSize) {
        this.placeSize = placeSize;
    }

    public String getPlacePhoto() {
        return placePhoto;
    }

    public void setPlacePhoto(String placePhoto) {
        this.placePhoto = placePhoto;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Double getResourcesNeeded() {
        return resourcesNeeded;
    }

    public void setResourcesNeeded(Double resourcesNeeded) {
        this.resourcesNeeded = resourcesNeeded;
    }

    public Species[] getSpecies() {
        return species;
    }

    public void setSpecies(Species[] species) {
        this.species = species;
    }

    public Community[] getCommunities() {
        return communities;
    }

    public void setCommunities(Community[] communities) {
        this.communities = communities;
    }

    public boolean addCommunity(Community community) {
        for (int i = 0; i < communities.length; i++) {
            if (communities[i] == null) {
                communities[i] = community;
                return true;
            }
        }
        System.out.println("No se puede agregar la comunidad. La capacidad máxima ha sido alcanzada.");
        return false;
    }

    public boolean addSpecies(Species species) {
        for (int i = 0; i < this.species.length; i++) {
            if (this.species[i] == null) {
                this.species[i] = species;
                return true;
            }
        }
        System.out.println("No se puede agregar el animal. La capacidad máxima ha sido alcanzada.");
        return false;
    }

    public boolean modifySpecies(int speciesIndex, Species updatedSpecies) {
        if (speciesIndex >= 0 && speciesIndex < species.length && species[speciesIndex] != null) {
            species[speciesIndex] = updatedSpecies;
            return true;
        }
        return false;
    }

}
