/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013-12-29 23:13:32                          */
/*==============================================================*/


drop table if exists T_Right;

drop table if exists T_Role;

drop table if exists T_RoleRight;

drop table if exists T_User;

drop table if exists T_UserRole;

/*==============================================================*/
/* Table: T_Right                                               */
/*==============================================================*/
create table T_Right
(
   rightId              int not null auto_increment,
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
   primary key (roleRightId),
   key AK_U_RoleRight (rightId, roleId)
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

alter table T_RoleRight add constraint FK_Reference_1 foreign key (rightId)
      references T_Right (rightId) on delete restrict on update restrict;

alter table T_RoleRight add constraint FK_Reference_2 foreign key (roleId)
      references T_Role (roleId) on delete restrict on update restrict;

alter table T_UserRole add constraint FK_Reference_3 foreign key (roleId)
      references T_Role (roleId) on delete restrict on update restrict;

alter table T_UserRole add constraint FK_Reference_4 foreign key (userId)
      references T_User (userId) on delete restrict on update restrict;

