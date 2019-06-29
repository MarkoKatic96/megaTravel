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

INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('1', '2');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('1', '3');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('2', '11');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('2', '8');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('2', '7');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('3', '3');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('3', '4');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('3', '5');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('4', '7');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('4', '10');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('4', '6');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('5', '5');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('5', '4');
INSERT INTO `db_xml_wix`.`smestaj_usluge` (`smestaj_id`, `usluga_id`) VALUES ('5', '3');
