--create schema if not exists DB_CERTIFICATES;
insert into db_certificates.admin_model (aktiviran_nalog, email, ime, lozinka, prezime)
values (b'1', 'test@megatravel.com', 'test', 'root', 'bibic');