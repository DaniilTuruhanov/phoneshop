package com.es.core.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.util.Objects;

public class QuickOrderEntity {

    @NotEmpty(message = "not empty pls")
    private String model;

    @Pattern(regexp = "[0-9]+", message = "not a number")
    private String quantity;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuickOrderEntity that = (QuickOrderEntity) o;
        return Objects.equals(model, that.model) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, quantity);
    }
}
