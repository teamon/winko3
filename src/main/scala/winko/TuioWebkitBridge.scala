package winko

import com.trolltech.qt.core._
import com.trolltech.qt.gui._
import com.trolltech.qt.webkit.QWebPage
import TUIO._
import formats._

class TuioWebkitBridge(window: QMainWindow, page: QWebPage) extends TuioListener with Log {
  protected def jseval(script: String) = page.mainFrame().evaluateJavaScript(script)

  // val cursors = scala.collection.mutable.Set[TuioCursor]()

  // TuioListener implementation
  def addTuioObject(tobj: TuioObject){}
  def updateTuioObject(tobj: TuioObject){}
  def removeTuioObject(tobj: TuioObject){}

  implicit def TuioCursorFormatWithSize = {
    val qsize = page.viewportSize
    TuioCursorFormat(qsize.width, qsize.height)
  }

  def addTuioCursor(tcur: TuioCursor){
    // cursors += tcur
    Utils.invokeLater {
      jseval("Winko.addCursor(%s)" format Json.write(tcur))

    //   val event = new QMouseEvent(QEvent.Type.MouseButtonPress, cursorToPoint(tcur),
    //                               Qt.MouseButton.LeftButton,
    //                               new Qt.MouseButtons(Qt.MouseButton.NoButton),
    //                               new Qt.KeyboardModifiers(Qt.KeyboardModifier.NoModifier));
    //   QApp.sendEvent(window, event)
    }
  }

  def updateTuioCursor(tcur: TuioCursor){
    Utils.invokeLater {
      jseval("Winko.updateCursor(%s)" format Json.write(tcur))

      // val event = new QMouseEvent(QEvent.Type.MouseMove, cursorToPoint(tcur),
      //                             Qt.MouseButton.NoButton,
      //                             new Qt.MouseButtons(Qt.MouseButton.NoButton),
      //                             new Qt.KeyboardModifiers(Qt.KeyboardModifier.NoModifier));
      // QApp.sendEvent(window, event)
    }
  }

  def removeTuioCursor(tcur: TuioCursor){
    // cursors -= tcur
    Utils.invokeLater {
      jseval("Winko.removeCursor(%s)" format Json.write(tcur))
      // val event = new QMouseEvent(QEvent.Type.MouseButtonRelease, cursorToPoint(tcur),
      //                             Qt.MouseButton.LeftButton,
      //                             new Qt.MouseButtons(Qt.MouseButton.NoButton),
      //                             new Qt.KeyboardModifiers(Qt.KeyboardModifier.NoModifier));
      // QApp.sendEvent(window, event)
    }
  }

  def cursorToPoint(tcur: TuioCursor) = {

    val qsize = QApplication.desktop().rect() // page.viewportSize
    // new QPoint(tcur.getX * qsize.height, tcur.getY * qsize.width)
    new QPoint((tcur.getX * qsize.width).toInt, (tcur.getY * qsize.height).toInt)
  }

  def refresh(ttime: TuioTime){}
}
