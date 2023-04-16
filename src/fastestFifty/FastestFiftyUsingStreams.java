package fastestFifty;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FastestFiftyUsingStreams {

    public static int sum;

    public static void calculateFifty(List<Integer> runs) {
        Map<String, Integer> result = IntStream.rangeClosed(9, runs.size())
                .mapToObj(numberOfBalls -> {
                    sum = IntStream.range(0, numberOfBalls)
                            .map(runs::get)
                            .sum();

                    return IntStream.range(numberOfBalls, runs.size())
                            .mapToObj(i -> {
                                if (sum >= 50) {
                                    return Map.of("Sum", sum, "NumberOfBalls", numberOfBalls, "IndexStart", i - numberOfBalls, "IndexEnd", numberOfBalls);
                                }
                                sum = sum + runs.get(i) - runs.get(i - numberOfBalls);
                                return null;
                            })
                            .filter(Objects::nonNull).findFirst().orElse(null);
                }).filter(Objects::nonNull).findFirst().orElse(null);
        if (result != null) {
            System.out.println("Sum is " + result.get("Sum") + " in " + result.get("NumberOfBalls") + "\n" +
                    "It is scored between ball " + result.get("IndexStart") + " and " + result.get("IndexEnd"));
        }
    }


    public static void main(String[] args) {
        List<Integer> runs = Arrays.asList(6, 4, 1, 0, 6, 1, 2, 3, 6, 1, 6, 1, 6, 1, 6, 6, 1, 6, 6, 1, 0, 4);
        calculateFifty(runs);
    }
}