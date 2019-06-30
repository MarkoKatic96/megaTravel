import React from 'react'
import {withRouter} from 'react-router-dom'

const Home= ({props}) =>{
    return(
        <h1>Dobro dosao {props !== undefined ? props.user.ime : "Nema ulogovan!"}!</h1>
    )
}
export default withRouter(Home)