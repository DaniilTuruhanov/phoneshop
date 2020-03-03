package com.es.core.dao.inter;

import com.es.core.model.FindAndSortModel;
import com.es.core.model.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneDao {
    Optional<Phone> get(Long key);

    void save(Phone phone);

    List<Phone> findAll(FindAndSortModel findAndSortModel);

    int countPage(FindAndSortModel findAndSortModel);
}
