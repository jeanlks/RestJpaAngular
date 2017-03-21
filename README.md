# RestJpaAngular
Simple Application using JPA and an AngularJS frontend consuming it.


# SQL Scripts for MySql
CREATE TABLE `AMIZADE` (   `AMIZADEID` int(6) NOT NULL AUTO_INCREMENT,   `ID1` int(6) NOT NULL,   `ID2` int(6) NOT NULL,   PRIMARY KEY (`AMIZADEID`) ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `pessoa` (
  `pessoaId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `empresa` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pessoaId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
