import Button from 'react-bootstrap/Button';
import React from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import Form from 'react-bootstrap/Form';
import FormControl from 'react-bootstrap/FormControl';

function Fotter() {
  return (
    <Navbar bg="dark" variant="dark" fixed="bottom">
      <Navbar.Brand href="#home">Trending</Navbar.Brand>
      <div class="d-flex flex-row-reverse">
        <Button class="p-2" variant="outline-info">
          Top!
        </Button>
      </div>
    </Navbar>
  );
}

export default Fotter;

<div style={{ display: 'flex' }}>
  <button style={{ marginLeft: 'auto' }}>Click</button>
</div>;
