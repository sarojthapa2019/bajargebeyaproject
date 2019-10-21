/*
-- Query: SELECT * FROM xtTDyNlrK1.user
LIMIT 0, 1000

-- Date: 2019-10-21 03:48
*/
INSERT INTO `user` (`id`,`card_type`,`csv`,`expiry_date`,`number`,`bill_city`,`bill_country`,`bill_state`,`bill_street`,`bill_zip`,`email`,`first_name`,`last_name`,`mail_city`,`mail_country`,`mail_state`,`mail_street`,`mail_zip`,`password`,`role_id`) VALUES (1,'Credit','225','2019-10-21','12345678','Fairfield','USA','IOWA','1000N 4th Street','52557','xyz@mum.edu','Seller','user','Fairfield','USA','IOWA','1000N 4th Street','52557','',1);
INSERT INTO `user` (`id`,`card_type`,`csv`,`expiry_date`,`number`,`bill_city`,`bill_country`,`bill_state`,`bill_street`,`bill_zip`,`email`,`first_name`,`last_name`,`mail_city`,`mail_country`,`mail_state`,`mail_street`,`mail_zip`,`password`,`role_id`) VALUES (2,'Credit','225','2019-10-21','12345678','Fairfield','USA','IOWA','1000N 4th Street','52557','abc@mum.edu','Admin','user','Fairfield','USA','IOWA','1000N 4th Street','52557','admin',2);
INSERT INTO `user` (`id`,`card_type`,`csv`,`expiry_date`,`number`,`bill_city`,`bill_country`,`bill_state`,`bill_street`,`bill_zip`,`email`,`first_name`,`last_name`,`mail_city`,`mail_country`,`mail_state`,`mail_street`,`mail_zip`,`password`,`role_id`) VALUES (3,'Credit','225','2019-10-21','12345678','Fairfield','USA','IOWA','1000N 4th Street','52557','pqr@mum.edu','Buyer','user','Fairfield','USA','IOWA','1000N 4th Street','52557','',3);

/*
-- Query: SELECT * FROM xtTDyNlrK1.role
LIMIT 0, 1000

-- Date: 2019-10-21 03:56
*/
INSERT INTO `role` (`id`,`role`) VALUES (1,'Seller');
INSERT INTO `role` (`id`,`role`) VALUES (2,'admin');
INSERT INTO `role` (`id`,`role`) VALUES (3,'Buyer');

