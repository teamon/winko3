package winko

trait Log {
  def log(args: Any*) = println("[%s] %s" format (this.getClass.getName, args.mkString(" ")))
}

object Utils {
  def invokeLater(f: => Unit) = Util.invokeLater(new Runnable() { def run = f })
}
