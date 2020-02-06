package unii.entertainment.randomizer.boardgame.got.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import unii.entertainment.randomizer.boardgame.got.R;
import unii.entertainment.randomizer.boardgame.got.business.logic.Game;
import unii.entertainment.randomizer.boardgame.got.databinding.RowGameSelectionBinding;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    @NonNull
    private List<Game> gameList;

    @NonNull
    AdapterClickHandler adapterClickHandler;

    public GameAdapter(@NonNull List<Game> gameList, @NonNull AdapterClickHandler adapterClickHandler) {
        this.gameList = gameList;
        this.adapterClickHandler = adapterClickHandler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowGameSelectionBinding rowGameSelectionBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_game_selection,
                parent,
                false);

        return new ViewHolder(rowGameSelectionBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.rowGameSelectionBinding.setClickHandler(adapterClickHandler);
        holder.rowGameSelectionBinding.setGame(game);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RowGameSelectionBinding rowGameSelectionBinding;

        public ViewHolder(RowGameSelectionBinding rowGameSelectionBinding) {
            super(rowGameSelectionBinding.getRoot());
            this.rowGameSelectionBinding = rowGameSelectionBinding;
        }
    }

}
