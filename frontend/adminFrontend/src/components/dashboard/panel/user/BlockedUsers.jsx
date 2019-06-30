import React, { Component } from 'react';
import axios from 'axios'

import './Aezakmi.css';

class BlockedUsers extends Component {
    state = {
        blocked: []
    }

    componentDidMount() {
        axios.get('http://localhost:8762/korisnik-service/korisnik-service/admin/allblocked').then(response => {
            if (response.data === null) {
                this.setState({
                    blocked: []
                })
            }
            else {
                this.setState({
                    blocked: response.data
                })
            }
        });
    }


    unblockUser = (id) => {
        axios.put('http://localhost:8762/korisnik-service/korisnik-service/admin/unblockuser/' + id)
            .then(response => {
                if (response.data)
                    this.componentDidMount();
                else
                    console.log(response.data);
            });
    }

    deleteUser = (id) => {
        axios.delete('http://localhost:8762/korisnik-service/korisnik-service/admin/removeuser/' + id)
            .then(response => {
                if (response.data)
                    this.componentDidMount();
                else
                    console.log(response.data);
            });
    }


    render() {
        const { blocked } = this.state;
        const userList = blocked.length ? (blocked.map(user => {
            return (
                <div className="row" key={user.idKorisnik}>
                    <div className="col s12 m6">
                        <div className="card blue lighten-5 kartica">
                            <div className="card-content white-text">
                                <span className="card-title black-text darken-4"><strong>{user.ime} {user.prezime}</strong></span>
                                <h5 className="black-text darken-4">{user.email}</h5>
                                <h6 className="black-text darken-4">{user.datumClanstva}</h6>
                            </div>
                            <div className="card-action">
                                <button className="btn waves-effect waves-light yellow left-align" onClick={() => { this.unblockUser(user.idKorisnik) }}>Deblokiraj</button>
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <button className="btn waves-effect waves-light red left-align" onClick={() => { if (window.confirm('Da li ste sigurni da zelite da obrišete korisnika?')) this.deleteUser(user.idKorisnik) }}>Ukloni</button>
                            </div>
                        </div>
                    </div>
                </div>

            )
        })) : (<img alt="" src="https://upload.wikimedia.org/wikipedia/en/a/aa/No_sign.png"></img>)

        return (
            <div className="center container">
                <h3 className="left-align container tekst">Spisak blokiranih korisnika:</h3>
                <br />
                {userList}
            </div>
        )

    }
}

export default BlockedUsers;