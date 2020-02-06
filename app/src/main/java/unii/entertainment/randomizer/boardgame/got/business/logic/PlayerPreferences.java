package unii.entertainment.randomizer.boardgame.got.business.logic;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class PlayerPreferences implements Parcelable {
    @NonNull
    private String playerName;

    @NonNull
    private List<HousePreference> housePreferences;

    public PlayerPreferences(@NonNull String playerName, @NonNull List<HousePreference> housePreferences) {
        this.playerName = playerName;
        this.housePreferences = housePreferences;
    }

    protected PlayerPreferences(Parcel in) {
        playerName = in.readString();
    }

    public static final Creator<PlayerPreferences> CREATOR = new Creator<PlayerPreferences>() {
        @Override
        public PlayerPreferences createFromParcel(Parcel in) {
            return new PlayerPreferences(in);
        }

        @Override
        public PlayerPreferences[] newArray(int size) {
            return new PlayerPreferences[size];
        }
    };

    @NonNull
    public String getPlayerName() {
        return playerName;
    }

    @NonNull
    public List<HousePreference> getHousePreferences() {
        return housePreferences;
    }

    public void setHousePreferences(@NonNull List<HousePreference> housePreferences) {
        this.housePreferences = housePreferences;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(playerName);
    }
}
