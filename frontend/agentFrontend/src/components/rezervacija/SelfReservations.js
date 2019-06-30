import React, {Component} from 'react'
import axios from 'axios'
import {withRouter} from 'react-router-dom'

class SelfReservations extends Component {
    state = {
        rezervacije: []
    }

    componentDidMount() {
        axios.get('http://localhost:8762/reservation-service/reservation-service/rezervacije/updatedb/' + this.props.token).then(response => {
            console.log(response.data)
            if (response.data === null) {
                this.setState({
                    rezervacije: []
                })
            }
            else {
                this.setState({
                    rezervacije: response.data
                })
            }
        });
    }

    render(){
        const { rezervacije } = this.state;
        const rezervacijeList = rezervacije.length ? (rezervacije.map(rezervacija =>{
        return(
            <div className = "center container" key = {rezervacija.rezervacijaId}>
                <div className="row">
                    <div className = "col s12 m12">
                        <div className="card grey darken-2 card-panel hoverable">
                            <div className="card-content white-text left-align">
                                <span className="card-title"><b>{rezervacija.smestajId}</b></span>
                                <div className="divider white"></div>
                                <br/>
                                <p>Datum od: {rezervacija.odDatuma.split("T")[0]}</p>
                                <p>Datum do: {rezervacija.doDatuma.split("T")[0]}</p>
                                <br/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
        })) : (<p>Nemate jos nijednu rezervaciju.</p>)

        return(
            <div className="center container">
                <h3 className="left-align container">Moje samostalne rezervacije:</h3>
                <br/>
                {rezervacijeList}
            </div>
        )

    }
}

export default withRouter(SelfReservations)