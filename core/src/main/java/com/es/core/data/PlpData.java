package com.es.core.data;

import com.es.core.model.Phone;

import java.util.ArrayList;
import java.util.List;

public class PlpData {
    private List<Phone> phoneList;

    public PlpData() {
        phoneList = new ArrayList<>();
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }
}
