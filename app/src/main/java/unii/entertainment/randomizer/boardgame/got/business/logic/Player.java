package unii.entertainment.randomizer.boardgame.got.business.logic;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class Player extends BaseObservable {

    @NonNull
    private String playerName;

    public Player() {
        playerName = "";
    }

    @NonNull
    @Bindable
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(@NonNull String playerName) {
        this.playerName = playerName;
        notifyPropertyChanged(BR.playerName);
        notifyPropertyChanged(BR.error);
    }


    @Bindable
    public String getError() {
        if (playerName == null || playerName.length() == 0) {
            return "Too short!";
        } else {
            return null;
        }
    }
}
