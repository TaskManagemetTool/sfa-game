package humancloud.service;

import humancloud.dto.SystemGamesRequest;

import java.util.List;

public interface SystemGamesService {

    public List<SystemGamesRequest> getSystemGames();
    public SystemGamesRequest getSystemGameById(long gameId);
    public SystemGamesRequest addSystemGame(SystemGamesRequest game);
    public SystemGamesRequest updateSystemGame(SystemGamesRequest game, long gameId);
    public void deleteSystemGameById(long gameId);
}
