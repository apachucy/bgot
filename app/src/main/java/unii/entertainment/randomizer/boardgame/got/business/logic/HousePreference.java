package unii.entertainment.randomizer.boardgame.got.business.logic;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class HousePreference extends BaseObservable {

    private int preference;
    @NonNull
    private House house;

    public HousePreference(int preference, @NonNull House house) {
        this.preference = preference;
        this.house = house;
    }

    @NonNull
    public House getHouse() {
        return house;
    }

    @Bindable
    public int getPreference() {
        return preference;
    }

    public void setPreference(int preference) {
        this.preference = preference;
        notifyPropertyChanged(BR.preference);
    }
}
