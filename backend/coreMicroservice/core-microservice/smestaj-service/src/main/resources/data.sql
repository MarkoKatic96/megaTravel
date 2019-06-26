INSERT INTO `db_xml_smestaj`.`tip_smestaja` (`tip_smestaja_id`, `naziv_tipa_smestaja`) VALUES ('1', 'Hotel');
INSERT INTO `db_xml_smestaj`.`tip_smestaja` (`tip_smestaja_id`, `naziv_tipa_smestaja`) VALUES ('2', 'Bed&Breakfast');
INSERT INTO `db_xml_smestaj`.`tip_smestaja` (`tip_smestaja_id`, `naziv_tipa_smestaja`) VALUES ('3', 'Apartman');

INSERT INTO `db_xml_smestaj`.`kategorija_smestaja` (`id`, `naziv`) VALUES ('1', '1.');
INSERT INTO `db_xml_smestaj`.`kategorija_smestaja` (`id`, `naziv`) VALUES ('2', '2.');
INSERT INTO `db_xml_smestaj`.`kategorija_smestaja` (`id`, `naziv`) VALUES ('3', '3.');
INSERT INTO `db_xml_smestaj`.`kategorija_smestaja` (`id`, `naziv`) VALUES ('4', '4.');
INSERT INTO `db_xml_smestaj`.`kategorija_smestaja` (`id`, `naziv`) VALUES ('5', '5.');

INSERT INTO `db_xml_smestaj`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('1', 'Parking');
INSERT INTO `db_xml_smestaj`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('2', 'WiFi');
INSERT INTO `db_xml_smestaj`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('3', 'Doručak');
INSERT INTO `db_xml_smestaj`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('4', 'Polu pansion');
INSERT INTO `db_xml_smestaj`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('5', 'Pansion,');
INSERT INTO `db_xml_smestaj`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('6', 'All Inclusive,');
INSERT INTO `db_xml_smestaj`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('7', 'Inclusive,');
INSERT INTO `db_xml_smestaj`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('8', 'Dozvoljeni kućni ljubimci');
INSERT INTO `db_xml_smestaj`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('9', 'TV');
INSERT INTO `db_xml_smestaj`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('10', 'Mini kuhinja/kuhinja');
INSERT INTO `db_xml_smestaj`.`dodatne_usluge` (`id_dodatne_usluge`, `naziv_dodatne_usluge`) VALUES ('11', 'Privatno kupatilo');


INSERT INTO `db_xml_smestaj`.`tkoordinate` (`koordinate_id`, `latitude`, `longitude`, `smestaj_id_smestaja`) VALUES ('1', '11', '11', '1');

INSERT INTO `db_xml_smestaj`.`tadresa` (`adresa_id`, `broj`, `grad`, `ulica`, `smestaj_id_smestaja`) VALUES ('1', '8', 'Novi Sad', 'Dositejeva', '1');

INSERT INTO `db_xml_smestaj`.`smestaj` (`id_smestaja`, `cena_jesen`, `cena_leto`, `cena_prolece`, `cena_zima`, `max_dana_za_otkazivanje`, `max_osoba`, `opis`, `vlasnik`, `adresa_id`, `kategorijasmestaja_id`, `koordinate_id`, `tipsmestaja_id`) VALUES ('1', '5', '5', '5', '5', '5', '5', 'aaa', '1', '1', '1', '1', '1');