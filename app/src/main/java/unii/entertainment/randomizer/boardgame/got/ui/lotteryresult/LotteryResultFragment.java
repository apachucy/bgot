package unii.entertainment.randomizer.boardgame.got.ui.lotteryresult;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import unii.entertainment.randomizer.boardgame.got.R;
import unii.entertainment.randomizer.boardgame.got.business.logic.Game;
import unii.entertainment.randomizer.boardgame.got.business.logic.PlayerHouseMatched;
import unii.entertainment.randomizer.boardgame.got.databinding.LotteryResultFragmentBinding;
import unii.entertainment.randomizer.boardgame.got.repository.GameRepository;
import unii.entertainment.randomizer.boardgame.got.repository.PlayerRepository;
import unii.entertainment.randomizer.boardgame.got.ui.adapter.PlayerHouseMatchedAdapter;

public class LotteryResultFragment extends Fragment {
    @NonNull
    private RecyclerView recyclerView;
    @NonNull
    private RecyclerView.Adapter adapter;
    @NonNull
    private LotteryResultViewModel viewModel;
    @NonNull
    private LotteryResultFragmentBinding binding;
    @NonNull
    private List<PlayerHouseMatched> list;
    @NonNull
    private LotteryClickHandler lotteryClickHandler = new LotteryClickHandler() {
        @Override
        public void onRandomizeClicked() {
            list = viewModel.executeAlgorithm();
            adapter.notifyDataSetChanged();
        }
    };

    public static LotteryResultFragment newInstance() {
        return new LotteryResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.lottery_result_fragment, container, false);
        binding.setLifecycleOwner(this);
        binding.setOnFabClickHandler(lotteryClickHandler);
        recyclerView = binding.lotteryPlayerHouseList;

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Game selectedGame = LotteryResultFragmentArgs.fromBundle(getArguments()).getSelectedGame();

        viewModel = ViewModelProviders
                .of(this, new LotteryResultViewModelFactory(new PlayerRepository(getContext()), new GameRepository(getContext()), selectedGame))
                .get(LotteryResultViewModel.class);
        list = viewModel.executeAlgorithm();
        adapter = new PlayerHouseMatchedAdapter(list);
        recyclerView.setAdapter(adapter);
    }

}
