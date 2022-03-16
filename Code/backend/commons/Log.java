package backend.commons;

public class Log {
    public static volatile Log instance;

    public static Log getLogger() {
        if (instance == null) {
            synchronized (Log.class) {
                if (instance == null) {
                    instance = new Log();
                }
            }
        }

        return instance;
    }

    public void write(String stringToLog) {
        Log.getLogger().write(stringToLog);
    }
}
