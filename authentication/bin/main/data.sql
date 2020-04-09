INSERT INTO authority  VALUES(1,'ROLE_OAUTH_ADMIN');
INSERT INTO authority VALUES(2,'ROLE_RESOURCE_ADMIN');
INSERT INTO authority VALUES(3,'ROLE_PRODUCT_ADMIN');
-- The encrypted password is `admin`
INSERT INTO credentials (id, version, name, password, enabled) VALUES(1,'1','james','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','1');
INSERT INTO credentials (id, version, name, password, enabled) VALUES(2,'1','alvaro','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','1');
INSERT INTO credentials (id, version, name, password, enabled) VALUES(3,'1','cesar','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','1');
INSERT INTO credentials_authorities VALUES (1,1);
INSERT INTO credentials_authorities VALUES (2,2);
INSERT INTO credentials_authorities VALUES (3,3);


-- The encrypted client_secret it `user`
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES ('clientId', 'product_api', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', 'read,write', 'password,client_credentials,refresh_token', 'ROLE_PRODUCT_ADMIN', 300, 0, NULL, 'true');

-- The encrypted client_secret it `user`
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('curl_client','product_api', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', 'read,write', 'client_credentials', 'ROLE_PRODUCT_ADMIN', 7200, 0, NULL, 'true');

--curl -s -u curl_client:user -X POST localhost:8588/authentication/oauth/token\?grant_type=client_credentials


