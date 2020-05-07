package com.es.core.form;

import java.util.List;

public class QuickOrderForm {
    private List<String> phoneModel;

    private List<String> phoneQuantity;

    public List<String> getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(List<String> phoneModel) {
        this.phoneModel = phoneModel;
    }

    public List<String> getPhoneQuantity() {
        return phoneQuantity;
    }

    public void setPhoneQuantity(List<String> phoneQuantity) {
        this.phoneQuantity = phoneQuantity;
    }
}
