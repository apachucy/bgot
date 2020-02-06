package unii.entertainment.randomizer.boardgame.got.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import unii.entertainment.randomizer.boardgame.got.business.logic.PlayerPreferences;

public class SharedPlayerPreferences implements Players {
    @NonNull
    private SharedPreferences appSharedPrefs;
    @NonNull
    private Gson gson;

    @NonNull
    private MutableLiveData<ArrayList<PlayerPreferences>> playerPreferencesArrayList;

    private static final String PLAYER_LIST_KEY = "PLAYER_LIST_KEY" + SharedPlayerPreferences.class.getCanonicalName();

    /**
     * TODO: Fix saving to shpref -
     * w momencie kiedy zmienają się zasoby graficzne
     * zapisane Resy dla domów robią się niepoprawne i odwołują się do złych ikonek
     *
     * @param context
     */
    public SharedPlayerPreferences(@NonNull Context context) {
        appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        gson = new Gson();
        getAllPlayers();
    }

    @Override
    public void addPlayer(@NonNull PlayerPreferences player) {
        playerPreferencesArrayList.getValue().add(player);
        playerPreferencesArrayList.setValue(playerPreferencesArrayList.getValue());
        saveList();
    }

    @Override
    public void removePlayer(@NonNull PlayerPreferences player) {
        playerPreferencesArrayList.getValue().remove(player);
        playerPreferencesArrayList.setValue(playerPreferencesArrayList.getValue());
        saveList();
    }

    @Override
    public void removePlayer(int index) {
        playerPreferencesArrayList.getValue().remove(index);
        playerPreferencesArrayList.setValue(playerPreferencesArrayList.getValue());
        saveList();
    }

    @Override
    public void editPlayer(@NonNull PlayerPreferences player) {
        PlayerPreferences playerLoaded = null;
        for (PlayerPreferences temp : playerPreferencesArrayList.getValue()) {
            if (temp.getPlayerName().equals(player.getPlayerName())) {
                playerLoaded = temp;
                break;
            }
        }
        if (playerLoaded != null) {
            playerPreferencesArrayList.getValue().remove(playerLoaded);
            playerPreferencesArrayList.getValue().add(player);

            playerPreferencesArrayList.setValue(playerPreferencesArrayList.getValue());
            saveList();
        }
    }

    @Override
    public MutableLiveData<ArrayList<PlayerPreferences>> getAllPlayers() {
        if (playerPreferencesArrayList == null || playerPreferencesArrayList.getValue() == null) {
            playerPreferencesArrayList = new MutableLiveData<>();
            playerPreferencesArrayList.setValue(new ArrayList<>());
            String data = appSharedPrefs.getString(PLAYER_LIST_KEY, "");
            Type type = new TypeToken<List<PlayerPreferences>>() {
            }.getType();

            List<PlayerPreferences> playerPreferences = gson.fromJson(data, type);

            if (playerPreferences != null && !playerPreferences.isEmpty()) {
                playerPreferencesArrayList.getValue().addAll(playerPreferences);
                playerPreferencesArrayList.setValue(playerPreferencesArrayList.getValue());

            }
        }

        return playerPreferencesArrayList;
    }

    @Override
    public void removeAllPlayers() {
        // playerPreferencesArrayList.getValue().clear();
        playerPreferencesArrayList.setValue(new ArrayList<>());

        saveList();
    }

    private void saveList() {
        String json = gson.toJson(playerPreferencesArrayList.getValue());
        appSharedPrefs.edit().putString(PLAYER_LIST_KEY, json).apply();
    }
}
