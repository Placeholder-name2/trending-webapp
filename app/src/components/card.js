import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import React from 'react';
import TwitterApi from './api/TwitterApi';
import { Tweet } from '../../node_modules/react-twitter-widgets'


function SimpleCard(item) {
  return (
    <Card>
      {console.log(item.name)}
      <Card.Header style={{ color: 'black' }}>Featured</Card.Header>
      <Card.Body>
        <Card.Title style={{ color: 'black' }}>Put the title to twitter thingy here</Card.Title>
        <TwitterApi></TwitterApi>
        {/*<Tweet tweetId="967824267948773377" options={{ theme: "dark" }}></Tweet>*/}
      </Card.Body>
    </Card>
  );
}

export default SimpleCard;
