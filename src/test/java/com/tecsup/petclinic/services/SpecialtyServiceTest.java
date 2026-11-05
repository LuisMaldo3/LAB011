package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.InvalidScheduleException;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpecialtyServiceTest {

	@Mock
	private SpecialtyRepository specialtyRepository;

	@InjectMocks
	private SpecialtyServiceImpl specialtyService;

	// ---------- Integrante C: validaciones y errores ----------

	@Test
	public void testFindSpecialtyById_NotFound() {

		when(specialtyRepository.findById(99)).thenReturn(Optional.empty());

		assertThrows(SpecialtyNotFoundException.class,
				() -> specialtyService.findById(99));

		verify(specialtyRepository).findById(99);
	}

	@Test
	public void testCreateSpecialty_InvalidSchedule() {

		// h_open >= h_close, ambos dentro de 0-23
		Specialty invalid = new Specialty(null, "surgery", "Maryland", 18, 8);
		Specialty equalHours = new Specialty(null, "surgery", "Maryland", 8, 8);

		assertThrows(InvalidScheduleException.class,
				() -> specialtyService.create(invalid));
		assertThrows(InvalidScheduleException.class,
				() -> specialtyService.create(equalHours));

		verify(specialtyRepository, never()).save(any(Specialty.class));
	}

	@Test
	public void testCreateSpecialty_InvalidHourRange() {

		Specialty negativeOpen = new Specialty(null, "radiology", "Farewell", -1, 10);
		Specialty closeOver23 = new Specialty(null, "radiology", "Farewell", 8, 24);

		assertThrows(IllegalArgumentException.class,
				() -> specialtyService.create(negativeOpen));
		assertThrows(IllegalArgumentException.class,
				() -> specialtyService.create(closeOver23));

		verify(specialtyRepository, never()).save(any(Specialty.class));
	}
}
