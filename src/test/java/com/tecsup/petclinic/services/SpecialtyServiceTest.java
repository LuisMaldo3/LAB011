package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.repositories.SpecialtyRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpecialtyServiceTest {

	@Mock
	private SpecialtyRepository specialtyRepository;

	@InjectMocks
	private SpecialtyServiceImpl specialtyService;


	// INTEGRANTE B - PRUEBA 1
	// Buscar especialidades por consultorio

	@Test
	public void testFindByOffice() {

		String office = "Consultorio 101";

		Specialty specialty1 = new Specialty();
		specialty1.setId(1);
		specialty1.setName("Cardiología");
		specialty1.setOffice(office);

		Specialty specialty2 = new Specialty();
		specialty2.setId(2);
		specialty2.setName("Cirugía");
		specialty2.setOffice(office);

		when(specialtyRepository.findByOffice(office))
				.thenReturn(List.of(specialty1, specialty2));

		List<Specialty> result =
				specialtyService.findByOffice(office);

		assertNotNull(result);
		assertEquals(2, result.size());

		assertTrue(result.stream()
				.allMatch(s -> office.equals(s.getOffice())));

		verify(specialtyRepository, times(1))
				.findByOffice(office);
	}


	// INTEGRANTE B - PRUEBA 2
	// Buscar especialidades por nombre

	@Test
	public void testFindByName() {

		String name = "Cardiología";

		Specialty specialty = new Specialty();
		specialty.setId(1);
		specialty.setName(name);
		specialty.setOffice("Consultorio 101");

		when(specialtyRepository.findByName(name))
				.thenReturn(List.of(specialty));

		List<Specialty> result =
				specialtyService.findByName(name);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(name, result.get(0).getName());

		verify(specialtyRepository, times(1))
				.findByName(name);
	}


	// INTEGRANTE B - PRUEBA 3
	// Eliminar una especialidad

	@Test
	public void testDeleteSpecialty() {

		Integer id = 5;

		Specialty specialty = new Specialty();
		specialty.setId(id);
		specialty.setName("Neurología");
		specialty.setOffice("Consultorio 105");

		when(specialtyRepository.findById(id))
				.thenReturn(Optional.of(specialty));

		specialtyService.delete(id);

		verify(specialtyRepository, times(1))
				.findById(id);

		verify(specialtyRepository, times(1))
				.delete(specialty);
	}
}