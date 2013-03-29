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

package be.fror.nw.swing;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import be.fror.nw.lwjgl.DisplayModes;

/**
 * @author Olivier Grégoire
 * 
 */
public class GameLauncher
	implements Runnable {
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

	final DisplayModes modes = DisplayModes.getInstance();

	SwingUtilities.invokeLater(new Runnable() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see java.lang.Runnable#run()
	     */
	    @Override
	    public void run() {
		runInEdt(modes);
	    }
	});
    }

    private void runInEdt(DisplayModes modes) {
	JFrame frame = new JFrame("Nuclear Winter launcher");
	frame.getContentPane().setLayout(new FlowLayout());
	
	ButtonGroup bitsPerPixelGroup = new ButtonGroup();
	
	for (int bitsPerPixel: modes.modes.keySet()) {
	    JRadioButton radio = new JRadioButton(bitsPerPixel + " bits");
	    
	    bitsPerPixelGroup.add(radio);
	    
	    frame.getContentPane().add(radio);
	}
	
	
	frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	frame.pack();
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
    }
}
