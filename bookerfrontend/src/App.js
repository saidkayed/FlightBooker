import React, { Component } from 'react';
import Ticket from './Ticket';
import { HashRouter as Router, Route, NavLink } from "react-router-dom";
import login from './login';

class App extends Component {

  render() {
    return (
      <Router>
      <div> 
       <ul>
         <li>
           <NavLink exact to="/">Tickets</NavLink>
         </li>
         <li>
        
         </li>
       </ul>
       <Route exact path="/" component={Ticket}/>
      </div>
      </Router>
    );
  }
}

export default App;
