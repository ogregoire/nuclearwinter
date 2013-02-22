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
import be.fror.nw.anim.Easings;
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
		{ 0.00000f, 0.0f, 1f },
		{ 0.10000f, 0.1f, 1f },
		{ 0.20000f, 0.2f, 1f },
		{ 0.30000f, 0.3f, 1f },
		{ 0.40000f, 0.4f, 1f },
		{ 0.50000f, 0.5f, 1f },
		{ 0.60000f, 0.6f, 1f },
		{ 0.70000f, 0.7f, 1f },
		{ 0.80000f, 0.8f, 1f },
		{ 0.90000f, 0.9f, 1f },
		{ 1.00000f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.LINEAR, data, 0.00001f);
    }

    @Test
    public void testQuadratic() {
	float[][] data = {
		{ 0.00000f, 0.0f, 1f },
		{ 0.01000f, 0.1f, 1f },
		{ 0.04000f, 0.2f, 1f },
		{ 0.09000f, 0.3f, 1f },
		{ 0.16000f, 0.4f, 1f },
		{ 0.25000f, 0.5f, 1f },
		{ 0.36000f, 0.6f, 1f },
		{ 0.49000f, 0.7f, 1f },
		{ 0.64000f, 0.8f, 1f },
		{ 0.81000f, 0.9f, 1f },
		{ 1.00000f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.QUADRATIC, data, 0.00001f);
    }

    @Test
    public void testQuadraticInverse() {
	float[][] data = {
		{ 0.00000f, 0.0f, 1f },
		{ 0.19000f, 0.1f, 1f },
		{ 0.36000f, 0.2f, 1f },
		{ 0.51000f, 0.3f, 1f },
		{ 0.64000f, 0.4f, 1f },
		{ 0.75000f, 0.5f, 1f },
		{ 0.84000f, 0.6f, 1f },
		{ 0.91000f, 0.7f, 1f },
		{ 0.96000f, 0.8f, 1f },
		{ 0.99000f, 0.9f, 1f },
		{ 1.00000f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.QUADRATIC_INVERSE, data, 0.00001f);
    }

    @Test
    public void testCubic() {
	float[][] data = {
		{ 0.00000f, 0.0f, 1f },
		{ 0.00100f, 0.1f, 1f },
		{ 0.00800f, 0.2f, 1f },
		{ 0.02700f, 0.3f, 1f },
		{ 0.06400f, 0.4f, 1f },
		{ 0.12500f, 0.5f, 1f },
		{ 0.21600f, 0.6f, 1f },
		{ 0.34300f, 0.7f, 1f },
		{ 0.51200f, 0.8f, 1f },
		{ 0.72900f, 0.9f, 1f },
		{ 1.00000f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.CUBIC, data, 0.00001f);
    }

    @Test
    public void testCubicInverse() {
	float[][] data = {
		{ 0.00000f, 0.0f, 1f },
		{ 0.27100f, 0.1f, 1f },
		{ 0.48800f, 0.2f, 1f },
		{ 0.65700f, 0.3f, 1f },
		{ 0.78400f, 0.4f, 1f },
		{ 0.87500f, 0.5f, 1f },
		{ 0.93600f, 0.6f, 1f },
		{ 0.97300f, 0.7f, 1f },
		{ 0.99200f, 0.8f, 1f },
		{ 0.99900f, 0.9f, 1f },
		{ 1.00000f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.CUBIC_INVERSE, data, 0.00001f);
    }

    @Test
    public void testQuartic() {
	float[][] data = {
		{ 0.00000f, 0.0f, 1f },
		{ 0.00010f, 0.1f, 1f },
		{ 0.00160f, 0.2f, 1f },
		{ 0.00810f, 0.3f, 1f },
		{ 0.02560f, 0.4f, 1f },
		{ 0.06250f, 0.5f, 1f },
		{ 0.12960f, 0.6f, 1f },
		{ 0.24010f, 0.7f, 1f },
		{ 0.40960f, 0.8f, 1f },
		{ 0.65610f, 0.9f, 1f },
		{ 1.00000f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.QUARTIC, data, 0.00001f);
    }

    @Test
    public void testQuarticInverse() {
	float[][] data = {
		{ 0.00000f, 0.0f, 1f },
		{ 0.34390f, 0.1f, 1f },
		{ 0.59040f, 0.2f, 1f },
		{ 0.75990f, 0.3f, 1f },
		{ 0.87040f, 0.4f, 1f },
		{ 0.93750f, 0.5f, 1f },
		{ 0.97440f, 0.6f, 1f },
		{ 0.99190f, 0.7f, 1f },
		{ 0.99840f, 0.8f, 1f },
		{ 0.99990f, 0.9f, 1f },
		{ 1.00000f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.QUARTIC_INVERSE, data, 0.00001f);
    }

    @Test
    public void testQuintic() {
	float[][] data = {
		{ 0.00000f, 0.0f, 1f },
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
	testEasing(Easings.DefaultEasing.QUINTIC, data, 0.00001f);
    }

    @Test
    public void testQuinticInverse() {
	float[][] data = {
		{ 0.00000f, 0.0f, 1f },
		{ 0.40951f, 0.1f, 1f },
		{ 0.67232f, 0.2f, 1f },
		{ 0.83193f, 0.3f, 1f },
		{ 0.92224f, 0.4f, 1f },
		{ 0.96875f, 0.5f, 1f },
		{ 0.98976f, 0.6f, 1f },
		{ 0.99757f, 0.7f, 1f },
		{ 0.99968f, 0.8f, 1f },
		{ 0.99999f, 0.9f, 1f },
		{ 1.00000f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.QUINTIC_INVERSE, data, 0.00001f);
    }
    
    @Test
    public void testCircular() {
	float[][] data = {
		{ 0.00000f, 0.0f, 1f },
		{ 0.00501f, 0.1f, 1f },
		{ 0.02020f, 0.2f, 1f },
		{ 0.04606f, 0.3f, 1f },
		{ 0.08348f, 0.4f, 1f },
		{ 0.13397f, 0.5f, 1f },
		{ 0.20000f, 0.6f, 1f },
		{ 0.28585f, 0.7f, 1f },
		{ 0.40000f, 0.8f, 1f },
		{ 0.56411f, 0.9f, 1f },
		{ 1.00000f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.CIRCULAR, data, 0.00001f);
    }
    
    @Test
    public void testCircularInverse() {
	float[][] data = {
		{ 0.00000f, 0.0f, 1f },
		{ 0.43589f, 0.1f, 1f },
		{ 0.60000f, 0.2f, 1f },
		{ 0.71415f, 0.3f, 1f },
		{ 0.80000f, 0.4f, 1f },
		{ 0.86603f, 0.5f, 1f },
		{ 0.91652f, 0.6f, 1f },
		{ 0.95394f, 0.7f, 1f },
		{ 0.97980f, 0.8f, 1f },
		{ 0.99499f, 0.9f, 1f },
		{ 1.00000f, 1.0f, 1f },
	};
	testEasing(Easings.DefaultEasing.CIRCULAR_INVERSE, data, 0.00001f);
    }
}
