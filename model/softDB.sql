--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: address; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE address (
    idaddress integer NOT NULL,
    street character varying NOT NULL,
    city character varying NOT NULL,
    postal_code character varying,
    country character varying
);


ALTER TABLE address OWNER TO postgres;

--
-- Name: address_idaddress_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE address_idaddress_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE address_idaddress_seq OWNER TO postgres;

--
-- Name: address_idaddress_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE address_idaddress_seq OWNED BY address.idaddress;


--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE person (
    idperson integer NOT NULL,
    name character varying NOT NULL,
    phone_number numeric(15,0),
    email_address character varying,
    idaddress integer NOT NULL
);


ALTER TABLE person OWNER TO postgres;

--
-- Name: person_idperson_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE person_idperson_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE person_idperson_seq OWNER TO postgres;

--
-- Name: person_idperson_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE person_idperson_seq OWNED BY person.idperson;


--
-- Name: professor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE professor (
    idperson integer NOT NULL,
    salary double precision NOT NULL
);


ALTER TABLE professor OWNER TO postgres;

--
-- Name: student; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE student (
    idperson integer NOT NULL,
    student_number numeric(15,0) NOT NULL,
    average_mark integer
);


ALTER TABLE student OWNER TO postgres;

--
-- Name: idaddress; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY address ALTER COLUMN idaddress SET DEFAULT nextval('address_idaddress_seq'::regclass);


--
-- Name: idperson; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person ALTER COLUMN idperson SET DEFAULT nextval('person_idperson_seq'::regclass);


--
-- Name: address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pkey PRIMARY KEY (idaddress);


--
-- Name: person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (idperson);


--
-- Name: professor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY professor
    ADD CONSTRAINT professor_pkey PRIMARY KEY (idperson);


--
-- Name: student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY student
    ADD CONSTRAINT student_pkey PRIMARY KEY (idperson);


--
-- Name: person_idaddress_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_idaddress_fkey FOREIGN KEY (idaddress) REFERENCES address(idaddress) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: professor_idperson_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY professor
    ADD CONSTRAINT professor_idperson_fkey FOREIGN KEY (idperson) REFERENCES person(idperson) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: student_idperson_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY student
    ADD CONSTRAINT student_idperson_fkey FOREIGN KEY (idperson) REFERENCES person(idperson) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

