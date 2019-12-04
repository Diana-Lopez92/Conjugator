create database frances;
use frances;
# Se crea la tabla para la conjugación en presente
create table presente(`idPresente` int not null auto_increment, `verbo` varchar(50), `Je` varchar(50), `Tu` varchar(50), 
`Il/Elle/On` varchar(50), `Nous` varchar(50), `Vous` varchar(50), `Ils/Elles` varchar(50), primary key(`idPresente`)); 

select * from presente;
INSERT INTO `presente` (`idPresente`, `verbo`, `Je`, `Tu`, `Il/Elle/On`, `Nous`, `Vous`, `Ils/Elles`) VALUES ('1', 'Être', 'suis', 'es', 'est', 'sommes', 'êtes', 'sont');