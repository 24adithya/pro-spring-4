select c.FIRST_NAME,c.LAST_NAME,c.BIRTH_DATE, cd.TEL_TYPE,cd.TEL_NUMBER from contact c left join contact_tel_detail cd on c.id = cd.contact_id;
select c.id as contact_id, c.FIRST_NAME,c.LAST_NAME,c.BIRTH_DATE, cd.TEL_TYPE,cd.TEL_NUMBER, cd.id as contact_tel_id from contact c left join contact_tel_detail cd on c.id = cd.contact_id;
select * from contact;
select * from contact_tel_detail;

desc contact;
desc contact_tel_detail;

DELIMITER //
CREATE FUNCTION getFirstNameById(in_id INT)
RETURNS VARCHAR(60)
BEGIN
RETURN (SELECT first_name FROM contact WHERE id = in_id);
END //
DELIMITER ;