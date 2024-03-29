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

import org.jetbrains.annotations.ApiStatus.NonExtendable;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * A filter that returns the inverse response of the {@link #filter() original filter}, as shown in the below table.
 *
 * <table>
 *   <caption>Inverse response mappings</caption>
 *   <thead>
 *     <tr>
 *       <th>Original Response</th>
 *       <th>Inverse Response</th>
 *     </tr>
 *   </thead>
 *   <tbody>
 *     <tr>
 *       <td>ALLOW</td>
 *       <td>DENY</td>
 *     </tr>
 *     <tr>
 *       <td>ABSTAIN</td>
 *       <td>ABSTAIN</td>
 *     </tr>
 *     <tr>
 *       <td>DENY</td>
 *       <td>ALLOW</td>
 *     </tr>
 *   </tbody>
 * </table>
 *
 * @see Filter#not(Filter)
 * @since 1.0.0
 */
@NonExtendable
@NullMarked
public sealed interface NotFilter extends Filter permits NotFilterImpl {
  /**
   * Gets the child filter.
   *
   * <p>The child filter should not be queried manually.</p>
   *
   * @return the child filter
   * @since 1.0.0
   */
  @Contract(pure = true)
  Filter filter();
}
