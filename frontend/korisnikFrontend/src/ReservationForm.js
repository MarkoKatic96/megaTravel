import React, { Component } from 'react'
import axios from 'axios'
import {withRouter} from 'react-router-dom';


class Registration extends Component{

    state = {
        datumOd:"",
        datumDo:""
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        const idSmestaja = this.props.match.params.idSmestaja;
        const vlasnik = this.props.match.params.vlasnik;
        const email = this.props.usersEmail;
        const token = this.props.user;
        var parts =this.state.datumOd.split('-');
        var mydate = new Date(parts[0], parts[1] - 1, parts[2]); 
        console.log(mydate.toDateString());
        var parts2 =this.state.datumDo.split('-');
        var mydate2 = new Date(parts2[0], parts2[1] - 1, parts2[2]); 
        console.log(mydate2.toDateString());
        if(mydate<mydate2){
            axios.get("http://localhost:8762/korisnik-service/korisnik-service/korisnik/" + email)
            .then(res =>{
                axios.post("http://localhost:8762/reservation-service/reservation-service/rezervisi/" + token, {smestajId:idSmestaja, korisnikId:res.data.idKorisnik, vlasnikId:vlasnik, odDatuma:this.state.datumOd, doDatuma:this.state.datumDo})
                .then(res => {
                    alert("Vasa rezervacija je poslata!")
                    this.props.history.push("/");
                }).catch(error => {
                    alert("Uneti vremenski interval je zauzet!")
                })
            })
        }else{
            alert("Sva polja moraju biti popunjena i datum do mora biti veci od datuma od.")
        }
        
    }

    render(){
        return(
            <div className = "center container">
                <h4 className="center">Rezervacija smestaja:</h4>
                <div className = "center container">
                    <form onSubmit={this.handleSubmit}>
                        <label className="left black-text" htmlFor="datumOd">Datum od:</label>
                        <input type="date" id="datumOd" onChange={this.handleChange}/>
                        <label className="left black-text" htmlFor="datumDo">Datum do:</label>
                        <input type="date" id="datumDo" onChange={this.handleChange}/>
                        <button className="btn waves-effect waves-light green">Rezervisi</button>
                    </form>
                </div>
            </div>
        )

    }
}

export default withRouter(Registration)