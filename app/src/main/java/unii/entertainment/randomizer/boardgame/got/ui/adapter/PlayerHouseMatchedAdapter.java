package unii.entertainment.randomizer.boardgame.got.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import unii.entertainment.randomizer.boardgame.got.R;
import unii.entertainment.randomizer.boardgame.got.business.logic.PlayerHouseMatched;
import unii.entertainment.randomizer.boardgame.got.databinding.RowHouseSelectedBinding;

public class PlayerHouseMatchedAdapter extends RecyclerView.Adapter<PlayerHouseMatchedAdapter.ViewHolder> {
    @NonNull
    private List<PlayerHouseMatched> playerHouseMatchedList;


    public PlayerHouseMatchedAdapter(@NonNull List<PlayerHouseMatched> playerHouseMatchedList) {
        this.playerHouseMatchedList = playerHouseMatchedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowHouseSelectedBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_house_selected,
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayerHouseMatched item = playerHouseMatchedList.get(position);
        holder.binding.setMatch(item);
    }

    @Override
    public int getItemCount() {
        return playerHouseMatchedList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RowHouseSelectedBinding binding;

        public ViewHolder(@NonNull RowHouseSelectedBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
