var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var fs = require('fs');


var server = app.listen(3000, function(){
  console.log("Express server has started on port 3000");
})

app.use(express.static('public'));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded());

var router = require('./router/router')(app, fs);
