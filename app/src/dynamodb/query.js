// Need aws credentials file in default directory to work

var AWS = require('aws-sdk');
var credentials = new AWS.SharedIniFileCredentials({profile: 'default'});
AWS.config.credentials = credentials;

process.env.REGION = "eu-north-1"; //put region here for now
AWS.config.update({ region : process.env.REGION});

var ddb = new AWS.DynamoDB.DocumentClient();//({ apiVersion: '2012-08-10' });

var params = {
  TableName: "trending_item", 
  Select: "ALL_ATTRIBUTES",
  //FilterExpression: "contains(#sv, :sv_name)",
  FilterExpression: "#sv = :sv_name",
  ExpressionAttributeNames: {
    "#sv": "service",
  },
  ExpressionAttributeValues: {
    ":sv_name": "TWITTER",
  }
};

ddb.scan(params, function(err, data) {
  if (err) {
     console.error("Unable to read item. Error JSON:", JSON.stringify(err, null, 2));
  } else {
     console.log("GetItem succeeded:", JSON.stringify(data, null, 2));
  }
});

