import React, { Component } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import paginationFactory from 'react-bootstrap-table2-paginator';
import ShoppingCart from './ShoppingCart';
import Dropdown from 'react-dropdown'
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import 'react-dropdown/style.css'
import FrontPage from './FrontPage';



//HUSK at skrive "npm install react-dropdown" for dependency


const URL = "http://localhost:8080/BookerBackend/api/ticket/foundtickets/"

export default class Ticket extends Component {
    constructor(props) {
        super(props);
        this.state = { names: [], sizePerPage: 10, page: 1, totalSize: 0, PSort: "" }
    }

    

    handleTableChange = async (type, props) => {
        const { page, sizePerPage } = props;

        const currentIndex = (page - 1) * sizePerPage;
        const end = currentIndex + sizePerPage;


        const URI = `${URL}?from=${currentIndex}&to=${end}` + "&airline="+this.props.airline +"&dept="+this.props.depature + "&dest="+this.props.destination + "&depdate=" + this.props.deptdate  +"&arrdate=" + this.props.arrdate +  this.state.PSort;
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
        this.setState({ PSort: "&sort" })
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