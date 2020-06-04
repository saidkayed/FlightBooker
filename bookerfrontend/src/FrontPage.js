import React, { Component } from 'react';
import 'react-dropdown/style.css';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Ticket from "./Ticket";
import Select from 'react-select';
import "./FrontPage.css";

const URL = "http://localhost:8080/BookerBackend/api/ticket/alltickets"
const options = [
    { value: 'LHR', label: 'LHR' },
    { value: 'FRA', label: 'FRA' },
    { value: 'AMS', label: 'AMS' },
    { value: 'MAD', label: 'MAD' },
    { value: 'CDG', label: 'CDG' }
];

export default class FrontPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [], airline: [], departure: [], destination: [], startDate: new Date(), endDate: new Date(), search: [],
            names: [], sizePerPage: 10, page: 1, totalSize: 0, PSort: "", p: [], URI: "hej",
            searchAirline: "", searchDeparture: "", searchDestination: "", searchDate: new Date()
        };
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

    }

    handleChangeDeparture = (evt) => {
        this.setState({ searchDeparture: evt.currentTarget.value })
    }
    handleChangeDestination = (evt) => {
        this.setState({ searchDestination: evt.currentTarget.value })
    }

    handleChangeDate = (date) => {
        this.setState({ startDate: date });
        this.setState({ searchDate: date })
    }

    render() {
        const { selectedOption, selectedOption1 } = this.state;

        return (
                <center>
            <div>
                    <select id="date" name="departure" onChange={this.handleChangeDeparture}>
                        {this.state.departure.map(function mapper(data) {
                            return <option value={data}>{data}</option>
                        })}
                    </select>
                    <select id="date" name="destination" onChange={this.handleChangeDestination}>
                        {this.state.destination.map(function mapper(data) {
                            return <option value={data}>{data}</option>
                        })}
                    </select>
                    <DatePicker id="date" dateFormat="dd-MM-YYYY" selected={this.state.startDate} onChange={this.handleChangeDate} />
                    <Ticket search={this.state.search} p={this.state.p} departure={this.state.searchDeparture} destination={this.state.searchDestination} date={this.state.searchDate} username={this.props.loggedInUser} />
                </div>
            </center>
        );
    }
}