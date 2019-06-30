INSERT INTO `db_xml_wix`.`tip_smestaja` (`tip_smestaja_id`, `naziv_tipa_smestaja`) VALUES ('1', 'Hotel');
INSERT INTO `db_xml_wix`.`tip_smestaja` (`tip_smestaja_id`, `naziv_tipa_smestaja`) VALUES ('2', 'Bed&Breakfast');
INSERT INTO `db_xml_wix`.`tip_smestaja` (`tip_smestaja_id`, `naziv_tipa_smestaja`) VALUES ('3', 'Apartman');

INSERT INTO `db_xml_wix`.`kategorija_smestaja` (`id`, `naziv`) VALUES ('1', '1 zvezdica');
INSERT INTO `db_xml_wix`.`kategorija_smestaja` (`id`, `naziv`) VALUES ('2', '2 zvezdice');
INSERT INTO `db_xml_wix`.`kategorija_smestaja` (`id`, `naziv`) VALUES ('3', '3 zvezdice');
INSERT INTO `db_xml_wix`.`kategorija_smestaja` (`id`, `naziv`) VALUES ('4', '4 zvezdice');
INSERT INTO `db_xml_wix`.`kategorija_smestaja` (`id`, `naziv`) VALUES ('5', '5 zvezdica');

INSERT INTO `db_xml_wix`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('1', 'Parking');
INSERT INTO `db_xml_wix`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('2', 'WiFi');
INSERT INTO `db_xml_wix`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('3', 'Doručak');
INSERT INTO `db_xml_wix`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('4', 'Polu pansion');
INSERT INTO `db_xml_wix`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('5', 'Pansion,');
INSERT INTO `db_xml_wix`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('6', 'All Inclusive,');
INSERT INTO `db_xml_wix`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('7', 'Inclusive,');
INSERT INTO `db_xml_wix`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('8', 'Dozvoljeni kućni ljubimci');
INSERT INTO `db_xml_wix`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('9', 'TV');
INSERT INTO `db_xml_wix`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('10', 'Mini kuhinja/kuhinja');
INSERT INTO `db_xml_wix`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('11', 'Privatno kupatilo');


INSERT INTO `db_xml_wix`.`tkoordinate` (`koordinate_id`, `latitude`, `longitude`, `smestaj_id_smestaja`) VALUES ('1', '44', '24', '1');
INSERT INTO `db_xml_wix`.`tkoordinate` (`koordinate_id`, `latitude`, `longitude`, `smestaj_id_smestaja`) VALUES ('2', '50', '60', '2');
INSERT INTO `db_xml_wix`.`tkoordinate` (`koordinate_id`, `latitude`, `longitude`, `smestaj_id_smestaja`) VALUES ('3', '71', '72', '3');
INSERT INTO `db_xml_wix`.`tkoordinate` (`koordinate_id`, `latitude`, `longitude`, `smestaj_id_smestaja`) VALUES ('4', '4', '8', '4');
INSERT INTO `db_xml_wix`.`tkoordinate` (`koordinate_id`, `latitude`, `longitude`, `smestaj_id_smestaja`) VALUES ('5', '26', '19', '5');



INSERT INTO `db_xml_wix`.`tadresa` (`adresa_id`, `broj`, `grad`, `ulica`, `smestaj_id_smestaja`) VALUES ('1', '8', 'Novi Sad', 'Dositejeva', '1');
INSERT INTO `db_xml_wix`.`tadresa` (`adresa_id`, `broj`, `grad`, `ulica`, `smestaj_id_smestaja`) VALUES ('2', '4', 'Novi Sad', 'Jovina', '2');
INSERT INTO `db_xml_wix`.`tadresa` (`adresa_id`, `broj`, `grad`, `ulica`, `smestaj_id_smestaja`) VALUES ('3', '2', 'Novi Sad', 'Zikina', '3');
INSERT INTO `db_xml_wix`.`tadresa` (`adresa_id`, `broj`, `grad`, `ulica`, `smestaj_id_smestaja`) VALUES ('4', '1', 'Nadalj', 'Jovina', '4');
INSERT INTO `db_xml_wix`.`tadresa` (`adresa_id`, `broj`, `grad`, `ulica`, `smestaj_id_smestaja`) VALUES ('5', '8', 'Nadalj', 'Mikina', '5');

