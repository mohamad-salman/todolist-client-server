package ms.todolist.domain.net;

/**
 *
 * @author MS
 */
public class Request {

    private final String action;
    private final String param;

    public String getAction() {
        return action;
    }

    public String getParam() {
        return param;
    }

    private Request(String action, String param) {
        this.action = action;
        this.param = param;
    }

    public static Request create(String action, String param) {
        return new Request(action, param);
    }

    public static Request create(String requestMessage) {
        String[] reqMsgArr = requestMessage.split(",");

        String action = getMsg(reqMsgArr[0]);
        String param = getMsg(reqMsgArr[1]);

        return new Request(action, param);
    }

    private static String getMsg(String reqMsgArr) {
        return reqMsgArr.split("=")[1];
    }

    @Override
    public String toString() {
        return "Request{" + "action=" + action + ", param=" + param + '}';
    }
}
