import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import React from 'react';
import TwitterApi from './api/TwitterApi';

function SimpleCard() {
  return (
    <Card>
      <Card.Header style={{ color: 'black' }}>Featured</Card.Header>
      <Card.Body>
        <Card.Title style={{ color: 'black' }}>Put the title to twitter thingy here</Card.Title>
        <TwitterApi></TwitterApi>
      </Card.Body>
    </Card>
  );
}

export default SimpleCard;
