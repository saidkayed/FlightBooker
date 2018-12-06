import React, { Component } from 'react';
import FrontPage from './FrontPage';
import { HashRouter as Router, Route, NavLink } from "react-router-dom";
import ReactModal from 'react-modal';
import "./loginmodal.css";
import "./Route.css";
import "./App.css";
import logo from './logo1.svg';
import userFacade from './userFacade';

export default class App extends Component {

  constructor() {
    super();
    this.state = { showModal: false, loggedIn: false, username: "", password: "", dataFromServer: "Fethcing!", loggedInUser: "" }
  }

  handleOpenModal = () => {
    this.setState({ showModal: true });
  }
  handleCloseModal = () => {
    this.setState({ showModal: false })
  }

  userRole = (user) => {
    this.setState({ username: user })
  }

  login2 = (evt) => {
    evt.preventDefault();
    this.userRole(this.state.username2);
    this.login(this.state.username, this.state.password);
    this.setState({ username: this.state.username })
    this.handleCloseModal();

  }
  componentDidMount() {

    if (this.state.loggedIn == true) {
      this.setState({ loggedInUser: this.state.username })
    } else {
      this.setState({ loggedInUser: "Guest" })
    }

  }

  login = async (user, pass) => {
    await userFacade.login(user, pass)
      .then(res => {
        this.setState({ loggedIn: true })
      });

    this.forceUpdate(this.componentDidMount);
  }


  logout = () => {
    userFacade.logout();
    this.setState({ loggedIn: false });
    this.setState({ loggedInUser: "Guest" })
  }




  onChange = (evt) => {
    this.setState({ [evt.target.id]: evt.target.value })
  }

  render() {
    return (

      <Router>
        <div>
          <ul className="header">
            <li>
              <NavLink exact to="/"></NavLink>
            </li>
            <li>
              {!this.state.loggedIn ?
                (<a onClick={this.handleOpenModal} style={{ cursor: 'pointer' }}>Log In</a>) :
                (<a onClick={this.logout} style={{ cursor: 'pointer' }}>Log out</a>)

              }
            </li>
            <li>

              <a id="loginname">{this.state.loggedInUser}</a>

            </li>
          </ul>
          <Route exact path="/" component={FrontPage} />

          <ReactModal 
            className="ReactModal"
            isOpen={this.state.showModal}
            contentLabel="Minimal Modal Example">

            <form onSubmit={this.login2} onChange={this.onChange} >
              <div className="ModalContent">
                <h2 id="logintext">Log In</h2>
                <input id="username" placeholder="username" type="username" name="username" required />
                <input id="password" placeholder="password" type="password" name="password" required />
              </div>
              <a id="closebtn" style={{ cursor: 'pointer' }} onClick={this.handleCloseModal}>Close</a>

              <button id="loginbtn">Log In</button>
            </form>

          </ReactModal>


        </div>

      </Router>
    );
  }
}
