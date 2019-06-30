import React, { Component } from 'react';
import { NavLink } from "react-router-dom";
import axios from 'axios';
import './Sidebar.css';

class Sidebar extends Component {

    state = {
        agents: [],
        users: [],
        comments: [],
        "badge1": "",
        "badge2": "",
        "badge3": ""
    }

    componentDidMount() {
        axios.get('http://localhost:8762/agent-global-service/agent-global-service/admin/allrequests').then(response => {
            if (response.data.length > 0)
                this.setState({
                    badge1: "red new badge"
                })
            this.setState({
                agents: response.data
            })
        });

        axios.get('http://localhost:8762/korisnik-service/korisnik-service/admin/allnotactivated').then(response => {
            if (response.data !== null)
                this.setState({
                    badge2: "red new badge"
                })
            this.setState({
                users: response.data
            })
        });

        axios.get('http://localhost:8762/rating-service/rating-service/admin/unpublcomms').then(response => {
            if (response.data !== null)
                this.setState({
                    badge3: "red new badge"
                })
            this.setState({
                comments: response.data
            })
        });

    }

    removeBadge1 = () => {
        this.setState({
            badge1: ""
        })
    }

    removeBadge2 = () => {
        this.setState({
            badge2: ""
        })
    }

    removeBadge3 = () => {
        this.setState({
            badge3: ""
        })
    }

    render() {
        return (
            <div>
                <div className="collection barr">
                    <span onClick={this.removeBadge1}><NavLink to="/agents" color="inherit" className="collection-item" >
                        <span className={(this.state.agents.length > 0) ? this.state.badge1 : null}>
                            {(this.state.badge1 !== "" && this.state.agents.length > 0) ? this.state.agents.length : null}
                        </span>Neregistrovani agenti</NavLink></span>
                    <span onClick={this.removeBadge2}><NavLink to="/noactusers" color="inherit" className="collection-item">
                        <span className={(this.state.users.length > 0) ? this.state.badge2 : null}>
                            {(this.state.badge2 !== "" && this.state.users.length > 0) ? this.state.users.length : null}
                        </span>Neaktivirani korisnici</NavLink></span>

                        <NavLink to="/actusers" color="inherit" className="collection-item">Aktivirani korisnici</NavLink>

                        <NavLink to="/blockusers" color="inherit" className="collection-item">Blokirani korisnici</NavLink>

                    <span onClick={this.removeBadge3}><NavLink to="/comments" color="inherit" className="collection-item">
                        <span className={(this.state.comments.length > 0) ? this.state.badge3 : null}>
                            {(this.state.badge3 !== "" && this.state.comments.length > 0) ? this.state.comments.length : null}
                        </span>Komentari</NavLink></span>

                    <NavLink to="/types" color="inherit" className="collection-item">Tipovi smeštaja</NavLink>
                    <NavLink to="/categories" color="inherit" className="collection-item">Kategorizacija smeštaja</NavLink>
                    <NavLink to="/services" color="inherit" className="collection-item">Dodatne usluge</NavLink>
                </div>
            </div>
        );
    }

}

export default Sidebar;
