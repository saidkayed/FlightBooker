import React, { Component } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import paginationFactory from 'react-bootstrap-table2-paginator';
import ShoppingCart from './ShoppingCart';
import filterFactory, { textFilter } from 'react-bootstrap-table2-filter';
import Dropdown from 'react-dropdown'
import 'react-dropdown/style.css'
const URL = "http://localhost:8084/BookerBackend/api/ticket/alltickets/"

//HUSK at skrive "npm install react-dropdown" for dependency

const options = ['Select Airline','THR','HEJ','TEST']
const defaultOption = options[0]



export default class Ticket extends Component {
    constructor(props) {
        super(props);
        this.state = { names: [], sizePerPage: 10, page: 1, totalSize: 0, PSort: "", booked: []}
    }

    handleTableChange = async (type, props) => {
        const { page, sizePerPage} = props;

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
            sort: false
        },
        {
            dataField: 'departure',
            text: 'From',
            sort: false
        },
        {
            dataField: 'desination',
            text: 'Destination',
            sort: false
        },
        {
            dataField: 'depTime',
            text: 'Departure',
            sort: false
        },
        {
            dataField: 'arrTime',
            text: 'Arrival',
            sort: false
        },
        {
            dataField: 'duration',
            text: 'Duration',
            sort: false
        },
        {
            dataField: 'price',
            text: 'Price',
            sort: true
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
                options={options} 
                onChange={this._onSelect} 
                value={defaultOption} 
                placeholder="Select an option" />

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