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

package be.fror.nw.lwjgl;

import java.util.Arrays;
import java.util.Comparator;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Throwables;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;

/**
 * @author Olivier Grégoire
 * 
 */
public class DisplayModes {

    private enum DisplayModeComparator implements Comparator<DisplayMode> {
	DEFAULT {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see java.util.Comparator#compare(java.lang.Object,
	     * java.lang.Object)
	     */
	    @Override
	    public int compare(DisplayMode o1, DisplayMode o2) {
		return ComparisonChain.start()
			.compare(o1.getBitsPerPixel(), o2.getBitsPerPixel())
			.compare(o2.getWidth(), o1.getWidth())
			.compare(o2.getHeight(), o1.getHeight())
			.compare(o2.getFrequency(), o1.getFrequency())
			.result();
	    }
	};
    }

    private static enum DisplayModeToInt implements Function<DisplayMode, Integer> {
	BITS_PER_PIXELS {
	    @Override
	    public Integer apply(DisplayMode mode) {
		return mode.getBitsPerPixel();
	    }
	},
	WIDTH {
	    @Override
	    public Integer apply(DisplayMode mode) {
		return mode.getWidth();
	    }
	},
	HEIGHT {
	    @Override
	    public Integer apply(DisplayMode mode) {
		return mode.getHeight();
	    }
	},
	FREQUENCY {
	    @Override
	    public Integer apply(DisplayMode mode) {
		return mode.getFrequency();
	    }
	};
    }

    private static class SingletonHolder {
	private static final DisplayModes instance = createInstance();
    }

    /**
     * @return
     */
    public static DisplayModes getInstance()
	    throws RuntimeException {
	try {
	    return SingletonHolder.instance;
	} catch (Throwable e) {
	    Optional<LWJGLException> cause = FluentIterable.from(Throwables.getCausalChain(e))
		    .filter(LWJGLException.class)
		    .first();
	    if (cause.isPresent()) {
		throw new RuntimeException(cause.get());
	    } else {
		throw e;
	    }
	}
    }

    private static DisplayModes createInstance()
	    throws RuntimeException {
	try {
	    ImmutableMultimap.Builder<Integer, DisplayMode> builder = ImmutableMultimap
		    .<Integer, DisplayMode> builder()
		    .orderKeysBy(Ordering.natural())
		    .orderValuesBy(DisplayModeComparator.DEFAULT)
		    .putAll(Multimaps.index(Arrays.asList(Display.getAvailableDisplayModes()),
			    DisplayModeToInt.BITS_PER_PIXELS));
	    return new DisplayModes(builder.build());
	} catch (LWJGLException e) {
	    throw new RuntimeException(e);
	}
    }

    public final ImmutableMultimap<Integer, DisplayMode> modes;

    private DisplayModes(ImmutableMultimap<Integer, DisplayMode> modes) {
	this.modes = modes;
    }
}
