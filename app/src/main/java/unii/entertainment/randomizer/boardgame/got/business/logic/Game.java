package unii.entertainment.randomizer.boardgame.got.business.logic;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

import androidx.annotation.NonNull;

public class Game implements Parcelable {

    @NonNull
    private String name;
    @NonNull
    private SparseArray<House> houseList;

    private int minPlayers;
    private int maxPlayers;

    public Game(@NonNull String name, @NonNull SparseArray<House> houseList, int minPlayers, int maxPlayers) {
        this.name = name;
        this.houseList = houseList;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    protected Game(Parcel in) {
        name = in.readString();
        minPlayers = in.readInt();
        maxPlayers = in.readInt();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public SparseArray<House> getHouseList() {
        return houseList;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(minPlayers);
        dest.writeInt(maxPlayers);
    }
}
