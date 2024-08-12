package proj.emcegom.quality.assess.utils;

public class LogUtil {


    public static String template(String... logs) {
        StringBuilder sb = new StringBuilder();
        for (String log : logs) {
            sb.append("[").append(log).append("]");
        }
        return sb.toString();
    }
}
