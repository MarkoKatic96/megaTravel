import React, {Component} from 'react'
import { Link, withRouter } from 'react-router-dom'

class Navbar extends Component {

    odjava = () =>{
        this.props.setUser(sessionStorage.getItem('jwtToken'));
        this.props.setEmail(sessionStorage.getItem('email'));
        this.props.history.push("/login");
    }

    render(){
        const isLogged = this.props.user
        return(          
            <nav className="nav-wrapper red darken-3">
                <div className="container">
                <Link to='/' className="brand-logo">MegaTravel</Link>
                { isLogged===undefined ? (
                    <ul id="nav-mobile" className="right hide-on-med-and-down">
                        <li><Link to="/login">Prijava</Link></li>
                        <li><Link to="/register">Registracija</Link></li>
                    </ul>
                ) : (
                    <ul id="nav-mobile" className="right hide-on-med-and-down">
                        
                        <li><Link to="/smestaji">Smestaji</Link></li>
                        <li><Link to="/rezervacije">Rezervacije</Link></li>
                        <li><Link to="/createsmestaj">Dodaj smestaj</Link></li>
                        <li><Link onClick={this.odjava}>Odjava</Link></li>
                    </ul>
                )}
                </div>
            </nav>       
        )
    }
}
export default withRouter(Navbar);