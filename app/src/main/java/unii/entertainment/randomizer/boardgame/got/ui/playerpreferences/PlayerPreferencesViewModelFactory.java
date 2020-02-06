package unii.entertainment.randomizer.boardgame.got.ui.playerpreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import unii.entertainment.randomizer.boardgame.got.business.logic.Game;
import unii.entertainment.randomizer.boardgame.got.repository.PlayerRepository;

public class PlayerPreferencesViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    private Game game;
    @NonNull
    private PlayerRepository repository;
    @NonNull
    private String defaultPlayerName;

    public PlayerPreferencesViewModelFactory(@NonNull PlayerRepository repository, @NonNull Game game,
                                             @NonNull String defaultPlayerName) {
        this.game = game;
        this.repository = repository;
        this.defaultPlayerName = defaultPlayerName;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlayerPreferencesViewModel(repository, game, defaultPlayerName);
    }
}
