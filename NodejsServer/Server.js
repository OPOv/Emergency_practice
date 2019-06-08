var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var fs = require('fs');
var request = require('request');
var convert = require('xml-js');

var server = app.listen(3000, function(){
  console.log("Express server has started on port 3000");
})

app.use(express.static('public'));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded());

var i = 1;
var i_0 = 1;
var jsonStr = '[';
var jsonStr1 = '[';

var url = 'http://apis.data.go.kr/1741000/EarthquakeIndoors/getEarthquakeIndoorsList';
var url1 = 'http://apis.data.go.kr/B552657/AEDInfoInqireService/getAedFullDown';
   
//var repeat = setInterval(EarthquakeIndoorsRequest, 500);
//var repeat1 = setInterval(AEDInfoRequest, 500);

var router = require('./router/router')(app, fs);

function EarthquakeIndoorsRequest(){
    var queryParams = '?' + encodeURIComponent('ServiceKey') + '=CFChzWsCjrGxW16mSq%2F9diy47VtyHqy8LDip17p%2BYdwmse88ft%2BePRJrfIyJHKIhWlqLJIMcQOtiI6hM7NQ1lQ%3D%3D'
    queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent(i);
    queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10');
    queryParams += '&' + encodeURIComponent('type') + '=' + encodeURIComponent('json');
    queryParams += '&' + encodeURIComponent('flag') + '=' + encodeURIComponent('Y');
    i = i + 1;

    request({
        url: url + queryParams,
        method: 'GET'
    }, function (error, response, body) {
	//console.log(body);
	var jsondata = JSON.parse(body);
	jsondata = jsondata.EarthquakeIndoors[1];

	var j;
	for(j = 0; j < 10; j++){	    
	    if(jsondata.row[j].ctprvn_nm == '강원도'){
		var temp = JSON.stringify(jsondata.row[j]);
		
		if(jsonStr != '[')
		    jsonStr = jsonStr + ',';
		jsonStr = jsonStr + temp;
	    }
	    //if(i == 480 && j == 8)
		//break;
	    fs.writeFile('./data/ShelterData1.json', jsonStr, 'utf8',function(err, jsonStr){
		if (err)
		    console.log('err');
		else;
		    //console.log('writing...');
	    });
	} 	
        console.log('i : ',i);
    });
    if(i == 480)
	clearInterval(repeat);
}

function AEDInfoRequest(){
    var queryParams = '?' + encodeURIComponent('ServiceKey') + '=CFChzWsCjrGxW16mSq%2F9diy47VtyHqy8LDip17p%2BYdwmse88ft%2BePRJrfIyJHKIhWlqLJIMcQOtiI6hM7NQ1lQ%3D%3D'
    queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent(i_0);
    queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10');

    i_0 = i_0 + 1;

    request({
        url: url1 + queryParams,
        method: 'GET'
    }, function (error, response, body) {
	var result = body;
	var jsondata = convert.xml2json(result, {compact:true,space: 0});
	jsondata = JSON.parse(jsondata);
	jsondata = jsondata.response.body.items;

	var j;
	for(j = 0; j < 10; j++){
	    //console.log(jsondata.item[j].sido._text);	    
	    if(jsondata.item[j].sido._text == '강원도'){
		var temp = JSON.stringify(jsondata.item[j]);
		
		if(jsonStr1 != '[')
		    jsonStr1 = jsonStr1 + ',';
		jsonStr1 = jsonStr1 + temp;
	    }
	   // if(i_0 == 1447 && j == 6)
		//break;
	    fs.writeFile('./data/AEDData.json', jsonStr1, 'utf8',function(err, jsonStr1){
		if (err)
		    console.log('err');
		else;
		    //console.log('writing...');
	    });
	}
        console.log('i_0 : ',i_0); 	
    });
    if(i_0 == 1446)
	clearInterval(repeat1);
}
