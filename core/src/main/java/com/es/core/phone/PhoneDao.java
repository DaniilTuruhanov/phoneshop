package com.es.core.phone;

import java.util.List;
import java.util.Optional;

public interface PhoneDao {
    Optional<Phone> get(Long key);

    void save(Phone phone);

    List<Phone> findAll(int offset, int limit, String query, String order, String sort);

    int countPage(int limit, String query);
}
