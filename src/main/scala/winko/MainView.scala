package winko

import com.trolltech.qt.core._
import com.trolltech.qt.gui._
import com.trolltech.qt.webkit._

class MainView extends QMainWindow {
  import Utils._

  val browser = new QWebView
  val urlfield = new QLineEdit
  val toolbar = addToolBar("Actions")
  val reload = toolbar.addAction("Reload")
  toolbar.addWidget(urlfield)
  toolbar.setFloatable(false)
  toolbar.setMovable(false)

  setCentralWidget(browser)
  statusBar().show()
  setWindowTitle("= WINKO =")

  urlfield.returnPressed.connect(this, "open()")

  reload.triggered.connect(browser, "reload()")

  browser.setPage(new WWebPage())

  val observer = new BrowserObserver(browser)
  // browser.loadStarted.connect(observer, "loadStarted()");
  // browser.loadProgress.connect(observer, "loadProgress(int)");
  browser.loadFinished.connect(observer, "loadDone()");
  // browser.urlChanged.connect(observer, "urlChanged(QUrl)");

  invokeLater {
    browser.load(new QUrl(Static.html.index.toString))
  }

  def open {
    val text = urlfield.text()
    val url = new QUrl(if(text.indexOf("://") < 0) "http://" + text else text)
    browser.load(url)
  }

  // @MAIN_URL@

  def page = browser.page()
  def frame = browser.page().mainFrame()
}

class BrowserObserver(browser: QWebView) {
  def loadDone(){
    injectJavascript("load_scripts.js")
    injectJavascript("main.js")
  }

  def injectJavascript(name: String) {
    val js = io.Source.fromURL(getClass.getResource("/js/" + name)).getLines.mkString("")
    Utils.invokeLater { browser.page().mainFrame().evaluateJavaScript(js) }
  }
}

object Static {
  object html {
    def index = getClass.getResource("/html/index.html")
  }

  val jsfiles = List("main").map { name =>
    <script src={getClass.getResource("/js/" + name + ".js").toString}></script>
  }
}

class WWebPage extends QWebPage with Log {
  override def javaScriptConsoleMessage(message: String, lineNumber: Int, sourceID: String){
    log("console.log: %s [%s:%d]" format (message, sourceID, lineNumber))
  }
}

