/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corp. and others
 *
 * This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License 2.0 which accompanies this
 * distribution and is available at https://www.eclipse.org/legal/epl-2.0/
 * or the Apache License, Version 2.0 which accompanies this distribution and
 * is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * This Source Code may also be made available under the following
 * Secondary Licenses when the conditions for such availability set
 * forth in the Eclipse Public License, v. 2.0 are satisfied: GNU
 * General Public License, version 2 with the GNU Classpath
 * Exception [1] and GNU General Public License, version 2 with the
 * OpenJDK Assembly Exception [2].
 *
 * [1] https://www.gnu.org/software/classpath/license.html
 * [2] http://openjdk.java.net/legal/assembly-exception.html
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0 OR GPL-2.0 WITH Classpath-exception-2.0 OR LicenseRef-GPL-2.0 WITH Assembly-exception
 *******************************************************************************/
package org.openj9.test.java.lang;

import java.lang.Class;
import java.lang.ClassNotFoundException;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

@Test(groups={ "level.sanity" }, invocationCount=2)
public class Test_Fma {
	
    @Test
	public void test_fma() {
		/**
		 * Testing special case: 
		 * If any argument is NaN, the result is NaN.
		 */
		double a, b, c, r;
		a = Double.NaN;
		b = Math.random(); 
        c = Math.random();
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = Math.random(); 
        b = Double.NaN;
		c = Math.random();
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = Math.random(); 
        b = Math.random(); 
        c = Double.NaN;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

 		a = Double.NaN;
        b = 0; 
        c = Math.random();
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = 0; 
		b = Double.NaN;
        c = Double.POSITIVE_INFINITY;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = Double.NEGATIVE_INFINITY; 
        b = 0; 
        c = Double.NaN;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

		/**
		 * Testing special case: 
		 * If one of the first two arguments is infinite and the other is zero, the result is NaN.
		 */
        a = Double.POSITIVE_INFINITY;
        b = 0; 
        c = Math.random();
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = 0; 
        b = Double.POSITIVE_INFINITY; 
        c = Math.random();
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = Double.NEGATIVE_INFINITY;
        b = 0; 
        c = Math.random();
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = 0;
        b = Double.NEGATIVE_INFINITY; 
        c = Math.random();
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

    	/**
		 * Testing special case: 
		 * If the exact product of the first two arguments is infinite (in other words, at least one of the arguments is infinite and
		 * the other is neither zero nor NaN) and the third argument is an infinity of the opposite sign, the result is NaN.
		 */
        a = Double.POSITIVE_INFINITY;
        b = Math.abs(Math.random()); 
        c = Double.NEGATIVE_INFINITY;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = Double.NEGATIVE_INFINITY;
        b = Math.abs(Math.random()); 
        c = Double.POSITIVE_INFINITY;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = Math.abs(Math.random());
        b = Double.POSITIVE_INFINITY;
        c = Double.NEGATIVE_INFINITY;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = Math.abs(Math.random());
        b = Double.NEGATIVE_INFINITY; 
        c = Double.POSITIVE_INFINITY;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = -Math.abs(Math.random());
        b = Double.NEGATIVE_INFINITY;
        c = Double.NEGATIVE_INFINITY;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = -Math.abs(Math.random());
        b = Double.POSITIVE_INFINITY; 
        c = Double.POSITIVE_INFINITY;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = -Math.abs(Math.random());
        b = Double.NEGATIVE_INFINITY;
        c = Double.NEGATIVE_INFINITY;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

        a = -Math.abs(Math.random());
        b = Double.POSITIVE_INFINITY; 
        c = Double.POSITIVE_INFINITY;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isNaN(r));

		/**
		 * Testing infinity numbers: 
		 */
        a = Double.POSITIVE_INFINITY;
        b = 2.2; 
        c = 3.3;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isInfinite(r));

        a = Double.POSITIVE_INFINITY;
        b = Double.NEGATIVE_INFINITY; 
        c = 3.3;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isInfinite(r));

        a = Double.POSITIVE_INFINITY;
        b = Double.NEGATIVE_INFINITY; 
        c = Double.NEGATIVE_INFINITY;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isInfinite(r));
        
        a = Double.POSITIVE_INFINITY;
        b = Double.NEGATIVE_INFINITY; 
        c = Double.NEGATIVE_INFINITY;
        r = Math.fma(a, b, c);
        AssertJUnit.assertTrue(Double.isInfinite(r));

		/**
		 * Testing against random numbers: 
		 */
        a = 0.9085095723204865;
        b = 0.08818918433337497;
        c = 0.6513665215590198;
        r = Math.fma(a, b, c);
        double expected = 0.7314872397010268;
        AssertJUnit.assertEquals(r, expected);

        a = 0.590462214807549;
        b = 0.15430281193816775;
        c = 0.17457974769099827;
        r = Math.fma(a, b, c);
        expected = 0.2656897277790415;
        AssertJUnit.assertEquals(r, expected);

        a = 0.04441749995782596;
        b = 0.08992714669433366;
        c = 0.25876787066490525;
        r = Math.fma(a, b, c);
        expected = 0.2627622096994082;
        AssertJUnit.assertEquals(r, expected);

		/**
		 * Testing against boundaries: 
		 */
        double max = Double.MAX_VALUE;
        double min = Double.MIN_VALUE;

        a = max;
        b = max;
        c = max;
        r = Math.fma(a, b, c);
        expected = Double.POSITIVE_INFINITY;
        AssertJUnit.assertEquals(r, expected);

        a = min;
        b = min;
        c = min;
        r = Math.fma(a, b, c);
        expected = 4.9E-324;
        AssertJUnit.assertEquals(r, expected);

        a = max - min;
        b = max - min;
        c = max - min;
        r = Math.fma(a, b, c);
        expected = Double.POSITIVE_INFINITY;
        AssertJUnit.assertEquals(r, expected);

        a = max;
        b = max;
        c = max;
        r = Math.fma(-a, -b, -c);
        expected = Double.POSITIVE_INFINITY;
        AssertJUnit.assertEquals(r, expected);

        a = min;
        b = min;
        c = min;
        r = Math.fma(-a, -b, -c);
        expected = -4.9E-324;
        AssertJUnit.assertEquals(r, expected);

        a = max - min;
        b = max - min;
        c = max - min;
        r = Math.fma(a, b, -c);
        expected = Double.POSITIVE_INFINITY;
        AssertJUnit.assertEquals(r, expected);

        a = max;
        b = max;
        c = max;
        r = Math.fma(a, -b, c);
        expected = Double.NEGATIVE_INFINITY;
        AssertJUnit.assertEquals(r, expected);

        a = max;
        b = max;
        c = max;
        r = Math.fma(a, b, -c);
        expected = Double.POSITIVE_INFINITY;
        AssertJUnit.assertEquals(r, expected);

        a = max;
        b = max;
        c = max;
        r = Math.fma(a, -b, -c);
        expected = Double.NEGATIVE_INFINITY;
        AssertJUnit.assertEquals(r, expected);

        a = min * 2;
        b = min * 2;
        c = min * 2;
        r = Math.fma(a, b, c);
        expected = 1.0E-323;
        AssertJUnit.assertEquals(r, expected);

        a = min * 2;
        b = min * 2;
        c = min * 2;
        r = Math.fma(-a, -b, -c);
        expected = -1.0E-323;
        AssertJUnit.assertEquals(r, expected);
	}
}