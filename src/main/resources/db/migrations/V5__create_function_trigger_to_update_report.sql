-- CREATE OR REPLACE FUNCTION update_report()
-- RETURNS trigger AS $update_report$
-- BEGIN
--
--     RETURN NEW;
-- END;
-- $update_report$ LANGUAGE plpgsql;
--
-- CREATE TRIGGER update_report
-- AFTER UPDATE OR DELETE ON registers.report
-- FOR EACH ROW
-- EXECUTE PROCEDURE update_report();