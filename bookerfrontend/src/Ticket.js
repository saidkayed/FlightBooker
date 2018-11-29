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
        this.state = { names: [], sizePerPage: 10, page: 1, totalSize: 0, PSort: "", booked: [] }
    }

    handleChange = (date) => {
        this.setState({
            startDate: date
        });
    }
    handleChange1 = (date) => {
        this.setState({
            endDate: date
        });
    }


    handleTableChange = async (type, props) => {
        const { page, sizePerPage } = props;

        const currentIndex = (page - 1) * sizePerPage;
        const end = currentIndex + sizePerPage;

        const names = this.props.p
        this.setState({ page, sizePerPage, names })

    }

    async componentDidMount() {
        const { page, sizePerPage } = this.state
        this.handleTableChange("didMount", { page, sizePerPage });
    }


    onSubmit = async (ev) => {
        ev.preventDefault();
        
        console.log(this.props.onSubmit)
        const p = await fetch(this.props.onSubmit).then(res => res.json());
        
        this.setState({ names: p })
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
            dataField: 'destination',
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
                    console.log(row)

                },
            },
            formatter: () => {
                return (

                    <button>Book</button>

                );

            }
        }]

        return (
            <form onSubmit={this.onSubmit}>
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
            <button>Submit</button>
            </form>
        )
    }
}