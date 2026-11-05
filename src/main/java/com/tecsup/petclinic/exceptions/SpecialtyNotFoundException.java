package com.tecsup.petclinic.exceptions;

public class SpecialtyNotFoundException
        extends RuntimeException {

    public SpecialtyNotFoundException(Integer id) {
        super("Especialidad no encontrada con ID: " + id);
    }
}
