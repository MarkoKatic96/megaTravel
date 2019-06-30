import React, {Component} from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import 'materialize-css/dist/css/materialize.min.css';

import Dashboard from './components/dashboard/Dashboard';
import Navbar from './components/layout/navbar/Navbar';
import Login from './components/auth/Login';
import IndexPage from './components/dashboard/IndexPage';

class App extends Component
{
  state = {
    user: undefined,
    userEmail: undefined
  }

  setUser = (jwt) =>
  {
    this.setState({
      user: jwt
    })
  }

  setEmail = (email) =>
  {
    this.setState({
      userEmail: email
    })
  }

  render()
  {
    return (
      <BrowserRouter>
        <div className="App">
          <Navbar user={this.state.user} setUser={this.setUser} setEmail={this.setEmail} />
          <Route exact path="/" user={this.state.user} component={IndexPage} />
          <Route path="/mainpanel" render={props => <Dashboard user={this.state.user} />} />
          <Route path="/login" render={props => <Login setUser={this.setUser} setEmail={this.setEmail} />} />
        </div>
      </BrowserRouter>
    );
  }
  
}

export default App;
