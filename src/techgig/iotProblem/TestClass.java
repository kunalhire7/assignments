package techgig.iotProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class TestClass {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        validateNoBoxesOrQuiz(N);
        Map<Integer, List<Integer>> boxesWithChocolates = fillBoxes(N, br.readLine());
        int noOfQuiz = Integer.parseInt(br.readLine());
        validateNoBoxesOrQuiz(noOfQuiz);
        getAnswers(boxesWithChocolates, noOfQuiz, br);
        br.close();
    }

    private static void validateNoBoxesOrQuiz(int n) throws InvalidInputException {
        if (n < 1 || n > 100000) {
            throw new InvalidInputException("Limit Constraint violated for no of boxes or quiz");
        }
    }

    private static Map<Integer, List<Integer>> fillBoxes(int noOfBoxes, String inputLine) throws InvalidInputException {
        Map<Integer, List<Integer>> boxesWithChocolates = new HashMap<>();
        String[] tokens = inputLine.split(" ", -1);
        if(tokens.length != noOfBoxes){
            throw new InvalidInputException("No of boxes and chocolates in each box are not matching");
        }
        int prevNo = 0;
        for (int i = 0; i < tokens.length; i++) {
            int noOfChocolates = Integer.parseInt(tokens[i]);
            if(noOfChocolates < 1 || noOfChocolates > 10){
                throw new InvalidInputException("Limit Constraint violated for no of chocolates in a box");
            }
            boxesWithChocolates.put(i + 1, getChocolates(prevNo, noOfChocolates));
            prevNo = noOfChocolates;
        }
        return boxesWithChocolates;
    }

    private static List<Integer> getChocolates(int prevNo, int noOfChocolates) {
        List<Integer> chocolates = new ArrayList<>();
        int chocolateNo = prevNo + 1;
        for(int i = 0; i < noOfChocolates; i++){
            chocolates.add(chocolateNo);
            chocolateNo++;
        }
        return chocolates;
    }

    private static void getAnswers(Map<Integer, List<Integer>> boxesWithChocolates, int noOfQuiz, BufferedReader br) throws IOException, InvalidInputException {
        int[] answers = new int[noOfQuiz];
        for (int i = 0; i < noOfQuiz; i++) {
            int chocolateNo = Integer.parseInt(br.readLine());
            answers[i] = getAnswer(boxesWithChocolates, chocolateNo);
        }
        for (int answer : answers) {
            System.out.println(answer);
        }
    }

    private static int getAnswer(Map<Integer, List<Integer>> boxesWithChocolates, int chocolateNo) throws InvalidInputException {
        for (Map.Entry<Integer, List<Integer>> entry : boxesWithChocolates.entrySet()) {
            if(entry.getValue().contains(chocolateNo)){
                return entry.getKey();
            }
        }
        return 0;
    }

    private static class InvalidInputException extends Exception {
        public InvalidInputException(String s) {
            super(s);
        }
    }
}