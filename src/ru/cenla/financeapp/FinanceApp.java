package ru.cenla.financeapp;

import java.util.HashMap;
import java.util.Scanner;

public class FinanceApp {
    private final HashMap<String, Double> exchangeRates;

    public FinanceApp() {
        this.exchangeRates = new HashMap<>();
        this.exchangeRates.put("RUB", 1.0);
        this.exchangeRates.put("USD", 96.67);
        this.exchangeRates.put("EUR", 104.81);
        this.exchangeRates.put("CNY", 13.5);
        this.exchangeRates.put("JPY", 0.635666);
    }

    public void setExchangeRates() {
        Scanner scanner = new Scanner(System.in);
        String[] values = new String[]{"USD", "EUR", "CNY", "JPY"};
        for (String value : values) {
            System.out.println("Задайте обменный курс к рублю для " + value + ":");
            double exchange = scanner.nextDouble();
            while (exchange <= 0) {
                System.out.println("Ошибка! Курс должен быть положительным числом!");
                System.out.println("Задайте обменный курс к рублю для " + value + ":");
                exchange = scanner.nextDouble();
            }
            this.exchangeRates.put(value, exchange);
        }
    }

    public double convertMoney(double money, String valueFrom, String valueTo) {
        if (!this.exchangeRates.containsKey(valueFrom) || !this.exchangeRates.containsKey(valueTo)) {
            System.out.println("Конвертер не работает с одной из введеных валют");
            return -1.0;
        }
        if (money < 0) {
            System.out.println("Сумма для конвертации должна быть неотрицательна");
            return -1.0;
        }
        double rubles = money * this.exchangeRates.get(valueFrom);
        return rubles / this.exchangeRates.get(valueTo);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceApp financeApp = new FinanceApp();
        System.out.println("Добро пожаловать в конвертер валют!");
        System.out.println("Хотите ли вы ввести курсы валют самостоятельно," +
                " в противном случае будут использоваться курсы по умолчанию(Y/n)?");
        String answer = scanner.nextLine();
        if (answer.equals("Y") || answer.equals("y") || answer.equals("Yes") || answer.equals("yes")) {
            financeApp.setExchangeRates();
        }
        System.out.println("Доступные валюты:");
        System.out.println("Рубль - RUB; Доллар США - USD; Евро - EUR; Китайский юань - CNY; Японская йена - JPY");
        System.out.println("Введите валюту из которой хотите произвести конвертацию в формате FFF:");
        String valueFrom = scanner.nextLine();
        System.out.println("Введите сумму для конвертации:");
        double money = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Введите валюту в которую хотитие произвести конвертацию в формате FFF:");
        String valueTo = scanner.nextLine();
        double exchanged = financeApp.convertMoney(money, valueFrom, valueTo);
        if (exchanged == -1.0) {
            System.out.println("Ошибка при конвертации");
        } else {
            System.out.println("Конвертация успешно проведена!");
            System.out.println("Было введено - " + money + " " + valueFrom + ", в " + valueTo + " это - " + exchanged);
        }
    }
}
