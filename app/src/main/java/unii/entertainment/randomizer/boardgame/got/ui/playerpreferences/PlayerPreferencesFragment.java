package unii.entertainment.randomizer.boardgame.got.ui.playerpreferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import unii.entertainment.randomizer.boardgame.got.R;
import unii.entertainment.randomizer.boardgame.got.business.logic.Game;
import unii.entertainment.randomizer.boardgame.got.databinding.PlayerPreferencesFragmentBinding;
import unii.entertainment.randomizer.boardgame.got.repository.PlayerRepository;
import unii.entertainment.randomizer.boardgame.got.ui.adapter.HousePreferencesAdapter;

public class PlayerPreferencesFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private PlayerPreferencesViewModel mViewModel;
    private PlayerPreferencesFragmentBinding binding;

    private SavePlayerClickHandler onClickAction = new SavePlayerClickHandler() {
        @Override
        public void onClick() {
            if (mViewModel.isPlayerNameValid()) {
                mViewModel.savePlayer();
                Navigation
                        .findNavController(getView())
                        .navigate(PlayerPreferencesFragmentDirections
                                .actionPlayerPreferencesFragmentToAddPlayerFragment(mViewModel.getGame()));
            } else {
                Toast.makeText(getActivity(), getString(R.string.error_empty_text), Toast.LENGTH_SHORT).show();
            }
        }
    };

    public static PlayerPreferencesFragment newInstance() {
        return new PlayerPreferencesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.player_preferences_fragment, container, false);

        recyclerView = binding.housePreferences;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Game game = PlayerPreferencesFragmentArgs.fromBundle(getArguments()).getGame();

        mViewModel = ViewModelProviders.of(this,
                new PlayerPreferencesViewModelFactory(new PlayerRepository(getContext()),
                        game, getString(R.string.default_player_name)))
                .get(PlayerPreferencesViewModel.class);
        binding.setPlayer(mViewModel.getPlayer());
        binding.setOnFabClickHandler(onClickAction);
        recyclerView.setAdapter(new HousePreferencesAdapter(mViewModel.getHousePreferences()));
    }


}
