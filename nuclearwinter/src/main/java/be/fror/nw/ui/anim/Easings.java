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

import static com.google.common.base.Preconditions.*;

import com.google.common.annotations.VisibleForTesting;

/**
 * @author Olivier Grégoire
 */
public class Easings {
    private Easings() {}

    /**
     * 
     * @return
     */
    public static Easing linear() {
	return DefaultEasing.LINEAR;
    }

    /**
     * 
     * @return
     */
    public static Easing quadratic() {
	return DefaultEasing.QUADRATIC;
    }

    /**
     * 
     * @return
     */
    public static Easing cubic() {
	return DefaultEasing.CUBIC;
    }

    /**
     * 
     * @return
     */
    public static Easing quartic() {
	return DefaultEasing.QUARTIC;
    }

    /**
     * 
     * @return
     */
    public static Easing quintic() {
	return DefaultEasing.QUINTIC;
    }

    /**
     * 
     * @return
     */
    public static Easing circular() {
	return DefaultEasing.CIRCULAR;
    }

    /**
     * 
     * @return
     */
    public static Easing exponential() {
	return DefaultEasing.EXPONENTIAL;
    }

    @VisibleForTesting
    static enum DefaultEasing implements InvertibleEasing {
	LINEAR {
	    @Override
	    public float ease(float t, float d) {
		return t / d;
	    }

	    @Override
	    public Easing inverse() {
		return this;
	    }
	},
	QUADRATIC {
	    @Override
	    public float ease(float t, float d) {
		t /= d;
		return t * t;
	    }

	    @Override
	    public Easing inverse() {
		return QUADRATIC_INVERSE;
	    }
	},
	QUADRATIC_INVERSE {
	    @Override
	    public float ease(float t, float d) {
		t /= d;
		return (2f - t) * t;
	    }

	    @Override
	    public Easing inverse() {
		return QUADRATIC;
	    }
	},
	CUBIC {
	    @Override
	    public float ease(float t, float d) {
		t /= d;
		return t * t * t;
	    }

	    @Override
	    public Easing inverse() {
		return CUBIC_INVERSE;
	    }
	},
	CUBIC_INVERSE {
	    @Override
	    public float ease(float t, float d) {
		t = (d - t) / d;
		return 1f - t * t * t;
	    }

	    @Override
	    public Easing inverse() {
		return CUBIC;
	    }
	},
	QUARTIC {
	    @Override
	    public float ease(float t, float d) {
		t /= d;
		return t * t * t * t;
	    }

	    @Override
	    public Easing inverse() {
		return QUARTIC_INVERSE;
	    }
	},
	QUARTIC_INVERSE {
	    @Override
	    public float ease(float t, float d) {
		t = (d - t) / d;
		return 1f - t * t * t * t;
	    }

	    @Override
	    public Easing inverse() {
		return QUARTIC;
	    }
	},
	QUINTIC {
	    @Override
	    public float ease(float t, float d) {
		t /= d;
		return t * t * t * t * t;
	    }

	    @Override
	    public Easing inverse() {
		return QUINTIC_INVERSE;
	    }
	},
	QUINTIC_INVERSE {
	    @Override
	    public float ease(float t, float d) {
		t = (d - t) / d;
		return 1f - t * t * t * t * t;
	    }

	    @Override
	    public Easing inverse() {
		return QUINTIC;
	    }
	},
	CIRCULAR {
	    @Override
	    public float ease(float t, float d) {
		t /= d;
		return 1f - (float) Math.sqrt(1d - t * t);
	    }

	    @Override
	    public Easing inverse() {
		return CIRCULAR_INVERSE;
	    }
	},
	CIRCULAR_INVERSE {
	    @Override
	    public float ease(float t, float d) {
		t = (d - t) / d;
		return (float) Math.sqrt(1d - t * t);
	    }

	    @Override
	    public Easing inverse() {
		return CIRCULAR;
	    }
	},
	EXPONENTIAL {
	    @Override
	    public float ease(float t, float d) {
		t /= d;
		return (float) Math.pow(2.0d, 10.0 * (t - 1));
	    }

	    @Override
	    public Easing inverse() {
		return EXPONENTIAL_INVERSE;
	    }
	},
	EXPONENTIAL_INVERSE {
	    @Override
	    public float ease(float t, float d) {
		t /= d;
		return 1f - (float) Math.pow(2.0d, -10 * (t / d));
	    }

	    @Override
	    public Easing inverse() {
		return EXPONENTIAL;
	    }
	};
    }

    /**
     * 
     * @param easing
     * @return
     * @throws NullPointerException
     *             if {@code easing} is {@code null}
     */
    public static Easing inverse(Easing easing) {
	checkNotNull(easing);
	if (easing instanceof InvertibleEasing) {
	    return ((InvertibleEasing) easing).inverse();
	}
	return new InverseEasing(easing);
    }

    private static class InverseEasing
	    implements InvertibleEasing {

	private final Easing easing;

	InverseEasing(Easing easing) {
	    this.easing = easing;
	}

	@Override
	public float ease(float t, float d) {
	    return 1f - this.easing.ease(d - t, d);
	}

	@Override
	public Easing inverse() {
	    return this.easing;
	}
    }
}
