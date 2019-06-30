import React, { Component } from 'react';
import axios from 'axios'
import M from "materialize-css";

import './MainPanel.css'

class CategorizationPanel extends Component {
    state = {
        categories: [],
        newCategory: "",
        changedCategory: ""
    }

    componentDidMount() {
        M.AutoInit();

        // document.addEventListener('DOMContentLoaded', function () {
        //     var elems = document.querySelectorAll('.modal');
        //     var instances = M.Modal.init(elems, 1000);
        // });

        axios.get('http://localhost:8762/smestaj-service/smestaj-service/adminrest/getcategories').then(response => {
            console.log(response.data)
            if (response.data === null) {
                this.setState({
                    categories: []
                })
            }
            else {
                this.setState({
                    categories: response.data
                })
            }
        });
    }


    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    addNewCategory = (e) => {
        e.preventDefault();

        let naziv = this.state.newCategory;

        axios.post('http://localhost:8762/smestaj-service/smestaj-service/adminrest/addcategory/', { id: 5000, naziv: naziv, smestaji: null }).then(response => {
            this.componentDidMount();
        });
    }

    changeCategory = (event, id) => {
        event.preventDefault();

        let naziv = this.state.changedCategory;

        axios.put('http://localhost:8762/smestaj-service/smestaj-service/adminrest/updatecategory/' + id, { id: 5000, naziv: naziv, smestaji: null })
            .then(response => {
                this.componentDidMount();
            });

    }

    deleteCategory = (id) => {

        axios.delete('http://localhost:8762/smestaj-service/smestaj-service/adminrest/deletecategory/' + id)
            .then(res => {
                this.componentDidMount();
            })
    }

    render() {
        const { categories } = this.state;
        const categoryList = categories.length ? (categories.map(category => {
            return (
                <div className="row" key={category.id}>
                    <div className="col s12 m6">
                        <div className="card blue lighten-5 karticamin">
                            <div className="card-content white-text">
                                <span className="card-title black-text darken-4"><strong>{category.naziv}</strong></span>
                            </div>
                            <div className="card-action">

                                <button data-target="izmeni" className="btn modal-trigger yellow">Izmeni</button>

                                <div id="izmeni" className="modal">
                                    <div className="modal-content">


                                        <form onSubmit={(e) => { this.changeCategory( e, category.id) }}>
                                            <div className="input-field col s6">
                                                <input id="changedCategory" type="text" className="validate" onChange={this.handleChange} />
                                                
                                                <label htmlFor="changedCategory">Unesi novi naziv kategorije smeštaja</label>
                                            </div>
                                            <button type="submit" className="modal-close waves-effect waves-white green btn-flat" >Izmeni</button>
                                        </form>
                                    </div>
                                </div>
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <button className="btn waves-effect waves-light red left-align" onClick={() => { this.deleteCategory(category.id) }} >Obriši</button>
                            </div>
                        </div>

                    </div>



                </div>

            )
        })) : (<p>Nema kategorija smeštaja.</p>)

        return (
            <div className="center container">
                <h3 className="left-align container tekst">Spisak kategorija smeštaja:</h3>
                <br />
                <button data-target="dodaj" className="btn modal-trigger green">Dodaj novu kategoriju</button>

                <div id="dodaj" className="modal">
                    <div className="modal-content">
                        <form onSubmit={this.addNewCategory}>
                            <div className="input-field col s6">
                                <input id="newCategory" type="text" className="validate" onChange={this.handleChange} />
                                <label htmlFor="newCategory">Naziv kategorije smeštaja</label>
                            </div>
                            <button type="submit" className="modal-close waves-effect waves-white green btn-flat" >Dodaj</button>
                        </form>
                    </div>
                </div>
                <div>
                    {categoryList}
                </div>

            </div>
        )

    }
}

export default CategorizationPanel;
