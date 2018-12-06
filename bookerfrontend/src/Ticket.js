import React, { Component } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import paginationFactory from 'react-bootstrap-table2-paginator';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "./Buttons.css"
import "./Table.css"


export default class Ticket extends Component {
    constructor(props) {
        super(props);
        this.state = { names: [], currentIndex: 0, end: 10, PSort: "", showMore: false,savednames:[],
    dept: "", dest: ""}
    }

    handleTableChange = async () => {


        const names = this.props.p
        const submitDate = this.props.date
        console.log(submitDate)
        this.setState({ names })

        const URI = `http://localhost:8080/BookerBackend/api/ticket/foundtickets?from=${this.state.currentIndex}&to=${this.state.end}` + "&dept=" + this.state.dept + "&dest=" + this.state.dest + this.state.PSort;
        console.log(URI)
        const p = await fetch(URI).then(res => res.json())
        
        
        this.setState({ names: p })
        console.log(this.state.names, "NAMES")

        var sortedFilteredArr = this.dateSortFilter(this.props.date)
        console.log(sortedFilteredArr, "SORTED")
        
        this.state.names.map((sortedFilteredArr.map((data =>
            
            
            
        

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

    dateSortFilter(date){
        
        var date = date.toString().substring(4,21)
        var yeardate = date.substring(7,11)
        var yeardate2 = yeardate.concat("-")
        var yeardate3 = yeardate2.concat(date.substring(4,6))
        var yeardate4 = yeardate3.concat("-")
        switch(date.substring(0,3)){
            case "Jan":
                var yeardate5 = yeardate4.concat("01")
                break;
            case "Feb":
                var yeardate5 = yeardate4.concat("02")
                break;
            case "Mar":
                var yeardate5 = yeardate4.concat("03")
                break;
            case "Apr":
                var yeardate5 = yeardate4.concat("04")
                break;
            case "May":
                var yeardate5 = yeardate4.concat("05")
                break;
            case "Jun":
                var yeardate5 = yeardate4.concat("06")
                break;
            case "Jul":
                var yeardate5 = yeardate4.concat("07")
                break;
            case "Aug":
                var yeardate5 = yeardate4.concat("08")
                break;
            case "Sep":
                var yeardate5 = yeardate4.concat("09")
                break;
            case "Oct":
                var yeardate5 = yeardate4.concat("10")
                break;
            case "Nov":
                var yeardate5 = yeardate4.concat("11")
                break;
            case "Dec":
                var yeardate5 = yeardate4.concat("12")
                break;
        }
        var yeardate6 = yeardate5.concat("T")
        var yeardate7 = yeardate6.concat(date.substring(12,17))


        var ticketDates = this.state.names.map(function mapper(data) {
            return data.depTime
        })

        var sortedDates = ticketDates.sort()
        
        var filteredDates = sortedDates.filter(function filter(data, index){
            return data > yeardate7
        })
    
        return filteredDates;
    }

    

    onSubmit = (ev) => {
        ev.preventDefault();
        this.setState({savednames : [],currentIndex: 0,end:10})
        this.setState({dest : this.props.destination})
        this.setState({dept : this.props.departure})
        this.forceUpdate(this.componentDidMount)

    }

    showMore = (ev) => {
        ev.preventDefault();

        this.setState({currentIndex: this.state.currentIndex+10})
        this.setState({end : this.state.end+10})

        this.forceUpdate(this.componentDidMount)
    }


    render(){
        let showMoreButton;
        if(this.state.showMore){
            showMoreButton = <form onSubmit={this.showMore}>
                <button>Show More</button>
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
                        </tr>
                    )}
                    



                </table>

                
                    <form onSubmit={this.onSubmit}>
                        <button>Submit</button>
                    </form> 


                        {showMoreButton}
                    
                   {/* {this.state.showMore 
                   (<form onSubmit={this.showMore}>
                    <button>Show More</button>
                </form>)
                   }*/}
            </div>


        )
    }
}
