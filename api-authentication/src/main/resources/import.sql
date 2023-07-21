INSERT INTO api (name, description) VALUES ('items', 'Service to provide all items of store');
INSERT INTO api (name, description) VALUES ('products', 'Service to provide all products at stock');

INSERT INTO endpoint (path, description, api_id) VALUES ('/api/get-list', 'End point to retrieve', 1);
INSERT INTO endpoint (path, description, api_id) VALUES ('/api/get/', 'End point to retrieve', 1);
INSERT INTO endpoint (path, description, api_id) VALUES ('/api/get-list', 'End point to retrieve', 2);
INSERT INTO endpoint (path, description, api_id) VALUES ('/api/get/', 'End point to retrieve', 2);

INSERT INTO owner_app (name, description) VALUES ('salazar-co', 'Software mexican-spain company');

INSERT INTO owner_app_api VALUES(1, 1);
INSERT INTO owner_app_api VALUES(1, 2);