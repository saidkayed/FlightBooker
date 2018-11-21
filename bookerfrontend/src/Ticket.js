import React, { Component } from 'react';
import facade from './TicketFacade';
import facade from "./apiFacade";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import paginationFactory from 'react-bootstrap-table2-paginator';
const columns = [
    {
        dataField: 'airline',
        text: 'Airline',
        sort: false
    },
    {
        dataField: 'departure',
        text: 'From',
        sort: true
    },
    {
        dataField: 'destination',
        text: 'Destination',
        sort: true
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
    }]
export default class pagination extends Component {
    state = { names: [], sizePerPage: 10, page: 1, totalSize: 0 }
    handleTableChange = async (type, props) => {
        const { page, sizePerPage, sortField, sortOrder } = props;
        console.log(props)  //Monitor this output, when you test this step
        const sortStr = (sortField && sortOrder) ? `&_sort=${sortField}&_order=${sortOrder}` : "";
        const currentIndex = (page - 1) * sizePerPage;
        const end = currentIndex + sizePerPage;
        const URI = `${URL}?_start=${currentIndex}&_end=${end}${sortStr}`;
        let p = await fetch(URI).then(res => {
            const totalSize = Number(res.headers.get("x-total-count"));
            if (totalSize) { this.setState({ totalSize }) }
            return res.json()
        });
        const names = await p;
        this.setState({ page, sizePerPage, names })
    }

    async componentDidMount() {
        const tickets = await facade.fetchTicket();
        console.log(tickets.price);
    }
    render() {
        const { page, sizePerPage, totalSize } = this.state;
        return (
            <div>
                <h2>AppRemote</h2>
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



            </div>)
    }
}