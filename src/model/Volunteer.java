package model;

public class Volunteer {
    private String volunteerName;
    private String volunteerId;

    public Volunteer(String volunteerName, String volunteerId) {
        this.volunteerName = volunteerName;
        this.volunteerId = volunteerId;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

}
