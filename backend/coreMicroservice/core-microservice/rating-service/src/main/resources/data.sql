INSERT INTO `ocena` (`idOcena`,`idKorisnik`,`idRezervacija`,`idSmestaj`,`ocena`,`timestamp`) VALUES (1,4,6,4,5,NULL);
INSERT INTO `ocena` (`idOcena`,`idKorisnik`,`idRezervacija`,`idSmestaj`,`ocena`,`timestamp`) VALUES (2,3,3,2,2,NULL);

INSERT INTO `komentar` (`idKomentara`,`idKorisnika`,`idRezervacije`,`idSmestaja`,`komentar`,`status`,`timestamp`) VALUES (1,4,6,4,'Bas je dobar smestaj',1,NULL);
INSERT INTO `komentar` (`idKomentara`,`idKorisnika`,`idRezervacije`,`idSmestaja`,`komentar`,`status`,`timestamp`) VALUES (2,3,3,2,'Strasno los smestaj ni klima ne radi',0,NULL);
