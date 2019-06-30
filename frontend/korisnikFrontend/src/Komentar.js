import React, { Component } from 'react'
import axios from 'axios'
import {withRouter} from 'react-router-dom';


class Komentar extends Component{

    state = {
        komentar:"",
        ocena:""
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleChange2 = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        var smestaj = this.props.match.params.smestajId;
        var rezervacija = this.props.match.params.rezervacijaId;
        var korisnik = this.props.match.params.korisnikId;
        if(this.state.komentar!=""){
            axios.post("http://localhost:8762/rating-service/rating-service/komentar/", {idSmestaja: smestaj, idRezervacije:rezervacija, idKorisnika:korisnik, komentar:this.state.komentar})
            .then(res=>{
                alert("Komentar uspesno poslat.");
                //this.props.history.push("/moje_rezervacije");
            })
        }else{
            alert("Unesite komentar pre objavljivanja.");
        }
        
    }

    handleSubmit2 = (e) => {
        e.preventDefault();
        var smestaj = this.props.match.params.smestajId;
        var rezervacija = this.props.match.params.rezervacijaId;
        var korisnik = this.props.match.params.korisnikId;
        if(this.state.ocena!=""){
            axios.post("http://localhost:8762/rating-service/rating-service/ocena/new", {idSmestaj: smestaj, idRezervacija:rezervacija, idKorisnik:korisnik, ocena:this.state.ocena})
            .then(res=>{
                alert("Ocena uspesno poslata.");
                //this.props.history.push("/moje_rezervacije");
            }).catch(error=>{
                alert("Vec ste ocenili ovu rezervaciju.");
            })
        }else{
            alert("Unesite ocenu pre objavljivanja.");
        }
        
    }

    render(){
        var user = this.props.user;
        var smestaj = this.props.match.params.smestajId;
        console.log(user + " " + smestaj);
        return(
            <div className = "center container">
                <h4 className="center">Komentar i ocena:</h4>
                <div className = "center container">
                    <form onSubmit={this.handleSubmit}>
                        <textarea id="komentar" className="materialize-textarea" onChange={this.handleChange}></textarea>
                        <button className="btn waves-effect waves-light green">Posalji</button>
                    </form>
                    <form onSubmit={this.handleSubmit2}>
                        <input type="number" id="ocena" onChange={this.handleChange2}/>
                        <button className="btn waves-effect waves-light green">Oceni</button>
                    </form>
                </div>
            </div>
        )

    }
}

export default withRouter(Komentar)