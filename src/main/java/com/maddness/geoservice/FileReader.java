package com.maddness.geoservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class FileReader {

    private static Logger LOG = LogManager.getLogger(FileReader.class);

    public static List<Cell> readCells(String filePath) {
        File file = createFileObject(filePath);
        List<Cell> cells = new ArrayList<>(1000);

        try (BufferedReader is = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = is.readLine()) != null) {
                String[] values = line.split(",");
                cells.add(new Cell(
                        parseInt(values[0]),
                        parseInt(values[1]),
                        parseDouble(values[2])
                ));
            }
        } catch (IOException e) {
            LOG.error("Problem with reading grid file.");
        }

        return cells;
    }

    public static List<User> readUsers(String filePath) {
        File file = createFileObject(filePath);
        List<User> cells = new ArrayList<>(100000);

        try (BufferedReader is = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = is.readLine()) != null) {
                String[] values = line.split(",");
                cells.add(new User(
                        parseInt(values[0]),
                        parseDouble(values[1]),
                        parseDouble(values[2])
                ));
            }
        } catch (IOException e) {
            LOG.error("Problem with reading users file.");
        }

        return cells;
    }

    private static File createFileObject(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File with path " + filePath + " not found.");
        }
        return file;
    }
}
