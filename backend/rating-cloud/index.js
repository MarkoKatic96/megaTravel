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

    if (ocena >= 1 && ocena <= 5) {
        var sql = "INSERT INTO ocena (idOcena, idKorisnik, idRezervacija, idSmestaj, ocena, timestamp) values (?, ?, ?, ?, ?, ?)";

        db.query(sql, [idOcena, idKorisnik, idRezervacija, idSmestaj, ocena, timestamp], function (err, result) {
            if (err)
                res.status(404).send('Not Found');
            else
                res.status(200).send('Created');
        })
    }

};

require('@google/cloud-debug');
exports.editDeleteOcena = function editDeleteOcena(req, res) {
    //2002-02-01T23:00:00.000Z
    let idOcena = req.body.idOcena;
    let idKorisnik = req.body.idKorisnik;
    let idRezervacija = req.body.idRezervacija;
    let idSmestaj = req.body.idSmestaj;
    let ocena = req.body.ocena;
    let timestamp = req.body.timestamp;

    if (ocena == 0) {
        var sql = "DELETE FROM ocena WHERE idOcena=" + idOcena;

        db.query(sql, (err, result) => {
            if (err)
                res.status(404).send('Not Found');
            else
                res.status(200).send("Izbrisano");
        });
    } else {
        if (ocena >= 1 && ocena <= 5) {
            var sql = "UPDATE ocena SET idOcena=" + idOcena + ", idKorisnik=" + idKorisnik + ", idRezervacija=" + idRezervacija +
                ", idSmestaj=" + idSmestaj + ", ocena=" + ocena + " WHERE idOcena=" + idOcena;

            db.query(sql, function (err, result) {
                if (err)
                    res.status(400).send('Bad Request');
                else
                    res.status(200).send('Updated');
            })
        }

    }

};

require('@google/cloud-debug');
exports.getObjavljeneKomentare = function getObjavljeneKomentare(req, res) {

    var sql = "select * from komentar where status=1";

    db.query(sql, (err, result) => {
        if (err)
            res.status(404).send('Not Found');
        else
            res.status(200).send(result);
    });

};

require('@google/cloud-debug');
exports.createKomentar = function createKomentar(req, res) {
    //2002-02-01T23:00:00.000Z
    let idKomentara = req.body.idKomentara;
    let idKorisnika = req.body.idKorisnika;
    let idRezervacije = req.body.idRezervacije;
    let idSmestaja = req.body.idSmestaja;
    let komentar = req.body.komentar;
    let status = 0;
    let timestamp = req.body.timestamp;

    var sql = "INSERT INTO komentar (idKomentara, idKorisnika, idRezervacije, idSmestaja, komentar, status, timestamp) values (?, ?, ?, ?, ?, ?, ?)";

    db.query(sql, [idKomentara, idKorisnika, idRezervacije, idSmestaja, komentar, status, timestamp], function (err, result) {
        if (err)
            res.status(404).send('Not Found');
        else
            res.status(200).send('Created');
    })

};

require('@google/cloud-debug');
exports.getNeobjavljeneKomentare = function getNeobjavljeneKomentare(req, res) {

    var sql = "select * from komentar where status=0";

    db.query(sql, (err, result) => {
        if (err)
            res.status(404).send('Not Found');
        else
            res.status(200).send(result);
    });

};

require('@google/cloud-debug');
exports.objaviKomentar = function objaviKomentar(req, res) {
    //2002-02-01T23:00:00.000Z
    let idKomentara = req.query.id;

    var sql = "UPDATE komentar SET status=" + 1 + " WHERE idKomentara=" + idKomentara;

    db.query(sql, function (err, result) {
        if (err)
            res.status(404).send('Not Found');
        else
            res.status(200).send('Created');
    })

};

require('@google/cloud-debug');
exports.blokirajKomentar = function blokirajKomentar(req, res) {
    //2002-02-01T23:00:00.000Z
    let idKomentara = req.query.id;

    var sql = "UPDATE komentar SET status=" + 2 + " WHERE idKomentara=" + idKomentara;

    db.query(sql, function (err, result) {
        if (err)
            res.status(404).send('Not Found');
        else
            res.status(200).send('Created');
    })

};