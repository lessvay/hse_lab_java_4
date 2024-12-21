package org.example;


import java.util.concurrent.*;

public class Elevator {
    private final int liftId;
    private int currentFloor;
    private final BlockingQueue<Request> requestQueue = new LinkedBlockingQueue<>();

    public Elevator(int liftId) {
        this.liftId = liftId;
        this.currentFloor = 1; // Начальный этаж
        startLiftThread(); // Запуск потока для обработки запросов
    }

    // Метод для запуска потока обработки запросов
    private void startLiftThread() {
        new Thread(() -> {
            try {
                while (true) {
                    // Получаем запрос и обрабатываем его
                    Request request = requestQueue.take();
                    handleRequest(request);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    // Добавление запроса в очередь
    public void addRequest(Request request) {
        try {
            requestQueue.put(request);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Обработка запроса лифта
    private void handleRequest(Request request) {
        synchronized (this) {
            System.out.println("Лифт " + liftId + " получил запрос: С " + request.GetfromFloor() + " на " + request.GetfromFloor());

            // Забор пассажира
            if (request.GetfromFloor() > currentFloor) {
                moveUp(request.GetfromFloor());
            } else if (request.GetfromFloor() < currentFloor) {
                moveDown(request.GetfromFloor());
            }
            System.out.println("Лифт " + liftId + " забрал пассажира на этаже " + request.GetfromFloor());

            // Двигаемся к цели
            if (request.GettoFloor() > currentFloor) {
                moveUp(request.GettoFloor());
            } else if (request.GettoFloor() < currentFloor) {
                moveDown(request.GettoFloor());
            }
            System.out.println("Лифт " + liftId + " высадил пассажира на этаже " + request.GettoFloor());
        }
    }

    // Метод для движения лифта вверх
    private void moveUp(int targetFloor) {
        while (currentFloor < targetFloor) {
            currentFloor++;
            System.out.println("Лифт " + liftId + " едет вверх на этаж " + currentFloor);
            try {
                Thread.sleep(1000); // Имитируем время на перемещение между этажами
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Метод для движения лифта вниз
    private void moveDown(int targetFloor) {
        while (currentFloor > targetFloor) {
            currentFloor--;
            System.out.println("Лифт " + liftId + " едет вниз на этаж " + currentFloor);
            try {
                Thread.sleep(1000); // Имитируем время на перемещение между этажами
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


