/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     24/02/2017 17:18:48                          */
/*==============================================================*/


drop index CONGRES_PK;

drop table CONGRES;

drop index COTISATION_PK;

drop table COTISATION;

drop index ASSOCIATION_6_FK;

drop index DETAILCONGRES_PK;

drop table DETAILCONGRES;

drop index MEMBRE_PK;

drop table MEMBRE;

drop index PAIEMENTCONGRES_FK;

drop index MEMBRE_PAIECONGRES_FK;

drop index PAIEMENTCONGRES_PK;

drop table PAIEMENTCONGRES;

drop index ASSOCIATION_7_FK;

drop index MEMBRE_PAIECOTISATION_FK;

drop index PAIEMENTCOTISATION_PK;

drop table PAIEMENTCOTISATION;

/*==============================================================*/
/* Table: CONGRES                                               */
/*==============================================================*/
create table CONGRES (
   IDCONGRES            SERIAL               not null,
   NOMCONGRES           VARCHAR(200)         null,
   DATECONGRES          DATE                 null,
   constraint PK_CONGRES primary key (IDCONGRES)
);

/*==============================================================*/
/* Index: CONGRES_PK                                            */
/*==============================================================*/
create unique index CONGRES_PK on CONGRES (
IDCONGRES
);

/*==============================================================*/
/* Table: COTISATION                                            */
/*==============================================================*/
create table COTISATION (
   IDCOTISATION         SERIAL               not null,
   MONTANT              FLOAT8               null,
   ANNEECOTISATION      NUMERIC              null,
   constraint PK_COTISATION primary key (IDCOTISATION)
);

/*==============================================================*/
/* Index: COTISATION_PK                                         */
/*==============================================================*/
create unique index COTISATION_PK on COTISATION (
IDCOTISATION
);

/*==============================================================*/
/* Table: DETAILCONGRES                                         */
/*==============================================================*/
create table DETAILCONGRES (
   IDDETAILCONGRES      SERIAL               not null,
   IDCONGRES            INT4                 not null,
   DESIGNATION          VARCHAR(200)         null,
   MONTANT              FLOAT8               null,
   constraint PK_DETAILCONGRES primary key (IDDETAILCONGRES)
);

/*==============================================================*/
/* Index: DETAILCONGRES_PK                                      */
/*==============================================================*/
create unique index DETAILCONGRES_PK on DETAILCONGRES (
IDDETAILCONGRES
);

/*==============================================================*/
/* Index: ASSOCIATION_6_FK                                      */
/*==============================================================*/
create  index ASSOCIATION_6_FK on DETAILCONGRES (
IDCONGRES
);

/*==============================================================*/
/* Table: MEMBRE                                                */
/*==============================================================*/
create table MEMBRE (
   IDMEMBRE             SERIAL               not null,
   NOM                  VARCHAR(100)         null,
   PRENOM               VARCHAR(100)         null,
   DATENAISSANCE        DATE                 null,
   SEXE                 VARCHAR(10)          null,
   EMAIL                VARCHAR(200)         null,
   TELEPHONE            VARCHAR(100)         null,
   ADRESSE              VARCHAR(100)         null,
   PROFESSION           VARCHAR(100)         null,
   CAPACITE             VARCHAR(500)         null,
   constraint PK_MEMBRE primary key (IDMEMBRE)
);

/*==============================================================*/
/* Index: MEMBRE_PK                                             */
/*==============================================================*/
create unique index MEMBRE_PK on MEMBRE (
IDMEMBRE
);

/*==============================================================*/
/* Table: PAIEMENTCONGRES                                       */
/*==============================================================*/
create table PAIEMENTCONGRES (
   IDPAIEMENTCONGRES    SERIAL               not null,
   IDDETAILCONGRES      INT4                 not null,
   IDMEMBRE             INT4                 not null,
   MONTANT              FLOAT8               null,
   DATEPAIEMENT         DATE                 null,
   constraint PK_PAIEMENTCONGRES primary key (IDPAIEMENTCONGRES)
);

/*==============================================================*/
/* Index: PAIEMENTCONGRES_PK                                    */
/*==============================================================*/
create unique index PAIEMENTCONGRES_PK on PAIEMENTCONGRES (
IDPAIEMENTCONGRES
);

/*==============================================================*/
/* Index: MEMBRE_PAIECONGRES_FK                                 */
/*==============================================================*/
create  index MEMBRE_PAIECONGRES_FK on PAIEMENTCONGRES (
IDMEMBRE
);

/*==============================================================*/
/* Index: PAIEMENTCONGRES_FK                                    */
/*==============================================================*/
create  index PAIEMENTCONGRES_FK on PAIEMENTCONGRES (
IDDETAILCONGRES
);

/*==============================================================*/
/* Table: PAIEMENTCOTISATION                                    */
/*==============================================================*/
create table PAIEMENTCOTISATION (
   IDPAIEMENTCOTISATION SERIAL               not null,
   IDMEMBRE             INT4                 not null,
   IDCOTISATION         INT4                 not null,
   DATEPAIEMENT         DATE                 null,
   MONTANT              FLOAT8               null,
   constraint PK_PAIEMENTCOTISATION primary key (IDPAIEMENTCOTISATION)
);

/*==============================================================*/
/* Index: PAIEMENTCOTISATION_PK                                 */
/*==============================================================*/
create unique index PAIEMENTCOTISATION_PK on PAIEMENTCOTISATION (
IDPAIEMENTCOTISATION
);

/*==============================================================*/
/* Index: MEMBRE_PAIECOTISATION_FK                              */
/*==============================================================*/
create  index MEMBRE_PAIECOTISATION_FK on PAIEMENTCOTISATION (
IDMEMBRE
);

/*==============================================================*/
/* Index: ASSOCIATION_7_FK                                      */
/*==============================================================*/
create  index ASSOCIATION_7_FK on PAIEMENTCOTISATION (
IDCOTISATION
);

alter table DETAILCONGRES
   add constraint FK_DETAILCO_ASSOCIATI_CONGRES foreign key (IDCONGRES)
      references CONGRES (IDCONGRES)
      on delete restrict on update restrict;

alter table PAIEMENTCONGRES
   add constraint FK_PAIEMENT_MEMBRE_PA_MEMBRE foreign key (IDMEMBRE)
      references MEMBRE (IDMEMBRE)
      on delete restrict on update restrict;

alter table PAIEMENTCONGRES
   add constraint FK_PAIEMENT_PAIEMENTC_DETAILCO foreign key (IDDETAILCONGRES)
      references DETAILCONGRES (IDDETAILCONGRES)
      on delete restrict on update restrict;

alter table PAIEMENTCOTISATION
   add constraint FK_PAIEMENT_ASSOCIATI_COTISATI foreign key (IDCOTISATION)
      references COTISATION (IDCOTISATION)
      on delete restrict on update restrict;

alter table PAIEMENTCOTISATION
   add constraint FK_PAIEMENT_MEMBRE_PA_MEMBRE foreign key (IDMEMBRE)
      references MEMBRE (IDMEMBRE)
      on delete restrict on update restrict;

