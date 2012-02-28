package winko

import com.trolltech.qt.gui._
import TUIO._

object App extends Log {
  def main(args: Array[String]) {
    QApplication.initialize(args)
    val view = new MainView
    // view.showFullScreen()
    view.show()

    val client = new TuioClient
    client.addTuioListener(new TuioWebkitBridge(view, view.page))
    client.connect

    QApplication.exec()
  }
}
