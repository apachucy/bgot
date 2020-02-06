package unii.entertainment.randomizer.boardgame.got.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import unii.entertainment.randomizer.boardgame.got.R;
import unii.entertainment.randomizer.boardgame.got.business.logic.HousePreference;
import unii.entertainment.randomizer.boardgame.got.databinding.RowHousePreferenceBinding;

public class HousePreferencesAdapter extends RecyclerView.Adapter<HousePreferencesAdapter.ViewHolder> {
    @NonNull
    private List<HousePreference> houseList;

    public HousePreferencesAdapter(@NonNull List<HousePreference> houseList) {
        this.houseList = houseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowHousePreferenceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_house_preference,
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HousePreference housePreference = houseList.get(position);
        holder.binding.setHousePreference(housePreference);
    }

    @Override
    public int getItemCount() {
        return houseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RowHousePreferenceBinding binding;

        public ViewHolder(@NonNull RowHousePreferenceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
