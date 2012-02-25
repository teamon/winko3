package winko;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.webkit.*;

public class Util {
    public static void invokeLater(Runnable runnable){
        QApplication.invokeLater(runnable);
    }
}
