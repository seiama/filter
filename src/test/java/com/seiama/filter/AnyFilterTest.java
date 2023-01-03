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
package com.seiama.filter;

import com.google.common.testing.EqualsTester;
import com.seiama.filter.test.TestFilterQuery;
import com.seiama.filter.test.TestFilters;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnyFilterTest {
  @Test
  void testUnboxOriginalFilters() {
    final Filter f0 = new TestFilters.Equals(0);
    final Filter f1 = new TestFilters.Equals(1);
    final AnyFilter filter = Filter.any(f0, f1);
    assertEquals(List.of(f0, f1), filter.filters());
  }

  @Test
  void testQuerySimple() {
    final Filter filter = Filter.any(
      new TestFilters.Equals(10),
      new TestFilters.Equals(20)
    );
    assertTrue(filter.allows(new TestFilterQuery(10)));
    assertTrue(filter.denies(new TestFilterQuery(15)));
    assertTrue(filter.allows(new TestFilterQuery(20)));
  }

  @Test
  void testQueryComplex() {
    final Filter filter = Filter.any(
      new TestFilters.Equals(6),
      new TestFilters.Above(9)
    );
    assertTrue(filter.allows(new TestFilterQuery(6)));
    assertTrue(filter.allows(new TestFilterQuery(10)));
  }

  @Test
  void testEquality() {
    final TestFilters.Equals f0 = new TestFilters.Equals(0);
    final TestFilters.Equals f1 = new TestFilters.Equals(1);
    final TestFilters.Equals f2 = new TestFilters.Equals(2);
    new EqualsTester()
      .addEqualityGroup(
        Filter.any(f0, f1),
        Filter.any(List.of(f0, f1))
      )
      .addEqualityGroup(
        Filter.any(f1, f2),
        Filter.any(List.of(f1, f2))
      )
      .testEquals();
  }
}
