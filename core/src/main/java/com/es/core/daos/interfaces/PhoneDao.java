package com.es.core.daos.interfaces;

import com.es.core.models.FindAndSortModel;
import com.es.core.models.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneDao {
    Optional<Phone> get(Long key);

    void save(Phone phone);

    List<Phone> findAll(FindAndSortModel findAndSortModel);

    int countPage(FindAndSortModel findAndSortModel);
}
