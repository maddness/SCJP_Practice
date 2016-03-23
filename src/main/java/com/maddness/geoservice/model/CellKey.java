package com.maddness.geoservice.model;

/**
 * Immutable class to be used as a key in a set of Users
 */
public final class CellKey {

    private final int lat;
    private final int lon;

    public static CellKey newCellKey(int lat, int lon) {
        return new CellKey(lat, lon);
    }

    public static CellKey cellKeyFor(double lat, double lon) {
        return new CellKey((int) lat, (int) lon);
    }

    private CellKey(int lat, int lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public int getLat() {
        return lat;
    }

    public int getLon() {
        return lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellKey cellKey = (CellKey) o;

        if (lat != cellKey.lat) return false;
        return lon == cellKey.lon;

    }

    @Override
    public int hashCode() {
        int result = lat;
        result = 31 * result + lon;
        return result;
    }

    @Override
    public String toString() {
        return lat + "," + lon;
    }
}