INSERT INTO `db_xml_wix`.`smestaj` (`id_smestaja`, `cena_jesen`, `cena_leto`, `cena_prolece`, `cena_zima`, `max_dana_za_otkazivanje`, `max_osoba`, `opis`, `vlasnik`, `adresa_id`, `kategorijasmestaja_id`, `koordinate_id`, `tipsmestaja_id`) VALUES ('1', '500', '550', '500', '510', '5', '2', 'aaa', '1', '1', '3', '1', '1');
INSERT INTO `db_xml_wix`.`smestaj` (`id_smestaja`, `cena_jesen`, `cena_leto`, `cena_prolece`, `cena_zima`, `max_dana_za_otkazivanje`, `max_osoba`, `opis`, `vlasnik`, `adresa_id`, `kategorijasmestaja_id`, `koordinate_id`, `tipsmestaja_id`) VALUES ('2', '400', '450', '420', '400', '7', '3', 'aaa', '2', '2', '2', '2', '1');
INSERT INTO `db_xml_wix`.`smestaj` (`id_smestaja`, `cena_jesen`, `cena_leto`, `cena_prolece`, `cena_zima`, `max_dana_za_otkazivanje`, `max_osoba`, `opis`, `vlasnik`, `adresa_id`, `kategorijasmestaja_id`, `koordinate_id`, `tipsmestaja_id`) VALUES ('3', '700', '700', '710', '710', '15', '5', 'aaa', '2', '3', '5', '3', '2');
INSERT INTO `db_xml_wix`.`smestaj` (`id_smestaja`, `cena_jesen`, `cena_leto`, `cena_prolece`, `cena_zima`, `max_dana_za_otkazivanje`, `max_osoba`, `opis`, `vlasnik`, `adresa_id`, `kategorijasmestaja_id`, `koordinate_id`, `tipsmestaja_id`) VALUES ('4', '200', '200', '200', '200', '3', '4', 'aaa', '1', '4', '1', '4', '3');
INSERT INTO `db_xml_wix`.`smestaj` (`id_smestaja`, `cena_jesen`, `cena_leto`, `cena_prolece`, `cena_zima`, `max_dana_za_otkazivanje`, `max_osoba`, `opis`, `vlasnik`, `adresa_id`, `kategorijasmestaja_id`, `koordinate_id`, `tipsmestaja_id`) VALUES ('5', '577', '500', '500', '500', '4', '4', 'aaa', '3', '5', '3', '5', '3');

INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('1', '2');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('1', '3');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('2', '11');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('2', '8');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('2', '7');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('3', '3');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('3', '4');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('3', '5');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('4', '7');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('4', '10');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('4', '6');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('5', '5');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('5', '4');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `tipsmestaja_id`) VALUES ('5', '3');




#Globala
INSERT INTO `db_xml_wix`.`neaktiviran_agent` (`id_neaktiviranog_agenta`, `email`, `ime`, `prezime`, `poslovni_maticni_broj`) VALUES ('213', 'maricn007@gmail.com', 'Maric', 'Nikola', '104123');
INSERT INTO `db_xml_wix`.`neaktiviran_agent` (`id_neaktiviranog_agenta`, `email`, `ime`, `prezime`, `poslovni_maticni_broj`) VALUES ('122', 'maricnikola752@gmail.com', 'Nik', 'Mar', '109492');
INSERT INTO `db_xml_wix`.`neaktiviran_agent` (`id_neaktiviranog_agenta`, `email`, `ime`, `prezime`, `poslovni_maticni_broj`) VALUES ('1253', 'borisbibic1996@gmail.com', 'Boris', 'Bibic', '104413');

