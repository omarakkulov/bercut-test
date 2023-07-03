package com.akkulov;

import java.time.Instant;
import java.util.concurrent.BlockingQueue;

/**
 * Класс-производитель данных для очереди.
 */
public class Producer implements Runnable {

  private final BlockingQueue<String> queue;
  private final RateLimiterWrapper rateLimiterWrapper;

  /**
   * Producer.
   *
   * @param queue            общий ресурс для консьюмера и продюсера в рамках задачи
   * @param permitsPerSecond permitsPerSecond ограничение на количество разрешений в секунду
   */
  public Producer(BlockingQueue<String> queue, double permitsPerSecond) {
    this.queue = queue;
    this.rateLimiterWrapper = new RateLimiterWrapper(permitsPerSecond);
  }

  /**
   * Вызываем 'rateLimiterWrapper.acquire()' чтобы ограничить нагрузку в соответствии с ограничением, заданным для RateLimiter. Это
   * означает, что Producer будет ждать, пока не будет доступно разрешение, прежде чем поместить данные в очередь. "Как я понимаю, когда
   * вызывается метод acquire(), он блокирует исполняемый поток до тех пор, пока не будет доступно разрешение."
   */
  @Override
  public void run() {
    try {
      while (true) {
        rateLimiterWrapper.acquire(); // Ожидание в соответствии с ограничением нагрузки
        String data = generateData();
        queue.put(data);
        System.out.println("Данные добавлены в очередь, размер очереди=" + queue.size());
        // Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private String generateData() {
    return "Data : " + Instant.now();
  }
}
