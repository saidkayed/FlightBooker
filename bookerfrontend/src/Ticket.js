import React, { Component } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import paginationFactory from 'react-bootstrap-table2-paginator';
import "react-datepicker/dist/react-datepicker.css";


export default class Ticket extends Component {
    constructor(props) {
        super(props);
        this.state = { names: [], sizePerPage: 10, page: 1, totalSize: 0, PSort: "", booked: []}
    }

    handleTableChange = async (type, props) => {
        const { page, sizePerPage } = props;

        const currentIndex = (page - 1) * sizePerPage;
        const end = currentIndex + sizePerPage;

        const names = this.props.p
        this.setState({ page, sizePerPage, names })

        const URL = `http://localhost:8080/BookerBackend/api/ticket/foundtickets?` + "dept=" + this.props.departure + "&dest=" + this.props.destination + this.state.PSort;
        
        const p = await fetch(URL).then(res =>res.json())
        this.setState({ totalSize: p.length })
         
        const URI = `http://localhost:8080/BookerBackend/api/ticket/foundtickets?from=${currentIndex}&to=${end}` + "&dept=" + this.props.departure + "&dest=" + this.props.destination + this.state.PSort;
        const t = await fetch(URI).then(res =>res.json())
        this.setState({names : t})


        console.log(this.props.date.toString().substring(4, 15))
        /*Fri Nov 30 2018 14:50:11 GMT+0100 (Central European Standard Time)*/
        /*Nov 30 2018*/

        console.log(this.state.names.map(function mapper(data){
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
        const { page, sizePerPage } = this.state
        this.handleTableChange("didMount", { page, sizePerPage });
    }


    onSubmit = async (ev) => {
        ev.preventDefault();
        this.forceUpdate(this.componentDidMount)

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