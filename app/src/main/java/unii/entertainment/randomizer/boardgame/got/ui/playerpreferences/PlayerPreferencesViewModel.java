package unii.entertainment.randomizer.boardgame.got.ui.playerpreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import unii.entertainment.randomizer.boardgame.got.business.logic.Game;
import unii.entertainment.randomizer.boardgame.got.business.logic.House;
import unii.entertainment.randomizer.boardgame.got.business.logic.HousePreference;
import unii.entertainment.randomizer.boardgame.got.business.logic.Player;
import unii.entertainment.randomizer.boardgame.got.business.logic.PlayerPreferences;
import unii.entertainment.randomizer.boardgame.got.repository.PlayerRepository;

public class PlayerPreferencesViewModel extends ViewModel {

    private final static int DEFAULT_PREFERENCE_FOR_HOUSE = 5;
    @NonNull
    PlayerRepository playerRepository;
    @NonNull
    private List<HousePreference> housePreferences;
    @NonNull
    private Game game;
    @NonNull
    private Player player;

    public PlayerPreferencesViewModel(@NonNull PlayerRepository repository, @NonNull Game game, @NonNull String defaultPlayerName) {
        housePreferences = new ArrayList<>();
        List<House> allHouseList = (List<House>) game.getHouseList().get(game.getHouseList().size() - 1);
        for (House house : allHouseList) {
            HousePreference newHouse = new HousePreference(DEFAULT_PREFERENCE_FOR_HOUSE, house);
            housePreferences.add(newHouse);
        }
        player = new Player();
        player.setPlayerName(defaultPlayerName);
        this.game = game;
        this.playerRepository = repository;
    }

    @NonNull
    public Game getGame() {
        return game;
    }

    @NonNull
    public List<HousePreference> getHousePreferences() {
        return housePreferences;
    }

    @NonNull
    public Player getPlayer() {
        return player;
    }

    @NonNull
    public PlayerPreferences getPlayerWithPreferences() {
        return new PlayerPreferences(player.getPlayerName(), getHousePreferences());
    }

    public boolean isPlayerNameValid() {
        return player.getError() == null;
    }

    public void savePlayer() {
        playerRepository.addPlayer(getPlayerWithPreferences());
    }
}
