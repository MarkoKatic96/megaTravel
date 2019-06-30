import React, { Component } from 'react'
import axios from 'axios'
import {withRouter} from 'react-router-dom';


class Login extends Component{

    state = {
        username: "",
        password: "",
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        var a = this.state.username; 
        var b = this.state.password;
        if(a!="" && b!=""){
            axios.post('http://localhost:8762/korisnik-service/korisnik-service/login', { email: a, password: b})
            .then(res => {
                sessionStorage.setItem('jwtToken', res.data);
                sessionStorage.setItem('email', a)
                this.props.setUser(sessionStorage.getItem('jwtToken')) 
                this.props.setEmail(sessionStorage.getItem('email'))
                this.props.history.push("/");
            }).catch(error=>{
                alert("Pogresno unet email ili lozinka.");
            })
        }else{
            alert("Unesite email i sifru kako biste se ulogovali.")
        }
    }

    render(){
        return(
            <div className = "center container">
                <h4 className="center">Prijavi se:</h4>
                <div className = "center container">
                    <form onSubmit={this.handleSubmit}>
                        <label className="left black-text" htmlFor="username">Email:</label>
                        <input type="text" id="username" onChange={this.handleChange} />
                        <label className="left black-text" htmlFor="password">Lozinka:</label>
                        <input type="password" id="password" onChange={this.handleChange} />
                        <button className="btn waves-effect waves-light green">Prijava</button>
                    </form>
                </div>
            </div>
        )

    }
}

export default withRouter(Login)