package com.maddness.geoservice.service;

import com.maddness.geoservice.model.Cell;
import com.maddness.geoservice.model.CellKey;
import com.maddness.geoservice.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.maddness.geoservice.model.CellKey.cellKeyFor;

/**
 * Created by maddness on 23/03/2016.
 */
public class StructureBuilder {

    private static Logger LOG = LogManager.getLogger(StructureBuilder.class);

    public static Map<CellKey, Cell> createCellsMap(List<Cell> cells) {
        Map<CellKey, Cell> cellsMap = new HashMap<>(1000);
        for (Cell cell : cells) {
            CellKey key = cellKeyFor(cell.getLat(), cell.getLon());
            if (!cellsMap.containsKey(key)) {
                cellsMap.put(key, cell);
            } else {
                throw new RuntimeException("Cell with duplicated coordinates " +
                        "(" + cell.getLat() + "," + cell.getLon() + ") found. Primary key violation.");
            }
        }
        return cellsMap;
    }

    public static Map<Long, User> createUsersMap(Map<CellKey, Cell> cells, List<User> users) {
        Map<Long, User> usersMap = new HashMap<>(100000);
        for (User user : users) {
            if (!usersMap.containsKey(user.getId())) {
                usersMap.put(user.getId(), user);

                CellKey cellKey = cellKeyFor(user.getLat(), user.getLon());
                Cell cell = cells.get(cellKey);

                if (cell != null) {
                    cell.incrementUserCount();
                    user.setCell(cell);
                } else {
                    LOG.warn("Cell for user " + user.getId() + " not found.");
                    //TODO: add user to NULL cell;
                }

            } else {
                throw new RuntimeException("User with duplicated ID (" + user.getId() + ") found. " +
                        "Primary key violation.");
            }
        }
        return usersMap;
    }
}
