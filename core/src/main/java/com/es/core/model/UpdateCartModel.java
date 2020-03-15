package com.es.core.model;

import java.util.ArrayList;
import java.util.List;

public class UpdateCartModel {
    private List<Long> updatePhonesIds;

    private List<Integer> updatePhonesQuantities;

    public UpdateCartModel() {
        updatePhonesIds = new ArrayList<>();
        updatePhonesQuantities = new ArrayList<>();
    }

    public List<Long> getUpdatePhonesIds() {
        return updatePhonesIds;
    }

    public void setUpdatePhonesIds(List<Long> updatePhonesIds) {
        this.updatePhonesIds = updatePhonesIds;
    }

    public List<Integer> getUpdatePhonesQuantities() {
        return updatePhonesQuantities;
    }

    public void setUpdatePhonesQuantities(List<Integer> updatePhonesQuantities) {
        this.updatePhonesQuantities = updatePhonesQuantities;
    }
}

