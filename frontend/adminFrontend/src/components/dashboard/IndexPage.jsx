import React, { Component } from 'react';

class IndexPage extends Component {

    state = { 
        user: ""
     }

    componentDidMount()
    {
        this.setState({
            user: this.props.user
        })
    }

    
    render() {
        
        return (
            <div>
                {sessionStorage.getItem("email") === null ? (
                    <div><h1> Administratorski modul.. Prijavi se</h1> </div>
                ) : (
                    <div><h1>Prijavljeni ste kao administrator</h1></div>
                )}
            </div>
        );
    }
}

export default IndexPage;
