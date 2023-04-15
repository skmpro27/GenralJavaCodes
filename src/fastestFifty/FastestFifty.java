package fastestFifty;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class FastestFifty {

    public static int sum;

    public static void calculateFifty(List<Integer> runs) {
        String result = IntStream.rangeClosed(9, runs.size())
                .mapToObj(numberOfBalls -> {
                    sum = IntStream.range(0, numberOfBalls)
                            .map(runs::get)
                            .sum();

                    return IntStream.range(numberOfBalls, runs.size())
                            .mapToObj(i -> {
                                if (sum >= 50) {
                                    return "Score is " + sum + " in " + numberOfBalls + "\nIt is scored between ball " +
                                            (i - numberOfBalls) + " and " + i;
                                } else {
                                    sum = sum + runs.get(i) - runs.get(i - numberOfBalls);
                                    return sum + " + " + runs.get(i) + " - " + runs.get(i - numberOfBalls) + " = " + sum;
                                }
                            })
                            .filter(str -> str.startsWith("Score"))
                            .findFirst();
                })
                .filter(Optional::isPresent)
                .map(Optional::get).findFirst().orElse("not found");
        System.out.println(result);
    }


    public static void main(String[] args) {
        List<Integer> runs = Arrays.asList(6, 4, 1, 0, 6, 1, 2, 3, 6, 1, 6, 1, 6, 1, 6, 6, 1, 6, 6, 1, 0, 4);
        calculateFifty(runs);
    }
}