package com.contribute.xtrct.dao.model.lookup;

import java.io.Serializable;

/**
 * POJO holding information representing an incentive's Vehicle Status in the database
 */
public class VehicleStatus implements Serializable {

    private int id;
    private String description;

    public VehicleStatus(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
