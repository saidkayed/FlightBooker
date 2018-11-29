import React, { Component } from "react"

export default class LogIn extends Component {

  login = (evt) => {
    evt.preventDefault();
    this.props.userRole(this.state.username);
    this.props.login(this.state.username, this.state.password);

  }
  onChange = (evt) => {
    this.setState({ [evt.target.id]: evt.target.value })
  }
  render() {
    return (
      <div>
        <h2>Login</h2>
        <form onSubmit={this.login} onChange={this.onChange} >
          <input placeholder="User Name" id="username" />
          <input placeholder="Password" id="password" />
          <button>Login</button>
        </form>
      </div>
    )
  }
}