__winkoLoadScript = (url) ->
  head = document.getElementsByTagName('head').item(0)
  script = document.createElement("script")
  script.type = "text/javascript"
  script.src = url
  head.appendChild(script)

__winkoLoadStyle = (url) ->
  head = document.getElementsByTagName('head').item(0)
  link = document.createElement("link")
  link.rel = "stylesheet"
  link.href = url
  head.appendChild(link)

__winkoLoadStyle "http://lea.verou.me/demos/iphone-keyboard/iphone-keyboard.css"

if typeof jQuery == "undefined"
  __winkoLoadScript "http://code.jquery.com/jquery-1.7.1.min.js"

null # XXX: qt bug
