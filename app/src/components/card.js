import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import React from 'react';
import TwitterApi from './api/TwitterApi';
import SpotifyApi from './api/SpotifyApi';
function SimpleCard(item) {
  return (
    <div>
      <Card>
        {' '}
        {console.log(item.name)} <Card.Header style={{ color: 'black' }}> Featured </Card.Header>{' '}
        <Card.Body>
          <Card.Title style={{ color: 'black' }}> Put the title to twitter thingy here </Card.Title>{' '}
          <TwitterApi> </TwitterApi> {/*<Tweet tweetId="967824267948773377" options={{ theme: "dark" }}></Tweet>*/}{' '}
          {/*to use Tweet element, need to run 'npm install --save react-twitter-widgets' */}{' '}
        </Card.Body>{' '}
      </Card>
      <Card>
        {' '}
        {console.log(item.name)} <Card.Header style={{ color: 'black' }}> Featured </Card.Header>{' '}
        <Card.Body>
          <Card.Title style={{ color: 'black' }}> Put the title to Spotify thingy here </Card.Title>{' '}
          <SpotifyApi></SpotifyApi>{' '}
        </Card.Body>{' '}
      </Card>{' '}
    </div>
  );
}

export default SimpleCard;
