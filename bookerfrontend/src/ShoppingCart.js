import React,{Component} from 'react';

export default class ShopingCart extends Component{
    constructor(props){
        super(props);
    }

    render(){
        return(
            
            <div>
                <h1>Booked</h1>     
                <h2>{this.props.Book.airline}</h2>
            </div>
        )
    }
}