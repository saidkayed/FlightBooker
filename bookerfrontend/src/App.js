import React, { Component } from 'react';
import FrontPage from './FrontPage';
import { HashRouter as Router, Route, NavLink } from "react-router-dom";
import ReactModal from 'react-modal';
import "./loginmodal.css";
import "./Route.css"
import userFacade from './userFacade';

export default class App extends Component {

  constructor(props) {
    super(props);
    this.state = { showModal: false, loggedIn: false, username: "", password: "" }

  }
  userRole = (user) => {
    this.setState({ username: user })
  }

  logout = () => {
    userFacade.logout();
    this.setState({ loggedIn: false });
  }

  login = (user, pass) => {
    userFacade.login(user, pass)
      .then(res => this.setState({ loggedIn: true }));
  }

  handleOpenModal = () => {
    this.setState({ showModal: true });
  }
  handleCloseModal = () => {
    this.setState({ showModal: false })
  }

  login2 = (evt) => {
    evt.preventDefault();
    this.state.userRole(this.state.username);
    this.state.login(this.state.username, this.state.password);

  }
  onChange = (evt) => {
    this.setState({ [evt.target.id]: evt.target.value })
  }
  componentDidMount() {
    if (this.state.username === "admin" || this.state.username === "Admin") {
      userFacade.fetchAdminData().then(res => this.setState({ dataFromServer: res }))
    } else {
      userFacade.fetchData().then(res => this.setState({ dataFromServer: res }));

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
              {!this.state.loggedIn ?
                (<a onClick={this.handleOpenModal}>Log In</a>) :
                (<a>Logged In As {this.state.username}</a>)
                  (<a onClick={this.logout}>Log out</a>)}


            </li>
          </ul>
          <Route exact path="/" component={FrontPage} />
          <ReactModal className="ReactModal"
            isOpen={this.state.showModal}
            contentLabel="Minimal Modal Example">

            <form onSubmit={this.login} onChange={this.onChange} >
              <div className="ModalContent">
                <h2 id="logintext">Log In</h2>
                <input id="username" placeholder="username" type="username" name="username" required />
                <input id="password" placeholder="password" type="password" name="password" required />
              </div>
              <a id="closebtn" onClick={this.handleCloseModal}>Close</a>

              <button id="loginbtn">Log In</button>
            </form>

          </ReactModal>

          <h3>Welcome {this.state.username}</h3>
        </div>

      </Router>
    );
  }
}
