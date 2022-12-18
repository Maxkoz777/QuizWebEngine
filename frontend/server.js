const express = require("express"),
  serveStatic = require("serve-static"),
  history = require("connect-history-api-fallback"),
  port = process.env.PORT || 8081;

console.log("PORT:", port);

const app = express();

app.use(history());
app.use(serveStatic(__dirname + "/dist/spa"));
app.listen(port);
