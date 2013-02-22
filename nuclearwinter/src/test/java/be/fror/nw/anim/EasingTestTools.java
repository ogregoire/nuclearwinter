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

import static org.junit.Assert.*;

/**
 * @author Olivier Grégoire
 */
public class EasingTestTools {
    public static void testEasing(Easing easing, float[][] data, float delta) {
	String easingName = easing.toString();
	for (float[] testData : data) {
	    assertEquals(easingName, testData[0], easing.ease(testData[1], testData[2]), delta);
	}
    }
}