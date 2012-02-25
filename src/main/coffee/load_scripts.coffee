__winkoLoadScript = (url, callback) ->
  head = document.getElementsByTagName('head').item(0)
  script = document.createElement("script")
  script.type = "text/javascript"
  script.src = url
  if callback
    script.onreadystatechange = ->
      if this.readyState == 'complete'
        callback()

    script.onload = callback
  head.appendChild(script)

__winkoLoadScript "http://code.jquery.com/jquery-1.7.1.min.js", () ->
  __winkoLoadScript "@MAIN_URL@", () ->
