import React, { Component } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import paginationFactory from 'react-bootstrap-table2-paginator';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";


export default class Ticket extends Component {
    constructor(props) {
        super(props);
        this.state = { names: [], currentIndex: 0, end: 10, PSort: "", showMore: false,savednames:[] }
    }

    handleTableChange = async () => {


        const names = this.props.p
        this.setState({ names })

        const URI = `http://localhost:8080/BookerBackend/api/ticket/foundtickets?from=${this.state.currentIndex}&to=${this.state.end}` + "&dept=" + this.props.departure + "&dest=" + this.props.destination + this.state.PSort;
        console.log(URI)
        const p = await fetch(URI).then(res => res.json())
        this.setState({ names: p })
        this.state.names.map((data) => {
            this.state.savednames.push(data);
        })
        console.log(URI);


        console.log(this.props.date.toString().substring(4, 15))
        /*Fri Nov 30 2018 14:50:11 GMT+0100 (Central European Standard Time)*/
        /*Nov 30 2018*/

        console.log(this.state.names.map(function mapper(data) {
            return data.depTime
            /*"2019-03-10T07-05", "2019-02-10T07-05", "2019-03-10T15-05"*/
        }))
        /*
                    dates = data.map(function mapper(data){
                        return data.depTime
                })
                    dates.sort(function sorter(){
        
                    })
        */




    }

    async componentDidMount() {
        await this.handleTableChange()
        if (this.state.names.length != 0) {
            this.setState({ showMore: true })
        }
    }


    onSubmit = (ev) => {
        ev.preventDefault();
        this.forceUpdate(this.componentDidMount)

    }

    showMore = (ev) => {
        ev.preventDefault();
        this.setState({currentIndex: this.state.currentIndex+10})
        this.setState({end : this.state.end+10})
        this.forceUpdate(this.componentDidMount)
    }


    render() {


        return (


            <div>
                <table>
                    <tr>
                        <th>Airline</th>
                        <th>From</th>
                        <th>Destination</th>
                        <th>Departure</th>
                        <th>Arrival</th>
                        <th>Duration</th>
                        <th>Price</th>
                    </tr>
                    {this.state.savednames.map((data) =>
                        <tr>
                            <td>{data.airline}</td>
                            <td>{data.from}</td>
                            <td>{data.destination}</td>
                            <td>{data.departure}</td>
                            <td>{data.arrival}</td>
                            <td>{data.duration}</td>
                            <td>{data.price}</td>
                        </tr>
                    )}



                </table>

                {!this.state.showMore ?
                    (<form onSubmit={this.onSubmit}>
                        <button>Submit</button>
                    </form>) :
                   (<form onSubmit={this.showMore}>
                    <button>Show More</button>
                </form>)
                }
            </div>


        )
    }
}