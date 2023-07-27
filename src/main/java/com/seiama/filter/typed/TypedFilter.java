/*
 * This file is part of filter, licensed under the MIT License.
 *
 * Copyright (c) 2021-2023 Seiama
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
package com.seiama.filter.typed;

import com.seiama.filter.Filter;
import com.seiama.filter.FilterQuery;
import com.seiama.filter.FilterResponse;
import org.jetbrains.annotations.ApiStatus.OverrideOnly;
import org.jetbrains.annotations.NotNull;

/**
 * A filter which accepts queries of type {@code Q}.
 *
 * @param <Q> the query type
 * @since 1.0.0
 */
public interface TypedFilter<Q extends FilterQuery> extends Filter {
  /**
   * Tests if this filter accepts the query.
   *
   * @param query the query
   * @return {@code true} if this filter accepts the query, {@code false} otherwise
   * @since 1.0.0
   */
  boolean queryableWith(final @NotNull FilterQuery query);

  @Override
  default @NotNull FilterResponse query(final @NotNull FilterQuery query) {
    if (this.queryableWith(query)) {
      @SuppressWarnings("unchecked")
      final Q queryAsQ = (Q) query;
      return this.typedQuery(queryAsQ);
    }
    return FilterResponse.ABSTAIN;
  }

  /**
   * Query this filter for a response.
   *
   * @param query the query
   * @return the response
   * @since 1.0.0
   */
  @OverrideOnly
  @NotNull FilterResponse typedQuery(final @NotNull Q query);
}
