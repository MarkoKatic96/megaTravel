import React, {Component} from 'react'
import axios from 'axios';
import {Link, withRouter} from 'react-router-dom';

class Smestaji extends Component {
    state = {
        smestaji: []
    }

    componentDidMount() {
        axios.get('http://localhost:8762/smestaj-service/smestaj-service/smestaj/all').then(response => {
            console.log(response.data)
            if (response.data === null) {
                this.setState({
                    smestaji: []
                })
            }
            else {
                this.setState({
                    smestaji: response.data
                })
            }
        });
    }

    render() {
        const { smestaji } = this.state;
        const smestajiList = smestaji.length ? (smestaji.map(smestaj =>{
        return(
            <div className = "center container" key = {smestaj.idSmestaja}>
                <div className="row">
                    <div className = "col s12 m12">
                        <div className="card grey darken-2 card-panel hoverable">
                            <div className="card-content white-text left-align">
                                <span className="card-title"><b>{smestaj.idSmestaja}</b></span>
                                <div className="divider white"></div>
                                <br/>
                                <p>Adresa: {smestaj.adresa.ulica} {smestaj.adresa.broj}, {smestaj.adresa.grad}</p>
                                <p>Tip smestaja: {smestaj.tipSmestaja.nazivTipaSmestaja}</p>
                                <br/>
                                <div className="divider white"></div>
                                <br/>
                                <p>Cena prolece: {smestaj.cenaProlece}</p>
                                <p>Cena leto: {smestaj.cenaLeto}</p>
                                <p>Cena jesen: {smestaj.cenaJesen}</p>
                                <p>Cena zima: {smestaj.cenaZima}</p>
                                <br/>
                                <div className="divider white"></div>
                                <br/>
                                <p>Opis: {smestaj.opis}</p>
                                <br/>
                                <div className="divider white"></div>
                                <br/>
                                <div className="">
                                    <Link to= {"/samostalna/" + smestaj.idSmestaja}>
                                        <button className="btn waves-effect waves-light green left-align" >Rezervisi smestaj</button>
                                    </Link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
        })) : (<p>Nemate smestaja.</p>)
        return(
            <div className="center container">
                <h3 className="left-align container">Moji smestaji:</h3>
                <br/>
                {smestajiList}
            </div>
        )
    }
}

export default withRouter(Smestaji)