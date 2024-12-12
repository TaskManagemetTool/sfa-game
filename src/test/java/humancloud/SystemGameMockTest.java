package humancloud;

import humancloud.dto.SystemGamesRequest;
import humancloud.exception.ResourceNotFound;
import humancloud.model.SystemGames;
import humancloud.repository.SystemGamesRepository;
import humancloud.service.Impl.SystemGamesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SystemGameMockTest {


        @Mock
        private SystemGamesRepository systemGamesRepository;

        @InjectMocks
        private SystemGamesServiceImpl systemGamesService;

        private SystemGames systemGame;
        private SystemGamesRequest systemGamesRequest;

        @BeforeEach
        void Setup(){

            //initialize the test data before each test case
            systemGame = new SystemGames();
            systemGame.setGameId(1);
            systemGame.setBusinessId(456);
            systemGame.setFacilityName("Test Facility");
            systemGame.setSportType("Football");
            systemGame.setCapacity(50);
            systemGame.setFacilityStatus("Active");
            systemGame.setHourlyRate(100.0);

            systemGamesRequest = SystemGamesRequest.builder()
                    .businessId(456)
                    .facilityName("Test Facility")
                    .sportType("Football")
                    .capacity(50)
                    .facilityStatus("Active")
                    .hourlyRate(100.0)
                    .build();
        }

        @Test
        void testGetSystemGames(){
            // Arrange
            List<SystemGames> systemGamesList = Arrays.asList(systemGame);
            when(systemGamesRepository.findAll()).thenReturn(systemGamesList);

            List<SystemGamesRequest> allGames = systemGamesService.getSystemGames();

            assertNotNull(allGames);
            assertEquals(1, allGames.size());
            verify(systemGamesRepository).findAll();
        }


        @Test
        void testGetSystemGamesById(){

            when(systemGamesRepository.findById(1L)).thenReturn(Optional.of(systemGame));

            SystemGamesRequest result = systemGamesService.getSystemGameById(1L);


            assertNotNull(result);
            assertEquals(456, result.getBusinessId());
            assertEquals("Test Facility", result.getFacilityName());
            assertEquals("Football", result.getSportType());
            assertEquals(50, result.getCapacity());
            assertEquals("Active", result.getFacilityStatus());
            assertEquals(100.0, result.getHourlyRate());
            verify(systemGamesRepository).findById(1L);
        }

        @Test
        void testGetSystemGameByIdNotFound() {

            when(systemGamesRepository.findById(1L)).thenReturn(Optional.empty());


            assertThrows(ResourceNotFound.class, () -> {
                systemGamesService.getSystemGameById(1L);
            });
        }

        @Test
        void testAddSystemGame() {
            when(systemGamesRepository.save(any(SystemGames.class))).thenReturn(systemGame);


            SystemGamesRequest savedGame = systemGamesService.addSystemGame(systemGamesRequest);


            assertNotNull(savedGame);
            assertEquals(456, savedGame.getBusinessId());
            assertEquals("Test Facility", savedGame.getFacilityName());
            assertEquals("Football", savedGame.getSportType());
            assertEquals(50, savedGame.getCapacity());
            assertEquals("Active", savedGame.getFacilityStatus());
            assertEquals(100.0, savedGame.getHourlyRate());
            verify(systemGamesRepository).save(any(SystemGames.class));
        }

        @Test
        void testUpdateSystemGame() {
            // Arrange
            SystemGamesRequest updatedRequest = SystemGamesRequest.builder()
                    .businessId(457)
                    .facilityName("Updated Facility")
                    .sportType("Basketball")
                    .capacity(60)
                    .facilityStatus("Inactive")
                    .hourlyRate(120.0)
                    .build();

            when(systemGamesRepository.findById(1L)).thenReturn(Optional.of(systemGame));
            when(systemGamesRepository.save(any(SystemGames.class))).thenReturn(systemGame);

            SystemGamesRequest result = systemGamesService.updateSystemGame(updatedRequest, 1L);


            assertNotNull(result);
            verify(systemGamesRepository).findById(1L);
            verify(systemGamesRepository).save(any(SystemGames.class));

        }

        @Test
        void testDeleteSystemGameById() {

            when(systemGamesRepository.findById(1L)).thenReturn(Optional.of(systemGame));
            doNothing().when(systemGamesRepository).delete(systemGame);


            systemGamesService.deleteSystemGameById(1L);

            verify(systemGamesRepository).findById(1L);
            verify(systemGamesRepository).delete(systemGame);
        }

        @Test
        void testDeleteSystemGameByIdNotFound() {

            when(systemGamesRepository.findById(1L)).thenReturn(Optional.empty());


            assertThrows(ResourceNotFound.class, () -> {
                systemGamesService.deleteSystemGameById(1L);
            });
        }

        @Test
        void testUpdateSystemGameWithNullValues() {
            // Arrange
            SystemGamesRequest existingGame = SystemGamesRequest.builder()
                    .businessId(456)
                    .facilityName("Existing Facility")
                    .sportType("Tennis")
                    .build();

            SystemGamesRequest updateRequest = SystemGamesRequest.builder()
                    .facilityName("Updated Facility")
                    .build();

            when(systemGamesRepository.findById(1L)).thenReturn(Optional.of(systemGame));
            when(systemGamesRepository.save(any(SystemGames.class))).thenReturn(systemGame);

            // Act
            SystemGamesRequest result = systemGamesService.updateSystemGame(updateRequest, 1L);

            // Assert
            assertNotNull(result);
            verify(systemGamesRepository).save(any(SystemGames.class));
        }
}
