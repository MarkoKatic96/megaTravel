import React, { Component } from 'react'
import axios from 'axios'
import {withRouter} from 'react-router-dom';

class MojePoruke extends Component{

    state = {
        porukeOdOdredjenog:[],
        poruke:[],
        brojAgenata:[],//koliko ima razlicitih agenata, da se toliko chatroomova kreira
        smestaji:[]
    }

    componentDidMount() {
        const token = this.props.user;
        var br = [];
        axios.get("http://localhost:8762/poruke-korisnik-service/poruke-korisnik-service/poruke/neprocitane/" + token)
        .then(res=>{
            console.log(res.data);
             this.setState({
                poruke: res.data
             })

             var lista = this.state.poruke;
       console.log("lista: " + this.state.poruke);
       var i;
       var h;
       var vecJeTu;
       for(i=0; i<lista.length; i++){
        vecJeTu = 0;
           for(h=0; h<br.length; h++){
                if(lista[i].posiljalac==br[h].posiljalac && lista[i].primalac == br[h].primalac) {
                    vecJeTu=1;
                }
            }
            if(vecJeTu==0){
                br.push(lista[i]);
            }
       }

       this.setState({
           brojAgenata: br
       })
       console.log("ovo je state: " + this.state.brojAgenata)
       })

       axios.get("http://localhost:8762/smestaj-service/smestaj-service/smestaj-korisnik/bll")
       .then(res=>{
           this.setState({
              smestaji: res.data
           })
        })
       
    }

    poruke = (idAgenta)=>{   
        this.props.history.push("/chatroom/" + idAgenta);              
    }

    render(){
        var { brojAgenata } = this.state;
        var { smestaji } = this.state;
        const porukeList = brojAgenata.length ? (brojAgenata.map(agent => {
        return(
            <div className = "center container" key={agent.idPoruke}>
                <div className="row">
                    <div className = "col s12 m12">
                        <div className="card grey darken-2 card-panel hoverable">
                            <div className="card-content white-text left-align">
                                {smestaji.map(smestaj =>{
                                    if(smestaj.vlasnik==agent.posiljalac){ 
                                        return(<span className="card-title"><b>Agent smestaja {smestaj.adresa.grad} {smestaj.adresa.ulica} {smestaj.adresa.broj}</b></span>)
                                    }})      
                                }
                                <div className="divider white"></div>
                                <br/>                              
                                <div className="center container">
                                    <button className="btn waves-effect waves-light green" onClick={()=>{this.poruke(agent.posiljalac)}}>Pogledaj poruke</button>
                                </div>                               
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    })):(<p/>)
    return(
        <div className="container"> 
            <h3 className="black-text">Moje poruke:</h3>
            {porukeList}
        </div>
        )
    }
}
export default withRouter(MojePoruke)