create table if not exists oauth_client_token (
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table if not exists oauth_client_details (
  client_id varchar(255) NOT NULL,
  resource_ids varchar(255) DEFAULT NULL,
  client_secret varchar(255) DEFAULT NULL,
  scope varchar(255) DEFAULT NULL,
  authorized_grant_types varchar(255) DEFAULT NULL,
  web_server_redirect_uri varchar(255) DEFAULT NULL,
  authorities varchar(255) DEFAULT NULL,
  access_token_validity integer(11) DEFAULT NULL,
  refresh_token_validity integer(11) DEFAULT NULL,
  additional_information varchar(255) DEFAULT NULL,
  autoapprove varchar(255) DEFAULT NULL
);

create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONGBLOB,
  refresh_token VARCHAR(255)
);

create table if not exists oauth_refresh_token(
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication LONGBLOB
);

create table if not exists authority (
  id  integer,
  authority varchar(255),
  primary key (id)
);

create table if not exists user (
  id  integer,
  enabled boolean not null,
  username varchar(255) not null,
  password varchar(255) not null,
  version integer,
  primary key (id)
);

create table if not exists users_authorities (
  user_id bigint not null,
  authority_id bigint not null
);

create table if not exists oauth_code (
  code VARCHAR(255), authentication VARBINARY(255)
);

create table if not exists oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt DATETIME,
    lastModifiedAt DATETIME
);

