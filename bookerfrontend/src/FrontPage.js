import React, { Component } from 'react';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';

const URL = "http://localhost:8080/BookerBackend/api/ticket/alltickets"

export default class FrontPage extends Component{
    constructor(props){
        super(props);
        this.state={data: [], airline: [], dept: [], dest: []};
    }


    

    async componentDidMount(){
        const data = await fetch(URL).then(res => res.json());
            this.setState({data : data});
    }

    render(){
 
        return(
            <div>
                <Dropdown
                    options={this.state.data.map((data) =>
                        data.airline
                        )}
                    onChange={this._onSelect}
                    placeholder="Select Airline"
                />
                <Dropdown
                    options={this.state.data.map((data) =>
                        data.departure
                        )}
                    onChange={this._onSelect}
                    placeholder="Select Depature"
                />
                <Dropdown
                    options={this.state.data.map((data) =>
                            data.destination
                        )}
                    onChange={this._onSelect}
                    placeholder="Select Destination"
                />
            </div>
        );
    }
}