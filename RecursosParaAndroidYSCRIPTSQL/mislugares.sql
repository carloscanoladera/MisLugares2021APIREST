CREATE DATABASE IF NOT EXISTS `mislugares` ;
ALTER DATABASE `mislugares` CHARACTER SET utf8 COLLATE utf8_general_ci;
use mislugares;
CREATE USER `lugares`@'%' IDENTIFIED BY 'lugares';
GRANT ALL PRIVILEGES ON mislugares.* TO 'lugares'@'%' IDENTIFIED BY 'lugares';
DROP TABLE IF EXISTS `lugares`;
CREATE TABLE lugares (
_id INT(10) PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(100),
direccion VARCHAR(200),
longitud FLOAT,
latitud REAL,
tipo VARCHAR(50),
foto VARCHAR(200),
telefono VARCHAR(20),
url VARCHAR(200),
comentario TEXT,
fecha BIGINT,
valoracion FLOAT);

INSERT INTO lugares VALUES (1, 'Escuela Politécnica Superior de Gandía', 'C/ Paranimf, 1 46730 Gandia (SPAIN)', -0.166093, 38.995656, 8 , '', 962849300, 'http://www.epsg.upv.es', 'Uno de los mejores lugares para formarse.', '11/05/2022' , 3.0);


INSERT INTO lugares VALUES (2, 'Al de siempre', 'P.Industrial Junto Molí Nou - 46722, Benifla (Valencia)', -0.190642, 38.925857, 3 , '', 636472405, '', 'No te pierdas el arroz en calabaza.', '11/05/2022' , 3.0);
     

INSERT INTO lugares VALUES (3, 'androidcurso.com', 'ciberespacio', 0.0, 0.0, 8 , '', 962849300 , 'http://androidcurso.com','Amplia tus conocimientos sobre Android.', '11/05/2022' , 5.0);
      

INSERT INTO lugares VALUES (4,'Barranco del Infierno', 'Vía Verde del río Serpis. Villalonga (Valencia)', -0.295058, 38.867180, 10 , '', 0, 'http://sosegaos.blogspot.com.es/2009/02/lorcha-villalonga-via-verde-del-rio.html', 'Espectacular ruta para bici o andar','11/05/2022', 4.0);
INSERT INTO lugares VALUES (5, 'La Vital', 'Avda. La Vital,0 46701 Gandia (Valencia)',-0.1720092,38.9705949,
                ' 7 +', '', 962881070,  'http://www.lavital.es', 'El típico centro comercial',
		'11/05/2022', 2.0);




