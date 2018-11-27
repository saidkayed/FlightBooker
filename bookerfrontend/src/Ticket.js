import React, { Component } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import paginationFactory from 'react-bootstrap-table2-paginator';
import ShoppingCart from './ShoppingCart';
import filterFactory, { textFilter } from 'react-bootstrap-table2-filter';
import Dropdown from 'react-dropdown'
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import 'react-dropdown/style.css'


//HUSK at skrive "npm install react-dropdown" for dependency

const airline = ['THR', 'SAS', 'SaidAirlines']
const from = ['Istanbul', 'Copenhagen', 'SaidLand']
const dest = ['Istanbul', 'Copenhagen', 'SaidLand']

const URL = "http://localhost:8080/BookerBackend/api/ticket/alltickets/"

export default class Ticket extends Component {
    constructor(props) {
        super(props);
        startDate: new Date()
        this.state = { names: [], sizePerPage: 10, page: 1, totalSize: 0, PSort: "", booked: [], startDate: new Date(), endDate: new Date() }
        this.handleChange = this.handleChange.bind(this);
        this.handleChange1 = this.handleChange1.bind(this);
    }

    handleChange(date) {
        this.setState({
            startDate: date
        });
    }
    handleChange1(date) {
        this.setState({
            endDate: date
        });
    }

    handleTableChange = async (type, props) => {
        const { page, sizePerPage } = props;

        const currentIndex = (page - 1) * sizePerPage;
        const end = currentIndex + sizePerPage;


        const URI = `${URL}?from=${currentIndex}&to=${end}` + this.state.PSort;
        let p = await fetch(URI).then(res => {
            const totalSize = Number(res.headers.get("X-Total-Count"));
            if (totalSize) { this.setState({ totalSize }) }
            return res.json()
        });


        const names = await p;
        this.setState({ page, sizePerPage, names })


    }

    async componentDidMount() {
        const { page, sizePerPage } = this.state
        this.handleTableChange("didMount", { page, sizePerPage });
    }


    onSubmit = (ev) => {
        ev.preventDefault();
        this.setState({ PSort: "&Sort" })
        this.forceUpdate(this.componentDidMount);
    }


    render() {
        const { page, sizePerPage, totalSize } = this.state;
        const columns = [{
            dataField: 'airline',
            text: 'Airline',
        },
        {
            dataField: 'departure',
            text: 'From',
        },
        {
            dataField: 'desination',
            text: 'Destination',
        },
        {
            dataField: 'depTime',
            text: 'Departure',
        },
        {
            dataField: 'arrTime',
            text: 'Arrival',
        },
        {
            dataField: 'duration',
            text: 'Duration',
        },
        {
            dataField: 'price',
            text: 'Price',
        }, {
            events: {
                onClick: (e, column, columnIndex, row, rowIndex) => {

                    this.setState({ booked: row })

                },
            },
            formatter: () => {
                return (

                    <button>Book</button>

                );

            }
        }]

        return (

            <div>
                <ShoppingCart Book={this.state.booked} />
                <form onSubmit={this.onSubmit}>
                    <button>Price</button>
                </form>
                
                <Dropdown
                    options={airline}
                    onChange={this._onSelect}
                    placeholder="Select Airline"
                />
                <Dropdown
                    options={from}
                    onChange={this._onSelect}
                    placeholder="Select From"
                />
                <Dropdown
                    options={dest}
                    onChange={this._onSelect}
                    placeholder="Select Destination"
                />
                departure date
                <DatePicker 
                    selected={this.state.startDate}
                    onChange={this.handleChange}
                    placeholder="deptTime"
                />Arrival date
                <DatePicker
                    selected={this.state.endDate}
                    onChange={this.handleChange1}
                    placeholder="Arrival time"
                />                
                <h2>Tickets</h2>
                <BootstrapTable
                    striped
                    remote
                    bootstrap4
                    keyField='id'
                    data={this.state.names}
                    columns={columns}
                    onTableChange={this.handleTableChange}
                    pagination={paginationFactory({ page, sizePerPage, totalSize })}
                />

            </div>
        )
    }
}