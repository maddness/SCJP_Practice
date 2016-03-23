package com.maddness.geoservice.model;

import static com.maddness.geoservice.service.InputsChecker.checkCoordinatesForUser;

public class User {

    private final long id;
    private double lat;
    private double lon;
    private Cell cell;

    public User(long id, double lat, double lon) {
        checkCoordinatesForUser(id, lat, lon);
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cellKey) {
        this.cell = cellKey;
    }

    @Override
    public String toString() {
        return "(" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", cell=" + cell + ')';
    }
}
