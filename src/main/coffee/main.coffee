class WinkoImpl
  addCursor: (cur) => console.log ["addCursor", cur]
  updateCursor: (cur) => console.log ["updateCursor", cur]
  removeCursor: (cur) => console.log ["removeCursor", cur]

Winko = new WinkoImpl
