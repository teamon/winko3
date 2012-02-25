package winko

import com.trolltech.qt.webkit.QWebFrame
import TUIO._
import formats._

class TuioWebkitBridge(frame: QWebFrame) extends TuioListener with Log {
  protected def jseval(script: String) = Utils.invokeLater { frame.evaluateJavaScript(script) }

  // val cursors = scala.collection.mutable.Set[TuioCursor]()

  // TuioListener implementation
  def addTuioObject(tobj: TuioObject){}
  def updateTuioObject(tobj: TuioObject){}
  def removeTuioObject(tobj: TuioObject){}

  def addTuioCursor(tcur: TuioCursor){
    // cursors += tcur
    jseval("Winko.addCursor(%s)" format Json.write(tcur))
  }

  def updateTuioCursor(tcur: TuioCursor){
    jseval("Winko.updateCursor(%s)" format Json.write(tcur))
  }

  def removeTuioCursor(tcur: TuioCursor){
    // cursors -= tcur
    jseval("Winko.removeCursor(%s)" format Json.write(tcur))
  }

  def refresh(ttime: TuioTime){}
}
