package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.InvalidScheduleException;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;

public interface SpecialtyService {

	/**
	 * Creates a specialty. Hours must be within 0-23 (IllegalArgumentException otherwise)
	 * and hOpen must be lower than hClose (InvalidScheduleException otherwise).
	 */
	Specialty create(Specialty specialty) throws InvalidScheduleException;

	Specialty findById(Integer id) throws SpecialtyNotFoundException;
}
