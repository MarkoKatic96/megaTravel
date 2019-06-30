import React, {Component} from 'react'
import {withRouter} from 'react-router-dom'
import Reservations from './Reservations';
import SelfReservations from './SelfReservations';

class ResDisplay extends Component {

    render(){
        return (
            <div className="center container">
                <Reservations userEmail = {this.props.userEmail} token = {this.props.token} />
                <SelfReservations userEmail = {this.props.userEmail} token = {this.props.token}/>
            </div>
        )
    }
}

export default withRouter(ResDisplay)