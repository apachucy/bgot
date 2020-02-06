package unii.entertainment.randomizer.boardgame.got.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import unii.entertainment.randomizer.boardgame.got.repository.GameRepository;
import unii.entertainment.randomizer.boardgame.got.repository.PlayerRepository;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    private GameRepository gameRepository;

    @NonNull
    private PlayerRepository playerRepository;

    MainViewModelFactory(@NonNull GameRepository gameRepository, @NonNull PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(gameRepository, playerRepository);
    }
}
