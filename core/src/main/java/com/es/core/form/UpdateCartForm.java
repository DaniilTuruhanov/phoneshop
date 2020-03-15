package com.es.core.form;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateCartForm {
     private List<String> phonesId;

     private List<String> quantity;

    public List<String> getPhonesId() {
        return phonesId;
    }

    public void setPhonesId(List<String> phonesId) {
        this.phonesId = phonesId;
    }

    public List<String> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<String> quantity) {
        this.quantity = quantity;
    }
}
