import React, { useState } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import InfinityScroll from './components/infinity-scroll';
import Fotter from './components/fotter';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <InfinityScroll />
        <Fotter />
      </header>
    </div>
  );
}

export default App;
