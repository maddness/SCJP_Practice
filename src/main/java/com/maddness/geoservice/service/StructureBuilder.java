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
        Map<CellKey, Cell> cellMap = new HashMap<>(1000);
        for (Cell cell : cells) {
            CellKey key = cellKeyFor(cell.getLat(), cell.getLon());
            if (!cellMap.containsKey(key)) {
                cellMap.put(key, cell);
            } else {
                LOG.error("Cell with duplicated coordinates found: (" + cell.getLat() + "," + cell.getLon() + ")." +
                        " Primary key violation.");
            }
        }
        return cellMap;
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
                }

            } else {
                LOG.error("User with duplicated ID found : " + user.getId() + ". Primary key violation.");
            }
        }
        return usersMap;
    }
}
