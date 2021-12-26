package org.nsu.fit.shared;

import java.util.Random;

public class RandomClientDataGenerator {
    private final static Random rnd = new Random();

    private static int getNextIntInRange(int startRange, int endRange) {
        int result = (Math.abs(rnd.nextInt()) % (endRange));
        if (result < startRange) result += (endRange - startRange);
        return result;
    }

    public static String createRandomName(int minSize, int maxSize) {
        int numLetters = 26;
        int nameLength = getNextIntInRange(minSize, maxSize + 1);
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < nameLength; ++i) {
            int latterIndex = getNextIntInRange(0, numLetters);
            if (0 == i) name.append((char) ('A' + latterIndex));
            else name.append((char) ('a' + latterIndex));
        }
        return name.toString();
    }

    private static boolean checkPasswordCorrect(String password, int minSize, int maxSize) {
        if (0 == password.length()) return false;
        if (password.equalsIgnoreCase("123qwe")) return false;
        if (password.equalsIgnoreCase("1q2w3e")) return false;
        if (password.length() < minSize || password.length() > maxSize) return false;
        return true;
    }

    public static String createPassword(int minSize, int maxSize) {
        return createRandomName(minSize, maxSize);
    }

    private static String generateWordInAlphabet(String alphabet, int minSize, int maxSize) {
        int wordLength = getNextIntInRange(minSize, maxSize + 1);
        int alphabetSize = alphabet.length();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < wordLength; ++i) {
            int symbolIndex = getNextIntInRange(0, alphabetSize);
            word.append(alphabet.charAt(symbolIndex));
        }
        return word.toString();
    }

    public static String createEmail() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String part1 = generateWordInAlphabet(alphabet, 2, 10);
        String part2 = generateWordInAlphabet(alphabet, 2, 10);
        String part3 = generateWordInAlphabet(alphabet, 2, 4);
        return part1 + '@' + part2 + '.' + part3;
    }
}
