package winko

import sjson.json._
import sjson.json.DefaultProtocol._
import sjson.json.JsonSerialization._
import dispatch.json._
import TUIO._

object formats {
  implicit val TuioCursorFormat = new Writes[TuioCursor] {
    def writes(tcur: TuioCursor) = JsObject(List(
      (tojson("id").asInstanceOf[JsString], tojson(tcur.getCursorID)),
      (tojson("x").asInstanceOf[JsString], tojson(tcur.getX)),
      (tojson("y").asInstanceOf[JsString], tojson(tcur.getY))
    ))
  }
}
object Json {
  def write[A : Writes](data: A) = JsValue.toJson(tojson(data))
}
