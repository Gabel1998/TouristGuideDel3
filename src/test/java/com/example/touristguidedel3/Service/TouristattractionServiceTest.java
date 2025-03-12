package com.example.touristguidedel3.Service;

import com.example.touristguidedel3.Model.Cities;
import com.example.touristguidedel3.Model.Tags;
import com.example.touristguidedel3.Model.Touristattraction;
import com.example.touristguidedel3.Repository.TouristattractionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TouristattractionServiceTest {

    @Mock
    private TouristattractionRepository touristattractionRepository;

    @InjectMocks
    private TouristattractionService touristattractionService;

    private Touristattraction mockAttraction;

    @BeforeEach
    void setUp() {
        // Opretter en attraktion i en dansk by med tags
        mockAttraction = new Touristattraction(
                "Den Lille Havfrue",
                "Kendt statue i København",
                new Tags[]{Tags.HISTORISK, Tags.KULTURELT},
                Cities.KOBENHAVN // Dansk by
        );

        lenient().when(touristattractionRepository.getAttractionByName("Den Lille Havfrue"))
                .thenReturn(mockAttraction);

        lenient().when(touristattractionRepository.getAttractionsTags("Den Lille Havfrue"))
                .thenReturn(Arrays.asList(Tags.HISTORISK, Tags.KULTURELT));

        lenient().when(touristattractionRepository.getAllAttractions())
                .thenReturn(Arrays.asList(mockAttraction));

        lenient().when(touristattractionRepository.saveAttraction(any(Touristattraction.class)))
                .thenReturn(mockAttraction);

        lenient().when(touristattractionRepository.updateAttraction(any(Touristattraction.class)))
                .thenReturn(mockAttraction);

        lenient().when(touristattractionRepository.deleteAttraction("Den Lille Havfrue"))
                .thenReturn(mockAttraction);
    }

    @Test
    void testGetAttractionByName() {
        Touristattraction result = touristattractionService.getAttractionByName("Den Lille Havfrue");
        assertNotNull(result);
        assertEquals("Den Lille Havfrue", result.getName());
        assertEquals(Cities.KOBENHAVN, result.getCity()); // Tjekker city
        assertArrayEquals(new Tags[]{Tags.HISTORISK, Tags.KULTURELT}, result.getTags()); // Tjekker tags
        verify(touristattractionRepository, times(1)).getAttractionByName("Den Lille Havfrue");
    }

    @Test
    void testGetAttractionsTags() {
        List<Tags> tags = touristattractionService.getAttractionsTags("Den Lille Havfrue");
        assertNotNull(tags);
        assertFalse(tags.isEmpty());
        assertEquals(2, tags.size()); // Forventer 2 tags
        assertTrue(tags.contains(Tags.HISTORISK));
        assertTrue(tags.contains(Tags.KULTURELT));
        verify(touristattractionRepository, times(1)).getAttractionsTags("Den Lille Havfrue");
    }

    @Test
    void testGetAllAttractions() {
        List<Touristattraction> attractions = touristattractionService.getAllAttractions();
        assertNotNull(attractions);
        assertFalse(attractions.isEmpty());
        assertEquals("Den Lille Havfrue", attractions.get(0).getName());
        assertEquals(Cities.KOBENHAVN, attractions.get(0).getCity());
        assertArrayEquals(new Tags[]{Tags.HISTORISK, Tags.KULTURELT}, attractions.get(0).getTags());
        verify(touristattractionRepository, times(1)).getAllAttractions();
    }

    @Test
    void testSaveAttraction() {
        Touristattraction result = touristattractionService.saveAttraction(mockAttraction);
        assertNotNull(result);
        assertEquals("Den Lille Havfrue", result.getName());
        assertEquals(Cities.KOBENHAVN, result.getCity());
        assertArrayEquals(new Tags[]{Tags.HISTORISK, Tags.KULTURELT}, result.getTags());
        verify(touristattractionRepository, times(1)).saveAttraction(mockAttraction);
    }

    @Test
    void testUpdateAttraction() {
        Touristattraction result = touristattractionService.updateAttraction(mockAttraction);
        assertNotNull(result);
        assertEquals("Den Lille Havfrue", result.getName());
        assertEquals(Cities.KOBENHAVN, result.getCity());
        assertArrayEquals(new Tags[]{Tags.HISTORISK, Tags.KULTURELT}, result.getTags());
        verify(touristattractionRepository, times(1)).updateAttraction(mockAttraction);
    }

    @Test
    void testDeleteAttraction() {
        Touristattraction result = touristattractionService.deleteAttraction("Den Lille Havfrue");
        assertNotNull(result);
        assertEquals("Den Lille Havfrue", result.getName());
        verify(touristattractionRepository, times(1)).deleteAttraction("Den Lille Havfrue");
    }
}
