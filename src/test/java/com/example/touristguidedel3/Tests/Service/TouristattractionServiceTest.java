package com.example.touristguidedel3.Tests.Service;

import com.example.touristguidedel3.Model.City;
import com.example.touristguidedel3.Model.Tag;
import com.example.touristguidedel3.Model.Touristattraction;
import com.example.touristguidedel3.Repository.TouristattractionRepository;
import com.example.touristguidedel3.Service.TouristattractionService;
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
    private City mockCity;
    private List<Tag> mockTags;

    @BeforeEach
    void setUp() {
        // Opret en mock City fra databasen
        mockCity = new City(1, "København");

        // Opret mock Tags fra databasen
        Tag tag1 = new Tag(1, "Historisk");
        Tag tag2 = new Tag(2, "Kulturelt");
        mockTags = Arrays.asList(tag1, tag2);

        // Opret en mock attraction
        mockAttraction = new Touristattraction(1, "Den Lille Havfrue", "Kendt statue i København", mockCity, mockTags);

        // Mock repository-metoder
        lenient().when(touristattractionRepository.getAttractionByName("Den Lille Havfrue"))
                .thenReturn(mockAttraction);

        lenient().when(touristattractionRepository.getAttractionsTags(mockAttraction.getId()))
                .thenReturn(mockTags);

        lenient().when(touristattractionRepository.findAll())
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
        assertEquals(mockCity, result.getCity()); // Tjekker city
        assertEquals(mockTags, result.getTags()); // Tjekker tags
        verify(touristattractionRepository, times(1)).getAttractionByName("Den Lille Havfrue");
    }

    @Test
    void testGetAttractionsTags() {
        List<Tag> tags = touristattractionService.getAttractionsTags(mockAttraction.getId());
        assertNotNull(tags);
        assertFalse(tags.isEmpty());
        assertEquals(2, tags.size()); // Forventer 2 tags
        assertTrue(tags.contains(mockTags.get(0)));
        assertTrue(tags.contains(mockTags.get(1)));
        verify(touristattractionRepository, times(1)).getAttractionsTags(mockAttraction.getId());
    }

    @Test
    void testGetAllAttractions() {
        List<Touristattraction> attractions = touristattractionService.getAllAttractions();
        assertNotNull(attractions);
        assertFalse(attractions.isEmpty());
        assertEquals("Den Lille Havfrue", attractions.get(0).getName());
        assertEquals(mockCity, attractions.get(0).getCity());
        assertEquals(mockTags, attractions.get(0).getTags());
        verify(touristattractionRepository, times(1)).findAll();
    }

    @Test
    void testSaveAttraction() {
        Touristattraction result = touristattractionService.saveAttraction(mockAttraction);
        assertNotNull(result);
        assertEquals("Den Lille Havfrue", result.getName());
        assertEquals(mockCity, result.getCity());
        assertEquals(mockTags, result.getTags());
        verify(touristattractionRepository, times(1)).saveAttraction(mockAttraction);
    }

    @Test
    void testUpdateAttraction() {
        Touristattraction result = touristattractionService.updateAttraction(mockAttraction);
        assertNotNull(result);
        assertEquals("Den Lille Havfrue", result.getName());
        assertEquals(mockCity, result.getCity());
        assertEquals(mockTags, result.getTags());
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
