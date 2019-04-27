import org.telegram.api.engine.LoggerInterface;
import org.telegram.mtproto.log.LogInterface;

public class TelegaShowOnlyErrors implements LoggerInterface, LogInterface {
    @Override
    public void w(String s, String s1) {
        // не печатаем warn сообщения
    }

    @Override
    public void d(String s, String s1) {
        // не печатаем debug сообщения
    }

    @Override
    public void e(String s, Throwable t) {
        // печатаем ошибки
        t.printStackTrace();
    }
}