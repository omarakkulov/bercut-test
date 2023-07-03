package com.akkulov;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskRunner {

  public static void main(String[] args) {
    // Инициализация очереди
    BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    // Инициализация продюсера и консьюмера
    Producer producer = new Producer(queue, 2);
    Consumer consumer = new Consumer(queue, 2000);

    // Запуск продюсера и консьюмера в отдельных потоках
    Thread producerThread = new Thread(producer);
    Thread consumerThread = new Thread(consumer);
    producerThread.start();
    consumerThread.start();
  }
}