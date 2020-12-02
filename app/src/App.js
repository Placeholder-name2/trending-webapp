import { Component } from 'react';
import './App.css';
import Todos from './components/Todos'

class App extends Component {
  state = {
    todos: [
      {
        id: 1,
        title: 'Take out the trash',
        completed: false
      },
      {
        id: 2,
        title: 'Take out the trash again',
        completed: true
      },
      {
        id: 3,
        title: 'Take out the trash agina again',
        completed: false
      },
      {
        id: 4,
        title: 'Take out the trash aoweifaoi',
        completed: false
      }
    ]
  }
  render() {
    return (
      <div className="App">
        <Todos todos={this.state.todos}>
          TESTIT
        </Todos>
      </div>
    );
  }
}

export default App;
