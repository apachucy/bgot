package unii.entertainment.randomizer.boardgame.got.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import unii.entertainment.randomizer.boardgame.got.repository.GameRepository;
import unii.entertainment.randomizer.boardgame.got.repository.PlayerRepository;

public class MainViewModel extends ViewModel {

    @NonNull
    private GameRepository gameRepository;
    @NonNull
    private PlayerRepository playerRepository;

    public MainViewModel(@NonNull GameRepository gameRepository, @NonNull PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @NonNull
    public GameRepository getGameRepository() {
        return gameRepository;
    }

    public void clearRepository() {
        playerRepository.removeAllPlayers();
    }
}
