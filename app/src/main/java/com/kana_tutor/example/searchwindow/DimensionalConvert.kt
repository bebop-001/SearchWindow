@file:Suppress("unused", "DUPLICATE_LABEL_IN_WHEN")

package com.kana_tutor.example.searchwindow
/*
 *  Copyright (C) 2021 kana-tutor.com
 *  Licensed under the Apache License, Version 2.0 (the "License")
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue.*
import java.lang.RuntimeException

/*
 * This file isn't actually used but I wanted to stash it somewhere
 * I could find it again and the method is useful.  Sorry for
 * ane confusion.
 *
 * See: https://stackoverflow.com/questions/9582263/convert-40px-to-sp
 * This code was translated from TypedValue.java:applyDimension.
 * Conversion between device independent units (dimensionals
 * like SP, DP, etc) and device pixels seems to come up every
 * few months so I put it here for next time.
 *
 * I kept running across the term "dimensional" in the docs but
 * couldn't find a definition for it so I'm assuming its a
 * COMPLEX_UNIT_ dimension.
 */

const val COMPLEX_UNIT_DP = COMPLEX_UNIT_DIP // alias.
fun Context.dimensionalToDevicePixels (
    dimensionalType:Int,
    value:Float
    ) : Float {
    val metrics: DisplayMetrics = resources.displayMetrics
    return when (dimensionalType) {
        COMPLEX_UNIT_PX -> value
        COMPLEX_UNIT_DIP -> value * metrics.density
        COMPLEX_UNIT_DP -> value * metrics.density
        COMPLEX_UNIT_SP -> value * metrics.scaledDensity
        COMPLEX_UNIT_PT -> value * metrics.xdpi * (1.0f/72)
        COMPLEX_UNIT_IN -> value * metrics.xdpi
        COMPLEX_UNIT_MM -> value * metrics.xdpi * (1.0f/25.4f)
        else  -> throw RuntimeException("""dimensionalToDevicePixels:
            | $dimensionalType: not a complex unit dimension.""".trimMargin("|"))
    }
}
fun Context.dimensionalToDevicePixels (
    dimensionalType:Int,
    value:Int
) : Float = dimensionalToDevicePixels(dimensionalType, value.toFloat())
