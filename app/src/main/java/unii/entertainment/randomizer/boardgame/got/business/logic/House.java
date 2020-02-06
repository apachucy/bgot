package unii.entertainment.randomizer.boardgame.got.business.logic;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class House {

    @NonNull
    private String name;

    @DrawableRes
    private int iconRes;

    public House(@NonNull String name, @DrawableRes int iconRes) {
        this.name = name;
        this.iconRes = iconRes;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @DrawableRes
    public int getIconRes() {
        return iconRes;
    }
}
