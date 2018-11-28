import React, { Component } from 'react';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';
import DatePicker, {calenderType} from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

const URL = "http://localhost:8080/BookerBackend/api/ticket/alltickets"

export default class FrontPage extends Component{
    constructor(props){
        super(props);
        this.state={data: [], airline: [], dept: [], dest: [], startDate: new Date(), endDate: new Date()};
    }

    

    

    async componentDidMount(){
        const data = await fetch(URL).then(res => res.json());
            this.setState({data : data});
        
        var mappedAirline = this.state.data.map((data) => data.airline)
        var airlineFilter = mappedAirline.filter((elem, pos, arr) => arr.indexOf(elem) == pos)
            this.setState({airline: airlineFilter})

        var mappedDepature = this.state.data.map((data) => data.departure)
        var departureFilter = mappedDepature.filter((elem, pos, arr) => arr.indexOf(elem) == pos)
            this.setState({departure : departureFilter})
        
        var mappedDestination = this.state.data.map((data) => data.destination)
        var destinationFilter = mappedDestination.filter((elem, pos, arr) => arr.indexOf(elem) == pos)
            this.setState({destination : destinationFilter})
            
            
    }

    handleChangeDepartureDate = (date) => {
        this.setState({startDate: date});
    }
    handleChangeArrivalDate = (date) => {
        this.setState({endDate: date});
    }

    render(){
        
        return(
            <form onSubmit="">
            <div>
                <Dropdown
                    options={this.state.airline}
                    onChange={this._onSelect}
                    placeholder="Select Airline"
                />
                <Dropdown
                    options={this.state.departure}
                    onChange={this._onSelect}
                    placeholder="Select Depature"
                />
                <Dropdown
                    options={this.state.destination}
                    onChange={this._onSelect}
                    placeholder="Select Destination"
                />
                Departure date
                <DatePicker 
                    dateFormat="dd/MM/yyyy" 
                    selected={this.state.startDate}
                    onChange={this.handleChangeDepartureDate}
                    placeholder="Departure Time"
                />Arrival date
                <DatePicker 
                    dateFormat="dd/MM/yyyy"
                    selected={this.state.endDate}
                    onChange={this.handleChangeArrivalDate}
                    placeholder="Arrival Time"
                />             
                <button>Submit</button>
            </div>
            </form>
        );
    }
}