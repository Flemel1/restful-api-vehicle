INSERT INTO vehicle.roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO vehicle.roles (name) VALUES ('ROLE_USER');

INSERT INTO vehicle.users (email,password,created_at,updated_at) VALUES ('admin@example.com','$2a$10$LdyuNxwwoDX5ImzDv99/JOMQEAPV1097kvRxcK19iA/OVBsFMAyem',NULL,NULL);
INSERT INTO vehicle.users (email,password,created_at,updated_at) VALUES ('user@example.com','$2a$10$LdyuNxwwoDX5ImzDv99/JOMQEAPV1097kvRxcK19iA/OVBsFMAyem',NULL,NULL);

INSERT INTO vehicle.user_roles (user_id,role_id) VALUES (1,1);
INSERT INTO vehicle.user_roles (user_id,role_id) VALUES (2,2);

INSERT INTO vehicle.vehicle_years (created_at,updated_at,`year`) VALUES (NULL,NULL,'2015');
INSERT INTO vehicle.vehicle_years (created_at,updated_at,`year`) VALUES (NULL,NULL,'2016');
INSERT INTO vehicle.vehicle_years (created_at,updated_at,`year`) VALUES (NULL,NULL,'2017');
INSERT INTO vehicle.vehicle_years (created_at,updated_at,`year`) VALUES (NULL,NULL,'2018');
INSERT INTO vehicle.vehicle_years (created_at,updated_at,`year`) VALUES (NULL,NULL,'2019');
INSERT INTO vehicle.vehicle_years (created_at,updated_at,`year`) VALUES (NULL,NULL,'2020');
INSERT INTO vehicle.vehicle_years (created_at,updated_at,`year`) VALUES (NULL,NULL,'2021');
INSERT INTO vehicle.vehicle_years (created_at,updated_at,`year`) VALUES (NULL,NULL,'2022');

INSERT INTO vehicle.vehicle_brands (created_at,name,updated_at) VALUES (NULL,'toyota',NULL);
INSERT INTO vehicle.vehicle_brands (created_at,name,updated_at) VALUES (NULL,'honda',NULL);
INSERT INTO vehicle.vehicle_brands (created_at,name,updated_at) VALUES (NULL,'yamaha',NULL);
INSERT INTO vehicle.vehicle_brands (created_at,name,updated_at) VALUES (NULL,'suzuki',NULL);
INSERT INTO vehicle.vehicle_brands (created_at,name,updated_at) VALUES (NULL,'hyundai',NULL);

INSERT INTO vehicle.vehicle_types (brand_id,created_at,name,updated_at) VALUES (1,NULL,'XA001',NULL);
INSERT INTO vehicle.vehicle_types (brand_id,created_at,name,updated_at) VALUES (1,NULL,'XA002',NULL);
INSERT INTO vehicle.vehicle_types (brand_id,created_at,name,updated_at) VALUES (1,NULL,'XA003',NULL);
INSERT INTO vehicle.vehicle_types (brand_id,created_at,name,updated_at) VALUES (1,NULL,'XA004',NULL);
INSERT INTO vehicle.vehicle_types (brand_id,created_at,name,updated_at) VALUES (2,NULL,'BA001',NULL);
INSERT INTO vehicle.vehicle_types (brand_id,created_at,name,updated_at) VALUES (2,NULL,'BA002',NULL);
INSERT INTO vehicle.vehicle_types (brand_id,created_at,name,updated_at) VALUES (2,NULL,'BA003',NULL);
INSERT INTO vehicle.vehicle_types (brand_id,created_at,name,updated_at) VALUES (3,NULL,'ZA001',NULL);
INSERT INTO vehicle.vehicle_types (brand_id,created_at,name,updated_at) VALUES (3,NULL,'ZA002',NULL);
INSERT INTO vehicle.vehicle_types (brand_id,created_at,name,updated_at) VALUES (3,NULL,'ZA003',NULL);

INSERT INTO vehicle.vehicle_models (created_at,name,type_id,updated_at) VALUES (NULL,'ZB001',1,NULL);
INSERT INTO vehicle.vehicle_models (created_at,name,type_id,updated_at) VALUES (NULL,'ZB002',1,NULL);
INSERT INTO vehicle.vehicle_models (created_at,name,type_id,updated_at) VALUES (NULL,'ZB003',1,NULL);
INSERT INTO vehicle.vehicle_models (created_at,name,type_id,updated_at) VALUES (NULL,'ZB004',1,NULL);
INSERT INTO vehicle.vehicle_models (created_at,name,type_id,updated_at) VALUES (NULL,'XB001',2,NULL);
INSERT INTO vehicle.vehicle_models (created_at,name,type_id,updated_at) VALUES (NULL,'XB002',2,NULL);
INSERT INTO vehicle.vehicle_models (created_at,name,type_id,updated_at) VALUES (NULL,'XB003',2,NULL);

INSERT INTO vehicle.pricelist (created_at,year_id,model_id,updated_at) VALUES (NULL,1,1,NULL);
INSERT INTO vehicle.pricelist (created_at,year_id,model_id,updated_at) VALUES (NULL,2,1,NULL);
INSERT INTO vehicle.pricelist (created_at,year_id,model_id,updated_at) VALUES (NULL,3,1,NULL);
INSERT INTO vehicle.pricelist (created_at,year_id,model_id,updated_at) VALUES (NULL,4,1,NULL);
INSERT INTO vehicle.pricelist (created_at,year_id,model_id,updated_at) VALUES (NULL,1,2,NULL);
INSERT INTO vehicle.pricelist (created_at,year_id,model_id,updated_at) VALUES (NULL,2,2,NULL);
INSERT INTO vehicle.pricelist (created_at,year_id,model_id,updated_at) VALUES (NULL,3,2,NULL);
INSERT INTO vehicle.pricelist (created_at,year_id,model_id,updated_at) VALUES (NULL,4,2,NULL);

