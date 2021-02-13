//import * as AWS from 'aws-sdk';
//process.env.AWS_SDK_LOAD_CONFIG = true; 

/*
var AWS = require("aws-sdk");


AWS.config.getCredentials(function(err) {
  if (err) console.log(err.stack); 
  // credentials not loaded
  else {
    console.log("Access key:", AWS.config.credentials.accessKeyId);
  }
});
*/
var AWS = require('aws-sdk');
var credentials = new AWS.SharedIniFileCredentials({profile: 'default'});
AWS.config.credentials = credentials;

process.env.REGION = "eu-north-1"; //put region here for now
AWS.config.update({ region : process.env.REGION});

var ddb = new AWS.DynamoDB.DocumentClient();//({ apiVersion: '2012-08-10' });

//Not done below
var params = {
  TableName: "Incli",
  KeyConditionExpression: "#iid = :id_val",
  ExpressionAttributeNames: { 
    "#iid": "ID",
  },
  ExpressionAttributeValues: {
    ":id_val": '120',
    ":id_val": '1',
  }
};

ddb.query(params, function (err, data) {
  if (err) {
    console.log('Error', err);
  } else {
    console.log("Query succeeded.");
    data.Items.forEach(function(item) {
        console.log(" -", item.ID + ": " + item.URL);
    });
  }
});
