package humancloud.service.Impl;

import humancloud.dto.SystemGamesRequest;
import humancloud.exception.ResourceNotFound;
import humancloud.model.SystemGames;
import humancloud.repository.SystemGamesRepository;
import humancloud.service.SystemGamesService;
import humancloud.util.SystemGamesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemGamesServiceImpl implements SystemGamesService {

    @Autowired
    private SystemGamesRepository systemGamesRepository;

    @Override
    public List<SystemGamesRequest> getSystemGames() {
        List<SystemGames> systemGames = systemGamesRepository.findAll();
        return systemGames.stream()
                .map(SystemGamesUtils::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SystemGamesRequest getSystemGameById(long gameId) {
        SystemGames gameById = systemGamesRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFound("System game not found with id: " + gameId));
        return SystemGamesUtils.mapToDto(gameById);
    }

    @Override
    public SystemGamesRequest addSystemGame(SystemGamesRequest game) {
        SystemGames systemGame = SystemGamesUtils.mapToEntity(game);
        SystemGames savedGame = systemGamesRepository.save(systemGame);
        return SystemGamesUtils.mapToDto(savedGame);
    }

    @Override
    public SystemGamesRequest updateSystemGame(SystemGamesRequest game, long gameId) {

        SystemGamesRequest systemGame = getSystemGameById(gameId);
        SystemGamesRequest finalGameRequest = systemGame.builder()
                .businessId(game.getBusinessId())
                .capacity(game.getCapacity())
                .facilityName(game.getFacilityName())
                .sportType(game.getSportType())
                .facilityStatus(game.getFacilityStatus())
                .hourlyRate(game.getHourlyRate())
                .build();
        return addSystemGame(finalGameRequest);
    }

    @Override
    public void deleteSystemGameById(long gameId) {
        SystemGames systemGame = systemGamesRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFound("System game not found with id: " + gameId));
        systemGamesRepository.delete(systemGame);
    }
}
