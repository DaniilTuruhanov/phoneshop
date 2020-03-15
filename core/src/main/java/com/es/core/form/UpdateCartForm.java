package com.es.core.form;

import java.util.List;

public class UpdateCartForm {
    private List<String> updatePhonesIds;

    private List<String> updatePhonesQuantities;

    public List<String> getUpdatePhonesIds() {
        return updatePhonesIds;
    }

    public void setUpdatePhonesIds(List<String> updatePhonesIds) {
        this.updatePhonesIds = updatePhonesIds;
    }

    public List<String> getUpdatePhonesQuantities() {
        return updatePhonesQuantities;
    }

    public void setUpdatePhonesQuantities(List<String> updatePhonesQuantities) {
        this.updatePhonesQuantities = updatePhonesQuantities;
    }
}
