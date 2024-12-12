package humancloud.controller;

import humancloud.dto.SystemGamesRequest;
import humancloud.service.Impl.SystemGamesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system-games")
public class SystemGameController {

    @Autowired
    private SystemGamesServiceImpl systemGameService;

    @GetMapping
    public ResponseEntity<List<SystemGamesRequest>> getAllSystemGames() {
        List<SystemGamesRequest> systemGames = systemGameService.getSystemGames();
        return ResponseEntity.ok(systemGames);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<SystemGamesRequest> getSystemGameById(@PathVariable("gameId") long gameId) {
        SystemGamesRequest systemGame = systemGameService.getSystemGameById(gameId);
        return ResponseEntity.ok(systemGame);
    }

    @PostMapping
    public ResponseEntity<SystemGamesRequest> createSystemGame(@RequestBody SystemGamesRequest systemGameDto) {
        SystemGamesRequest createdGame = systemGameService.addSystemGame(systemGameDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<SystemGamesRequest> updateSystemGame(@PathVariable("gameId") long gameId,
                                                               @RequestBody SystemGamesRequest systemGameDto) {
        SystemGamesRequest updatedGame = systemGameService.updateSystemGame(systemGameDto, gameId);
        return ResponseEntity.ok(updatedGame);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteSystemGame(@PathVariable("gameId") long gameId) {
        systemGameService.deleteSystemGameById(gameId);
        return ResponseEntity.noContent().build();
    }
}
