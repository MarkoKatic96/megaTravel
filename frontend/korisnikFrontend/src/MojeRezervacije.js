import React, { Component } from 'react'
import axios from 'axios'
import {withRouter} from 'react-router-dom';

class MojeRezervacije extends Component{

    state = {
        rezervacije:[],
        smestaji:[]
    }

    componentDidMount() {
        const token = this.props.user;
        axios.get("http://localhost:8762/reservation-service/reservation-service/rezervacije/" + token)
        .then(res=>{
            console.log(res.data);
            this.setState({
                rezervacije: res.data
            })
        })

        axios.get("http://localhost:8762/smestaj-service/smestaj-service/smestaj-korisnik/bll")
         .then(res=>{
             this.setState({
                smestaji: res.data
             })
        })
    }

    otkazi = (id) =>{
        const token = this.props.user;
        axios.delete("http://localhost:8762/reservation-service/reservation-service/otkazi/" + id + "/" + token)
            .then(res=>{
                console.log(res.data)
                alert("Uspesno otkazana rezervacija.");
                this.componentDidMount();
            }).catch(error=>{
                alert("Otkazivanje nije uspelo. Rok za otkazivanje je prosao.");
            })
    }

    kontaktiraj = (vlasnikId) =>{
        this.props.history.push("/poruka/" + vlasnikId);
    }

    komentarisi = (smestajId, rezervacijaId, korisnikId) =>{
        this.props.history.push("/komentar/" + smestajId + "/" + rezervacijaId + "/" + korisnikId);
    }

    render(){
        const { rezervacije } = this.state;
        const { smestaji } = this.state;
        const rezervacijeList = rezervacije.length ? (rezervacije.map(rezervacija =>{
        return(
            <div className = "center container" key = {rezervacija.rezervacijaId}>
                <div className="row">
                    <div className = "col s12 m12">
                        <div className="card grey darken-2 card-panel hoverable">
                            <div className="card-content white-text left-align">
                                {smestaji.map(smestaj => {
                                    if(smestaj.idSmestaja==rezervacija.smestajId){
                                        return(<span className="card-title"><b>{smestaj.adresa.grad} {smestaj.adresa.ulica} {smestaj.adresa.broj}</b></span>)
                                    }
                                })
                                    
                                }
                                <div className="divider white"></div>
                                <br/>
                                <p>Datum od: {rezervacija.odDatuma.split("T")[0]}</p>
                                <p>Datum do: {rezervacija.doDatuma.split("T")[0]}</p>
                                <br/>
                                <div className="divider white"></div>
                                <br/>
                                {rezervacija.statusRezervacije=="KREIRANA" ? (
                                    <div className="">
                                        <button className="btn waves-effect waves-light green left-align" onClick={()=>this.kontaktiraj(rezervacija.vlasnikId)}>Kontaktiraj vlasnika</button>
                                        <button className="btn waves-effect waves-light red right" onClick={()=>{this.otkazi(rezervacija.rezervacijaId)}}>Otkazi</button>
                                    </div>
                                ) : (rezervacija.statusRezervacije=="POTVRDJENA" ? (
                                    <div className="">
                                        <button className="btn waves-effect waves-light green left-align" onClick={()=>this.komentarisi(rezervacija.smestajId, rezervacija.rezervacijaId, rezervacija.korisnikId)}>Komentarisi i oceni</button>
                                    </div>
                                ):
                                (<p/>)) 
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
export default withRouter(MojeRezervacije)