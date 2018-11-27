import React, { Component } from 'react';
import Ticket from './Ticket';
import login from './login';
import { HashRouter as Router, Route, NavLink } from "react-router-dom";

class App extends Component {

  render() {
    return (
      <Router>
      <div> 
       <ul>
         <li>
           <NavLink exact to="/">Tickets</NavLink>
           <NavLink to="/login">log in</NavLink>
         </li>
       </ul>
       <Route exact path="/" component={Ticket}/>
       <Route path="/login" component={login}/>
      </div>
      </Router>
    );
  }
}

export default App;
