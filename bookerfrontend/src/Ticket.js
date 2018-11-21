import React, { Component } from 'react';
import facade from './TicketFacade';
const columns = [{
    dataField: 'id',
    text: 'ID',
    sort: true
},
{
    dataField: 'gender',
    text: 'Gender',
    sort: true
},
{
    dataField: 'firstName',
    text: 'First Name',
    sort: true
},
{
    dataField: 'lastName',
    text: 'Last Name',
    sort: true
},
{
    dataField: 'email',
    text: 'Email',
    sort: true
}]

export default class Ticket extends Component {
    constructor(props) {
        super(props)

        this.state = { tickets: [] }
    }


    async componentDidMount() {
        const tickets = await facade.fetchTicket();
        console.log(tickets.price);
    }
    render() {
        return (
            <div>
                <table>
                    <thead>
                        <tr>
                     <th>hej</th>

                        </tr>


                    </thead>
                    <tbody>

                    </tbody>
                    </table>


            </div>
                )
            }
}