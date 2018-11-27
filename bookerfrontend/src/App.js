import React, { Component } from 'react';
import Ticket from './Ticket';
import { HashRouter as Router, Route, NavLink } from "react-router-dom";

class App extends Component {

  render() {
    return (
      <Router>
      <div> 
       <Ticket  />
      </div>
      </Router>
    );
  }
}

export default App;
