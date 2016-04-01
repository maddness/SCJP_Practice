package com.maddness.geoservice;

public class CellKey {
    private final int lat;
    private final int lon;

    private CellKey(int lat, int lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public static CellKey cellKey(int lat, int lon) {
        return new CellKey(lat, lon);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellKey cell = (CellKey) o;

        if (lat != cell.lat) return false;
        return lon == cell.lon;

    }

    @Override
    public int hashCode() {
        int result = lat;
        result = 31 * result + lon;
        return result;
    }

    @Override
    public String toString() {
        return lon + "," + lat;
    }
}
