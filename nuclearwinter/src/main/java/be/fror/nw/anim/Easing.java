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

/**
 * @author Olivier Grégoire
 */
public interface Easing {

    /**
     * 
     * 
     * <p>
     * <b>Note:</b> the caller must ensure the following truth:
     * {@code 0 <= t <= d}. If the caller does not ensure this, the result is
     * not guaranteed and {@code RuntimeException} may be thrown.
     * 
     * 
     * @param t
     * @param d
     * @return
     * @throws RuntimeException
     *             depending on the input values and the easing function used.
     */
    public float ease(float t, float d);
}
