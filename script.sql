CREATE VIEW CONGRESDETAILVIEW AS
SELECT C.IDCONGRES, C.NOMCONGRES, C.DATECONGRES,
		D.*
FROM CONGRES C
LEFT JOIN LISTDETAILCONGRES D
ON C.IDCONGRES = D.IDCONGRES;

CREATE VIEW SOMMEPAYECOTISATION AS
SELECT M.*,
		C.*,
      SUM(P.MONTANT) AS MONTANTPAYE

FROM COTISATION C
JOIN PAIEMENTCOTISATION P
ON P.IDCOTISATION = C.IDCOTISATION
JOIN MEMBRE M
ON M.IDMEMBRE = P.IDMEMBRE
GROUP BY C.IDCOTISATION, M.IDMEMBRE;

CREATE VIEW SOMMEPAYECONGRES AS
SELECT M.*,
		C.IDCONGRES, C.NOMCONGRES, C.IDDETAILCONGRES,C.DESIGNATION, C.MONTANT,
      SUM(P.MONTANT) AS MONTANTPAYE
FROM CONGRESDETAILVIEW C
JOIN PAIEMENTCONGRES P
ON P.IDDETAILCONGRES = C.IDDETAILCONGRES
JOIN MEMBRE M
ON M.IDMEMBRE = P.IDMEMBRE
GROUP BY C.IDCONGRES,C.NOMCONGRES,C.IDDETAILCONGRES, C.DESIGNATION, C.MONTANT, M.IDMEMBRE;

CREATE VIEW DETAILPAIEMENTCOTISATION AS
SELECT M.*,
		C.*,
		P.IDPAIEMENTCOTISATION,P.DATEPAIEMENT,P.MONTANT AS MONTANTPAYE
FROM COTISATION C
LEFT JOIN PAIEMENTCOTISATION P
ON P.IDCOTISATION = C.IDCOTISATION
LEFT JOIN MEMBRE M
ON M.IDMEMBRE = P.IDMEMBRE;

CREATE VIEW DETAILPAIEMENTCONGRES AS
SELECT M.*,
		C.IDCONGRES, C.NOMCONGRES, C.IDDETAILCONGRES,C.DESIGNATION, C.MONTANT,
      P.IDPAIEMENTCONGRES,P.DATEPAIEMENT,P.MONTANT AS MONTANTPAYE
FROM CONGRESDETAILVIEW C
LEFT JOIN PAIEMENTCONGRES P
ON P.IDDETAILCONGRES = C.IDDETAILCONGRES
LEFT JOIN MEMBRE M
ON M.IDMEMBRE = P.IDMEMBRE;

CREATE VIEW LISTCOTISATION AS
SELECT COUNT(DISTINCT M.IDMEMBRE) AS CONTRIBUABLE,
		C.*,
		SUM(P.MONTANT) AS MONTANTPAYE
FROM COTISATION C
LEFT JOIN PAIEMENTCOTISATION P
ON P.IDCOTISATION = C.IDCOTISATION
LEFT JOIN MEMBRE M
ON M.IDMEMBRE = P.IDMEMBRE
GROUP BY C.ANNEECOTISATION, C.MONTANTOBJECTIF, C.IDCOTISATION;

CREATE VIEW LISTDETAILCONGRES AS
SELECT COUNT(DISTINCT M.IDMEMBRE) AS CONTRIBUABLE,
		C.*,
		SUM(P.MONTANT) AS MONTANTPAYE
FROM DETAILCONGRES C
LEFT JOIN PAIEMENTCONGRES P
ON P.IDDETAILCONGRES = C.IDDETAILCONGRES
LEFT JOIN MEMBRE M
ON M.IDMEMBRE = P.IDMEMBRE
GROUP BY C.IDDETAILCONGRES,C.DESIGNATION,C.MONTANT;

SELECT IDMEMBRE,IDCOTISATION,SUM(MONTANT),extract(year from DATEPAIEMENT)  
FROM PAIEMENTCOTISATION 
GROUP BY IDCOTISATION, IDMEMBRE, extract(year from DATEPAIEMENT)