INSERT INTO `db_xml_wix`.`agent` (`id_agenta`, `datum_clanstva`, `email`, `ime`, `lozinka`, `poslovni_maticni_broj`, `prezime`) VALUES ('1', '2003-3-3', 'agent1@megatravel.com', 'Sale', '123', '5448974189', 'Stanic');
INSERT INTO `db_xml_wix`.`agent` (`id_agenta`, `datum_clanstva`, `email`, `ime`, `lozinka`, `poslovni_maticni_broj`, `prezime`) VALUES ('2', '2003-3-3', 'agent2@megatravel.com', 'Sale', '123', '5491876878', 'Staric');
INSERT INTO `db_xml_wix`.`agent` (`id_agenta`, `datum_clanstva`, `email`, `ime`, `lozinka`, `poslovni_maticni_broj`, `prezime`) VALUES ('3', '2003-3-3', 'agent3@megatravel.com', 'Sale', '123', '8979889892', 'Stapic');


#rezervacije
INSERT INTO `db_xml_wix`.`rezervacija` (`rezervacija_id`,`do_datuma`,`korisnik_id`,`od_datuma`,`smestaj_id`,`status_rezervacije`,`timestamp`,`update_timestamp`,`vlasnik_id`) VALUES (1,'2019-06-22 00:00:00',1,'2019-06-15 00:00:00',1,'KREIRANA','2019-06-29 16:47:01','2019-06-29 16:47:01',1);
INSERT INTO `db_xml_wix`.`rezervacija` (`rezervacija_id`,`do_datuma`,`korisnik_id`,`od_datuma`,`smestaj_id`,`status_rezervacije`,`timestamp`,`update_timestamp`,`vlasnik_id`) VALUES (2,'2019-11-22 00:00:00',1,'2019-11-01 00:00:00',3,'OTKAZANA','2019-06-29 16:48:20','2019-06-29 16:48:20',2);
INSERT INTO `db_xml_wix`.`rezervacija` (`rezervacija_id`,`do_datuma`,`korisnik_id`,`od_datuma`,`smestaj_id`,`status_rezervacije`,`timestamp`,`update_timestamp`,`vlasnik_id`) VALUES (3,'2019-05-30 00:00:00',3,'2019-05-09 00:00:00',2,'POTVRDJENA','2019-06-29 16:49:21','2019-06-29 16:49:21',2);
INSERT INTO `db_xml_wix`.`rezervacija` (`rezervacija_id`,`do_datuma`,`korisnik_id`,`od_datuma`,`smestaj_id`,`status_rezervacije`,`timestamp`,`update_timestamp`,`vlasnik_id`) VALUES (4,'2019-07-17 00:00:00',4,'2019-07-03 00:00:00',5,'NEIZVRSENA','2019-06-29 16:51:57','2019-06-29 16:51:57',3);
INSERT INTO `db_xml_wix`.`rezervacija` (`rezervacija_id`,`do_datuma`,`korisnik_id`,`od_datuma`,`smestaj_id`,`status_rezervacije`,`timestamp`,`update_timestamp`,`vlasnik_id`) VALUES (5,'2019-08-16 00:00:00',4,'2019-08-01 00:00:00',1,'OTKAZANA','2019-06-29 16:52:21','2019-06-29 16:52:21',1);
INSERT INTO `db_xml_wix`.`rezervacija` (`rezervacija_id`,`do_datuma`,`korisnik_id`,`od_datuma`,`smestaj_id`,`status_rezervacije`,`timestamp`,`update_timestamp`,`vlasnik_id`) VALUES (6,'2020-03-11 00:00:00',4,'2020-02-06 00:00:00',4,'POTVRDJENA','2019-06-29 16:53:12','2019-06-29 16:53:12',1);

INSERT INTO `db_xml_wix`.`samostalna_rezervacija` (`samostalna_rezervacija_id`,`do_datuma`,`od_datuma`,`smestaj_id`,`timestamp`,`vlasnik_id`) VALUES (1,'2019-07-17 00:00:00','2019-07-07 00:00:00',1,'2019-07-07 00:00:00',1);
INSERT INTO `db_xml_wix`.`samostalna_rezervacija` (`samostalna_rezervacija_id`,`do_datuma`,`od_datuma`,`smestaj_id`,`timestamp`,`vlasnik_id`) VALUES (2,'2019-10-10 00:00:00','2019-10-02 00:00:00',5,'2019-10-02 00:00:00',3);

