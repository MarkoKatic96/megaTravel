import React, {Component} from 'react';
import { BrowserRouter, Route, withRouter } from 'react-router-dom';
import NavBar from './NavBar';
import Home from './Home';
import Login from './Login';
import Registration from './Registration';
import ReservationForm from './ReservationForm';
import MojeRezervacije from './MojeRezervacije';
import Poruka from "./Poruka";
import Komentar from './Komentar';
import Ocena from './Ocena';
import MojePoruke from './MojePoruke';
import ChatRoom from './ChatRoom';


class App extends Component {

  state = {
    user:undefined,
    usersEmail:undefined
  }

    setUser = (jwt) =>{
      this.setState({
        user:jwt
      })
    }
  

  setEmail = (email) =>{
    this.setState({
      usersEmail:email
    })
  }
  

  render(){
    return (
       <BrowserRouter>
        <div className="App grey lighten-1">
          <NavBar user = {this.state.user} setUser = {this.setUser} setEmail = {this.setEmail}/>
          <Route exact path = '/' render={props => <Home user = {this.state.user}/>}/>
          <Route path = '/login' render={props => <Login setUser = {this.setUser} setEmail = {this.setEmail}/>}/>
          <Route path = '/register' component = {Registration}/>
          <Route path = "/rezervisi/:idSmestaja/:vlasnik" render={props => <ReservationForm user = {this.state.user} usersEmail = {this.state.usersEmail}/>}/>
          <Route path = "/moje_rezervacije"  render={props => <MojeRezervacije user = {this.state.user} usersEmail = {this.state.usersEmail}/>}/>
          <Route path = "/moje_poruke"  render={props => <MojePoruke user = {this.state.user} usersEmail = {this.state.usersEmail}/>}/>
          <Route path = "/poruka/:idVlasnika" render={props => <Poruka user = {this.state.user}/>}/>
          <Route path = "/komentar/:smestajId/:rezervacijaId/:korisnikId" render={props => <Komentar user = {this.state.user}/>}/>
          <Route path = "/chatroom/:agentId" render={props => <ChatRoom user = {this.state.user}/>}/>
        </div>
       </BrowserRouter>
    );
  }
}

export default App;
