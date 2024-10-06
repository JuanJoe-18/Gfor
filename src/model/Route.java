package model;

import java.util.Date;

public class Route {

    private String routeName;
    private String meetingPlace;
    private Date startingDate;
    private Date endingDate;
    private int guidesQtty;
    private int participantsQtty;
    private double temperature;
    private double humidity;

    public Route(String routeName, String meetingPlace, Date startingDate, Date endingDate) {
        this.routeName = routeName;
        this.meetingPlace = meetingPlace;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getMeetingPlace() {
        return meetingPlace;
    }

    public void setMeetingPlace(String meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

}
