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
package be.fror.nw.util;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class to get platform-specific information.
 * 
 * @author Olivier Grégoire
 */
public final class Platform {
	private Platform() {
	}

	private static final OperatingSystem os;
	static {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win")) {
			os = OperatingSystem.WINDOWS;
		} else if (osName.contains("mac")) {
			os = OperatingSystem.MAC_OSX;
		} else if (osName.contains("solaris") || osName.contains("sunos")) {
			os = OperatingSystem.SOLARIS;
		} else if (osName.contains("linux") || osName.contains("unix")) {
			os = OperatingSystem.LINUX;
		} else {
			os = OperatingSystem.UNKNOWN;
		}
	}

	private static Path userHomePath;

	/**
	 * This method returns the logical user home path or the current working
	 * directory if the user home path is not available.
	 * 
	 * @return a {@code Path} representing the user home path or the current
	 *         working directory
	 * @see System#getProperty(String)
	 */
	public static Path userHomePath() {
		if (userHomePath == null) {
			userHomePath = Paths.get(System.getProperty("user.home", "."));
		}
		return userHomePath;
	}

	/**
	 * This method returns the path in which the current application can persist
	 * data.
	 * 
	 * <p>
	 * This method does not do the best to find the actual application data for
	 * the given application name, but it tries several options.
	 * 
	 * <p>
	 * For instance:
	 * <ul>
	 * <li>Under Windows, the environment variable {@code %APPDATA%} is used. If
	 * it's not available, this method uses the user home or the current
	 * directory.
	 * <li>Under Unix/Linux/Solaris the user home is preferred then the current
	 * directory is used. The application name is prepended with {@code .} to
	 * hide it.
	 * <li>Under MacOS, the {@code $HOME/Library/Application Support} folder is
	 * used.
	 * <li>Under unknown systems, the user folder is used as parent of
	 * {@code name}.
	 * </ul>
	 * 
	 * @param name
	 *            the name of the application folder
	 * @return the path in which the current application can persist data.
	 * 
	 */
	public static Path applicationDataPath(String name) {
		return os.applicationDataPath(name);
	}

	private enum OperatingSystem {
		LINUX {
			@Override
			Path applicationDataPath(String name) {
				return userHomePath().resolve("." + name);
			}
		},
		WINDOWS {
			private final Path appDataPath;
			{
				String appData = System.getenv("APPDATA");
				appDataPath = (appData != null) ? Paths.get(appData)
						: userHomePath();
			}

			@Override
			Path applicationDataPath(String name) {
				return appDataPath.resolve("." + name);
			}
		},
		MAC_OSX {
			@Override
			Path applicationDataPath(String name) {
				return userHomePath().resolve("Library")
						.resolve("Application Support").resolve(name);
			}
		},
		SOLARIS {
			@Override
			Path applicationDataPath(String name) {
				return userHomePath().resolve("." + name);
			}
		},
		UNKNOWN {
			@Override
			Path applicationDataPath(String name) {
				return userHomePath().resolve(name);
			}
		};

		abstract Path applicationDataPath(String name);
	}
}
