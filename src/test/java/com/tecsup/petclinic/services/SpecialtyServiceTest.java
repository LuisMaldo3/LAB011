package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.repositories.SpecialtyRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Grupo 3 - Integrante A
@ExtendWith(MockitoExtension.class)
class SpecialtyServiceTest {

    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialtyServiceImpl specialtyService;

    // PRUEBA 1: CREAR ESPECIALIDAD
    @Test
    void testCreateSpecialty() {

        // Arrange
        Specialty nueva = new Specialty(
                null, "Cardiología", "Consultorio A", 8, 17
        );

        Specialty guardada = new Specialty(
                10, "Cardiología", "Consultorio A", 8, 17
        );

        when(specialtyRepository.save(any(Specialty.class)))
                .thenReturn(guardada);

        // Act
        Specialty resultado = specialtyService.create(nueva);

        // Assert
        assertNotNull(resultado);
        assertEquals(10, resultado.getId());
        assertEquals("Cardiología", resultado.getName());
        assertEquals("Consultorio A", resultado.getOffice());
        assertEquals(8, resultado.getHOpen());
        assertEquals(17, resultado.getHClose());

        ArgumentCaptor<Specialty> captor =
                ArgumentCaptor.forClass(Specialty.class);

        verify(specialtyRepository, times(1))
                .save(captor.capture());

        assertNull(captor.getValue().getId());
        assertEquals("Cardiología", captor.getValue().getName());
    }

    // PRUEBA 2: ACTUALIZAR ESPECIALIDAD
    @Test
    void testUpdateSpecialty() {

        // Arrange
        Specialty existente = new Specialty(
                3, "Cirugía", "Consultorio 1", 8, 12
        );

        Specialty cambios = new Specialty(
                3, "Cirugía avanzada", "Consultorio 2", 9, 16
        );

        when(specialtyRepository.findById(3))
                .thenReturn(Optional.of(existente));

        when(specialtyRepository.save(any(Specialty.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Specialty resultado = specialtyService.update(cambios);

        // Assert
        assertNotNull(resultado);
        assertEquals(3, resultado.getId());
        assertEquals("Cirugía avanzada", resultado.getName());
        assertEquals("Consultorio 2", resultado.getOffice());
        assertEquals(9, resultado.getHOpen());
        assertEquals(16, resultado.getHClose());

        verify(specialtyRepository, times(1)).findById(3);
        verify(specialtyRepository, times(1))
                .save(any(Specialty.class));
    }

    // PRUEBA 3: BUSCAR ESPECIALIDAD POR ID
    @Test
    void testFindSpecialtyById() {

        // Arrange
        Specialty esperada = new Specialty(
                5, "Radiología", "Consultorio 3", 7, 15
        );

        when(specialtyRepository.findById(5))
                .thenReturn(Optional.of(esperada));

        // Act
        Specialty resultado = specialtyService.findById(5);

        // Assert
        assertNotNull(resultado);
        assertSame(esperada, resultado);
        assertEquals(5, resultado.getId());
        assertEquals("Radiología", resultado.getName());
        assertEquals("Consultorio 3", resultado.getOffice());
        assertEquals(7, resultado.getHOpen());
        assertEquals(15, resultado.getHClose());

        verify(specialtyRepository, times(1)).findById(5);
        verify(specialtyRepository, never())
                .save(any(Specialty.class));
    }
}
