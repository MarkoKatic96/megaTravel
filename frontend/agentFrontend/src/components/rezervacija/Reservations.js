import React, {Component} from 'react'
import axios from 'axios'
import {withRouter} from 'react-router-dom'

class Reservations extends Component {
    state = {
        rezervacije: []
    }

    componentDidMount() {
        console.log(this.props.token)
        axios.get('http://localhost:8762/agent-local-service/agent-local-service/rezervacije/updatedb/' + sessionStorage.getItem("jwtToken")).then(response => {
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

    potvrdiDolazak = (id) => {
        axios.post('http://localhost:8762/agent-local-service/agent-local-service/rezervacije/potvrdi',
        {
            potvrdaRezervacijeId: null,
            rezervacijaId : id,
            statusRezervacije: 'POTVRDJENA',
            timestamp : new Date()
        }).then(response => {
            console.log(response.data)
        }); 
    }
    potvrdiOdstustvo = (id) => {
        axios.post('http://localhost:8762/agent-local-service/agent-local-service/rezervacije/potvrdi',
        {
            potvrdaRezervacijeId: null,
            rezervacijaId : id,
            statusRezervacije: 'NEIZVRSENA',
            timestamp : new Date()
        }).then(response => {
            console.log(response.data)
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
                                <div className="divider white"></div>
                                <br/>
                                {(rezervacija.statusRezervacije==="KREIRANA" || rezervacija.statusRezervacije==="U_TOKU")? (
                                    <div className="">
                                        <button className="btn waves-effect waves-light green left-align" onClick={()=>this.potvrdiDolazak(rezervacija.rezervacijaId)}>Potvrdi dolazak</button>
                                        <button className="btn waves-effect waves-light red right" onClick={()=>{this.potvrdiOdstustvo(rezervacija.rezervacijaId)}}>Potvrdi odsustvo</button>
                                    </div>
                                ) : 
                                (<p/>) 
                                }
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
        })) : (<p>Nemate jos nijednu rezervaciju.</p>)

        return(
            <div className="center container">
                <h3 className="left-align container">Moje rezervacije:</h3>
                <br/>
                {rezervacijeList}
            </div>
        )

    }
}

export default withRouter(Reservations)