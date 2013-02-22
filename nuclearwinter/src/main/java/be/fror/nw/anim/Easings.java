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

import static com.google.common.base.Preconditions.*;

import java.util.Locale;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

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
	NO_OP {
	    @Override
	    public float ease(float t, float d) {
		return 0.0f;
	    }

	    @Override
	    public Easing inverse() {
		return this;
	    }
	},
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

	private final boolean isInverse;

	private DefaultEasing() {
	    this.isInverse = name().endsWith("_INVERSE");
	}

	@Override
	public String toString() {
	    // Format according to the api-name
	    if (isInverse) {
		return "inverse(" + inverse() + ")";
	    }
	    return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name()) + "()";
	}
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
	return new InversedEasing(easing);
    }

    @VisibleForTesting
    static class InversedEasing
	    implements InvertibleEasing {

	private final Easing easing;

	InversedEasing(Easing easing) {
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

	@Override
	public String toString() {
	    return "inverse(" + this.easing + ")";
	}
    }

    public static ComposedEasingBuilder compose() {
	return new ComposedEasingBuilder();
    }

    public static class ComposedEasingBuilder {

	private final ImmutableList.Builder<ComposedEasingItem> builder;

	ComposedEasingBuilder() {
	    this.builder = ImmutableList.builder();
	}

	public ComposedEasingBuilder add(Easing easing, float weight, float amplitude) {
	    checkNotNull(easing);
	    checkArgument(weight > 0);
	    checkArgument(amplitude > 0);

	    this.builder.add(new ComposedEasingItem(easing, weight, amplitude));

	    return this;
	}

	public ComposedEasingBuilder addBlank(float weight) {
	    checkArgument(weight > 0);

	    this.builder.add(new ComposedEasingItem(DefaultEasing.NO_OP, weight, 0));

	    return this;
	}

	public Easing build() {
	    return new ComposedEasing(this);
	}
    }

    static class ComposedEasing
	    implements Easing {

	private final ImmutableList<ComposedEasingItem> items;
	private final float totalWeight;
	private final float totalAmplitude;

	private ComposedEasing(ComposedEasingBuilder builder) {
	    this.items = builder.builder.build();
	    float totalWeight = 0.0f;
	    float totalAmplitude = 0.0f;
	    for (ComposedEasingItem i : this.items) {
		totalWeight += i.weight;
		totalAmplitude += i.amplitude;
	    }
	    this.totalWeight = totalWeight;
	    this.totalAmplitude = totalAmplitude;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.fror.nw.ui.anim.Easing#ease(float, float)
	 */
	@Override
	public float ease(float t, float d) {
	    final float totalWeight = t / d * this.totalWeight;
	    float currentWeight = 0.0f;
	    float currentAmplitude = 0.0f;
	    for (ComposedEasingItem i : this.items) {
		float nextCurrentWeight = currentWeight + i.weight;
		if (nextCurrentWeight < totalWeight) {
		    currentAmplitude += i.amplitude;
		    currentWeight = nextCurrentWeight;
		    continue;
		}
		return i.easing.ease(totalWeight - currentWeight, i.weight) * (i.amplitude / totalAmplitude)
			+ currentAmplitude / totalAmplitude;
	    }
	    return 0;
	}

	@Override
	public String toString() {
	    return Joiner.on(", ").appendTo(new StringBuilder("composed("), items).append(')').toString();
	}
    }

    static class ComposedEasingItem {
	private final Easing easing;
	private final float weight;
	private final float amplitude;

	ComposedEasingItem(Easing easing, float weight, float amplitude) {
	    this.easing = easing;
	    this.weight = weight;
	    this.amplitude = amplitude;
	}

	@Override
	public String toString() {
	    return String.format(Locale.ENGLISH, "{%s, weight=%.2f, amplitude=%.2f}", this.easing, this.weight,
		    this.amplitude);
	}
    }
}
