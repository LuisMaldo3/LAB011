package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import java.util.List;

public interface SpecialtyService {

    // Buscar especialidades por consultorio
    List<Specialty> findByOffice(String office);

    // Buscar especialidades por nombre
    List<Specialty> findByName(String name);

    // Eliminar una especialidad por ID
    void delete(Integer id);
}