package unii.entertainment.randomizer.boardgame.got.ui.addplayer;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import unii.entertainment.randomizer.boardgame.got.business.logic.Game;
import unii.entertainment.randomizer.boardgame.got.repository.PlayerRepository;

public class AddPlayerViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    private final PlayerRepository playerRepository;
    @NonNull
    private Game selectedGame;


    public AddPlayerViewModelFactory(@NonNull PlayerRepository playerRepository, @NonNull Game selectedGame) {
        this.selectedGame = selectedGame;
        this.playerRepository = playerRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddPlayerViewModel(playerRepository, selectedGame);
    }
}
