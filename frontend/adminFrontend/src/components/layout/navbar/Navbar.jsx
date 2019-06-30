import React, { Component } from 'react'
import { NavLink, withRouter } from "react-router-dom"

class Navbar extends Component {

    signOut = () => {
        console.log(sessionStorage.getItem("email"));
        this.props.setUser(sessionStorage.removeItem('jwtToken'));
        this.props.setEmail(sessionStorage.removeItem('email'));
        this.props.history.push("/"); //ne radi wt
        console.log(sessionStorage.getItem("email"));
    }

    render() {
        return (
            <nav className="nav-wrapper blue darken-3">
                <div className="container">
                    <NavLink to='/' className="brand-logo">MegaTravel</NavLink>
                    {(sessionStorage.getItem("email") == null) ? (
                        <ul id="nav-mobile" className="right hide-on-med-and-down">
                            <li><NavLink to="/login" color="inherit">Prijavi se</NavLink></li>
                        </ul>
                    ) : (
                            <ul id="nav-mobile" className="right hide-on-med-and-down">
                                <li><NavLink to="/mainpanel" color="inherit">Panel</NavLink></li>
                                <li><NavLink to="/" color="inherit" onClick={this.signOut}>Odjavi se</NavLink></li>
                            </ul>
                        )}
                </div>
            </nav>
        )
    }
}
// export default 
export default withRouter(Navbar)