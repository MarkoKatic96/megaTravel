import React, { Component } from 'react'
import axios from 'axios'
import {withRouter} from 'react-router-dom';

class ChatRoom extends Component{

    state = {
        poruke:[],
        porukaZaSlanje:""
    }

    componentDidMount(){
        var agent = this.props.match.params.agentId;
        var token = this.props.user;
        axios.get("http://localhost:8762/poruke-korisnik-service/poruke-korisnik-service/poruke/" + agent +"/" + token)
        .then(res=>{
            console.log(res.data);
            this.setState({
                poruke: res.data
            })
        })
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        var vlasnik = this.props.match.params.agentId;
        var user = this.props.user;
        var polje = this.refs.porukaZaSlanje;
        if(this.state.porukaZaSlanje!=""){
        axios.post("http://localhost:8762/poruke-korisnik-service/poruke-korisnik-service/poruke/posalji/" + user, {primalac:vlasnik, sadrzaj:this.state.porukaZaSlanje})
            .then(res => {
                console.log(res.data);
                this.setState({
                    porukaZaSlanje:""
                })
                this.componentDidMount();
            })
        }else{
            alert("Unesite poruku koju zelite da posaljete.");
        }
    }

    render(){
        var { poruke } = this.state
        const porukeList = poruke.length ? (poruke.map(poruka =>{
            return(       
                <div className="card-content white-text left-align">
                    {poruka.tipPosiljaoca == "AGENT" ? (
                        <p>Agent: {poruka.sadrzaj}</p>
                    ) : (
                        <p>Ja: {poruka.sadrzaj}</p>
                    )

                    }
                    <div className="divider white"></div>
                </div>
            )
        })):(<p>Doslo je do problema prilikom ucitavanja poruka.</p>)

        return(
            <div className = "center container">
                <div className="row">
                    <div className = "col s12 m12">
                        <div className="card grey darken-2 card-panel hoverable">
                            <div className="card-content white-text left-align">
                                {porukeList}
                            </div>
                            <div className = "center container">
                                <form onSubmit={this.handleSubmit}>
                                    <textarea id="porukaZaSlanje" className="materialize-textarea" onChange={this.handleChange}></textarea>
                                    <button className="btn waves-effect waves-light green">Posalji</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        ) 
    }
}

export default withRouter(ChatRoom)