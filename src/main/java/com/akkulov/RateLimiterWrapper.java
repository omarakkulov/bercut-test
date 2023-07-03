package com.akkulov;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Класс-обертка над {@link RateLimiter}.
 */
public class RateLimiterWrapper {

  private final RateLimiter rateLimiter;

  /**
   * RateLimiterWrapper.
   *
   * @param permitsPerSecond ограничение на количество разрешений в секунду
   */
  public RateLimiterWrapper(double permitsPerSecond) {
    this.rateLimiter = RateLimiter.create(permitsPerSecond);
  }

  public void acquire() {
    rateLimiter.acquire();
  }
}
