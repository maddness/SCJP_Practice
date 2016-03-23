package com.maddness.geoservice.model;

import static com.maddness.geoservice.model.CellKey.cellKeyFor;
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

    public CellKey getPossibleCellKey() {
        return cellKeyFor((int) lat, (int) lon);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
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
