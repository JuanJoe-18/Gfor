package model;

import java.util.Date;

public class Controller {

    private Department[] departments;
    private Volunteer volunteer;
    private Community[] communities;
    private Species[] species;

    public Controller(Department[] departments, Volunteer volunteer) {
        this.departments = new Department[32];
        this.volunteer = new Volunteer(null, null);
        this.communities = new Community[30];
        this.species = new Species[30];
    }

    public boolean addVolunteer(String name, String id) {
        if (this.volunteer.getVolunteerName() == null && this.volunteer.getVolunteerId() == null) {
            this.volunteer = new Volunteer(name, id);
            return true;
        }
        return false;
    }

    public boolean rectifyVolunteer(boolean thereIsVolunteer) {
        if (thereIsVolunteer) {
            System.out.println("Voluntario registrado correctamente\n");
            System.out.println("Bienvenido " + this.volunteer.getVolunteerName() + ". Gracias por registrarse \n");
            return true;
        } else {
            System.out.println("Ya se encuentra registrado un voluntario");
            return false;
        }
    }

    public boolean addBiodiversePlaceInDepartment(String placeName, String department, double placeSize,
            String placePhoto, Date openingDate, Double resourcesNeeded, Species[] species, Community[] communities) {

        BiodiversePlace newBiodiversePlace = new BiodiversePlace(placeName, placeSize, placePhoto, openingDate, resourcesNeeded, species, communities);

        for (int i = 0; i < departments.length; i++) {
            if (this.departments[i] != null) {
                if (this.departments[i].getName().equals(department)) {
                    return this.departments[i].addBiodiversePlace(newBiodiversePlace);
                }
            }

            if (this.departments[i] == null) {
                this.departments[i] = new Department(department);
                return this.departments[i].addBiodiversePlace(newBiodiversePlace);
            }
        }
        return false;
    }

