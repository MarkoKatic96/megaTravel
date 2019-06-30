import React, { Component } from 'react';
import axios from 'axios'

import './MainPanel.css';

class CommentPanel extends Component {
    state = {
        comments: [],
        users: [],
        korisnikIzID: ""
    }

    componentDidMount() {
        axios.get('http://localhost:8762/rating-service/rating-service/admin/unpublcomms').then(response => {
            
            if (response.data === null) {
                this.setState({
                    comments: []
                })
            }
            else {
                this.setState({
                    comments: response.data
                })
            }
        });

        axios.get('http://localhost:8762/korisnik-service/korisnik-service/admin/allactivated').then(response => {
            if (response.data === null) {
                this.setState({
                    users: []
                })
            }
            else {
                this.setState({
                    users: response.data
                })
            }
        });

        this.getUserFromCommentId(2);
    }


    acceptComment = (id) => 
    {
        axios.put('http://localhost:8762/rating-service/rating-service/admin/publcomm/' + id)
        .then(res => {
            this.componentDidMout();
        })
    }

    deleteComment = (id) => 
    {
        axios.delete('http://localhost:8762/rating-service/rating-service/admin/removecomm/' + id)
            .then(res => {
                this.componentDidMount();
            })
    }
    
    getUserFromCommentId = (id) =>
    {
        var list = this.state.users.filter(user => user.idKorisnik === id);
        console.log(list.ime);
        this.setState({
            korisnikIzID: list
        })

    }


    render() {
        const { comments } = this.state;
        const commentList = comments.length ? (comments.map(comment => {
            
            return (
                <div className="row" key={comment.idKomentara}>
                    <div className="col s12 m6">
                        <div className="card blue lighten-5 kartica">
                            <div className="card-content white-text">
                                <span onLoad={() => {this.getUserFromCommentId(comment.idKorisnika)}} className="card-title black-text darken-4"><strong> { this.state.korisnikIzID } </strong></span>
                                <p className="black-text darken-4">{comment.komentar}</p>
                            </div>
                            <div className="card-action">
                                <button className="btn waves-effect waves-light blue left-align"  onClick={() => { this.acceptComment(comment.idKomentara) }}>Dozvoli</button>
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <button className="btn waves-effect waves-light red left-align" onClick={() => { if (window.confirm('Da li ste sigurni da zelite da obrišete komentar?')) this.deleteComment(comment.idKomentara) } }>Obriši</button>
                            </div>
                        </div>
                    </div>
                </div>
            )
        })) : (<p>Nema novih neobjavljenih komentara.</p>)

        return (
            <div className="center container">
                <h3 className="left-align container">Spisak neobjavljenih komentara:</h3>
                <br />
                {commentList}
            </div>
        )

    }
}

export default CommentPanel;