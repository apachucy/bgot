package unii.entertainment.randomizer.boardgame.got.ui.addplayer;

import androidx.annotation.NonNull;

import unii.entertainment.randomizer.boardgame.got.business.logic.Game;

public interface AddNewPlayerClickHandler {
    void onClick(@NonNull Game game);

    void onClickLotteryResult(@NonNull Game game);
}
