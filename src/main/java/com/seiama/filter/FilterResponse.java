/*
 * This file is part of filter, licensed under the MIT License.
 *
 * Copyright (c) 2021-2022 Seiama
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.seiama.filter;

import java.util.function.BooleanSupplier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A response from querying a {@link Filter}.
 *
 * @since 1.0.0
 */
public enum FilterResponse {
  /**
   * The filter allows the query.
   *
   * @since 1.0.0
   */
  ALLOW {
    @Override
    @NotNull FilterResponse inverse() {
      return DENY;
    }
  },
  /**
   * The filter abstains from responding to the query.
   *
   * @since 1.0.0
   */
  ABSTAIN {
    @Override
    @NotNull FilterResponse inverse() {
      return this; // no inverse
    }
  },
  /**
   * The filter denies the query.
   *
   * @since 1.0.0
   */
  DENY {
    @Override
    @NotNull FilterResponse inverse() {
      return ALLOW;
    }
  };

  /**
   * Converts a {@code boolean} into a response.
   *
   * @param value the boolean
   * @return the response
   * @since 1.0.0
   */
  @Contract(pure = true)
  public static @NotNull FilterResponse fromBoolean(final boolean value) {
    return value ? ALLOW : DENY;
  }

  /**
   * Converts to a {@code boolean}, supplying from {@code abstain} when the response is {@link #ABSTAIN}.
   *
   * @param abstain the response supplier when the response is {@link #ABSTAIN}
   * @return a {@code boolean}
   * @since 1.0.0
   */
  public boolean toBoolean(final @NotNull BooleanSupplier abstain) {
    return switch (this) {
      case ALLOW -> true;
      case ABSTAIN -> abstain.getAsBoolean();
      case DENY -> false;
    };
  }

  abstract @NotNull FilterResponse inverse();
}
