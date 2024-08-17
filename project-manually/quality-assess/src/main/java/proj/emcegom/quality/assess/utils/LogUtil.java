package proj.emcegom.quality.assess.utils;

public class LogUtil {


    public static String template(Object... logs) {
        StringBuilder sb = new StringBuilder();
        for (Object log : logs) {
            sb.append("[").append(log).append("]");
        }
        return sb.toString();
    }
}
