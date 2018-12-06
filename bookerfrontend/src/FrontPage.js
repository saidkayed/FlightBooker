import React, { Component } from 'react';
import 'react-dropdown/style.css';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Ticket from "./Ticket";
import Select from 'react-select';
import "./FrontPage.css";

const URL = "http://localhost:8080/BookerBackend/api/ticket/alltickets"
const options = [
    { value: 'SaidLand', label: 'SaidLand' },
    { value: 'KristianLand', label: 'KristianLand' },
    { value: 'TobiasLand', label: 'TobiasLand' }
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
            <div classname="divs">
                <Select id="style" placeholder="Departure Airport" value={selectedOption} options={options}/>
                <Select id="style"  placeholder="Destination Airport" value={selectedOption1} options={options}/>

                Day Of Depature 
                <DatePicker id="date" dateFormat="dd-MM-YYYY" selected={this.state.startDate} onChange={this.handleChangeDate}/>

                <Ticket search={this.state.search} p={this.state.p} departure={this.state.searchDeparture} destination={this.state.searchDestination} date={this.state.searchDate} />
            </div>
        );
    }
}