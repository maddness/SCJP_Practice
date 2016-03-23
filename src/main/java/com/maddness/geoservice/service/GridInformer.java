package com.maddness.geoservice.service;

import com.maddness.geoservice.model.Cell;
import com.maddness.geoservice.model.CellKey;

import java.util.Map;

import static com.maddness.geoservice.model.CellKey.cellKeyFor;

/**
 * Created by maddness on 23/03/2016.
 */
public class GridInformer {

    private final Map<CellKey, Cell> cellsMap;

    public GridInformer(Map<CellKey, Cell> cellsMap) {
        this.cellsMap = cellsMap;
    }

    public int getUsersInCell(double lat, double lon) {
        Cell cell = cellsMap.get(cellKeyFor(lat, lon));
        if (cell == null) {
            throw new RuntimeException("No cells found for coordinates: " + lat + "," + lon);
        } else {
            return cell.getUserCount();
        }
    }
}
