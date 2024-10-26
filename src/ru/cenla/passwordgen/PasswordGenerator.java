package ru.cenla.passwordgen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 12;

    public static String generatePassword(int length) {
        if (length > MAX_LENGTH || length < MIN_LENGTH) {
            System.out.println("Длина пароля должна составлять от " + MIN_LENGTH + " до " + MAX_LENGTH);
            return null;
        }
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = upperCaseLetters.toLowerCase();
        String digits = "0123456789";
        String specialChars = "?!@#$%^&*()-_+=";
        String allChars = upperCaseLetters + lowerCaseLetters + digits + specialChars;
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        password.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        password.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));
        for (int i = 0; i < length - 4; i++) {
            int index = random.nextInt(allChars.length());
            password.append(allChars.charAt(index));
        }
        return shuffleString(password.toString());
    }

    private static String shuffleString(String string) {
        List<Character> characters = new ArrayList<>();
        for (char c : string.toCharArray()) {
            characters.add(c);
        }
        Collections.shuffle(characters);
        StringBuilder shuffled = new StringBuilder();
        for (char c : characters) {
            shuffled.append(c);
        }
        return shuffled.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите длину пароля от " + MIN_LENGTH + " до " + MAX_LENGTH);
        int length = scanner.nextInt();
        while (length > MAX_LENGTH || length < MIN_LENGTH) {
            System.out.println("Введена некорректная длина пароля!");
            System.out.println("Введите длину пароля от " + MIN_LENGTH + " до " + MAX_LENGTH);
            length = scanner.nextInt();
        }
        System.out.print("Ваш сгенерированный пароль: ");
        System.out.println(generatePassword(length));
    }
}
