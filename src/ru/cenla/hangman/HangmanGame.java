package ru.cenla.hangman;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class HangmanGame {
    private static final String[] WORDS = {"УЛИЦА", "КОМПЬЮТЕР", "ИГРОК", "ЭКРАН",
            "МОНЕТА", "ОБЛАКО", "МАШИНА", "РАКЕТА", "АЗБУКА"};
    private static final int MAX_LIFES = 6;
    private final String wordToGuess;
    private final char[] currentWord;
    private boolean isCompleted;
    private final Set<Character> remainingLetters = new TreeSet<>();
    private int lives = MAX_LIFES;

    public HangmanGame() {
        Random random = new Random();
        this.wordToGuess = WORDS[random.nextInt(WORDS.length)];
        this.currentWord = "_".repeat(wordToGuess.length()).toCharArray();
        this.isCompleted = false;
        for (char c = 'А'; c <= 'Я'; c++) {
            this.remainingLetters.add(c);
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (!this.isCompleted) {
            printMessage();
            char letter = scanner.nextLine().toUpperCase().charAt(0);
            if (!this.remainingLetters.contains(letter)) {
                System.out.println("Эта буква уже была введена или такой буквы нет в русском алфавите," +
                        " попробуйте другую!");
                continue;
            }
            this.remainingLetters.remove(letter);
            boolean isCorrectLetter = false;
            for (int i = 0; i < this.wordToGuess.length(); i++) {
                if (this.wordToGuess.charAt(i) == letter) {
                    isCorrectLetter = true;
                    this.currentWord[i] = letter;
                }
            }
            if (!isCorrectLetter) {
                System.out.println("Буквы " + letter + " нет в загаданом слове");
                this.lives -= 1;
            }
            if (this.lives <= 0 || this.wordToGuess.equals(new String(this.currentWord))) {
                this.isCompleted = true;
            }
        }
        if (this.lives > 0) {
            System.out.println("Поздравляем! Вы победили!");
        } else {
            System.out.println("К сожалению вы проиграли! :(");
        }
        System.out.println("Было загадано слово " + this.wordToGuess);
    }

    private void printMessage() {
        System.out.println("Оставшиеся буквы:");
        System.out.println(this.remainingLetters.toString());
        System.out.println("Текущее слово:");
        System.out.println(new String(this.currentWord));
        System.out.println("Осталось жизней:");
        System.out.println("❤".repeat(this.lives));
        System.out.println("Введите вашу букву:");
    }

    public static void main(String[] args) {
        HangmanGame game = new HangmanGame();
        game.play();
    }


}
