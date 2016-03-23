package com.maddness.geoservice.service;

import com.maddness.geoservice.model.Cell;
import com.maddness.geoservice.model.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class InputReader {

    public static List<Cell> readCells(String filePath) {
        File file = createFileObject(filePath);
        List<Cell> cells = new ArrayList<>(1000);

        String line = "";
        try (BufferedReader is = new BufferedReader(new FileReader(file))) {
            while ((line = is.readLine()) != null) {
                String[] values = line.split(",");
                cells.add(new Cell(
                        parseInt(values[0]),
                        parseInt(values[1]),
                        parseDouble(values[2])
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException("Problem with processing grid file");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Problem with parsing grid file at line: '" + line + "'. " + e.getMessage());
        }

        return cells;
    }

    public static List<User> processUsers(String filePath) {
        File file = createFileObject(filePath);
        List<User> users = new ArrayList<>(100000);

        String line = "";
        try (BufferedReader is = new BufferedReader(new FileReader(file))) {
            while ((line = is.readLine()) != null) {
                String[] values = line.split(",");
                users.add(new User(
                        parseLong(values[0]),
                        parseDouble(values[2]),
                        parseDouble(values[1])
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException("Problem with processing users file");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Problem with parsing users file at line: '" + line + "'. " + e.getMessage());
        }

        return users;
    }

    private static File createFileObject(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File with path '" + filePath + "' not found.");
        }
        return file;
    }
}
