package unii.entertainment.randomizer.boardgame.got.ui.addplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import unii.entertainment.randomizer.boardgame.got.R;
import unii.entertainment.randomizer.boardgame.got.business.logic.Game;
import unii.entertainment.randomizer.boardgame.got.business.logic.PlayerPreferences;
import unii.entertainment.randomizer.boardgame.got.databinding.AddPlayerFragmentBinding;
import unii.entertainment.randomizer.boardgame.got.repository.PlayerRepository;
import unii.entertainment.randomizer.boardgame.got.ui.adapter.PlayerPreferenceAdapter;
import unii.entertainment.randomizer.boardgame.got.ui.adapter.SwipeToDeleteCallback;

public class AddPlayerFragment extends Fragment {
    private AddPlayerFragmentBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private AddPlayerViewModel mViewModel;
    private List<PlayerPreferences> playerPreferencesList;
    @NonNull
    private AddNewPlayerClickHandler addNewPlayerClickHandler = new AddNewPlayerClickHandler() {
        @Override
        public void onClick(@NonNull Game game) {
            Navigation
                    .findNavController(getView())
                    .navigate(AddPlayerFragmentDirections
                            .actionAddPlayerFragmentToPlayerPreferencesFragment(game));
        }

        @Override
        public void onClickLotteryResult(@NonNull Game game) {
            Navigation
                    .findNavController(getView())
                    .navigate(AddPlayerFragmentDirections
                            .actionAddPlayerFragmentToLotteryResultFragment(game)
                    );
        }
    };

    public static AddPlayerFragment newInstance() {
        return new AddPlayerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_player_fragment, container, false);
        binding.setLifecycleOwner(this);

        recyclerView = binding.playerList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Game game = AddPlayerFragmentArgs.fromBundle(getArguments()).getGame();
        PlayerRepository playerRepository = new PlayerRepository(getContext());
        mViewModel = ViewModelProviders
                .of(this, new AddPlayerViewModelFactory(playerRepository, game))
                .get(AddPlayerViewModel.class);

        binding.setSelectedGame(mViewModel.getSelectedGame());
        binding.setFabClickHandler(addNewPlayerClickHandler);
        binding.setViewModel(mViewModel);


        mViewModel.getPlayerList().observe(this, playerPreferences -> {
                    mViewModel.validPlayerNumbers(playerPreferences.size());
                }
        );
        playerPreferencesList = new ArrayList<>(mViewModel.getPlayerList().getValue());
        adapter = new PlayerPreferenceAdapter(playerPreferencesList);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback((PlayerPreferenceAdapter) adapter, playerRepository, getResources().getColor(R.color.colorPrimaryDark)));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        mViewModel.isNumberOfPlayersValid().observe(this, aBoolean -> {
            binding.addNewPlayerFab.setEnabled(mViewModel.playersInUpperLimit());
            binding.lotteryFab.setEnabled(aBoolean &&
                    mViewModel.playersInRange());
        });
    }

}
