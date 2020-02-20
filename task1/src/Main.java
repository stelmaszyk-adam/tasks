import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    private static final int MIN_NUMBER_OF_GOALKEEPER = 5;
    private static final int RANDOM_GOALKEEPER_NUMBER = 10;
    private static final int MAX_NUMBER_PLAYERS = 20;

    private static Random randomNumber = new Random();

    public static void main(String[] args) {

        List<Player> parentCollection = generatePlayers();
//        parentCollection.stream().forEach(player -> {System.out.println(player.getName());});
//        System.out.println();

        List<Player> collection1 = fillCollection(parentCollection);
//        collection1.stream().forEach(player -> {System.out.println(player.getName());});
//        System.out.println();

        List<Player> collection2 = fillCollection(parentCollection);
//        collection2.stream().forEach(player -> {System.out.println(player.getName());});
//        System.out.println();

        System.out.println("\nIn Parent collection, but not in child collections");
        parentCollection.stream()
                .filter(player -> !Stream.concat(collection1.stream(),collection2.stream()).collect(Collectors.toList())
                .contains(player))
                .forEach(player -> System.out.println(player.getName()));

        System.out.println("\nIn two collection");
        collection1.stream()
                .filter(player -> collection2.contains(player))
                .forEach(player -> System.out.println(player.getName()));

        System.out.println("\nOnly once");
        collection1.stream()
                .filter(player -> !collection2.contains(player))
                .forEach(player -> System.out.println(player.getName()));
        collection2.stream()
                .filter(player -> !collection1.contains(player))
                .forEach(player -> System.out.println(player.getName()));

    }

    private static List<Player> fillCollection(List<Player> parentCollection) {

        AtomicBoolean isGoolKeeper = new AtomicBoolean(false);
        List<Player> childCollection = new ArrayList<>();
        int playersNumber = randomNumber.nextInt(5) + 5;

        while (5 > childCollection.size()) {
            parentCollection.stream().forEach(player -> {

                if (randomNumber.nextInt(2) == 1 && childCollection.size() < playersNumber) {

                    childCollection.add(player);

                    if (player.getClass().getName() == "Goalkeeper") {
                        isGoolKeeper.set(true);
                    }
                }
            });
        }

        if (!isGoolKeeper.get()) {
            parentCollection.stream().forEach(player -> {
                    if (player.getClass().getName() == "Goalkeeper") {
                        childCollection.remove(childCollection.size()-1);
                        childCollection.add(player);
                        return;
                    }
            });
        }

        return childCollection;
    }

    private static List<Player> generatePlayers() {
        int numberGoalkeeper = randomNumber.nextInt(RANDOM_GOALKEEPER_NUMBER) + MIN_NUMBER_OF_GOALKEEPER;

        Stream<Player> goalkeepers = IntStream.range(0, numberGoalkeeper)
                .mapToObj(player -> new Goalkeeper("Goalkeeper " + String.valueOf(player + 1)));

        Stream<Player> players = IntStream.range(0, MAX_NUMBER_PLAYERS - numberGoalkeeper)
                .mapToObj(player -> new Player("Player " + String.valueOf(player + 1)));

        return Stream.concat(goalkeepers, players).collect(Collectors.toList());
    }
}
