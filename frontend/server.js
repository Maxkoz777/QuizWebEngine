const
  express = require('express'),
  serveStatic = require('serve-static'),
  history = require('connect-history-api-fallback'),
  port = process.env.BACK_URL || 5000

const app = express()

app.use(history())
app.use(serveStatic(__dirname + '/dist/spa'))
app.listen(port)
