package com.akkulov;

import java.util.concurrent.BlockingQueue;

/**
 * Обработчик тасок из очереди.
 */
public class Consumer implements Runnable {

  private final BlockingQueue<String> queue;
  private final int consumptionDelay;

  /**
   * Consumer.
   *
   * @param queue            общий ресурс для консьюмера и продюсера в рамках задачи
   * @param consumptionDelay задержка потребления данных
   */
  public Consumer(BlockingQueue<String> queue, int consumptionDelay) {
    this.queue = queue;
    this.consumptionDelay = consumptionDelay;
  }

  @Override
  public void run() {
    try {
      while (true) {
        String data = queue.take();
        process(data);
        Thread.sleep(consumptionDelay);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private void process(String data) {
    System.out.println(Thread.currentThread().getName() + ":data=" + data);
  }
}
