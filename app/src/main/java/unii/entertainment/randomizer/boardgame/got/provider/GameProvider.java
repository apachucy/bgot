package unii.entertainment.randomizer.boardgame.got.provider;

import android.content.Context;
import android.util.SparseArray;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import unii.entertainment.randomizer.boardgame.got.R;
import unii.entertainment.randomizer.boardgame.got.business.logic.Game;
import unii.entertainment.randomizer.boardgame.got.business.logic.House;

public class GameProvider {

    private GameProvider() {
    }

    @NonNull
    public static List<Game> getAllGames(@NonNull Context context) {
        ArrayList<Game> games = new ArrayList<>();
        games.add(createBaseGame(context));
        games.add(createFeastForCrows(context));
        games.add(createDanceWithDragons(context));
        games.add(createMotherWithDragons(context));
        games.add(createMotherWithDragonsWithoutTargaryens(context));
        return games;
    }

    @NonNull
    private static Game createFeastForCrows(@NonNull Context context) {
        SparseArray array = new SparseArray(1);
        ArrayList<House> players_4 = new ArrayList<>(Arrays.asList(createHouseBarethon(context),
                createHouseStark(context),
                createHouseLannister(context),
                createHouseArryn(context)));
        array.put(0, players_4);
        return new Game(context.getString(R.string.game_first_addition), array, 4, 4);
    }

    @NonNull
    private static Game createDanceWithDragons(@NonNull Context context) {
        SparseArray array = new SparseArray(1);
        ArrayList<House> players_6 = new ArrayList<>(Arrays.asList(createHouseBarethon(context),
                createHouseStark(context),
                createHouseLannister(context),
                createHouseGreyjoy(context),
                createHouseTyrell(context),
                createHouseMartell(context)));
        array.put(0, players_6);
        return new Game(context.getString(R.string.game_second_addition), array, 6, 6);
    }

    @NonNull
    private static Game createMotherWithDragonsWithoutTargaryens(@NonNull Context context) {
        SparseArray array = new SparseArray(1);
        ArrayList<House> players_7 = new ArrayList<>(Arrays.asList(createHouseBarethon(context),
                createHouseStark(context),
                createHouseLannister(context),
                createHouseGreyjoy(context),
                createHouseTyrell(context),
                createHouseMartell(context),
                createHouseArryn(context)));
        array.put(0, players_7);
        return new Game(context.getString(R.string.game_third_addition_without_dragons), array, 3, 7);
    }

    @NonNull
    private static Game createMotherWithDragons(@NonNull Context context) {
        SparseArray array = new SparseArray(1);
        ArrayList<House> players_7 = new ArrayList<>(Arrays.asList(createHouseBarethon(context),
                createHouseStark(context),
                createHouseLannister(context),
                createHouseGreyjoy(context),
                createHouseTyrell(context),
                createHouseMartell(context),
                createHouseArryn(context)));
        array.put(0, players_7);
        ArrayList<House> players_8 = new ArrayList<>();
        players_8.addAll(players_7);
        players_8.add(createHouseTargaryen(context));
        array.put(1, players_8);
        return new Game(context.getString(R.string.game_third_addition), array, 3, 8);
    }

    @NonNull
    private static Game createBaseGame(@NonNull Context context) {
        SparseArray array = new SparseArray(4);
        ArrayList<House> players_3 = new ArrayList<>(Arrays.asList(createHouseBarethon(context),
                createHouseLannister(context),
                createHouseStark(context)));
        array.put(0, players_3);
        ArrayList<House> players_4 = new ArrayList<>();
        players_4.addAll(players_3);
        players_4.add(createHouseGreyjoy(context));
        array.put(1, new ArrayList<>(players_4));
        ArrayList<House> players_5 = new ArrayList<>();
        players_5.addAll(players_4);
        players_5.add(createHouseTyrell(context));
        array.put(2, new ArrayList<>(players_5));
        ArrayList<House> players_6 = new ArrayList<>();
        players_6.addAll(players_5);
        players_6.add(createHouseMartell(context));
        array.put(3, new ArrayList<>(players_6));
        Game baseGame = new Game(context.getString(R.string.base_game), array, 3, 6);
        return baseGame;
    }

    @NonNull
    private static House createHouseBarethon(@NonNull Context context) {
        return new House(context.getString(R.string.house_barethon), R.drawable.ic_baratheon);
    }

    @NonNull
    private static House createHouseStark(@NonNull Context context) {
        return new House(context.getString(R.string.house_stark), R.drawable.ic_stark);
    }

    @NonNull
    private static House createHouseGreyjoy(@NonNull Context context) {
        return new House(context.getString(R.string.house_greyjoy), R.drawable.ic_greyjoy);
    }

    @NonNull
    private static House createHouseLannister(@NonNull Context context) {
        return new House(context.getString(R.string.house_lannister), R.drawable.ic_lannister);
    }

    @NonNull
    private static House createHouseMartell(@NonNull Context context) {
        return new House(context.getString(R.string.house_martell), R.drawable.ic_martell);
    }

    @NonNull
    private static House createHouseTyrell(@NonNull Context context) {
        return new House(context.getString(R.string.house_tyrell), R.drawable.ic_tyrell);
    }

    @NonNull
    private static House createHouseArryn(Context context) {
        return new House(context.getString(R.string.house_arryn), R.drawable.ic_arryn);
    }

    @NonNull
    private static House createHouseTargaryen(Context context) {
        return new House(context.getString(R.string.house_targaryen), R.drawable.ic_targaryen);
    }
}
