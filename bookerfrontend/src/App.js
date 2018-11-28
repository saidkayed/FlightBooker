import React, { Component } from 'react';
import FrontPage from './FrontPage';
import { HashRouter as Router, Route, NavLink } from "react-router-dom";
import ReactModal from 'react-modal';
import "./loginmodal.css";
import "./Route.css"
import FrontPage from './FrontPage'

export default class App extends Component {

  constructor(){
    super();
    this.state = {
showModal: false
    };
  }
  handleOpenModal = () => {
    this.setState({ showModal: true });
  }

  handleCloseModal = () => {
    this.setState({ showModal: false });
  }

  render() {
    return (
      <Router>
      <div> 
       <ul className="header">
         <li>
           <NavLink exact to="/">Front Page</NavLink>
         </li>
         <li>
            <a onClick={this.handleOpenModal}>Log In</a>        
         </li>
       </ul>
       <Route exact path="/" component={FrontPage}/>
       <ReactModal className="ReactModal"
           isOpen={this.state.showModal}
           contentLabel="Minimal Modal Example">

           <div className="ModalContent">
           <h2 id="logintext">Log In</h2>
           <input id="username" placeholder="username" type="username" name="username" required />
           <input id="password" placeholder="password" type="password" name="password" required />
           </div>
           <a id="closebtn" onClick={this.handleCloseModal}>Close</a>

        </ReactModal>
      </div>
      </Router>
    );
  }
}
