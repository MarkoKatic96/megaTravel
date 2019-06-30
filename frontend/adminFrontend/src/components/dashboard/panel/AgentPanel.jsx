import React, { Component } from 'react';
import axios from 'axios'

import './MainPanel.css';

class AgentPanel extends Component {

    state = {
        agents: []
    }

    componentDidMount() {
        axios.get('http://localhost:8762/agent-global-service/agent-global-service/admin/allrequests').then(response => {
            console.log(response.data)
            if (response.data === null) {
                this.setState({
                    agents: []
                })
            }
            else {
                this.setState({
                    agents: response.data
                })
            }
        });
    }


    acceptAgentRequest = (id) => {
        axios.post('http://localhost:8762/agent-global-service/agent-global-service/admin/confirmrequest/' + id)
        .then(response => {
            if(response.data === "OK")
                this.componentDidMount();
            else
                console.log(response.data);
    });

    }

    deleteAgentRequest = (id) => {

        axios.delete('http://localhost:8762/agent-global-service/agent-global-service/admin/refuserequest/' + id)
            .then(res => {
                this.componentDidMount();
            })
    }

    render() {
        const { agents } = this.state;
        const agentList = agents.length ? (agents.map(agent => {
            return (
                <div className="row" key={agent.idNeaktiviranogAgenta}>
                    <div className="col s12 m6">
                        <div className="card blue lighten-5 kartica">
                            <div className="card-content white-text">
                                <span className="card-title black-text darken-4"><strong>{agent.email}</strong></span>
                                <h5 className="black-text darken-4">{agent.ime} {agent.prezime}</h5>
                            </div>
                            <div className="card-action">
                                <button className="btn waves-effect waves-light blue left-align"  onClick={() => { this.acceptAgentRequest(agent.idNeaktiviranogAgenta) }}>Prihvati zahtev</button>
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <button className="btn waves-effect waves-light red left-align" onClick={() => { if (window.confirm('Da li ste sigurni da zelite da odbijete zahtev ' + agent.email + ' za agenta?')) this.deleteAgentRequest(agent.idNeaktiviranogAgenta) } }>Obriši zahtev</button>
                            </div>
                        </div>
                    </div>
                </div>

            )
        })) : (<p>Nema novih zahteva za agente.</p>)

        return (
            <div className="center container">
                <h3 className="left-align container tekst">Spisak neregistrovanih agenata:</h3>
                <br />
                <div>
                    {agentList}
                </div>
                
            </div>
        )

    }
}

export default AgentPanel;

