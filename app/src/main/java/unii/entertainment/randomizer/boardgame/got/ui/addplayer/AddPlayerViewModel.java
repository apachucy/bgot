package unii.entertainment.randomizer.boardgame.got.ui.addplayer;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import unii.entertainment.randomizer.boardgame.got.business.logic.Game;
import unii.entertainment.randomizer.boardgame.got.business.logic.HousePreference;
import unii.entertainment.randomizer.boardgame.got.business.logic.PlayerPreferences;
import unii.entertainment.randomizer.boardgame.got.repository.PlayerRepository;

public class AddPlayerViewModel extends ViewModel {
    @NonNull
    private Game selectedGame;
    @NonNull
    private PlayerRepository playerRepository;
    @NonNull
    private int currentPlayers;
    private MutableLiveData<Boolean> isNumberOfPlayersValid;
    @NonNull
    private MutableLiveData<ArrayList<PlayerPreferences>> playerList;

    private int minPlayers;
    private int maxPlayers;

    public AddPlayerViewModel(@NonNull PlayerRepository playerRepository, @NonNull Game selectedGame) {
        this.selectedGame = selectedGame;
        currentPlayers = selectedGame.getMinPlayers();
        maxPlayers = selectedGame.getMaxPlayers();
        minPlayers = selectedGame.getMinPlayers();
        this.playerRepository = playerRepository;
        playerList = playerRepository.getAllPlayers();
        if (playerList.getValue().size() > currentPlayers && playerList.getValue().size() <= maxPlayers) {
            currentPlayers = playerList.getValue().size();
        }
        sortPlayerPreferences(playerList.getValue());
        isNumberOfPlayersValid = new MutableLiveData<>();
        isNumberOfPlayersValid.setValue(true);

    }


    @NonNull
    public Game getSelectedGame() {
        return selectedGame;
    }

    public void setSelectedGame(@NonNull Game selectedGame) {
        this.selectedGame = selectedGame;
    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }


    public MutableLiveData<Boolean> isNumberOfPlayersValid() {
        return isNumberOfPlayersValid;
    }

    public MutableLiveData<ArrayList<PlayerPreferences>> getPlayerList() {
        return playerList;
    }

    public void onPlayerCountChanged(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            isNumberOfPlayersValid.setValue(false);
            return;
        }
        currentPlayers = Integer.parseInt(text.toString());
        validPlayerNumbers(playerList.getValue().size());
    }

    private void sortPlayerPreferences(List<PlayerPreferences> listForSorting) {
        for (PlayerPreferences playerPreferences : listForSorting) {
            sortListDesc(playerPreferences.getHousePreferences());
        }
    }

    private void sortListDesc(@NonNull List<HousePreference> housePreferences) {
        Collections.sort(housePreferences, (o1, o2) ->
        {
            if (o1.getPreference() == o2.getPreference()) {
                return 0;
            } else {
                return o1.getPreference() > o2.getPreference() ? -1 : 1;
            }
        });
    }


    public void validPlayerNumbers(int players) {
        if (currentPlayers >= minPlayers && maxPlayers >= currentPlayers
                && players >= minPlayers && players <= maxPlayers) {
            isNumberOfPlayersValid.setValue(true);
        } else {
            isNumberOfPlayersValid.setValue(false);
        }
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public boolean playersInUpperLimit() {
        return getCurrentPlayers() <= getMaxPlayers();
    }

    public boolean playersInRange() {
        return getCurrentPlayers() >= getMinPlayers()
                && getCurrentPlayers() <= getMaxPlayers();
    }

}
