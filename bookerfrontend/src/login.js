import React, {Component} from 'react'
import ReactDOM from 'react-dom';
import ReactModal from 'react-modal';
import App from './App';

export default class login extends Component{
constructor(){
    super();
    this.state = {
showModal: false
    };
    this.handleOpenModal = this.handleOpenModal.bind(this);
    this.handleCloseModal = this.handleCloseModal.bind(this);
}
handleOpenModal () {
    this.setState({ showModal: true });
  }
  componentDidMount(){
    this.setState({ showModal: true });
  }
  
  handleCloseModal () {
    this.setState({ showModal: false });
  }
  
  render () {
    return (
      <div>
        <ReactModal 
           isOpen={this.state.showModal}
           contentLabel="Minimal Modal Example"
          >
        Email: <input type="email" name="email" />
          <button onClick={this.handleCloseModal}>Close Modal</button>
        </ReactModal>
        
      </div>
    );
  }
}


