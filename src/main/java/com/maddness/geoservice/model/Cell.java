package com.maddness.geoservice.model;

import static com.maddness.geoservice.service.InputsChecker.checkCoordinatesForCell;
import static com.maddness.geoservice.service.InputsChecker.checkMedianDistance;

public class Cell {

    private final int lat;
    private final int lon;
    private final double meanDistance;

    private int userCount;

    public Cell(int lat, int lon, double meanDistance) {
        checkCoordinatesForCell(lat, lon);
        checkMedianDistance(meanDistance);
        this.lat = lat;
        this.lon = lon;
        this.meanDistance = meanDistance;
        this.userCount = 0;
    }

    public int getLat() {
        return lat;
    }

    public int getLon() {
        return lon;
    }

    public double getMeanDistance() {
        return meanDistance;
    }

    public int getUserCount() {
        return userCount;
    }

    public void incrementUserCount() {
        userCount++;
    }

    public void decrementUserCount() {
        if (userCount > 0) {
            userCount--;
        } else {
            throw new RuntimeException("Can't remove users from empty cell!");
        }
    }

    @Override
    public String toString() {
        return "(" + lat + "," + lon + ") -> " + userCount + " users, " + meanDistance + " dist";
    }
}
