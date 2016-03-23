package com.maddness.geoservice.service;

import com.maddness.geoservice.model.Cell;
import com.maddness.geoservice.model.CellKey;
import com.maddness.geoservice.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.maddness.geoservice.model.CellKey.cellKeyFor;

/**
 * Created by maddness on 23/03/2016.
 */
public class UserUpdater {

    private static Logger LOG = LogManager.getLogger(UserUpdater.class);

    private final Map<CellKey, Cell> cellsMap;
    private final Map<Long, User> usersMap;

    public UserUpdater(Map<CellKey, Cell> cellsMap, Map<Long, User> usersMap) {
        this.cellsMap = cellsMap;
        this.usersMap = usersMap;
    }

    public String addOrUpdateUser(long userId, double lat, double lon) {
        User existingUser = usersMap.get(userId);

        if (existingUser == null) {
            createNewUser(userId, lat, lon);

        } else {
            changeExistingUser(existingUser, lat, lon);
        }

        return "done";
    }

    private void createNewUser(long userId, double lat, double lon) {
        LOG.info("Adding user with ID: " + userId + ", coordinates: (" + lat + "," + lon + ")");

        User newUser = new User(userId, lat, lon);

        Cell cell = cellsMap.get(cellKeyFor(lat, lon));
        if (cell == null) {
            LOG.warn("Cell not found for a user " + userId);

            //TODO: add user to NULL cell

        } else {
            newUser.setCell(cell);
            cell.incrementUserCount();
        }

        usersMap.put(userId, newUser);
    }

    private void changeExistingUser(User existingUser, double lat, double lon) {
        //TODO: fill in
    }
}
