import React, { Component } from 'react'
import axios from 'axios'
import {withRouter} from 'react-router-dom';


class Ocena extends Component{

    state = {
        ocena:""
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        if(this.state.ocena!=""){
        //var smestaj = this.props.match.params.smestajId;
        //this.props.history.push("/moje_rezervacije");
        //dodaj axios
        }else{}
        
    }

    render(){
        var user = this.props.user;
        var smestaj = this.props.match.params.smestajId;
        console.log(user + " " + smestaj);
        return(
            <div className = "center container">
                <h4 className="center">Ocenite smestaj (do 5):</h4>
                <div className = "center container">
                    <form onSubmit={this.handleSubmit}>
                        <input type="number" id="ocena" onChange={this.handleChange}/>
                        <button className="btn waves-effect waves-light green">Oceni</button>
                    </form>
                </div>
            </div>
        )

    }
}

export default withRouter(Ocena)