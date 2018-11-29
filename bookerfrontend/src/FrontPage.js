import React, { Component } from 'react';
import 'react-dropdown/style.css';
import DatePicker, { calenderType } from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Ticket from "./Ticket"

const URL = "http://localhost:8084/BookerBackend/api/ticket/alltickets"

export default class FrontPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [], airline: [], departure: [], destination: [], startDate: [], endDate: [], search: [],
            names: [], sizePerPage: 10, page: 1, totalSize: 0, PSort: "",URL:""};
    }



    async componentDidMount() {
        const data = await fetch(URL).then(res => res.json());
        this.setState({ data: data });

        var mappedAirline = this.state.data.map((data) => data.airline)
        var airlineFilter = mappedAirline.filter((elem, pos, arr) => arr.indexOf(elem) == pos)
        this.setState({ airline: airlineFilter })

        var mappedDepature = this.state.data.map((data) => data.departure)
        var departureFilter = mappedDepature.filter((elem, pos, arr) => arr.indexOf(elem) == pos)
        this.setState({ departure: departureFilter })

        var mappedDestination = this.state.data.map((data) => data.destination)
        var destinationFilter = mappedDestination.filter((elem, pos, arr) => arr.indexOf(elem) == pos)
        this.setState({ destination: destinationFilter })


        var mappedStartDate = this.state.data.map((data) => data.depTime)
        var startDateFilter = mappedStartDate.filter((elem, pos, arr) => arr.indexOf(elem) == pos)
        var sortedStartDate = startDateFilter.sort()
        this.setState({ startDate: sortedStartDate })

        var mappedArrDate = this.state.data.map((data) => data.arrTime)
        var arrDateFilter = mappedArrDate.filter((elem, pos, arr) => arr.indexOf(elem) == pos)
        var sortedArrDate = arrDateFilter.sort()
        this.setState({ endDate: sortedArrDate })
    }



    onSubmit = async (evt) => {
        evt.preventDefault();

        
        const URI = `http://localhost:8084/BookerBackend/api/ticket/foundtickets?from=${0}&to=${10}` + "&airline=" + evt.currentTarget.airline.value + "&dept=" + evt.currentTarget.departure.value + "&dest=" + evt.currentTarget.destination.value + "&deptdate=" + evt.currentTarget.departureDate.value + "&arrdate=" + evt.currentTarget.arrivalDate.value + this.state.PSort;
        console.log(URI);
      
    //this.setState({ airline: evt.target.airline.value, departure: evt.target.departure.value, destination: evt.target.destination.value, startDate:  evt.target.departureDate.value, endDate:evt.target.arrivalDate.value})
    this.setState({URL:URI.toString()})

    }

    handleChangeDepartureDate = (date) => {

        this.setState({ startDate: date });
    }
    handleChangeArrivalDate = (date) => {

        this.setState({ endDate: date });
    }
    onChange = async (ev) => {
        

        const URI = `http://localhost:8084/BookerBackend/api/ticket/foundtickets?from=${0}&to=${10}` + "&airline=" + this.state.airline + "&dept=" + this.state.depature + "&dest=" + this.state.destination + "&depdate=" + this.state.deptdate + "&arrdate=" + this.state.arrdate + this.state.PSort;

        let p = await fetch(URI).then(res => {
            return res.json()
        });
        

        this.setState({search: p })
    }

    render() {

        return (
            <form onSubmit={this.onSubmit}>
                <div>

                    Airline
                    <select name="airline">
                        {this.state.airline.map(function mapper(data) {
                            return <option value={data}>{data}</option>
                        })}
                    </select>
                    Departure Airport
                    <select name="departure">
                        {this.state.departure.map(function mapper(data) {
                            return <option value={data}>{data}</option>
                        })}
                    </select>
                    Destination Airport
                    <select name="destination">
                        {this.state.destination.map(function mapper(data) {
                            return <option value={data}>{data}</option>
                        })}
                    </select>
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


                </div>
                <Ticket search={this.state.search} URL={this.state.URL} />
                <button>Submit</button>
              
            </form>
        );
    }
}