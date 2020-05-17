-- Table: public.entity

-- DROP TABLE public.entity;

CREATE TABLE public.entity
(
    id bigint NOT NULL,
    createdat date NOT NULL,
    CONSTRAINT "Entity_pk" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.entity
    OWNER to postgres;


-- Table: public.company

-- DROP TABLE public.company;

CREATE TABLE public.company
(
    -- Inherited from table public.entity: id bigint NOT NULL,
    -- Inherited from table public.entity: createdat date NOT NULL,
    name character varying(25) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Company_pk" PRIMARY KEY (name, id)
)
    INHERITS (public.entity)
TABLESPACE pg_default;

ALTER TABLE public.company
    OWNER to postgres;



-- Table: public.device

-- DROP TABLE public.device;

CREATE TABLE public.device
(
    -- Inherited from table public.entity: id bigint NOT NULL,
    -- Inherited from table public.entity: createdat date NOT NULL,
    uuid character varying(25) COLLATE pg_catalog."default" NOT NULL,
    lastemit date,
    countemit bigint,
    disabletag boolean,
    addressip character varying COLLATE pg_catalog."default",
    CONSTRAINT "Device_pk" PRIMARY KEY (uuid, id)
)
    INHERITS (public.entity)
TABLESPACE pg_default;

ALTER TABLE public.device
    OWNER to postgres;