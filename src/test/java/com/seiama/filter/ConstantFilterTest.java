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

import com.google.common.testing.EqualsTester;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConstantFilterTest {
  private static final int REPETITIONS = 100;

  @RepeatedTest(REPETITIONS)
  void testQueryAllowsEverything() {
    record Query() implements FilterQuery {
    }
    assertTrue(Filter.allow().allows(new Query()));
  }

  @RepeatedTest(REPETITIONS)
  void testQueryAbstainsEverything() {
    record Query() implements FilterQuery {
    }
    assertTrue(Filter.abstain().abstains(new Query()));
  }

  @RepeatedTest(REPETITIONS)
  void testQueryDeniesEverything() {
    record Query() implements FilterQuery {
    }
    assertTrue(Filter.deny().denies(new Query()));
  }

  @Test
  void testResponse() {
    assertEquals(FilterResponse.ALLOW, Filter.allow().response());
    assertEquals(FilterResponse.ABSTAIN, Filter.abstain().response());
    assertEquals(FilterResponse.DENY, Filter.deny().response());
  }

  @Test
  void testEquality() {
    new EqualsTester()
      .addEqualityGroup(
        Filter.allow(),
        Filter.always(FilterResponse.ALLOW)
      )
      .addEqualityGroup(
        Filter.abstain(),
        Filter.always(FilterResponse.ABSTAIN)
      )
      .addEqualityGroup(
        Filter.deny(),
        Filter.always(FilterResponse.DENY)
      )
      .testEquals();
  }
}
