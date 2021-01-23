import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import React from 'react';
import TwitterApi from './api/TwitterApi';
import SpotifyApi from './api/SpotifyApi';
function SimpleCard(item) {
  return (
    <div>
      <Card>
        {/* <Card.Body className="d-flex justify-content-center">
          <Card.Title style={{ color: 'black' }} className="d-flex justify-content-center"></Card.Title>
          <TwitterApi> </TwitterApi>
        </Card.Body> */}
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
