create table cliente (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 tipo_cliente varchar(20) not null,
 primary key (id)
);

create table producto (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 aplica_iva TINYINT not null,
 valor DECIMAL(10,2) not null,
 primary key (id)
);

create table factura (
 id int(11) not null auto_increment,
 id_cliente int(11) not null,
 valor_total DECIMAL(10,2) not null,
 estado varchar(20) not null,
 primary key (id)
);

create table producto_facturar (
 id int(11) not null auto_increment,
 id_factura int(11) not null,
 id_producto int(11) not null,
 cantidad int(11) not null,
 primary key (id)
);

create table mascota (
 id int(11) not null auto_increment,
 codigo_mascota varchar(8) not null,
 nombre varchar(100) not null,
 tipo_mascota int(11) not null,
 primary key (id)
);

create table cupon_descuento (
 id int(11) not null auto_increment,
 codigo_cupon varchar(50) not null,
 id_mascota int(11) not null,
 valor_descuento double precision not null,
 fecha_vigencia date not null,
 primary key (id)
);

create table servicio (
 id int(11) not null auto_increment,
 descripcion text not null,
 valor double precision not null,
 primary key (id)
);

create table tratamiento (
 id int(11) not null auto_increment,
 codigo_tratamiento varchar(8) not null,
 id_mascota int(11) not null,
 fecha_inicio date not null,
 fecha_fin date not null,
 tipo_tratamiento int(11) not null,
 id_servicio int(11) not null,
 primary key (id)
);

ALTER TABLE factura
ADD CONSTRAINT cliente_fk
  FOREIGN KEY (id_cliente)
  REFERENCES cliente (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE producto_facturar
ADD CONSTRAINT producto_fk
  FOREIGN KEY (id_producto)
  REFERENCES producto (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE producto_facturar
ADD CONSTRAINT factura_fk
  FOREIGN KEY (id_factura)
  REFERENCES factura (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE cupon_descuento
ADD CONSTRAINT cupo_masc_fk
  FOREIGN KEY (id_mascota)
  REFERENCES mascota (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE tratamiento
ADD CONSTRAINT trat_masc_fk
  FOREIGN KEY (id_mascota)
  REFERENCES mascota (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
ALTER TABLE tratamiento
ADD CONSTRAINT servicio_fk
  FOREIGN KEY (id_servicio)
  REFERENCES servicio (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;