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
package com.seiama.filter.typed;

import com.seiama.filter.FilterQuery;
import com.seiama.filter.FilterResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TypedFilterTest {
  @Test
  void testQueryableWith() {
    final TypedFilter<A> filter = new AllowTypesOfA();

    class RandomA implements A {
    }

    assertTrue(filter.queryableWith(new A() {
    }));
    assertTrue(filter.queryableWith(new RandomA()));
    assertFalse(filter.queryableWith(new FilterQuery() {
    }));
  }

  @Test
  void testQueryOfNotQueryableType() {
    final TypedFilter<A> filter = new AllowTypesOfA();

    assertEquals(FilterResponse.ABSTAIN, filter.query(new FilterQuery() {
    }));
  }

  private interface A extends FilterQuery {
  }

  private static class AllowTypesOfA implements TypedFilter<A> {
    @Override
    public boolean queryableWith(final FilterQuery query) {
      return query instanceof A;
    }

    @Override
    public FilterResponse typedQuery(final A query) {
      return FilterResponse.ALLOW;
    }
  }
}
