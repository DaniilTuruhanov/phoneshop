package com.es.core.form;

import com.es.core.model.QuickOrderEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

public class QuickOrderForm {

    @Valid
    List<QuickOrderEntity> quickOrderEntityList;

    public List<QuickOrderEntity> getQuickOrderEntityList() {
        return quickOrderEntityList;
    }

    public void setQuickOrderEntityList(List<QuickOrderEntity> quickOrderEntityList) {
        this.quickOrderEntityList = quickOrderEntityList;
    }
}
