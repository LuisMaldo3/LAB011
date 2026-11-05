package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.InvalidScheduleException;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

	private static final int MIN_HOUR = 0;
	private static final int MAX_HOUR = 23;

	private final SpecialtyRepository specialtyRepository;

	@Override
	public Specialty create(Specialty specialty) throws InvalidScheduleException {

		validateHours(specialty);

		return specialtyRepository.save(specialty);
	}

	@Override
	public Specialty findById(Integer id) throws SpecialtyNotFoundException {

		return specialtyRepository.findById(id)
				.orElseThrow(() -> new SpecialtyNotFoundException("Record not found...!"));
	}

	private void validateHours(Specialty specialty) throws InvalidScheduleException {

		Integer open = specialty.getHOpen();
		Integer close = specialty.getHClose();

		if (open == null || close == null
				|| open < MIN_HOUR || open > MAX_HOUR
				|| close < MIN_HOUR || close > MAX_HOUR) {
			throw new IllegalArgumentException("Hours must be between 0 and 23");
		}

		if (open >= close) {
			throw new InvalidScheduleException("h_open must be lower than h_close");
		}
	}
}
