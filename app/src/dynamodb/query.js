import * as AWS from 'aws-sdk';
process.env.REGION = ''; //put region here for now
AWS.config.update({ region: process.env.REGION });

var ddb = new AWS.DynamoDB({ apiVersion: '2012-08-10' });

//Not done below
var params = {
  ExpressionAttributeValues: {
    ':s': { N: '2' },
    ':e': { N: '09' },
    ':topic': { S: 'PHRASE' },
  },
  KeyConditionExpression: 'Season = :s and Episode > :e',
  ProjectionExpression: 'Episode, Title, Subtitle',
  FilterExpression: 'contains (Subtitle, :topic)',
  TableName: 'EPISODES_TABLE',
};

ddb.query(params, function (err, data) {
  if (err) {
    console.log('Error', err);
  } else {
    //console.log("Success", data.Items);
    data.Items.forEach(function (element, index, array) {
      console.log(element.Title.S + ' (' + element.Subtitle.S + ')');
    });
  }
});
