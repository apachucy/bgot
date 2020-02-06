package unii.entertainment.randomizer.boardgame.got.ui.lotteryresult;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import unii.entertainment.randomizer.boardgame.got.business.logic.Game;
import unii.entertainment.randomizer.boardgame.got.repository.GameRepository;
import unii.entertainment.randomizer.boardgame.got.repository.PlayerRepository;

public class LotteryResultViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    private PlayerRepository repository;
    @NonNull
    private GameRepository gameRepository;
    @NonNull
    private Game selectedGame;

    public LotteryResultViewModelFactory(@NonNull PlayerRepository repository,
                                         @NonNull GameRepository gameRepository, @NonNull Game selectedGame) {
        this.repository = repository;
        this.gameRepository = gameRepository;
        this.selectedGame = selectedGame;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LotteryResultViewModel(repository, gameRepository, selectedGame);
    }
}