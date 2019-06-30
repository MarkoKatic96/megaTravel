import React, { Component } from 'react'
import axios from 'axios'
import {withRouter} from 'react-router-dom';


class Poruka extends Component{

    state = {
        poruka:""
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        var vlasnik = this.props.match.params.idVlasnika;
        var user = this.props.user;
        if(this.state.poruka!=""){
        axios.post("http://localhost:8762/poruke-korisnik-service/poruke-korisnik-service/poruke/posalji/" + user, {primalac:vlasnik, sadrzaj:this.state.poruka})
            .then(res => {
                this.props.history.push("/moje_rezervacije");
                console.log(res.data);
            })
        }else{
            alert("Unesite poruku koju zelite da posaljete.");
        }
    }

    render(){
        var user = this.props.user;
        var vlasnik = this.props.match.params.idVlasnika;
        console.log(user + " " + vlasnik);
        return(
            <div className = "center container">
                <h4 className="center">Poruka:</h4>
                <div className = "center container">
                    <form onSubmit={this.handleSubmit}>
                        <textarea id="poruka" className="materialize-textarea" onChange={this.handleChange}></textarea>
                        <button className="btn waves-effect waves-light green">Posalji</button>
                    </form>
                </div>
            </div>
        )

    }
}

export default withRouter(Poruka)