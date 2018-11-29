import React, { Component } from 'react';
import FrontPage from './FrontPage';
import apiFacade from './apiFacade';
import { HashRouter as Router, Route, NavLink } from "react-router-dom";
import ReactModal from 'react-modal';
import "./loginmodal.css";
import "./Route.css"

export default class App extends Component {

  constructor(){
    super();
    this.state = { loggedIn: false }
    this.state = { username: "", password: "" }
    this.state = { showModal: false }
  }
  
  handleOpenModal = () => {
    this.setState({ showModal: true });
    
    userRole = (user) => {
      this.setState({ username: user })
    }
  
    logout = () => {
      apiFacade.logout();
      this.setState({ loggedIn: false });
    }
  
    login = (user, pass) => {
      apiFacade.login(user, pass)
        .then(res => this.setState({ loggedIn: true }));
    }
  }

  handleCloseModal = () => {
    
    login = (evt) => {
      
      evt.preventDefault();
      this.props.userRole(this.state.username);
      this.props.login(this.state.username, this.state.password);
      console.log(username)
      if(this.state.loggedIn=== true){
        this.setState({ showModal: false });
      }else{
        console.log(username)
      }
    }
    
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
