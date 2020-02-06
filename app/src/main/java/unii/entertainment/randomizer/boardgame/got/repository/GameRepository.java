package unii.entertainment.randomizer.boardgame.got.repository;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import unii.entertainment.randomizer.boardgame.got.business.logic.Game;
import unii.entertainment.randomizer.boardgame.got.provider.GameProvider;

public class GameRepository {
    @NonNull
    private Context context;

    public GameRepository(@NonNull Context context) {
        this.context = context;
    }

    public List<Game> getAllGames(){
        return new ArrayList<>(GameProvider.getAllGames(context));
    }
}
