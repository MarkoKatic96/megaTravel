import React, { Component } from 'react'
import axios from 'axios'
import 'materialize-css/dist/css/materialize.min.css'
import Select from 'react-select';
import { withRouter } from 'react-router-dom';

class Home extends Component{

    state = {
        korisnici:[],
        napredna: false,
        mesto:"",
        udaljenostP:"",
        danaZaOtkazivanje:"",
        brojOsoba:"",
        datumOd:"",
        datumDo:"",
        smestaji:[],
        kategorije:[],
        tipovi:[],
        dodatne:[],
        sort:[
            { value: 'cena rastuca', label: 'Rastucoj ceni' },
            { value: 'cena opadajuca', label: 'Opadajucoj ceni' },
            { value: 'udaljenost', label: 'Udaljenosti' },
            { value: 'ocena', label: 'Ocenama' },
            { value: 'kategorija rastuca', label: 'Rastucoj kategoriji' },
            { value: 'kategorija opadajuca', label: 'Opadajucoj kategoriji' }
        ],
        selectedSort:"",
        selectedUsluge:[],
        selectedTip: "",
        selectedKategorija: "",
        user:"",
        rastojanja:[],
        prosecneOcene:[],
        komentariL: false,
        komentari:[]
    }

    componentDidMount() {
        this.setState({
            user: this.props.user
        })

         axios.get("http://localhost:8762/smestaj-service/smestaj-service/smestaj-korisnik/bll")
         .then(res=>{
             this.setState({
                smestaji: res.data
             })
             var lista = [];
             var { smestaji } = this.state;
             smestaji.map(smestaj => {
                axios.get("http://localhost:8762/smestaj-service/smestaj-service/smestaj-korisnik/rastojanje/" + smestaj.idSmestaja)
                .then(res=>{
                    var r = new Object();
                    r.smestajId = smestaj.idSmestaja;
                    r.rastojanje = res.data;
                    lista.push(r);
                    this.setState({
                        rastojanja: lista
                    })
                })
            })
            var lista2 = [];
             smestaji.map(smestaj => {
                axios.get("http://localhost:8762/rating-service/rating-service/ocena/" + smestaj.idSmestaja)
                .then(res=>{
                    var o = new Object();
                    o.smestajId = smestaj.idSmestaja;
                    o.ocena = res.data;
                    lista2.push(o);
                    this.setState({
                        prosecneOcene: lista2
                    })
                })
            })
         })

         axios.get("http://localhost:8762/smestaj-service/smestaj-service/smestaj-korisnik/allTipovi")
         .then(res=>{
             console.log(res.data);
              this.setState({
                 tipovi: res.data
              })
        })

        axios.get("http://localhost:8762/smestaj-service/smestaj-service/smestaj-korisnik/allKategorije")
         .then(res=>{
              this.setState({
                kategorije: res.data
              })
        })

        axios.get("http://localhost:8762/smestaj-service/smestaj-service/smestaj-korisnik/allUsluge")
         .then(res=>{
              this.setState({
                dodatne: res.data
              })
        })

        axios.get("http://localhost:8762/rating-service/rating-service/komentar/all")
         .then(res=>{
              this.setState({
                komentari: res.data
              })
        })

        axios.get("http://localhost:8762/korisnik-service/korisnik-service/korisnici")
            .then(res=>{
                console.log(res.data)
                this.setState({
                    korisnici: res.data
                })
        })
    }

    componentWillReceiveProps(userProps){
        this.setState({
            user: userProps.data
        })
    }

    napredna = (e) =>{
        e.preventDefault();
        if(this.state.napredna){
            this.setState({
                napredna: false,
                selectedUsluge:[],
                selectedTip: "",
                selectedKategorija: ""
            })
        }else{
            this.setState({
                napredna: true
            })
        }
    }

    komentari = (smestajId)=>{
        if(this.state.komentariL){
            this.setState({ //zatvori
                komentariL: false
            })
        }else{
            this.setState({ //otvori
                komentariL: true
            })
        }
    }

