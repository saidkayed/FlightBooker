import React, {Component} from 'react';
const URL = "http://localhost:8080/BookerBackend/api/ticket/alltickets/"


export default class frontpage extends Component{
constructor(props){
    super(props);
    this.state = {ticket:[]}
}



async componentDidMount(){
    const data = await fetch(URL).then(res => res.json());
this.setState({ticket: data});
    console.log(this.state.ticket);
    console.log(this.state.ticket[0].airline)
}




    render(){
        return(
            <div>

            </div>
        )
    }
}


