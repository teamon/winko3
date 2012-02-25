package winko;


import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.webkit.*;

class HelloWebKit extends QWidget {

    private QWebView browser;
    private QLineEdit field;

    public HelloWebKit() {
        field = new QLineEdit();
        browser = new QWebView();

        QVBoxLayout layout = new QVBoxLayout(this);
        layout.addWidget(field);
        layout.addWidget(browser);

        field.returnPressed.connect(this, "open()");
    }

    public void open() {
        String text = field.text();

        if (text.indexOf("://") < 0)
            text = "http://" + text;

        // browser.load(new QUrl(text));


        browser.page().mainFrame().setHtml("<h1>hello</h1>");
        browser.page().mainFrame().evaluateJavaScript("alert(1);");
    }

    public static void main(String args[]) {
        QApplication.initialize(args);

        HelloWebKit widget = new HelloWebKit();
        widget.show();

        QApplication.exec();
    }
}
