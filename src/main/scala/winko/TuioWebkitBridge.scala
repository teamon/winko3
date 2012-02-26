package winko

import com.trolltech.qt.webkit.QWebPage
import TUIO._
import formats._

class TuioWebkitBridge(page: QWebPage) extends TuioListener with Log {
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
    Utils.invokeLater { jseval("Winko.addCursor(%s)" format Json.write(tcur)) }
  }

  def updateTuioCursor(tcur: TuioCursor){
    Utils.invokeLater { jseval("Winko.updateCursor(%s)" format Json.write(tcur)) }
  }

  def removeTuioCursor(tcur: TuioCursor){
    // cursors -= tcur
    Utils.invokeLater { jseval("Winko.removeCursor(%s)" format Json.write(tcur)) }
  }

  def refresh(ttime: TuioTime){}
}
