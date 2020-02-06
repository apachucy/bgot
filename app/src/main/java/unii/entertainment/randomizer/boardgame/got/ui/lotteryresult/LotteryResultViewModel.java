package unii.entertainment.randomizer.boardgame.got.ui.lotteryresult;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.annimon.stream.Stream;
import com.google.common.primitives.Doubles;

import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import unii.entertainment.randomizer.boardgame.got.algorithm.HungarianAlgorithm;
import unii.entertainment.randomizer.boardgame.got.business.logic.Game;
import unii.entertainment.randomizer.boardgame.got.business.logic.HousePreference;
import unii.entertainment.randomizer.boardgame.got.business.logic.PlayerHouseMatched;
import unii.entertainment.randomizer.boardgame.got.business.logic.PlayerPreferences;
import unii.entertainment.randomizer.boardgame.got.repository.GameRepository;
import unii.entertainment.randomizer.boardgame.got.repository.PlayerRepository;

import static org.paukov.combinatorics.CombinatoricsFactory.createPermutationGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

public class LotteryResultViewModel extends ViewModel {

    private final static int MAX_LUCK = 10;
    private final static int BASE_GAME_INDEX = 0;
    private HungarianAlgorithm hungarianAlgorithm;
    @NonNull
    private PlayerRepository repository;
    private double[][] hungarianAlgorithmInput;
    @NonNull
    private List<PlayerHouseMatched> matchedList = new ArrayList<>();
    @NonNull
    private List<PlayerPreferences> playerPreferences;
    @NonNull
    private List<HousePreference> housePreferences;
    @NonNull
    private Generator<PlayerPreferences> generatedPlayerPermutation;
    private int numberOfPlayerReRolls;
    private int numberOfHouseReRolls;
    @NonNull
    private Generator<HousePreference> generatedHousePermutation;

    public LotteryResultViewModel(@NonNull PlayerRepository playerRepository,
                                  @NonNull GameRepository gameRepository, @NonNull Game selectedGame) {
        this.repository = playerRepository;

        this.playerPreferences = new ArrayList<>(Objects.requireNonNull(playerRepository.getAllPlayers().getValue()));
        ICombinatoricsVector<PlayerPreferences> vectorPlayer = createVector(playerPreferences);
        this.generatedPlayerPermutation = createPermutationGenerator(vectorPlayer);
        List<HousePreference> houseList = null;
        if (selectedGame.getName().equals(gameRepository.getAllGames().get(BASE_GAME_INDEX).getName())) {
            houseList = new ArrayList<>(playerPreferences.get(0).getHousePreferences().subList(0, playerPreferences.size()));
        } else {
            houseList = new ArrayList<>(playerPreferences.get(0).getHousePreferences());
        }


        this.housePreferences = new ArrayList<>(houseList);
        ICombinatoricsVector<HousePreference> vectorHouse = createVector(housePreferences);
        this.generatedHousePermutation = createPermutationGenerator(vectorHouse);

        numberOfPlayerReRolls = 0;
        numberOfHouseReRolls = 0;
    }

    private void calculateInput() {
        // if all players has the same luck (base) randomize will not working correctly so there is additional line for randomizing player
        randomizePlayerInput();
        randomizeHouseInput();
        //it won't give me unique solution
        hungarianAlgorithmInput = new double[playerPreferences.size()][];
        for (int i = 0; i < playerPreferences.size(); i++) {
            PlayerPreferences player = playerPreferences.get(i);
            player.setHousePreferences(player.getHousePreferences());
            Integer[] arrayBeforeConversion = Stream.of(randomizePlayerHousePreference(player.getHousePreferences()))
                    .map(HousePreference::getPreference)
                    .toArray(Integer[]::new);

            double[] column = Doubles.toArray(Arrays.asList(arrayBeforeConversion));
            hungarianAlgorithmInput[i] = column;
        }
    }

    @NonNull
    private List<HousePreference> randomizePlayerHousePreference(@NonNull List<HousePreference> playerHousePreferences) {
        List<HousePreference> randomHousePreferences = new ArrayList<>();
        for (HousePreference housePreference : housePreferences) {
            for (HousePreference playerHousePreference : playerHousePreferences) {
                if (housePreference.getHouse().getName().equals(playerHousePreference.getHouse().getName())) {
                    randomHousePreferences.add(playerHousePreference);
                    break;
                }
            }
        }
        return randomHousePreferences;
    }

    private void randomizePlayerInput() {
        playerPreferences.clear();
        int counter = 0;
        if (numberOfPlayerReRolls >= generatedPlayerPermutation.getNumberOfGeneratedObjects()) {
            numberOfPlayerReRolls = 0;
        }
        for (ICombinatoricsVector<PlayerPreferences> combination : generatedPlayerPermutation) {
            if (counter == numberOfPlayerReRolls) {
                playerPreferences.addAll(combination.getVector());
                numberOfPlayerReRolls++;
                break;
            }
            counter++;
        }
    }

    private void randomizeHouseInput() {
        housePreferences.clear();
        int counter = 0;
        if (numberOfHouseReRolls >= generatedHousePermutation.getNumberOfGeneratedObjects()) {
            numberOfHouseReRolls = 0;
        }

        for (ICombinatoricsVector<HousePreference> combination : generatedHousePermutation) {
            if (counter == numberOfHouseReRolls) {
                housePreferences.addAll(combination.getVector());
                numberOfHouseReRolls++;
                break;
            }
            counter++;
        }
    }

    private void reverseInput(double[][] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                inputArray[i][j] = MAX_LUCK - inputArray[i][j];
            }
        }
    }

    @NonNull
    public List<PlayerHouseMatched> executeAlgorithm() {
        calculateInput();
        reverseInput(hungarianAlgorithmInput);
        hungarianAlgorithm = new HungarianAlgorithm(hungarianAlgorithmInput);
        int[] luck = hungarianAlgorithm.execute();
        createMapping(luck);
        return matchedList;
    }

    private void createMapping(int[] luck) {
        matchedList.clear();
        for (int i = 0; i < playerPreferences.size(); i++) {
            PlayerPreferences player = playerPreferences.get(i);
            matchedList.add(new PlayerHouseMatched(i, player.getPlayerName(),
                    player.getHousePreferences().get(luck[i]).getHouse()));
        }
    }

}
