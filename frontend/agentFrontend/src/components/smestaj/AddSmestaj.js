import React, {Component} from 'react'
import axios from 'axios'
import {withRouter} from 'react-router-dom'

class AddSmestaj extends Component {

    state = {
        idSmestaja : '',
        grad : '',
        ulica : '',
        broj : '',
        latitude : '',
        longitude : '',
        tipSmestaja : '',
        kategorijaSmestaja : '',
        opis : '',
        maxOsoba : '',
        maxDanaZaOtkazivanje : '',
        cenaProlece : '',
        cenaLeto : '',
        cenaJesen : '',
        cenaZima : '',
        vlasnik : '',
        listaDodatnihUsluga: [],
        tipovi: [],
        kategorije: [],
        dodatne: []
    }
    componentDidMount() {
        axios.get('http://localhost:8762/smestaj-service/smestaj-service/adminrest/gettypes')
        .then(res => {
            console.log(res.data)
            if(res.data === null) {
                this.setState({
                    ...this.state,
                    tipovi : []
                })
            }else{
                this.setState({
                    ...this.state,
                    tipovi : res.date 
                })
            }
        })
        
        axios.get('http://localhost:8762/smestaj-service/smestaj-service/adminrest/getcategories')
        .then(res => {
            console.log(res.data)
            if(res.data === null) {
                this.setState({
                    ...this.state,
                    kategorije : []
                })
            }else{
                this.setState({
                    ...this.state,
                    kategorije : res.date 
                })
            }
        })
        
        axios.get('http://localhost:8762/smestaj-service/smestaj-service/adminrest/getservices')
        .then(res => {
            console.log(this.state)
            if(res.data === null) {
                this.setState({
                    ...this.state,
                    dodatne : []
                })
            }else{
                this.setState({
                    ...this.state,
                    dodatne : res.date 
                })
            }
        })
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id] : e.target.value
        })
        
    }

    handleSubmit = (e) => {
        e.preventDefault();
        console.log(this.state.listaDodatnihUsluga);
        var ret = {
            idSmestaja : null,
            adresa: {
                adresaId : null,
                grad : this.state.grad,
                ulica : this.state.ulica,
                broj : this.state.broj
            },
            latitude : this.state.latitude,
            longitude : this.state.longitude,
            tipSmestaja : {
                tipSmestajaId : this.state.tipSmestaja,
                nazivTipaSmestaja : null
            },
            kategorijaSmestaja : this.state.kategorijaSmestaja,
            opis : this.state.opis,
            maxOsoba : this.state.maxOsoba,
            maxDanaZaOtkazivanje : this.state.maxDanaZaOtkazivanje,
            cenaProlece : this.state.cenaProlece,
            cenaLeto : this.state.cenaLeto,
            cenaJesen : this.state.cenaJesen,
            cenaZima : this.state.cenaZima,
            vlasnik : null,
            listaDodatnihUsluga : []
        }
        console.log(ret);
        axios.post('http://localhost:8762/smestaj-service/smestaj-service/smestaj', ret)
        .then(res => {
            console.log(res.data)
        })

        this.props.history.push("/smestaji")
    }

    render() {
        const tipovi = this.state.tipovi
        const tipoviList = tipovi.length ? tipovi.map(tip => {
                return(
                    <option value={tip.tipSmestajaId}>{tip.nazivTipaSmestaja}</option>
                )
            }) : (<p></p>)
        

        const kategorije = this.state.kategorije
        const kategorijeList = kategorije.length ? kategorije.map(kategorija => {
                return(
                    <option value={kategorija.id}>{kategorija.naziv}</option>
                )
            }) : (<p></p>)
        

        const dodatne = this.state.dodatne
        const dodatneList =  dodatne.length ? dodatne.map(dodatna => {
                return(
                    <option value={dodatna.idDodatneUsluge}>{dodatna.nazivDodatneUsluge}</option>
                )
            }) : (<p></p>)
        
        return(
            <div className = "center container">
                <h4 className="center">Prijavi se:</h4>
                <div className = "center container">
                    <form onSubmit={this.handleSubmit}>
                        <label className="left black-text" htmlFor="grad">Grad:</label>
                        <input type="text" id="grad" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="ulica">Ulica:</label>
                        <input type="text" id="ulica" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="broj">Broj:</label>
                        <input type="number" id="broj" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="latitude">Latitude:</label>
                        <input type="number" id="latitude" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="longitude">Longitude:</label>
                        <input type="number" id="longitude" onChange={this.handleChange} />

                        
                        { this.state.tipovi.length ? <div className="container">
                            <div className="row">
                            <label className="left black-text" htmlFor="tipSmestaja">Tip smestaja:</label>
                                <div className="col-md-4"></div>
                                    <div className="col-md-4">
                                        <select options={ tipoviList } id="tipSmestaja" onChange={this.handleChange}/>
                                    </div>
                                    <div className="col-md-4"></div>
                                </div>
                            </div> 
                        : (<p></p>)}
                        
                        
                        <label className="left black-text" htmlFor="kategorijaSmestaja">Kategorija smestaja:</label>
                        { this.state.kategorije.length ? <div className="container">
                            <div className="row">
                            <label className="left black-text" htmlFor="kategorijaSmestaja">Kategorija smestaja:</label>
                                <div className="col-md-4"></div>
                                    <div className="col-md-4">
                                        <select id="kategorijaSmestaja" onChange={this.handleChange}> 
                                            {kategorijeList}
                                        </select>
                                    </div>
                                    <div className="col-md-4"></div>
                                </div>
                            </div> 
                        : <p></p>}

                        <label className="left black-text" htmlFor="listaDodatnihUsluga">Dodatne usluge:</label>
                        { this.state.dodatne.length ? <div className="container">
                            <div className="row">
                                <label className="left black-text" htmlFor="listaDodatnihUsluga">Dodatne usluge:</label>
                                <div className="col-md-4"></div>
                                    <div className="col-md-4">
                                        <select multiple={true} options={ dodatneList } id="listaDodatnihUsluga" onChange={this.handleChange}/>
                                    </div>
                                    <div className="col-md-4"></div>
                                </div>
                            </div> 
                        : <p></p>}

                        <label className="left black-text" htmlFor="opis">Opis:</label>
                        <input type="text" id="opis" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="maxOsoba">Maksimum broj osoba:</label>
                        <input type="number" id="maxOsoba" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="maxDanaZaOtkazivanje">Maksimum dana za otkazivanje:</label>
                        <input type="number" id="maxDanaZaOtkazivanje" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="cenaProlece">Cena prolece:</label>
                        <input type="number" id="cenaProlece" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="cenaLeto">Cena leto:</label>
                        <input type="number" id="cenaLeto" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="cenaJesen">Cena jesen:</label>
                        <input type="number" id="cenaJesen" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="cenaZima">Cena zima:</label>
                        <input type="number" id="cenaZima" onChange={this.handleChange} />

                        <label className="left black-text" htmlFor="vlasnik">Vlasnik:</label>
                        <input type="number" id="vlasnik" onChange={this.handleChange} />

                        <button className="btn waves-effect waves-light green">Prijava</button>
                    </form>
                </div>
            </div>
        )
    }
}

export default withRouter(AddSmestaj)