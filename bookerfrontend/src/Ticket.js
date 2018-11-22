import React, { Component } from 'react';
import facade from './TicketFacade';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import paginationFactory from 'react-bootstrap-table2-paginator';
const URL = "http://localhost:8080/BookerBackend/api/ticket/alltickets/"
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
        sort: false
    }]
export default class Ticket extends Component {
constructor(props){
    super(props);
    this.state = { names: [], sizePerPage: 10, page:1, totalSize: 0,PSort:"",Price_Sort:false}
}
    
   
    
    
    handleTableChange = async (type, props) => {
        const { page, sizePerPage, sortField, sortOrder } = props;
        console.log(props)  //Monitor this output, when you test this step
        
        const currentIndex = (page - 1) * sizePerPage;
        const end = currentIndex + sizePerPage;
   
        
       const URI = `${URL}start=${currentIndex}&end=${end}` + this.state.PSort;
        let p = await fetch(URI).then(res => {const totalSize = Number(res.headers.get("X-Total-Count"));
         if (totalSize) { this.setState({ totalSize }) }
         return res.json()
        });
        
        
        
        const names = await p;
        this.setState({ page, sizePerPage, names })
    

    }

    async componentDidMount() {
        const {page,sizePerPage} = this.state
    this.handleTableChange("didMount",{page,sizePerPage});

      }


      componentDidUpdate() {
        console.timeEnd("rendering");
      }

      onSubmit = (ev) => {
        ev.preventDefault();
        if(this.state.Price_Sort == false ){
            this.setState({PSort:"?Sort"})   
        this.forceUpdate(this.componentDidMount);
        }
       }


    render() {
        const { page, sizePerPage, totalSize } = this.state;
        
        return (
            <div>
                <form onSubmit={this.onSubmit}>
                    <button>Price</button>
                </form>


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



            </div>)
    }
}