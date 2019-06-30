import React, {Component} from 'react'
import {withRouter} from 'react-router-dom'
import axios from 'axios';

class CreateSamoRez extends Component {
    state = {
        dateOd: '',
        dateDo: ''
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }
    handleSubmit = (e) => {
        e.preventDefault();
        var a = this.state.dateOd;
        var b = this.state.dateDo;
        console.log(this.props.match.params.smestaj_id)
        if(a !== '' && b !== '' && a < b) {
            axios.post('http://localhost:8762/reservation-service/reservation-service/agent/' + this.props.userEmail, {
                samostalnaRezervacijaId : null,
                smestajId : this.props.match.params.smestaj_id,
                odDatuma : this.state.dateOd,
                doDatuma : this.state.dateDo,
                timestamp : new Date()
            }).then(res => {
                console.log(res.data)
                this.props.history.push('/rezervacije')
            }).catch(res => {
                alert("Došlo je do konflikta prilikom kreiranja rezervacije")
            })
                
        }else {
            alert("Unesite oba datuma i prvi mora biti manji od drugog")
        }
        
    }
    render() {
        return(
            <div className = "center container">
                <h4 className="center">Samostalno rezervisi:</h4>
                <div className = "center container">
                    <form onSubmit={this.handleSubmit}>
                        <label className="left black-text" htmlFor="dateOd">Od:</label>
                        <input type="date" id="dateOd" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="dateDo">Do:</label>
                        <input type="date" id="dateDo" onChange={this.handleChange} />
                        <button className="btn waves-effect waves-light green">Rezervisi</button>
                    </form>
                </div>
            </div>
        )
    }
}

export default withRouter(CreateSamoRez)