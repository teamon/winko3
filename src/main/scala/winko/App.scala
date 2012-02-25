package winko

import com.trolltech.qt.core._
import com.trolltech.qt.gui._
import com.trolltech.qt.webkit._

class MainView extends QWidget {
  val browser = new QWebView

  setupLayout

  // Hello world
  frame.setHtml("<h1>Hello</h1>")
  // frame.evaluateJavaScript("alert(1)")

  def frame = browser.page().mainFrame()

  def setupLayout {
    val layout = new QVBoxLayout(this)
    layout.addWidget(browser)
  }
}

object App {
  def main(args: Array[String]) {
    QApplication.initialize(args)
    val view = new MainView
    view.showFullScreen()
    QApplication.exec()
  }
}
