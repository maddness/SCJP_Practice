package com.maddness.geoservice.exceptions;

public class FileNotFoundEcxeption extends RuntimeException {
    private final String reason;

    public FileNotFoundEcxeption(String missingPath) {
        this.reason = "File with path " + missingPath + " not found.";
    }

    public String getReason() {
        return reason;
    }
}
