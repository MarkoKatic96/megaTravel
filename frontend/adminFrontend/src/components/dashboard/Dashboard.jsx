import React, {Component} from 'react';
import MainPanel from './panel/MainPanel';
import './Dashboard.css';

class Dashboard extends Component
{
    render()
    {
        return (
            <div className="side">
                {sessionStorage.getItem("email") === null ? (<h1>Ne mozete pristupiti panelu, niste ulogovani</h1>) : (<MainPanel />)}
                
            </div>
        );
    }
    
}

export default Dashboard;
