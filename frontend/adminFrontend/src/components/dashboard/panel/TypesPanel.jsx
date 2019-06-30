import React, { Component } from 'react';
import axios from 'axios'
import M from "materialize-css";

import './MainPanel.css'

class TypesPanel extends Component {
    state = {
        types: [],
        newType: "",
        changedType: ""
    }

    componentDidMount() {
        M.AutoInit();

        // document.addEventListener('DOMContentLoaded', function () {
        //     var elems = document.querySelectorAll('.modal');
        //     var instances = M.Modal.init(elems, 1000);
        // });

        axios.get('http://localhost:8762/smestaj-service/smestaj-service/adminrest/gettypes').then(response => {
            console.log(response.data)
            if (response.data === null) {
                this.setState({
                    types: []
                })
            }
            else {
                this.setState({
                    types: response.data
                })
            }
        });
    }


    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    addNewType = (e) => {
        e.preventDefault();

        let naziv = this.state.newType;

        axios.post('http://localhost:8762/smestaj-service/smestaj-service/adminrest/addtype/', { tipSmestajaId: 5000, nazivTipaSmestaja: naziv, smestaji: null }).then(response => {
            this.componentDidMount();
            
        });
        this.setState({ 
            newType: ""
        })
    }

    changeType = (event, id) => {
        event.preventDefault();
        console.log(id);

        let naziv = this.state.changedType;
        console.log(naziv);

        axios.put('http://localhost:8762/smestaj-service/smestaj-service/adminrest/updatetype/' + id, { tipSmestajaId: id, nazivTipaSmestaja: naziv, smestaji: null })
            .then(response => {
                this.componentDidMount();
            });

    }

    deleteType = (id) => {

        axios.delete('http://localhost:8762/smestaj-service/smestaj-service/adminrest/deletetype/' + id)
            .then(res => {
                this.componentDidMount();
            })
    }

    render() {
        const { types } = this.state;
        const typeList = types.length ? (types.map(type => {
            return (
                <div className="row" key={type.tipSmestajaId}>
                    <div className="col s12 m6">
                        <div className="card blue lighten-5 karticamin">
                            <div className="card-content white-text">
                                <span className="card-title black-text darken-4"><strong>{type.nazivTipaSmestaja}</strong></span>
                            </div>
                            <div className="card-action">

                                <button data-target="izmeni" className="btn modal-trigger yellow">Izmeni</button>

                                <div id="izmeni" className="modal">
                                    <div className="modal-content">


                                        <form onSubmit={(e) => { this.changeType( e, type.tipSmestajaId) }}>
                                            <div className="input-field col s6">
                                                <input id="changedType" type="text" className="validate" onChange={this.handleChange} />
                                                
                                                <label htmlFor="changedType">Unesi novi naziv tipa smeštaja</label>
                                            </div>
                                            <button type="submit" className="modal-close waves-effect waves-white green btn-flat" >Izmeni</button>
                                        </form>
                                    </div>
                                </div>
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <button className="btn waves-effect waves-light red left-align" onClick={() => { this.deleteType(type.tipSmestajaId) }} >Obriši</button>
                            </div>
                        </div>

                    </div>



                </div>

            )
        })) : (<p>Nema tipova smeštaja.</p>)

        return (
            <div className="center container">
                <h3 className="left-align container tekst">Spisak tipova smeštaja:</h3>
                <br />
                <button data-target="dodaj" className="btn modal-trigger green">Dodaj novi tip</button>

                <div id="dodaj" className="modal">
                    <div className="modal-content">
                        <form onSubmit={this.addNewType}>
                            <div className="input-field col s6">
                                <input id="newType" type="text" className="validate" onChange={this.handleChange} />
                                <label htmlFor="newType">Naziv tipa smeštaja</label>
                            </div>
                            <button type="submit" className="modal-close waves-effect waves-white green btn-flat" >Dodaj</button>
                        </form>
                    </div>
                </div>
                <div>
                    {typeList}
                </div>

            </div>
        )

    }
}

export default TypesPanel; 