INSERT INTO authority VALUES(1,'ROLE_OAUTH_ADMIN');
INSERT INTO authority VALUES(2,'ROLE_RESOURCE_ADMIN');
INSERT INTO authority VALUES(3,'ROLE_PRODUCT_ADMIN');
-- The encrypted password is `admin`
INSERT INTO user (id, version, username, password, enabled) VALUES(10,'1','james','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','1');
INSERT INTO user (id, version, username, password, enabled) VALUES(11,'1','alvaro','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','1');
INSERT INTO user (id, version, username, password, enabled) VALUES(12,'1','cesar','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','1');

INSERT INTO users_authorities (user_id, authority_id) VALUES (10,1);
INSERT INTO users_authorities (user_id, authority_id) VALUES (11,2);
INSERT INTO users_authorities (user_id, authority_id) VALUES (12,3);


-- The encrypted client_secret it `user`
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES ('clientId', NULL, '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', 'read,write', 'password,client_credentials,refresh_token', 'ROLE_PRODUCT_ADMIN', 3600, 0, NULL, 'true');

-- The encrypted client_secret it `user`
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('curl_client','product_api', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', 'read,write', 'client_credentials', 'ROLE_PRODUCT_ADMIN', 7200, 0, NULL, 'true');

--curl -s -u curl_client:user -X POST localhost:8588/authentication/oauth/token\?grant_type=client_credentials


