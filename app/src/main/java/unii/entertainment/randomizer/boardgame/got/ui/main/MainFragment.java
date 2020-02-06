package unii.entertainment.randomizer.boardgame.got.ui.main;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import unii.entertainment.randomizer.boardgame.got.R;
import unii.entertainment.randomizer.boardgame.got.databinding.MainFragmentBinding;
import unii.entertainment.randomizer.boardgame.got.repository.GameRepository;
import unii.entertainment.randomizer.boardgame.got.repository.PlayerRepository;
import unii.entertainment.randomizer.boardgame.got.ui.adapter.AdapterClickHandler;
import unii.entertainment.randomizer.boardgame.got.ui.adapter.GameAdapter;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private MainFragmentBinding mainFragmentBinding;

    @NonNull
    private AdapterClickHandler adapterClickHandler = game -> Navigation.findNavController(getView())
            .navigate(MainFragmentDirections
                    .actionMainFragmentToAddPlayerFragment(game));

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        recyclerView = mainFragmentBinding.gameList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        return mainFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this,
                new MainViewModelFactory(new GameRepository(getContext()), new PlayerRepository(getContext())))
                .get(MainViewModel.class);
        mViewModel.clearRepository();
        adapter = new GameAdapter(mViewModel.getGameRepository().getAllGames(), adapterClickHandler);
        recyclerView.setAdapter(adapter);

    }

}
