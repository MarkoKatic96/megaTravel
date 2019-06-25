var db = require('./connect');

require('@google/cloud-debug');
exports.getOcene = function getOcene(req, res) {

    var sql = "select * from ocena where idSmestaj=" + req.query.id;

    db.query(sql, (err, result) => {
        if (err)
            res.status(404).send('Not Found');
        else
            res.status(200).send(result);
    });
  
};

require('@google/cloud-debug');
exports.createOcena = function createOcena(req, res) {

    let idOcena = req.body.idOcena;
    let idKorisnik = req.body.idKorisnik;
    let idRezervacija = req.body.idRezervacija;
    let idSmestaj = req.body.idSmestaj;
    let ocena = req.body.ocena;
    let timestamp = req.body.timestamp;

    var sql = "INSERT INTO ocena (idOcena, idKorisnik, idRezervacija, idSmestaj, ocena, timestamp) values (?, ?, ?, ?, ?, ?)";

    db.query(sql, [idOcena, idKorisnik, idRezervacija, idSmestaj, ocena, timestamp], function (err, result) {
        if (err)
            res.status(400).send('Bad Request');
        else
            res.status(200).send('Created');
    })

};