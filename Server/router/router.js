module.exports = function(app, fs)
{
  app.get('/HospitalData', function(req,res){
    fs.readFile(__dirname + "/../data/HospitalData.json",'utf8',function(err,data){
      var jsondata = JSON.parse(data);

      var length = Object.keys(jsondata.DATA).length;

      var i;
      var jsonStr = "[";
      for(i = 0; i < length; i++){
        if(jsondata.DATA[i].MC_TYPE == "의원" || jsondata.DATA[i].MC_TYPE == "종합병원"
        || jsondata.DATA[i].MC_TYPE == "병의원" || jsondata.DATA[i].MC_TYPE == "병원"){
          var temp = JSON.stringify(jsondata.DATA[i]);

          if(i != 0)
            jsonStr = jsonStr + ",";
          jsonStr = jsonStr + temp;
        }//end if
      }//end for loop
      jsonStr = jsonStr + "]";
      jsonStr = JSON.parse(jsonStr);
      res.status(200).json(jsonStr);
    })//end readFile()
  })//end app.get()

  app.get('/ShelterData', function(req,res){
    fs.readFile(__dirname + "/../data/ShelterData.json",'utf8',function(err,data){
      var jsondata = JSON.parse(data);

      res.status(200).json(jsondata.DATA);
    })//end readFile()
  })//end app.get()

  app.get('/KnowledgeData', function(req,res){
    fs.readFile(__dirname + "/../data/KnowledgeData.json",'utf8',function(err,data){
      var jsondata = JSON.parse(data);

      res.status(200).json(jsondata.DATA);
    })//end readFile()
  })//end app.get()

  app.get('/DetailData', function(req,res){
    fs.readFile(__dirname + "/../data/DetailData.json",'utf8',function(err,data){
      var jsondata = JSON.parse(data);

      res.status(200).json(jsondata.DETAIL_DATA);
    })//end readFile()
  })//end app.get()

  app.get('/img/:VIEWDIR/:IMGSRC', function(req,res){
    fs.readFile(__dirname + "/../img/"+ req.params.VIEWDIR + "/" + req.params.IMGSRC,function(err,data){
      res.writeHead(200, { "Context-Type": "image/jpg" });
      res.write(data);
      res.end();

    })//end readFile()
  })//end app.get()
}
