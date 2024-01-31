/*
 * This file is part of filter, licensed under the MIT License.
 *
 * Copyright (c) 2021-2024 Seiama
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

import java.util.List;
import org.jspecify.annotations.NullMarked;

@NullMarked
record AllFilterImpl(List<? extends Filter> filters) implements AllFilter {
  AllFilterImpl {
    filters = List.copyOf(filters);
  }

  @Override
  @SuppressWarnings("ForLoopReplaceableByForEach")
  public FilterResponse query(final FilterQuery query) {
    FilterResponse result = FilterResponse.ABSTAIN;
    final List<? extends Filter> filters = this.filters;
    for (int i = 0, size = filters.size(); i < size; i++) {
      final FilterResponse response = filters.get(i).query(query);
      if (response == FilterResponse.ALLOW) {
        result = FilterResponse.ALLOW;
      } else if (response == FilterResponse.DENY) {
        return FilterResponse.DENY;
      }
    }
    return result;
  }
}
