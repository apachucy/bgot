package unii.entertainment.randomizer.boardgame.got.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import unii.entertainment.randomizer.boardgame.got.R;
import unii.entertainment.randomizer.boardgame.got.business.logic.PlayerPreferences;
import unii.entertainment.randomizer.boardgame.got.databinding.RowPlayerPrefferedSelectionBinding;

public class PlayerPreferenceAdapter extends RecyclerView.Adapter<PlayerPreferenceAdapter.ViewHolder> {


    @NonNull
    private List<PlayerPreferences> list;

    public PlayerPreferenceAdapter(@NonNull List< PlayerPreferences> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowPlayerPrefferedSelectionBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_player_preffered_selection,
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayerPreferences playerPreferences = list.get(position);
        holder.binding.setPlayerPreference(playerPreferences);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RowPlayerPrefferedSelectionBinding binding;

        public ViewHolder(@NonNull RowPlayerPrefferedSelectionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void deleteItem(int position) {

        list.remove(position);
       notifyItemRemoved(position);
    }
}
