package unii.entertainment.randomizer.boardgame.got.business.logic;

import androidx.annotation.NonNull;

public class PlayerHouseMatched {

    private int id;
    @NonNull
    private String playerName;
    @NonNull
    private House house;

    @NonNull
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(@NonNull String playerName) {
        this.playerName = playerName;
    }

    @NonNull
    public House getHouse() {
        return house;
    }

    public void setHouse(@NonNull House house) {
        this.house = house;
    }

    public PlayerHouseMatched(int id, @NonNull String playerName, @NonNull House house) {
        this.id = id;
        this.playerName = playerName;
        this.house = house;
    }

    public int getId() {
        return id;
    }
}
