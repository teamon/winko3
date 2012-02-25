package winko

import com.trolltech.qt.core._
import com.trolltech.qt.gui._
import com.trolltech.qt.webkit._
import TUIO._

object Static {
  val mainHtml = <html>
    <head>
      <script type="text/javascript" src="http://github.com/DmitryBaranovskiy/raphael/raw/master/raphael-min.js"></script>
      <script type="text/javascript">{mainJS}</script>
    </head>
    <body>
      <div id="#main"></div>
    </body>
  </html>.toString

  val mainJS = """
    val Bridge = { cursors: { add: function(){
      alert("dupa");
      return 123;
      }} };

    Raphael(function() {
      var r = Raphael("main", 640, 480),
      Bridge = {
        cursors: {
          add: function(cur){
            alert(cur)
            var circle = paper.circle(50, 40, 10);
            circle.attr("fill", "#f00");
            circle.attr("stroke", "#fff");
            return 123;
          },
          update: function(cur){

          },
          remove: function(cur){

          }
        }
      }
    })
  """
}

class MainView extends QWidget {
  val browser = new QWebView

  setupLayout

  // Hello world
  frame.setHtml(Static.mainHtml)
  // frame.evaluateJavaScript("alert(1)")

  def frame = browser.page().mainFrame()

  def setupLayout {
    val layout = new QVBoxLayout(this)
    layout.addWidget(browser)
  }
}

object App extends Log {
  def ui(f: => Unit) = Util.invokeLater(new Runnable() { def run = f })
  // QApplication.invokeLater(new Runnable { def run = f })


  def main(args: Array[String]) {
    QApplication.initialize(args)
    val view = new MainView
    // view.showFullScreen()
    view.show()

    val eval = (cmd: String) => ui {
      log(cmd, view.frame.evaluateJavaScript(cmd))
    }

    val bridge = new TuioBridge(
      (tcur) => eval("alert(1)"),
      (tcur) => {}, //eval("Bridge.cursors.update();"),
      (tcur) => {} //eval("Bridge.cursors.remove();")
    )

    QApplication.exec()
  }
}

trait Log {
  def log(args: Any*) = println("[%s] %s" format (this.getClass.getName, args.mkString(" ")))
}

class TuioBridge(addc: TuioCursor => Unit, updatec: TuioCursor => Unit, removec: TuioCursor => Unit) extends TuioListener with Log {
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
    addc(tcur)
  }

  def updateTuioCursor(tcur: TuioCursor){
    log("cursor:update", tcur)
    updatec(tcur)
  }

  def removeTuioCursor(tcur: TuioCursor){
    log("cursor:remove", tcur)
    cursors -= tcur
    removec(tcur)
  }

  def refresh(ttime: TuioTime){}
}








