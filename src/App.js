import React, { useState } from "react";
import logo from "./logo.svg";
import "./App.css";
import Button from "react-bootstrap/Button";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <div class="jumbotron text-center">
          <h1>My First Bootstrap Page</h1>
          <p>Resize this responsive page to see the effect!</p>
        </div>

        <div class="container">
          <div class="row">
            <div class="col-sm-4">
              <h3>Column 1</h3>
              <p>Lorem ipsum dolor..</p>
            </div>
            <div class="col-sm-4">
              <h3>Column 2</h3>
              <p>Lorem ipsum dolor..</p>
            </div>
            <div class="col-sm-4">
              <h3>Column 3</h3>
              <p>Lorem ipsum dolor..</p>
            </div>
          </div>
        </div>
        <Button>Hello</Button>
      </header>
    </div>
  );
}

export default App;
