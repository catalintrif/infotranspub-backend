-- Table: public."T_CrawlUpdatedUrls"

-- DROP TABLE public."T_CrawlUpdatedUrls";

CREATE TABLE public."T_CrawlUpdatedUrls"
(
    url "char" NOT NULL,
    hash "char",
    processed boolean,
    modified_date date,
    CONSTRAINT "CRAWL_UPDATED_URLS_pkey" PRIMARY KEY (url)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."T_CrawlUpdatedUrls"
    OWNER to postgres;