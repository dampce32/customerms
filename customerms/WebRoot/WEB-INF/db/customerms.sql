/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014-01-12 22:28:27                          */
/*==============================================================*/


drop table if exists T_Customer;

drop table if exists T_CustomerRecharge;

drop table if exists T_CustomerType;

drop table if exists T_Goods;

drop table if exists T_Right;

drop table if exists T_Role;

drop table if exists T_RoleRight;

drop table if exists T_Sale;

drop table if exists T_SaleGoodsDetail;

drop table if exists T_SaleItem;

drop table if exists T_SaleItemDetail;

drop table if exists T_User;

drop table if exists T_UserRole;

/*==============================================================*/
/* Table: T_Customer                                            */
/*==============================================================*/
create table T_Customer
(
   customerId           int not null auto_increment,
   customerTypeId       int,
   customerCode         varchar(100) not null,
   customerName         varchar(100) not null,
   telephone            varchar(50),
   wechat               varchar(50),
   amount               float,
   primary key (customerId),
   key AK_A_customerCode (customerCode)
);

/*==============================================================*/
/* Table: T_CustomerRecharge                                    */
/*==============================================================*/
create table T_CustomerRecharge
(
   customerRechargeId   int not null auto_increment,
   customerId           int,
   rechargeDate         varchar(20) not null,
   amount               float not null,
   primary key (customerRechargeId)
);

/*==============================================================*/
/* Table: T_CustomerType                                        */
/*==============================================================*/
create table T_CustomerType
(
   customerTypeId       int not null auto_increment,
   customerTypeName     varchar(100) not null,
   discount             float not null,
   primary key (customerTypeId)
);

/*==============================================================*/
/* Table: T_Goods                                               */
/*==============================================================*/
create table T_Goods
(
   goodsId              int not null auto_increment,
   goodsName            varchar(100) not null,
   amount               float not null,
   isDiscount           int not null,
   primary key (goodsId)
);

/*==============================================================*/
/* Table: T_Right                                               */
/*==============================================================*/
create table T_Right
(
   rightId              int not null auto_increment,
   parentRightId        int,
   rightPKCode          varchar(50) not null,
   rightCode            varchar(100) not null,
   rightName            varchar(100) not null,
   rightUrl             varchar(300),
   array                int not null,
   isLeaf               int not null,
   rightKind            int not null,
   iconCls              varchar(50),
   status               int not null,
   primary key (rightId),
   key AK_U_RightCode (rightCode),
   key AK_U_RightPKCode (rightPKCode)
);

/*==============================================================*/
/* Table: T_Role                                                */
/*==============================================================*/
create table T_Role
(
   roleId               int not null auto_increment,
   roleCode             varchar(100) not null,
   roleName             varchar(100) not null,
   array                int not null,
   status               int not null,
   primary key (roleId),
   key AK_U_RoleCode (roleCode)
);

/*==============================================================*/
/* Table: T_RoleRight                                           */
/*==============================================================*/
create table T_RoleRight
(
   roleRightId          int not null auto_increment,
   rightId              int,
   roleId               int,
   status               int,
   primary key (roleRightId),
   key AK_U_RoleRight (rightId, roleId)
);

/*==============================================================*/
/* Table: T_Sale                                                */
/*==============================================================*/
create table T_Sale
(
   saleId               int not null auto_increment,
   customerId           int,
   userId               int,
   saleDate             varchar(20) not null,
   notIntoDiscountAmount float,
   intoDiscountAmount   float,
   discount             float,
   amount               float,
   primary key (saleId)
);

/*==============================================================*/
/* Table: T_SaleGoodsDetail                                     */
/*==============================================================*/
create table T_SaleGoodsDetail
(
   saleGoodsDetail      int not null auto_increment,
   saleId               int,
   goodsId              int,
   userId               int,
   amount               float,
   isDiscount           int,
   primary key (saleGoodsDetail)
);

/*==============================================================*/
/* Table: T_SaleItem                                            */
/*==============================================================*/
create table T_SaleItem
(
   saleItemId           int not null auto_increment,
   saleItemName         varchar(100) not null,
   amount               float not null,
   isDiscount           int not null,
   primary key (saleItemId)
);

/*==============================================================*/
/* Table: T_SaleItemDetail                                      */
/*==============================================================*/
create table T_SaleItemDetail
(
   saleItemDetailId     int not null auto_increment,
   saleId               int not null,
   saleItemId           int not null,
   userId               int,
   amount               float not null,
   isDiscount           int not null,
   primary key (saleItemDetailId)
);

/*==============================================================*/
/* Table: T_User                                                */
/*==============================================================*/
create table T_User
(
   userId               int not null auto_increment,
   userCode             varchar(100) not null,
   userName             varchar(100) not null,
   passwords            varchar(100) not null,
   status               int,
   primary key (userId),
   key AK_U_UserCode (userCode)
);

/*==============================================================*/
/* Table: T_UserRole                                            */
/*==============================================================*/
create table T_UserRole
(
   userRoleId           int not null auto_increment,
   roleId               int,
   userId               int,
   primary key (userRoleId),
   key AK_U_UserRole (roleId, userId)
);

alter table T_Customer add constraint FK_Reference_6 foreign key (customerTypeId)
      references T_CustomerType (customerTypeId) on delete restrict on update restrict;

alter table T_CustomerRecharge add constraint FK_Reference_15 foreign key (customerId)
      references T_Customer (customerId) on delete restrict on update restrict;

alter table T_Right add constraint FK_Reference_5 foreign key (parentRightId)
      references T_Right (rightId) on delete restrict on update restrict;

alter table T_RoleRight add constraint FK_Reference_1 foreign key (rightId)
      references T_Right (rightId) on delete restrict on update restrict;

alter table T_RoleRight add constraint FK_Reference_2 foreign key (roleId)
      references T_Role (roleId) on delete restrict on update restrict;

alter table T_Sale add constraint FK_Reference_14 foreign key (userId)
      references T_User (userId) on delete restrict on update restrict;

alter table T_Sale add constraint FK_Reference_7 foreign key (customerId)
      references T_Customer (customerId) on delete restrict on update restrict;

alter table T_SaleGoodsDetail add constraint FK_Reference_11 foreign key (saleId)
      references T_Sale (saleId) on delete restrict on update restrict;

alter table T_SaleGoodsDetail add constraint FK_Reference_12 foreign key (goodsId)
      references T_Goods (goodsId) on delete restrict on update restrict;

alter table T_SaleGoodsDetail add constraint FK_Reference_13 foreign key (userId)
      references T_User (userId) on delete restrict on update restrict;

alter table T_SaleItemDetail add constraint FK_Reference_10 foreign key (userId)
      references T_User (userId) on delete restrict on update restrict;

alter table T_SaleItemDetail add constraint FK_Reference_8 foreign key (saleId)
      references T_Sale (saleId) on delete restrict on update restrict;

alter table T_SaleItemDetail add constraint FK_Reference_9 foreign key (saleItemId)
      references T_SaleItem (saleItemId) on delete restrict on update restrict;

alter table T_UserRole add constraint FK_Reference_3 foreign key (roleId)
      references T_Role (roleId) on delete restrict on update restrict;

alter table T_UserRole add constraint FK_Reference_4 foreign key (userId)
      references T_User (userId) on delete restrict on update restrict;

