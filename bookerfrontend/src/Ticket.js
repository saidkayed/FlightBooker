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
        this.state = { names: [], currentIndex: 0, end:10,PSort:"",showMore: false}
    }

    handleTableChange = async (type, props) => {
      

        const names = this.props.p
        this.setState({names})

        const URI = `http://localhost:8080/BookerBackend/api/ticket/foundtickets?from=${this.state.currentIndex}&to=${this.state.end}` + "&dept=" + this.props.departure + "&dest=" + this.props.destination + this.state.PSort;
    
        const p = await fetch(URI).then(res => res.json())
        this.setState({ names: p })
        console.log(URI);


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
        console.log(this.state.names.length)
        if(this.state.names.length != 0){
        this.setState({showMore: true})
        }
        await this.forceUpdate(this.componentDidMount)
        
    }


    render() {
        
        
        return (
            
            <form onSubmit={this.onSubmit}>
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
                    {this.state.names.map((data) =>
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

                
            </div>
            {!this.state.showMore ? 
            (<button>Submit</button>) : 
            ( <button>SHOW MORE</button>)
            }
            </form>
        )
    }
}