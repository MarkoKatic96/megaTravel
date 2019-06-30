import React, {Component} from 'react';
import { BrowserRouter, Route } from 'react-router-dom'
import Login from './components/login/Login'
import Home from './components/navbar/Home'
import Navbar from './components/navbar/Navbar'
import Smestaji from './components/smestaj/Smestaji';
import CreateSamoRez from './components/rezervacija/CreateSamoRez';
import AddSmestaj from './components/smestaj/AddSmestaj';
import ResDisplay from './components/rezervacija/ResDisplay';
import Registration from './components/login/Registration';

class App extends Component {
  state = {
    user : undefined,
    email: undefined
  }

  setUser = (jwt) => {
    this.setState({
      user : jwt
    })
  }

  setEmail = (email) => {
    this.setState({
      email
    })
  }
  render() {
    return (
      <BrowserRouter>
        <div className="App">
          <header className="App-header">
            <Navbar user = {this.state.user} setUser = {this.setUser} setEmail = {this.setEmail} />
            <Route exact path= "/" render = {props => <Home user = {this.state.user}/>}/>
            <Route path= "/login" render = {props => <Login setUser = {this.setUser} setEmail = {this.setEmail} />} />
            <Route path= "/register" render = {props => <Registration token = {this.state.user}/>} />
            <Route path= "/rezervacije" render = {props => <ResDisplay userEmail = {this.state.email} token = {this.state.user} />} />
            <Route path= "/smestaji" render = {props => <Smestaji />} />
            <Route path= "/createsmestaj" render = {props => <AddSmestaj />} />
            <Route path= "/samostalna/:smestaj_id" render = {props => <CreateSamoRez userEmail = {this.state.email} />} />
            
          </header>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
