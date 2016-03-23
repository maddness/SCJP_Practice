package com.maddness.geoservice;

import com.maddness.geoservice.model.Cell;
import com.maddness.geoservice.model.CellKey;
import com.maddness.geoservice.model.User;
import com.maddness.geoservice.service.GridInformer;
import com.maddness.geoservice.service.UserUpdater;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static com.maddness.geoservice.service.InputReader.processUsers;
import static com.maddness.geoservice.service.InputReader.readCells;
import static com.maddness.geoservice.service.StructureBuilder.createCellsMap;
import static com.maddness.geoservice.service.StructureBuilder.createUsersMap;

public class GeoService {

    // lat long to short


    private static Logger LOG = LogManager.getLogger(GeoService.class);

    public static void main(String[] args) {

        List<Cell> cells = readCells("/Users/maddness/_Progamming/SCJP_Practice/src/main/resources/cells.csv");
        List<User> users = processUsers("/Users/maddness/_Progamming/SCJP_Practice/src/main/resources/users.csv");

        Map<CellKey, Cell> cellsMap = createCellsMap(cells);
        Map<Long, User> usersMap = createUsersMap(cellsMap, users);

        System.out.println("== Users");
        usersMap.values().forEach(System.out::println);

        System.out.println("== Cells");
        cellsMap.values().forEach(System.out::println);

        UserUpdater updater = new UserUpdater(cellsMap, usersMap);
        updater.addOrUpdateUser(13, 12.4, 11.5);

        GridInformer provider = new GridInformer(cellsMap);
        System.out.println(provider.getUsersInCell(11, 12));


    }
}
