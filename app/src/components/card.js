import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import React from 'react';
import TwitterApi from './api/TwitterApi';
import SpotifyApi from './api/SpotifyApi';
import { Tweet } from '../../node_modules/react-twitter-widgets'
function SimpleCard(item) {
  return (
    <div>
      <Card>
        <Card.Body className="d-flex justify-content-center">
          <Card.Title style={{ color: 'black' }} className="d-flex justify-content-center"></Card.Title>
          <ul>
            <div>Trending right now on Twitter: {"test123"}</div>
            <Tweet tweetId={1352993929474367488}></Tweet>
          </ul>
        </Card.Body>
      </Card>
      {/* <Card>
        <Card.Body>
          <Card.Title style={{ color: 'black' }}> Put the title to Spotify thingy here </Card.Title>
           <SpotifyApi></SpotifyApi> 
        </Card.Body>
      </Card> */}
    </div>
  );
}

export default SimpleCard;
