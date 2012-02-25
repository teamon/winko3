package winko

import com.trolltech.qt.core._
import com.trolltech.qt.gui._
import com.trolltech.qt.webkit._
import TUIO._

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
    // view.showFullScreen()
    view.show()

    val bridge = new TuioBridge

    QApplication.exec()
  }
}

trait Log {
  def log(args: Any*) = println(args.mkString(" "))
}


class TuioBridge extends TuioListener with Log {
  val client = new TuioClient
  client.addTuioListener(this)
  client.connect


  val cursors = scala.collection.mutable.Set[TuioCursor]()

  // TuioListener implementation

  def addTuioObject(tobj: TuioObject){}
  def updateTuioObject(tobj: TuioObject){}
  def removeTuioObject(tobj: TuioObject){}

  def addTuioCursor(tcur: TuioCursor){
    log("cursor:add", tcur)
    cursors += tcur
  }

  def updateTuioCursor(tcur: TuioCursor){
    log("cursor:update", tcur)
  }

  def removeTuioCursor(tcur: TuioCursor){
    log("cursor:remove", tcur)
    cursors -= tcur
  }

  def refresh(ttime: TuioTime){}
}








