package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;

public interface SpecialtyService {

    Specialty create(Specialty specialty);

    Specialty update(Specialty specialty);

    Specialty findById(Integer id);
}
