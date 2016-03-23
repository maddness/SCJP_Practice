package com.maddness.geoservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static com.maddness.geoservice.CellKey.cellKey;
import static com.maddness.geoservice.FileReader.readCells;
import static com.maddness.geoservice.FileReader.readUsers;

public class Main {
    private static Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        List<Cell> cells = readCells("C:\\Git\\SCJP_Practice\\src\\main\\resources\\cells.csv");
        List<User> users = readUsers("C:\\Git\\SCJP_Practice\\src\\main\\resources\\users.csv");

        Map<CellKey, Cell> cellsMap = createCellsMap(cells);
        Map< Integer, User> usersMap = createUsersMap(cellsMap, users);

        System.out.println("== Users");
        usersMap.values().forEach(System.out::println);

        System.out.println("== Calls");
        cellsMap.values().forEach(System.out::println);

    }

    public String addOrUpdateUser(int userId, double lat, double lon) {
        return "processing...";
    }

    private static Map<CellKey, Cell> createCellsMap(List<Cell> cells) {
        Map<CellKey, Cell> cellMap = new HashMap<>(1000);
        for (Cell cell : cells) {
            CellKey key = cellKey(cell.getLat(), cell.getLon());
            if (!cellMap.containsKey(key)) {
                cellMap.put(key, cell);
            } else {
                LOG.error("Cell with duplicated coordinates found: (" + cell.getLat() + "," + cell.getLon() + ")." +
                        " Primary key violation.");
            }
        }
        return cellMap;
    }

    private static Map<Integer, User> createUsersMap(Map<CellKey, Cell> cells, List<User> users) {
        Map<Integer, User> usersMap = new HashMap<>(100000);
        for (User user : users) {
            if (!usersMap.containsKey(user.getId())) {
                usersMap.put(user.getId(), user);

                CellKey cellKey = cellKey((int) user.getLat(), (int) user.getLon());
                Cell cell = cells.get(cellKey);

                if (cell != null) {
                    cell.incrementUserCount();
                    user.setCellKey(cellKey);
                } else {
                    LOG.warn("Cell for user " + user.getId() + " not found.");
                }

            } else {
                LOG.error("User with duplicated ID found : " + user.getId() + ". Primary key violation.");
            }
        }
        return usersMap;
    }
}
