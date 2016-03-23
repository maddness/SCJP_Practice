package com.maddness.geoservice.service;

/**
 * Created by maddness on 23/03/2016.
 */
public class InputsChecker {

    public static void checkCoordinatesForUser(long userId, double lat, double lon) {
        if (lat < -90 || lat > 90) {
            throw new RuntimeException("Latitude: " + lat + " is not in range [-90, 90] for User " + userId);
        } else if (lon < -180 || lon > 180) {
            throw new RuntimeException("Longitude: " + lon + " is not in range [-180, 180] for User " + userId);
        }
    }

    public static void checkCoordinatesForCell(double lat, double lon) {
        if (lat < -90 || lat > 90) {
            throw new RuntimeException("Cell latitude: " + lat + "is not in range [-90, 90]");
        } else if (lon < -180 || lon > 180) {
            throw new RuntimeException("Cell longitude: " + lon + " is not in range [-180, 180]");
        }
    }

    public static void checkMedianDistance(double medianDistance) {
        if (medianDistance < 0) {
            throw new RuntimeException("Median distance " + medianDistance + " can't be less than zero");
        }
    }
}
