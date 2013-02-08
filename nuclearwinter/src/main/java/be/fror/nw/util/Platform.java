package be.fror.nw.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Platform {

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

    public static Path userHomePath() {
	if (userHomePath == null) {
	    userHomePath = Paths.get(System.getProperty("user.home", "."));
	}
	return userHomePath;
    }

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
		appDataPath = (appData != null)
			? Paths.get(appData)
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
		return userHomePath().resolve("Library").resolve("Application Support").resolve(name);
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
