import React, { Component } from 'react'
import axios from 'axios'
import {withRouter} from 'react-router-dom';

import validator from 'validator';
import Input from 'react-validation/build/input';
import Form from 'react-validation/build/form';

/*
 VALIDACIJA ADMIN LOGIN-A
*/
const required_val = (value) =>
{
    if(!value.toString().trim().length)
    {
        return 'require';
    }
}

const email_val = (value) =>
{
    if(!validator.isEmail(value))
    {
        return `${value} nije validna email adresa..`
    }
}



//location, match i history => tri props koja se dobijaju iz react-router
class Login extends Component{

    state = {
        email: "",
        lozinka: ""
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        var a = this.state.email; 
        var b = this.state.lozinka;
        if(a !== "" && b !== ""){
            axios.post('http://localhost:8762/admin-service/admin-service/admin/login', { email: a, lozinka: b})
            .then(res => {
                if(res === "1" || res === 1 || res === 1 || res === "1" || res === "")
                {
                    //ispravi ovo u zavisnosti od toga sta ce da se vraca
                    //alert("Pogresna lozinka ili email");
                }
                else
                {
                    sessionStorage.setItem('jwtToken', res.data);
                    sessionStorage.setItem('email', a);
                    this.props.setUser(sessionStorage.getItem('jwtToken'));
                    this.props.setEmail(sessionStorage.getItem('email'));
                    this.props.history.push("/mainpanel");
                }
            })
        }else{
            alert("Unesite email i sifru kako biste se ulogovali.");
            return;
        }

    
    }

    render(){
        return(
            <div className = "center container">
                <h4 className="center">Prijavi se:</h4>
                <div className = "center container">
                    <Form onSubmit={(e) => {this.handleSubmit(e)}}>
                        <label htmlFor="email">Email:</label>
                        <Input name='email' validations={[required_val, email_val]} className="validate" type="text" id="email" onChange={this.handleChange} />
                        
                        <label htmlFor="lozinka">Lozinka:</label>
                        <Input name='password' validations={[required_val]} className="validate" type="password" id="lozinka" onChange={this.handleChange} />
                        <button type="submit" className="btn waves-effect waves-light green">Prijava</button>
                    </Form>
                </div>
            </div>
        )

    }
}

export default withRouter(Login)