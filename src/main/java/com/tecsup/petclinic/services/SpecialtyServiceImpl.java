package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl
        implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public Specialty create(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty update(Specialty specialty) {

        Specialty existing = findById(specialty.getId());

        existing.setName(specialty.getName());
        existing.setOffice(specialty.getOffice());
        existing.setHOpen(specialty.getHOpen());
        existing.setHClose(specialty.getHClose());

        return specialtyRepository.save(existing);
    }

    @Override
    public Specialty findById(Integer id) {
        return specialtyRepository.findById(id)
                .orElseThrow(() ->
                        new SpecialtyNotFoundException(id));
    }
}
