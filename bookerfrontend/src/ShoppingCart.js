import React,{Component} from 'react';
import Ticket from './Ticket'



export default class ShopingCart extends Component{
    constructor(props){
        super(props);
    }


    render(){
        console.log(this.props.Book,"saa")
        return(
            
            <div>
                <h1>Booked</h1>
               
                   <h2>{this.props.Book.airline}</h2>
            </div>
        )
    }
}