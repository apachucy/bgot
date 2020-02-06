package unii.entertainment.randomizer.boardgame.got.preferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import unii.entertainment.randomizer.boardgame.got.business.logic.PlayerPreferences;

public interface Players {

    void addPlayer(@NonNull PlayerPreferences player);

    void removePlayer(@NonNull PlayerPreferences player);

    void removePlayer(int index);

    void editPlayer(@NonNull PlayerPreferences player);

    MutableLiveData<ArrayList<PlayerPreferences>> getAllPlayers();

    void removeAllPlayers();
}
