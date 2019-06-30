import React, { Component } from 'react'
import axios from 'axios'
import {withRouter} from 'react-router-dom';


class Registration extends Component{

    state = {
        username: "",
        password: "",
        firstname:"",
        lastname:""
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        if(this.state.username!="" && this.state.password!="" && this.state.firstname!="" && this.state.lastname!=""){
            axios.post("http://localhost:8762/agent-global-service/agent-global-service/agent/register", {email: this.state.username, lozinka: this.state.password, ime: this.state.firstname, prezime: this.state.lastname})
            .then(res =>{
                this.props.history.push("/");
            })
        }else{
            alert("Morate popuniti sva polja da bi se uspesno registrovali.");
        }
    }

    render(){
        return(
            <div className = "center container">
                <h4 className="center">Registruj se:</h4>
                <div className = "center container">
                    <form onSubmit={this.handleSubmit}>
                        <label className="left black-text" htmlFor="username">Email:</label>
                        <input type="text" id="username" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="password">Lozinka:</label>
                        <input type="password" id="password" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="firstname">Ime:</label>
                        <input type="text" id="firstname" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="lastname">Prezime:</label>
                        <input type="text" id="lastname" onChange={this.handleChange} />
                        <button className="btn waves-effect waves-light green">Registracija</button>
                    </form>
                </div>
            </div>
        )

    }
}

export default withRouter(Registration)