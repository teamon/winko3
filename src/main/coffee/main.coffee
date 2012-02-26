HTMLElement.prototype.click = ->
  evt = this.ownerDocument.createEvent('MouseEvents')
  evt.initMouseEvent('click', true, true, this.ownerDocument.defaultView, 1, 0, 0, 0, 0, false, false, false, false, 0, null)
  this.dispatchEvent(evt)

class WinkoImpl
  addCursor: (cur) ->
    dot = $("<div/>")
      .css("position", "fixed")
      .css("left", cur.x)
      .css("top", cur.y)
      .css("background", "#f00")
      .css("width", "50px")
      .css("height", "50px")
      .css("margin-left", "-25px")
      .css("margin-top", "-25px")
      .css("opacity", 0.5)
      .css("z-index", 100000)
      .attr("id", "__cur#{cur.id}")
    $("body").append(dot)

    null # XXX: qt bug

  updateCursor: (cur) ->
    $("#__cur#{cur.id}").css("left", cur.x).css("top", cur.y)
    null # XXX: qt bug

  removeCursor: (cur) ->
    $("#__cur#{cur.id}").remove()
    @clickAt(cur.x, cur.y)
    null # XXX: qt bug

  clickAt: (x,y) ->
    e = document.elementFromPoint(x,y)
    $(e).focus()
    e.click()
    # console.log(e)

Winko = new WinkoImpl

null # XXX: qt bug
