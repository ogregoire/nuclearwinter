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
package be.fror.nw.ui.anim;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Olivier Grégoire
 */
public class DefaultEasingsTest {

    public void testEasing(Easing easing, float[][] data, float delta) {
	String easingName = easing.toString();
	for (float[] testData : data) {
	    assertEquals(easingName, testData[0], easing.ease(testData[1], testData[2]), delta);
	}
    }

    @Test
    public void testLinear() {
	float[][] data = {
		{ 0.1f, 0.1f, 1f },
		{ 0.2f, 0.2f, 1f },
		{ 0.3f, 0.3f, 1f },
		{ 0.4f, 0.4f, 1f },
		{ 0.5f, 0.5f, 1f },
		{ 0.6f, 0.6f, 1f },
		{ 0.7f, 0.7f, 1f },
		{ 0.8f, 0.8f, 1f },
		{ 0.9f, 0.9f, 1f },
		{ 1.0f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.LINEAR, data, 0.01f);
    }

    @Test
    public void testQuadratic() {
	float[][] data = {
		{ 0.01f, 0.1f, 1f },
		{ 0.04f, 0.2f, 1f },
		{ 0.09f, 0.3f, 1f },
		{ 0.16f, 0.4f, 1f },
		{ 0.25f, 0.5f, 1f },
		{ 0.36f, 0.6f, 1f },
		{ 0.49f, 0.7f, 1f },
		{ 0.64f, 0.8f, 1f },
		{ 0.81f, 0.9f, 1f },
		{ 1.00f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.QUADRATIC, data, 0.001f);
    }
    
    @Test
    public void testCubic() {
	float[][] data = {
		{ 0.001f, 0.1f, 1f },
		{ 0.008f, 0.2f, 1f },
		{ 0.027f, 0.3f, 1f },
		{ 0.064f, 0.4f, 1f },
		{ 0.125f, 0.5f, 1f },
		{ 0.216f, 0.6f, 1f },
		{ 0.343f, 0.7f, 1f },
		{ 0.512f, 0.8f, 1f },
		{ 0.729f, 0.9f, 1f },
		{ 1.00f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.CUBIC, data, 0.0001f);
    }
    
    @Test
    public void testQuartic() {
	float[][] data = {
		{ 0.0001f, 0.1f, 1f },
		{ 0.0016f, 0.2f, 1f },
		{ 0.0081f, 0.3f, 1f },
		{ 0.0256f, 0.4f, 1f },
		{ 0.0625f, 0.5f, 1f },
		{ 0.1296f, 0.6f, 1f },
		{ 0.2401f, 0.7f, 1f },
		{ 0.4096f, 0.8f, 1f },
		{ 0.6561f, 0.9f, 1f },
		{ 1.0000f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.QUARTIC, data, 0.00001f);
    }
    
    @Test
    public void testQuintic() {
	float[][] data = {
		{ 0.00001f, 0.1f, 1f },
		{ 0.00032f, 0.2f, 1f },
		{ 0.00243f, 0.3f, 1f },
		{ 0.01024f, 0.4f, 1f },
		{ 0.03125f, 0.5f, 1f },
		{ 0.07776f, 0.6f, 1f },
		{ 0.16807f, 0.7f, 1f },
		{ 0.32768f, 0.8f, 1f },
		{ 0.59049f, 0.9f, 1f },
		{ 1.00000f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.QUINTIC, data, 0.000001f);
    }
}
