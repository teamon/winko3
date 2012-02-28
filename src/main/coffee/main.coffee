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

    # Keyboard.show()

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


Keyboard =
  show: ->
    if $("#_wkey").size() == 0
      key = $("<div id='_wkey' />")
      key.css("position", "fixed")
      key.css("width", "500px")
      key.css("bottom", 0)
      key.css("left", "50%")
      key.css("margin-left", "-250px")
      key.html(Keyboard.html)
      $("body").prepend(key)

  html: """

<div id="letters-keyboard">
    <ul id="iphone-keyboard">
      <li>
        <button type="button">Q</button>
        <button type="button">W</button>
        <button type="button">E</button>

        <button type="button">R</button>
        <button type="button">T</button>
        <button type="button">Y</button>
        <button type="button">U</button>
        <button type="button">I</button>
        <button type="button">O</button>

        <button type="button">P</button>
      </li>
      <li>
        <button type="button">A</button>
        <button type="button">S</button>
        <button type="button">D</button>
        <button type="button">F</button>

        <button type="button">G</button>
        <button type="button">H</button>
        <button type="button">J</button>
        <button type="button">K</button>
        <button type="button">L</button>
      </li>

      <li>
        <button type="button" class="specialkey shift">S</button>
        <button type="button">Z</button>
        <button type="button">X</button>
        <button type="button">C</button>
        <button type="button">V</button>

        <button type="button">B</button>
        <button type="button">N</button>
        <button type="button">M</button>
        <button type="button" class="specialkey backspace">B</button>
      </li>
      <li>
        <button type="button" class="specialkey numbers">123</button>

        <button type="button" class="specialkey international">I</button>
        <button type="button" class="space">space</button>
        <button type="button" class="specialkey return">return</button>
      </li>
    </ul>
  </div>

"""

null # XXX: qt bug
