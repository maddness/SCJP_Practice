package com.maddness.geoservice;

public class User {

    private final int id;

    private double lon;
    private double lat;
    private CellKey cellKey;

    public User(int id, double lon, double lat) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
    }

    public int getId() {
        return id;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public CellKey getCellKey() {
        return cellKey;
    }

    public void setCellKey(CellKey cellKey) {
        this.cellKey = cellKey;
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
        return id;
    }

    @Override
    public String toString() {
        return "(" +
                "id=" + id +
                ", lon=" + lon +
                ", lat=" + lat +
                ", cell=" + cellKey + ')';
    }
}
