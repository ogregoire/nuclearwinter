/*
 *    Copyright 2013 Olivier Grégoire
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package be.fror.nw.anim;

import org.junit.Test;

import be.fror.nw.anim.Easing;

import static be.fror.nw.anim.Easings.*;

/**
 * @author Olivier Grégoire
 * 
 */
public class ComposedEasingTest {
    @Test
    public void testComposed() {
	Easing easing = compose()
		.add(linear(), 1, 2)
		.add(linear(), 2, 6)
		.add(linear(), 1, 2)
		.build();

	float[][] data = {
		{ 0.000f, 0.00f, 1f },
		{ 0.040f, 0.05f, 1f },
		{ 0.080f, 0.10f, 1f },
		{ 0.120f, 0.15f, 1f },
		{ 0.160f, 0.20f, 1f },
		{ 0.200f, 0.25f, 1f },
		{ 0.260f, 0.30f, 1f },
		{ 0.320f, 0.35f, 1f },
		{ 0.380f, 0.40f, 1f },
		{ 0.440f, 0.45f, 1f },
		{ 0.500f, 0.50f, 1f },
		{ 0.560f, 0.55f, 1f },
		{ 0.620f, 0.60f, 1f },
		{ 0.680f, 0.65f, 1f },
		{ 0.740f, 0.70f, 1f },
		{ 0.800f, 0.75f, 1f },
		{ 0.840f, 0.80f, 1f },
		{ 0.880f, 0.85f, 1f },
		{ 0.920f, 0.90f, 1f },
		{ 0.960f, 0.95f, 1f },
		{ 1.000f, 1.00f, 1f },
	};
	EasingTestTools.testEasing(easing, data, 0.0001f);
    }
}
