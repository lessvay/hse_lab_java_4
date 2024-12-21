package org.example;

import java.util.*;

public class Main {

    private static final int NUM_OF_FLOORS = 10;
    private static final int NUM_OF_LIFTS = 2;
    private static final List<Elevator> lifts = new ArrayList<>();

    public static void main(String[] args) {
        // Инициализируем лифты
        for (int i = 0; i < NUM_OF_LIFTS; i++) {
            lifts.add(new Elevator(i + 1));
        }

        // Чтение запросов от пользователя
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите запрос в формате 'откуда-куда' (или введите 'exit' для выхода):");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                // Проверка на корректный формат запроса
                Request request = parseRequest(input);
                // Отправляем запрос в один из лифтов
                Elevator availableLift = lifts.get(new Random().nextInt(lifts.size())); // Выбираем случайный лифт
                availableLift.addRequest(request);
                System.out.println("Запрос добавлен: С " + request.GetfromFloor() + " на " + request.GettoFloor());
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Неизвестная ошибка: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // Метод для парсинга запроса и проверки его корректности
    private static Request parseRequest(String input) {
        String[] parts = input.split(" ");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Неправильный формат запроса. Используйте 'откуда-куда'.");
        }

        try {
            int fromFloor = Integer.parseInt(parts[0]);
            int toFloor = Integer.parseInt(parts[1]);

            // Проверка на корректность этажей
            if (fromFloor < 1 || fromFloor > NUM_OF_FLOORS || toFloor < 1 || toFloor > NUM_OF_FLOORS || fromFloor == toFloor) {
                throw new IllegalArgumentException("Этажи должны быть в пределах от 1 до " + NUM_OF_FLOORS + " и не могут быть одинаковыми.");
            }

            return new Request(fromFloor, toFloor);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Этажи должны быть целыми числами.");
        }
    }
}









