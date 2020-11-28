import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import React from "react";

function SimpleCard() {
  return (
    <Card>
      <Card.Header style={{ color: "black" }}>Featured</Card.Header>
      <Card.Body>
        <Card.Title style={{ color: "black" }}>
          Put the title to twitter thingy here
        </Card.Title>
        <Card.Text style={{ color: "black" }}>
          Put twitter thingy here
        </Card.Text>
        <Button variant="primary">Go to top</Button>
      </Card.Body>
    </Card>
  );
}

export default SimpleCard;
