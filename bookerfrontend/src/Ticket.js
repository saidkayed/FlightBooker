import React, { Component } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import "react-datepicker/dist/react-datepicker.css";
import "./Buttons.css"
import "./Table.css"
import facade from './userFacade';
import { Button } from 'react-bootstrap';


export default class Ticket extends Component {
    constructor(props) {
        super(props);
        this.state = {
            names: [], currentIndex: 0, end: 10, PSort: "", showMore: false, savednames: [],
            dept: "", dest: ""
        }
    }

    handleTableChange = async () => {
        const names = this.props.p
        this.setState({ names })

        const URI = `http://localhost:8080/BookerBackend/api/ticket/foundtickets?from=${this.state.currentIndex}&to=${this.state.end}` + "&dept=" + this.state.dept + "&dest=" + this.state.dest + this.state.PSort;
        const p = await fetch(URI).then(res => res.json())
        this.setState({ names: p })

        var sortedFilteredArr = this.dateSortFilter(this.props.date)
        this.setState({ names: sortedFilteredArr })

        this.state.names.map((data) => {
            this.state.savednames.push(data);
        })
    }

    async componentDidMount() {
        await this.handleTableChange()
        if (this.state.names.length != 0) {
            this.setState({ showMore: true })
        }
    }

    dateSortFilter(date) {

        var date = date.toString().substring(4, 21)
        var yeardate = date.substring(7, 11)
        var yeardate1 = yeardate.concat("-")
        switch (date.substring(0, 3)) {
            case "Jan":
                var yeardate2 = yeardate1.concat("01")
                break;
            case "Feb":
                var yeardate2 = yeardate1.concat("02")
                break;
            case "Mar":
                var yeardate2 = yeardate1.concat("03")
                break;
            case "Apr":
                var yeardate2 = yeardate1.concat("04")
                break;
            case "May":
                var yeardate2 = yeardate1.concat("05")
                break;
            case "Jun":
                var yeardate2 = yeardate1.concat("06")
                break;
            case "Jul":
                var yeardate2 = yeardate1.concat("07")
                break;
            case "Aug":
                var yeardate2 = yeardate1.concat("08")
                break;
            case "Sep":
                var yeardate2 = yeardate1.concat("09")
                break;
            case "Oct":
                var yeardate2 = yeardate1.concat("10")
                break;
            case "Nov":
                var yeardate2 = yeardate1.concat("11")
                break;
            case "Dec":
                var yeardate2 = yeardate1.concat("12")
                break;
        }

        var yeardate3 = yeardate2.concat("-")
        var yeardate4 = yeardate3.concat(date.substring(4, 6))
        var yeardate5 = yeardate4.concat("T")
        var yeardate6 = yeardate5.concat(date.substring(12, 17))


        var sortedArr = this.state.names.sort((a, b) =>
            (a.depTime > b.depTime) ? 1 : ((b.depTime > a.depTime) ? -1 : 0)
        )

        var filteredSortedArr = sortedArr.filter((data) => {
            return data.depTime > yeardate6
        }
        )
        return filteredSortedArr;
    }

    onSubmit = (ev) => {
        ev.preventDefault();
        this.setState({ savednames: [], currentIndex: 0, end: 10 })
        this.setState({ dest: this.props.destination })
        this.setState({ dept: this.props.departure })
        facade.submitData(this.props.name, this.props.departure, this.props.destination, this.props.date)

        this.forceUpdate(this.componentDidMount)
    }
    showTicket = (ev) => {
        ev.preventDefault();
        this.setState({ dest: this.props.destination })
        this.setState({ dept: this.props.departure })
        facade.submitData(this.props.name, this.props.departure, this.props.destination, this.props.date)
       

        this.forceUpdate(this.componentDidMount)
    }

    showMore = (ev) => {
        ev.preventDefault();
        this.setState({ currentIndex: this.state.currentIndex + 10 })
        this.setState({ end: this.state.end + 10 })
        this.forceUpdate(this.componentDidMount)
    }

    render() {
        let showMoreButton;
        if (this.state.showMore) {
            showMoreButton = <form onSubmit={this.showMore}>
                <button id="button">Show More</button>
            </form>
        }

        return (
            <div>
                <table id="table">
                    <tr>
                        <th>Airline</th>
                        <th>Departure</th>
                        <th>Destination</th>
                        <th>Departure Time</th>
                        <th>Arrival Time</th>
                        <th>Duration</th>
                        <th>Price</th>
                    </tr>
                    {this.state.savednames.map((data) =>

                        <tr>
                            <td>{data.airline}</td>
                            <td>{data.departure}</td>
                            <td>{data.destination}</td>
                            <td>{data.depTime}</td>
                            <td>{data.arrTime}</td>
                            <td>{data.duration}</td>
                            <td>{data.price}</td>
                            <td>
                            <form onSubmit={this.showTicket}>
                                <button id="button"> Se billet </button>
                            </form>
                            </td>
                        </tr>
                    )}
                </table>

                <form onSubmit={this.onSubmit}>
                    <button id="button"> Submit </button>
                </form>
                <center>
                    {showMoreButton}
                </center>
            </div>
        )
    }
}
