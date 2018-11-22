import React, { Component } from 'react';
import Ticket from './Ticket';

class App extends Component {
  constructor(props){
    super(props);
  }

  onSubmit = (ev) => {
    ev.preventDefault();
    this.setState({Price_Sort:"?Sort"})   
   }


  render() {
    return (
      <div>
       <Ticket  />
      </div>
      
    );
  }
}

export default App;