    handleChange = (e) => { //za inpute
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleCBChange = (e) =>{
        var lista = this.state.selectedUsluge;
        var dodat = 0;
        var i;
        for(i=0; i<lista.length; i++){
            if(lista[i]===e.target.value){
                lista.splice(i, 1);
                dodat=1;
            }
        }
        if(dodat==0){
            lista.push(e.target.value);
        } 
    }

    handleChangeSort = selectedSort => { //za tip selcet
        this.setState({ selectedSort });
        axios({
            url: "http://localhost:8762/pretraga-service/pretraga-service/sort/" + selectedSort.value,
            method: 'post',
            data: this.state.smestaji
        }).then(res=>{
              this.setState({
                smestaji:res.data
              })
        })
    };

    handleChangeTip = selectedTip => { //za tip selcet
        console.log(selectedTip);
        this.setState({ selectedTip });
    };

    handleChangeKategorija = selectedKategorija => { //za kategorija select
        this.setState({ selectedKategorija });
    }; 
     
    handleSubmit = (e) => {
        e.preventDefault();
            axios.post("http://localhost:8762/pretraga-service/pretraga-service/pretraga", {mesto: this.state.mesto, brojOsoba: this.state.brojOsoba, datumDolaska: this.state.datumOd, datumPolaska: this.state.datumDo, tipSmestaja:this.state.selectedTip.value, kategorijaSmestaja: this.state.selectedKategorija.value, dodatneUsluge: this.state.selectedUsluge, udaljenost: this.state.udaljenostP, danaZaOtkazivanje: this.state.danaZaOtkazivanje})
            .then(res => {
                this.setState({
                    smestaji: res.data
                })
            })
    }

    rezervisi = (idSmestaja, vlasnik) =>{
        this.props.history.push('/rezervisi/' + idSmestaja +"/" + vlasnik);
    }

    rastojanje = (idSmestaja) =>{
        axios.get("http://localhost:8762/smestaj-service/smestaj-service/smestaj-korisnik/rastojanje/" + idSmestaja)
        .then(res => {
            this.setState({
                rastojanja:res.data
            })
        })
    }

    render(){
        var isLogged = this.state.user;
        var { komentari } = this.state;
        var { korisnici } = this.state;
        var { selectedTip } = this.state; //onaj tip koji je selectovan, pocetna vrednost je ""
        var { selectedKategorija } = this.state; //-||-
        var {selectedSort} = this.state;
        var { komentariL } = this.state;
        var { smestaji } = this.state;
        var { dodatne } = this.state;
        var { rastojanja } = this.state;
        var { prosecneOcene } = this.state;
        var { sort } = this.state;
        var { tipovi } = this.state; //tipovi koje smo ajaxom dobili iz baze
        var { kategorije } = this.state;//-||-
        var napredna = this.state.napredna;
        var listaTipova = [];//lista objekata tip
        tipovi.map(tip => {            //prolazimo kroz sve tipove i za svaki pravimo novi objekat sa
            var options = new Object();//poljima value i label posto su ta dva polja neophodna za rad react-select
            options.value = tip.tipSmestajaId;
            options.label = tip.nazivTipaSmestaja;
            listaTipova.push(options); //dodajemo objekat u listu
        })
        var listaKategorija = [];
        kategorije.map(kategorija => {
            var options = new Object();
            options.value = kategorija.id;
            options.label = kategorija.naziv;
            listaKategorija.push(options);
        })
        const smestajiList = smestaji.length ? (smestaji.map(smestaj =>{
            return(   
                <div className="center container"  key = {smestaj.idSmestaja}> 
                    <div className="row">
                        <div className="col s12 m12">
                            <div className="card grey darken-2 card-panel hoverable">
                                <div className="card-content white-text left-align">
                                        <span className="card-title"><b>{smestaj.adresa.grad} {smestaj.adresa.ulica} {smestaj.adresa.broj}</b></span>
                                        <div className="divider white"></div>
                                        <br/>
                                        <p>Tip smestaja: {smestaj.tipSmestaja.nazivTipaSmestaja}</p>
                        
                                        <p>Kategorija smestaja: {smestaj.kategorijaSmestaja.naziv}</p>
                        
                                        <p>Cena: {smestaj.cena}</p>
                        
                                        <p>Max.Osoba: {smestaj.maxOsoba}</p>
                            
                                        <p>Max. dana za otkazivanje: {smestaj.maxDanaZaOtkazivanje}</p>
                        
                                        <p>Opis: {smestaj.opis}</p>

                                        {rastojanja.map(rastojanje=>{
                                            if(rastojanje.smestajId==smestaj.idSmestaja){
                                                return(
                                                <p key = {rastojanje.smestajId}>Rastojanje od centra: {rastojanje.rastojanje} metara</p>
                                                )
                                            }})}

                                        {prosecneOcene.map(ocena=>{
                                        if(ocena.smestajId==smestaj.idSmestaja){
                                            return(
                                            <p key = {ocena.smestajId}>Prosecna ocena: {ocena.ocena}</p>
                                            )
                                        }})}
                                        
                                    </div>                                          
                                    <div className="carousel carousel-slider">
                                        <a className="carousel-item" href="#one!"><img src="https://lorempixel.com/250/250/nature/1"/></a>
                                        <a className="carousel-item" href="#two!"><img src="https://lorempixel.com/250/250/nature/2"/></a>
                                    </div>
                                    {komentariL ? (
                                        <div>
                                            {komentari.map(komentar=>{
                                                if(komentar.idSmestaja==smestaj.idSmestaja){
                                                    return(
                                                        <div className="container left-align" key = {komentar.idKomentara}>
                                                            <div className="divider white"></div>
                                                                {
                                                                    korisnici.map(korisnik=>{
                                                                    if(korisnik.idKorisnik==komentar.idKorisnika){
                                                                        return(<p className="white-text"><b>{korisnik.ime} {korisnik.prezime}</b>:  {komentar.komentar}</p>)
                                                                    }
                                                                })             
                                                                }
                                                        </div>
                                                        )
                                                }})}
                                            <button className="btn waves-effect waves-light green" onClick={()=>{this.komentari(smestaj.idSmestaja)}}>Zatvori</button>
                                        </div>
                                    ):(
                                            <button className="btn waves-effect waves-light green" onClick={()=>{this.komentari(smestaj.idSmestaja)}}>Komentari</button>
                                    )}
                                    <p/>
                                    <div className="divider white"></div>
                                    
                                    {isLogged==undefined ? (                  
                                            <p/>
                                    ):(
                                        <div className="card-action grey darken-2">
                                            <button className="btn waves-effect waves-light green btn-large" onClick={()=>{this.rezervisi(smestaj.idSmestaja, smestaj.vlasnik)}}>Rezervisi</button>
                                        </div>
                                    )}
                        </div>
                    </div>
                </div>
            </div>
        )

        })) : (<div className="center">Nije pronadjen nijedan smestaj.</div>)

        return(    
            <div className="center container">
                <br/>
                <div className="container">
                    <form onSubmit={this.handleSubmit}>
                        <label className="left black-text" htmlFor="mesto">Mesto:</label>
                        <input type="text" id="mesto" onChange={this.handleChange}/>
                        <label className="left black-text" htmlFor="brojOsoba">Broj osoba:</label>
                        <input type="number" id="brojOsoba" onChange={this.handleChange}/>
                        <label className="left black-text" htmlFor="datumOd">Datum od:</label>
                        <input type="date" id="datumOd" onChange={this.handleChange}/>
                        <label className="left black-text" htmlFor="datumDo">Datum do:</label>
                        <input type="date" id="datumDo" onChange={this.handleChange}/>
                        {napredna ? (
                            <div className="container">
                                <label className="left black-text" htmlFor="udaljenostP">Udaljenost (metri):</label>
                                <input type="number" id="udaljenostP" onChange={this.handleChange}/>
                                <label className="left black-text" htmlFor="danaZaOtkazivanje">Max. dana za otkazivanje:</label>
                                <input type="number" id="danaZaOtkazivanje" onChange={this.handleChange}/>
                                <label className="left black-text" htmlFor="tipSmestaja">Tip smestaja:</label>
                                <Select 
                                    value={selectedTip}
                                    onChange={this.handleChangeTip}
                                    options={ listaTipova } />
                                <p/>
                                <label className="left black-text" htmlFor="kategorijaSmestaja">Kategorija smestaja:</label>
                                <Select
                                    value={selectedKategorija}
                                    onChange={this.handleChangeKategorija}
                                    options = {listaKategorija}/>
                                <p/>
                                <label className="left black-text" htmlFor="dodatneUsluge">Dodatne usluge:</label>
                                <br/>
                                { dodatne.map(dodatna => {
                                    return(
                                        <p key = {dodatna.idDodatneUsluge}>
                                            <label>
                                                <input className="black" type="checkbox" id={dodatna.idDodatneUsluge} value={dodatna.idDodatneUsluge} onChange={this.handleCBChange}/>
                                                <span className="black-text">{dodatna.nazivDodatneUsluge}</span>
                                            </label>
                                        </p>
                                    )
                                })}
                            </div>
                        ) : (
                            <div />
                        )
                            
                        }
                        <br/>
                        <button className="btn waves-effect waves-light green">Pretrazi</button>
                        <p/>
                    </form>
                    {napredna ? (
                            <div>
                                <button className="btn waves-effect waves-light green" onClick={this.napredna}>Iskljuci naprednu pretragu</button>
                            </div>
                        ) : (
                            <button className="btn waves-effect waves-light green" onClick={this.napredna}>Napredna pretraga</button>
                        )
                            
                    }
                </div>
                <br/>
                <div className="container">
                <label className="left black-text" htmlFor="sortBy">Izlistaj po:</label>
                    <Select
                                        value={selectedSort}
                                        onChange={this.handleChangeSort}
                                        options = {sort}/>
                    <br/>
                </div>
                <h3 className="left-align container">Smestaji:</h3>
                {smestajiList}
            </div>
        )
    }

}
export default withRouter(Home)