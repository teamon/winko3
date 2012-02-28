package winko;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.webkit.*;

public class Util {
    public static void invokeLater(Runnable runnable){
        QApplication.invokeLater(runnable);
    }
}

class QApp {
    public static void sendEvent(QObject receiver, QEvent event){
        QApplication.sendEvent(receiver, event);
    }

    public static void postEvent(QObject receiver, QEvent event){
        QApplication.postEvent(receiver, event);
    }
}
