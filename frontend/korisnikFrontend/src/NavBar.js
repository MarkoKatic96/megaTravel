import React, { Component } from 'react'
import {Link, NavLink, withRouter} from "react-router-dom"

class NavBar extends Component{

    state={
        user:undefined
    }

    componentDidMount(){
        this.setState({
            user: this.props.user
        })
    }

    odjava = () =>{
        sessionStorage.setItem('jwtToken', undefined);
        sessionStorage.setItem('email', undefined)
        this.props.setUser(sessionStorage.getItem('jwtToken'));
        this.props.setEmail(sessionStorage.getItem('email'));
        this.props.history.push("/");
    }

    componentWillReceiveProps(nextProps) {
        console.log("EEEE: " + nextProps.user)
        this.setState({ user: nextProps.user});
    }

    render(){
        var isLogged = this.props.user
        console.log("HAY: " + isLogged);
        return(          
            <nav className="nav-wrapper red darken-3">
                <div className="container">
                <Link to='/' className="brand-logo">MegaTravel</Link>
                { isLogged !== undefined ? (
                    <ul id="nav-mobile" className="right hide-on-med-and-down">
                    {console.log("Defined: " + isLogged)}
                        <li><Link to="/moje_poruke">Moje poruke</Link></li>
                        <li><Link to="/moje_rezervacije">Moje rezervacije</Link></li>
                        <li><Link onClick={this.odjava}>Odjava</Link></li>
                    </ul>
                ) : (
                    <ul id="nav-mobile" className="right hide-on-med-and-down">
                    {console.log("Undefined: " + isLogged)}
                        <li><Link to="/login">Prijava</Link></li>
                        <li><Link to="/register">Registracija</Link></li>
                    </ul>
                    
                )}
                </div>
            </nav>       
        )
    }
}
export default withRouter(NavBar)