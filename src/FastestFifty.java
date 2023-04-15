import java.util.Arrays;
import java.util.List;

public class FastestFifty {

    public static void calculateFifty(List<Integer> runs) {
        int numberOfBalls = 9;
        while (numberOfBalls < runs.size()) {
            int sum = runs.stream()
                    .mapToInt(Integer::intValue)
                    .limit(numberOfBalls).sum();
            for (int i = numberOfBalls; i < runs.size(); i++) {
                if(sum >= 50) {
                    System.out.println("Score is " + sum + " in " + numberOfBalls);
                    System.out.println("It is scored between ball " + (i - numberOfBalls) + " and " + i);
                    break;
                } else {
                    System.out.print(sum + " + " + runs.get(i) + " - " + runs.get(i - numberOfBalls));
                    sum = sum + runs.get(i) - runs.get(i - numberOfBalls);
                    System.out.println(" = " + sum);
                }
            }
            if (sum >= 50) {
                break;
            }
            System.out.println("Number of ball increase");
            numberOfBalls++;
        }
    }

    public static void main(String[] args) {
        List<Integer> runs = Arrays.asList(6, 4, 1, 0, 6, 1, 2, 3, 6, 1, 6, 1, 6, 1, 6, 6, 1, 6, 6, 1, 0, 4);
        calculateFifty(runs);
    }
}