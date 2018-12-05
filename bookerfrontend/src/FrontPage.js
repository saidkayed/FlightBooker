import React, { Component } from 'react';
import 'react-dropdown/style.css';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Ticket from "./Ticket"

const URL = "http://localhost:8084/BookerBackend/api/ticket/alltickets"

export default class FrontPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [], airline: [], departure: [], destination: [], startDate: new Date(), endDate: new Date(), search: [],
            names: [], sizePerPage: 10, page: 1, totalSize: 0, PSort: "",p:[], URI: "hej", 
            searchAirline: "", searchDeparture : "", searchDestination:"", searchDate: new Date() };
    }



    async componentDidMount() {
        const data = await fetch(URL).then(res => res.json());
        this.setState({ data: data });

        var mappedDepature = this.state.data.map((data) => data.departure)
        var departureFilter = mappedDepature.filter((elem, pos, arr) => arr.indexOf(elem) === pos)
        departureFilter.unshift("Choose Departure Airport")
        this.setState({ departure: departureFilter })

        var mappedDestination = this.state.data.map((data) => data.destination)
        var destinationFilter = mappedDestination.filter((elem, pos, arr) => arr.indexOf(elem) === pos)
        destinationFilter.unshift("Choose Destination Airport")
        this.setState({ destination: destinationFilter })
        /*

        var mappedStartDate = this.state.data.map((data) => data.depTime)
        var startDateFilter = mappedStartDate.filter((elem, pos, arr) => arr.indexOf(elem) === pos)
        var sortedStartDate = startDateFilter.sort()
        this.setState({ startDate: sortedStartDate })

        var mappedArrDate = this.state.data.map((data) => data.arrTime)
        var arrDateFilter = mappedArrDate.filter((elem, pos, arr) => arr.indexOf(elem) === pos)
        var sortedArrDate = arrDateFilter.sort()
        this.setState({ endDate: sortedArrDate })
        */
    }



handleChangeDeparture = (evt) => {
    this.setState({searchDeparture : evt.currentTarget.value})
}
handleChangeDestination = (evt) => {
    this.setState({searchDestination : evt.currentTarget.value})
}

        
handleChangeDate = (date) => {
        this.setState({ startDate: date });
        this.setState({searchDate : date})
    }
    
    

    render() {

        return (
                <div>
                    
                  
                    Departure Airport
                    <select name="departure" onChange={this.handleChangeDeparture}>
                        {this.state.departure.map(function mapper(data) {
                            return <option value={data}>{data}</option>
                        })}
                    </select>
                    Destination Airport
                    <select name="destination" onChange={this.handleChangeDestination}>
                        {this.state.destination.map(function mapper(data) {
                            return <option value={data}>{data}</option>
                        })}
                    </select>
                    Day Of Depature
                    <DatePicker 
                    dateFormat="dd-MM-YYYY"
                    selected={this.state.startDate}
                    onChange={this.handleChangeDate}
                    />
                    {/*
                    Departure Date
                    <select name="departureDate">
                        {this.state.startDate.map(function mapper(data) {
                            return <option value={data}>{data.substring(0, 10)}</option>
                        })}
                    </select>
                    Arrival Date
                        <select name="arrivalDate">
                        {this.state.endDate.map(function mapper(data) {
                            return <option value={data}>{data.substring(0, 10)}
                            </option>
                        })}
                    </select>
                    */}

                
                <Ticket search={this.state.search} p={this.state.p} departure={this.state.searchDeparture} destination={this.state.searchDestination} date={this.state.searchDate} />
                </div>
        );
    }
}