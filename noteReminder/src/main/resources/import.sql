insert into users(ID_USER,ACTIVED,DROIT,MATRICULE,USERNAME,PASSWORD) values(1,1,'','','diallo','$2a$10$cClkMekpRFN6eg3kFavRRO13xWWbIhfnFs/14/VJ7HWDo69zL0F/6');
insert into users(ID_USER,ACTIVED,DROIT,MATRICULE,USERNAME,PASSWORD) values(2,1,'','','user','$2a$10$cClkMekpRFN6eg3kFavRRO13xWWbIhfnFs/14/VJ7HWDo69zL0F/6');
insert into users(ID_USER,ACTIVED,DROIT,MATRICULE,USERNAME,PASSWORD) values(3,1,'','','admin','$2a$10$cClkMekpRFN6eg3kFavRRO13xWWbIhfnFs/14/VJ7HWDo69zL0F/6');

insert into note (ID_NOTE ,COULEUR,ECHEANCE,ORDRE ,TEXTE,ID_USER )  values (1,	'[string]',	'[string]',	0,	'[diallo]',1);
insert into note (ID_NOTE ,COULEUR,ECHEANCE,ORDRE ,TEXTE,ID_USER )  values (2,	'[string]',	'[string]',	0,	'[diallo2]',	1);
insert into note (ID_NOTE ,COULEUR,ECHEANCE,ORDRE ,TEXTE,ID_USER )  values (3	,'[string]'	,'[string]',	0,	'[user]'	,2);