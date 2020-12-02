import React, { Component, useState } from 'react';


class Todos extends Component {
    fetch('https://swapi.dev/api/people/1/')
    .then(res => res.json())
    .then((result) => {
        result.name
    })

render() {
    // var completed;
    // if (this.props.completed) {
    //     completed = 'true'
    // } else {
    //     completed = 'false'
    // }
    if (this.props.todos) {

        return this.props.todos.map((todo) => (
            <div>
                <button>HELLO + {todo.title}</button>
                <div>
                    {todo.completed ? 'yes' : 'no'}
                </div>
            </div>
        ));
    }
    return <h3>no todos found</h3>
};
}

export default Todos;