#rejting
INSERT INTO `db_xml_wix`.`ocena` (`idOcena`,`idKorisnik`,`idRezervacija`,`idSmestaj`,`ocena`,`timestamp`) VALUES (1,4,6,4,5,NULL);
INSERT INTO `db_xml_wix`.`ocena` (`idOcena`,`idKorisnik`,`idRezervacija`,`idSmestaj`,`ocena`,`timestamp`) VALUES (2,3,3,2,2,NULL);

INSERT INTO `db_xml_wix`.`komentar` (`idKomentara`,`idKorisnika`,`idRezervacije`,`idSmestaja`,`komentar`,`status`,`timestamp`) VALUES (1,4,6,4,'Bas je dobar smestaj',1,NULL);
INSERT INTO `db_xml_wix`.`komentar` (`idKomentara`,`idKorisnika`,`idRezervacije`,`idSmestaja`,`komentar`,`status`,`timestamp`) VALUES (2,3,3,2,'Strasno los smestaj ni klima ne radi',0,NULL);

#poruka
INSERT INTO `db_xml_wix`.`poruka` (`id_poruke`,`datum_slanja`,`posiljalac`,`primalac`,`sadrzaj`,`status`,`tip_posiljaoca`,`tip_primaoca`) VALUES (1,'2019-06-29 17:33:45',1,1,'Hello agent 007','POSLATA','KORISNIK','AGENT');
INSERT INTO `db_xml_wix`.`poruka` (`id_poruke`,`datum_slanja`,`posiljalac`,`primalac`,`sadrzaj`,`status`,`tip_posiljaoca`,`tip_primaoca`) VALUES (2,'2019-06-30 01:55:55',1,1,'sadforreal','POSLATA','AGENT','KORISNIK');
INSERT INTO `db_xml_wix`.`poruka` (`id_poruke`,`datum_slanja`,`posiljalac`,`primalac`,`sadrzaj`,`status`,`tip_posiljaoca`,`tip_primaoca`) VALUES (3,'2019-06-30 01:58:45',1,1,'aj jos jednom','POSLATA','AGENT','KORISNIK');

#korisnik
INSERT INTO `db_xml_wix`.`korisnik` (`id_korisnik`,`blokiran`,`datum_clanstva`,`email`,`ime`,`lozinka`,`prezime`,`registrovan`,`rola`) VALUES (1,b'0','2002-02-02 00:00:00','marko@megatravel.com','Marko','marko','Katic',b'1','KORISNIK');
INSERT INTO `db_xml_wix`.`korisnik` (`id_korisnik`,`blokiran`,`datum_clanstva`,`email`,`ime`,`lozinka`,`prezime`,`registrovan`,`rola`) VALUES (2,b'1','2002-02-02 00:00:00','nikola@megatravel.com','Nikola','nikola','Maric',b'1','KORISNIK');
INSERT INTO `db_xml_wix`.`korisnik` (`id_korisnik`,`blokiran`,`datum_clanstva`,`email`,`ime`,`lozinka`,`prezime`,`registrovan`,`rola`) VALUES (3,b'0','2002-02-02 00:00:00','boris@megatravel.com','Boris','boris','Bibic',b'0','KORISNIK');
INSERT INTO `db_xml_wix`.`korisnik` (`id_korisnik`,`blokiran`,`datum_clanstva`,`email`,`ime`,`lozinka`,`prezime`,`registrovan`,`rola`) VALUES (4,b'0','2002-02-02 00:00:00','igor@megatravel.com','Igor','igor','Lovric',b'0','KORISNIK');
INSERT INTO `db_xml_wix`.`korisnik` (`id_korisnik`,`blokiran`,`datum_clanstva`,`email`,`ime`,`lozinka`,`prezime`,`registrovan`,`rola`) VALUES (5,b'1','2002-02-02 00:00:00','sale@megatravel.com','Sale','sale','Pare',b'1','KORISNIK');