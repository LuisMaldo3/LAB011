package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.repositories.SpecialtyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    // Buscar especialidades por consultorio
    @Override
    public List<Specialty> findByOffice(String office) {
        return specialtyRepository.findByOffice(office);
    }

    // Buscar especialidades por nombre
    @Override
    public List<Specialty> findByName(String name) {
        return specialtyRepository.findByName(name);
    }

    // Eliminar una especialidad por ID
    @Override
    public void delete(Integer id) {

        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Especialidad no encontrada: " + id
                        )
                );

        specialtyRepository.delete(specialty);
    }
}