package unii.entertainment.randomizer.boardgame.got.ui.binding.utils;

import android.util.SparseArray;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.databinding.BindingAdapter;

import java.util.List;

import unii.entertainment.randomizer.boardgame.got.business.logic.House;
import unii.entertainment.randomizer.boardgame.got.business.logic.HousePreference;

public class CommonUtils {
    @BindingAdapter("iconsForDisplay")
    public static void setImages(LinearLayout view, SparseArray<House> houses) {
        List<House> houseList = (List<House>) houses.get(houses.size() - 1);
        for (House house : houseList) {
            ImageView imageView = new ImageView(view.getContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(128, 128));
            imageView.setImageDrawable(view.getContext().getDrawable(house.getIconRes()));
            view.addView(imageView);
        }
    }


    @BindingAdapter("iconsForDisplay")
    public static void setImages(LinearLayout view, List<HousePreference> housePreferences) {
        for (HousePreference house : housePreferences) {
            ImageView imageView = new ImageView(view.getContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(128, 128));
            imageView.setImageDrawable(view.getContext().getDrawable(house.getHouse().getIconRes()));
            view.addView(imageView);
        }
    }

    @BindingAdapter("srcCompat")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter("errorText")
    public static void bindError(EditText view, String error) {
        view.setError(error);
    }

}
