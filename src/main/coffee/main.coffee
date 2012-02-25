class WinkoImpl
  addCursor: (cur) => console.log ["addCursor", cur]
  updateCursor: (cur) => console.log ["updateCursor", cur]
  removeCursor: (cur) => console.log ["removeCursor", cur]

Winko = new WinkoImpl

loadScripts = () -> 
  head = document.getElementsByTagName('head').item(0)
  script= document.createElement("script")
  script.type = "text/javascript"
  script.src= "http://code.jquery.com/jquery-1.7.1.min.js";
  head.appendChild(script);