    public String showBiodiversePlacesOrder() {
        BiodiversePlace[] sortedPlaces = new BiodiversePlace[30];
        int index = 0;

        for (Department department : departments) {
            if (department != null) {
                for (BiodiversePlace place : department.getBiodiversePlaces()) {
                    if (place != null) {
                        sortedPlaces[index++] = place;
                    }
                }
            }
        }

        for (int i = 0; i < sortedPlaces.length - 1; i++) {
            for (int j = 0; j < sortedPlaces.length - 1 - i; j++) {
                if (sortedPlaces[j] == null || sortedPlaces[j + 1] == null) {
                    continue;
                }
                if (sortedPlaces[j].getPlaceSize() < sortedPlaces[j + 1].getPlaceSize()) {
                    BiodiversePlace temp = sortedPlaces[j];
                    sortedPlaces[j] = sortedPlaces[j + 1];
                    sortedPlaces[j + 1] = temp;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sortedPlaces.length; i++) {
            if (sortedPlaces[i] != null) {
                result.append(sortedPlaces[i].getPlaceName())
                        .append(" - ")
                        .append(sortedPlaces[i].getPlaceSize())
                        .append(" hectáreas\n");
            }
        }
        return result.toString();
    }

    public boolean addCommunity(int idDepartment, int idPlace, String communityName, String communityType,
            int communitySize, String[] problems, String nameRepresentative, String phoneRepresentative) {
        CommunityType type = CommunityType.valueOf(communityType.toUpperCase());

        ProblemTypes[] problemsType = new ProblemTypes[problems.length];
        for (int i = 0; i < problems.length; i++) {
            problemsType[i] = ProblemTypes.valueOf(problems[i].toUpperCase());
        }

        if (this.departments[idDepartment] != null) {
            if (this.departments[idDepartment].getBiodiversePlaces()[idPlace] != null) {
                Representative representative = new Representative(nameRepresentative, phoneRepresentative);
                Community community = new Community(communityName, type, communitySize, representative, problemsType);
                return this.departments[idDepartment].getBiodiversePlaces()[idPlace].addCommunity(community);
            }
        }
        return false;
    }

    public boolean addSpeciesToPlace(int departmentIndex, int placeIndex, Species species) {
        if (departmentIndex >= 0 && departmentIndex < departments.length && departments[departmentIndex] != null) {
            BiodiversePlace[] places = departments[departmentIndex].getBiodiversePlaces();
            if (placeIndex >= 0 && placeIndex < places.length && places[placeIndex] != null) {
                return places[placeIndex].addSpecies(species);
            }
        }
        return false;
    }

    public boolean modifySpeciesInPlace(int departmentIndex, int placeIndex, int speciesIndex, Species updatedSpecies) {
        if (departmentIndex >= 0 && departmentIndex < departments.length && departments[departmentIndex] != null) {
            BiodiversePlace[] places = departments[departmentIndex].getBiodiversePlaces();
            if (placeIndex >= 0 && placeIndex < places.length && places[placeIndex] != null) {
                Species[] speciesArray = places[placeIndex].getSpecies();
                if (speciesIndex >= 0 && speciesIndex < speciesArray.length && speciesArray[speciesIndex] != null) {
                    speciesArray[speciesIndex] = updatedSpecies;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addBiodiversePlaceInDepartment(String placeName, String department, double placeSize, String placePhoto, Date openingDate, Double resourcesNeeded) {

        BiodiversePlace biodiversePlace = new BiodiversePlace(placeName, placeSize, placePhoto, openingDate, resourcesNeeded, species, communities);

        for (int i = 0; i < departments.length; i++) {
            if (this.departments[i] != null) {
                if (this.departments[i].getName().equals(department)) {
                    return this.departments[i].addBiodiversePlace(biodiversePlace);
                }
            } else {
                this.departments[i] = new Department(department);
                return this.departments[i].addBiodiversePlace(biodiversePlace); // Add to the newly created department
            }
        }
        return false; // No space in departments array
    }

    public String showDepartments() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < departments.length; i++) {
            if (this.departments[i] != null) {
                result.append(i).append(". ").append(this.departments[i].getName()).append("\n");
            }
        }
        return result.toString();
    }

    public String showPlacesInDepartment(int department) {
        if (this.departments[department] != null) {
            BiodiversePlace[] places = this.departments[department].getBiodiversePlaces();
            StringBuilder result = new StringBuilder();
            for (int j = 0; j < places.length; j++) {
                if (places[j] != null) {
                    result.append(j).append(". ").append(places[j].getPlaceName())
                            .append(" - ").append(places[j].getPlaceSize()).append(" hectáreas\n");
                }
            }
            return result.toString();
        }
        return "No se encontró el departamento.";
    }

    public String showCommunityTypes() {
        StringBuilder result = new StringBuilder();
        for (CommunityType type : CommunityType.values()) {
            result.append(type).append("\n");
        }
        return result.toString();
    }

    public String showProblemTypes() {
        StringBuilder result = new StringBuilder();
        for (ProblemTypes type : ProblemTypes.values()) {
            result.append(type).append("\n");
        }
        return result.toString();
    }

    public String showPlacesWithoutSchoolOrHospital() {
        StringBuilder result = new StringBuilder();
        for (Department department : departments) {
            if (department != null) {
                for (BiodiversePlace place : department.getBiodiversePlaces()) {
                    if (place != null) {
                        boolean hasNotSchool = false;
                        boolean hasNotHospital = false;

                        for (Community community : place.getCommunities()) {
                            if (community != null) {
                                for (ProblemTypes problem : community.getProblems()) {
                                    if (problem == ProblemTypes.NO_SCHOOL) {
                                        hasNotSchool = true;
                                    }
                                    if (problem == ProblemTypes.NO_HOSPITAL) {
                                        hasNotHospital = true;
                                    }
                                }
                            }
                        }

                        if (hasNotSchool || hasNotHospital) {
                            result.append(place.getPlaceName()).append("\n");
                        }
                    }
                }
            }
        }
        return result.toString();
    }

    public String mostBiodiverseDepartment() {
        int[] placeCount = new int[departments.length];
        int highestCount = 0;
        int departmentIndex = 0;

        for (int i = 0; i < departments.length; i++) {
            if (departments[i] != null) {
                for (BiodiversePlace place : departments[i].getBiodiversePlaces()) {
                    if (place != null) {
                        placeCount[i]++;
                    }
                }
            }

            if (placeCount[i] > highestCount) {
                highestCount = placeCount[i];
                departmentIndex = i;
            }
        }

        if (highestCount > 0) {
            return "El departamento más biodiverso es: " + departments[departmentIndex].getName()
                    + " con " + highestCount + " lugares biodiversos.";
        } else {
            return "No se han registrado departamentos o lugares biodiversos.";
        }
    }

    public String weatherForecast(double temperature, double humidity) {
        String wF = "";
        if ((temperature >= 20) && (temperature <= 25) && (humidity >= 40) && (humidity <= 60)) {
            wF = "¡Hace un buen día para caminar por Cali!";
        }
        return wF;
    }

    public String calculateBuses(int participantsQtty, int guidesQtty) {
        final int busCapacity = 25;
        int cantT = participantsQtty + guidesQtty;
        int cantB = (cantT / busCapacity);
        int qttyBuses;
        if (cantT % busCapacity != 0) {
            qttyBuses = cantB + 1;
        } else {
            qttyBuses = cantB;
        }
        String msgB = "Al ser un total de " + cantT + " personas que harán parte de la actividad, se necesitarán un total de: " + qttyBuses + " buses para llevarla a cabo de manera exitosa. ¡Nos vemos en la COP16!";
        return msgB;
    }

}
