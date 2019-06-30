import React, { Component } from 'react';
import axios from 'axios'
import M from "materialize-css";

import './MainPanel.css'

class ServicePanel extends Component {
    state = {
        services: [],
        newService: "",
        changedService: "",
        cardCounter: 0,
        side: ""
    }

    componentDidMount() {
        M.AutoInit();

        // document.addEventListener('DOMContentLoaded', function () {
        //     var elems = document.querySelectorAll('.modal');
        //     var instances = M.Modal.init(elems, 1000);
        // });

        axios.get('http://localhost:8762/smestaj-service/smestaj-service/adminrest/getservices').then(response => {
            console.log(response.data)
            if (response.data === null) {
                this.setState({
                    services: []
                })
            }
            else {
                this.setState({
                    services: response.data
                })
            }
        });
    }


    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    addNewService = (e) => {
        e.preventDefault();

        let naziv = this.state.newService;

        axios.post('http://localhost:8762/smestaj-service/smestaj-service/adminrest/addservice/', { idDodatneUsluge: 5000, nazivDodatneUsluge: naziv, listaSmestaja: null }).then(response => {
            this.componentDidMount();
        });
    }

    changeService = (event, id) => {
        event.preventDefault();
        console.log(id);

        let naziv = this.state.changedService;
        console.log(naziv);

        axios.put('http://localhost:8762/smestaj-service/smestaj-service/adminrest/updateservice/' + id, { idDodatneUsluge: 2000, nazivDodatneUsluge: naziv, listaSmestaja: null })
            .then(response => {
                this.componentDidMount();
            });

    }

    deleteService = (id) => {

        axios.delete('http://localhost:8762/smestaj-service/smestaj-service/adminrest/deleteservice/' + id)
            .then(res => {
                this.componentDidMount();
            })
    }

    render() {
        const { services } = this.state;
        const serviceList = services.length ? (services.map(service => {
            return (
                <div className="row" key={service.idDodatneUsluge}>
                    <div className="col s12 m6">
                        {/* {this.setState({
                            cardCounter: this.state.cardCounter + 1
                        })}
                        {(this.state.cardCounter % 3 == 0) ? this.setState({side: "side"}) : ""} */}
                        <div className="card blue lighten-5 karticamin">
                            <div className="card-content white-text">
                                <span className="card-title black-text darken-4"><strong>{service.nazivDodatneUsluge}</strong></span>
                            </div>
                            <div className="card-action">

                                <button data-target="izmeni" className="btn modal-trigger yellow">Izmeni</button>

                                <div id="izmeni" className="modal">
                                    <div className="modal-content">


                                        <form onSubmit={(e) => { this.changeService( e, service.idDodatneUsluge) }}>
                                            <div className="input-field col s6">
                                                <input id="changedService" type="text" className="validate" onChange={this.handleChange} />
                                                
                                                <label htmlFor="changedService">Unesi novi naziv dodatne usluge</label>
                                            </div>
                                            <button type="submit" className="modal-close waves-effect waves-white green btn-flat" >Izmeni</button>
                                        </form>
                                    </div>
                                </div>
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <button className="btn waves-effect waves-light red left-align" onClick={() => { this.deleteService(service.idDodatneUsluge) }} >Obriši</button>
                            </div>
                        </div>

                    </div>



                </div>

            )
        })) : (<p>Nema dodatnih usluga.</p>)

        return (
            <div className="center container">
                <h3 className="left-align container tekst">Spisak dodatnih usluga:</h3>
                <br />
                <button data-target="dodaj" className="btn modal-trigger green">Dodaj novu dodatnu uslugu</button>

                <div id="dodaj" className="modal">
                    <div className="modal-content">
                        <form onSubmit={this.addNewService}>
                            <div className="input-field col s6">
                                <input id="newService" type="text" className="validate" onChange={this.handleChange} />
                                <label htmlFor="newService">Naziv dodatne usluge</label>
                            </div>
                            <button type="submit" className="modal-close waves-effect waves-white green btn-flat" >Dodaj</button>
                        </form>
                    </div>
                </div>
                <div>
                    {serviceList}
                </div>

            </div>
        )

    }
}

export default ServicePanel;