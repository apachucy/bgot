package unii.entertainment.randomizer.boardgame.got.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import unii.entertainment.randomizer.boardgame.got.business.logic.PlayerPreferences;
import unii.entertainment.randomizer.boardgame.got.preferences.Players;
import unii.entertainment.randomizer.boardgame.got.preferences.SharedPlayerPreferences;

public class PlayerRepository {
    @NonNull
    private Players currentPlayers;

    public PlayerRepository(@NonNull Context context) {
        currentPlayers = new SharedPlayerPreferences(context.getApplicationContext());
    }

    public void addPlayer(@NonNull PlayerPreferences player) {
        currentPlayers.addPlayer(player);
    }


    public MutableLiveData<ArrayList<PlayerPreferences>> getAllPlayers() {
        return currentPlayers.getAllPlayers();
    }

    public void removePlayer(int index) {
        currentPlayers.removePlayer(index);
    }

    public void removeAllPlayers() {
        currentPlayers.removeAllPlayers();
    }
}
