
-- Used by EclipseLink JPA to generate Ids
CREATE TABLE SEQUENCE (
    SEQ_NAME VARCHAR(50) NOT NULL,
    SEQ_COUNT NUMBER(19),
    PRIMARY KEY (SEQ_NAME)
);

INSERT INTO SEQUENCE VALUES('SEQ_GEN_TABLE',0);

CREATE TABLE country (
    idCountry INTEGER PRIMARY KEY,
    name TEXT UNIQUE
);

CREATE TABLE descriptor (
    idDescriptor INTEGER PRIMARY KEY,
    name TEXT UNIQUE
);

CREATE TABLE subDescriptor (
    idSubDescriptor INTEGER PRIMARY KEY,
    name TEXT UNIQUE,
    idDescriptor INTEGER NOT NULL,
    FOREIGN KEY(idDescriptor) REFERENCES descriptor(idDescriptor)
);

CREATE TABLE organization (
    idOrganization INTEGER PRIMARY KEY,
    code INTEGER UNIQUE,
    name TEXT NOT NULL,
    address TEXT NOT NULL,
    city TEXT NOT NULL,
    idCountry INTEGER NOT NULL,
    phone1 TEXT,
    phone2 TEXT,
    fax1 TEXT,
    fax2 TEXT,
    email TEXT,
    website TEXT,
    postalCode TEXT,
    FOREIGN KEY(idCountry) REFERENCES country(idCountry)
);

CREATE TABLE representative (
    idRepresentative INTEGER PRIMARY KEY,
    idOrganization INTEGER NOT NULL,
    name TEXT NOT NULL,
    position TEXT,
    profession TEXT,
    UNIQUE(idOrganization, name),
    FOREIGN KEY(idOrganization) REFERENCES organization(idOrganization)
);

CREATE TABLE project (
    idProject INTEGER PRIMARY KEY,
    code TEXT NOT NULL UNIQUE,
    name TEXT NOT NULL,
    idRepresentative INTEGER NOT NULL,
    idCountry INTEGER NOT NULL,
    generalObjective TEXT,
    specificObjective TEXT,
    methodology TEXT,
    startingDate TEXT, -- Sqlite does not have date
    duration TEXT,
    staffLivingInAmazon TEXT,
    staffPartOfCommunity TEXT,
    FOREIGN KEY(idRepresentative) REFERENCES representative(idRepresentative),
    FOREIGN KEY(idCountry) REFERENCES country(idCountry)
);

CREATE TABLE project_descriptor (
    idProject INTEGER NOT NULL,
    idDescriptor INTEGER NOT NULL,
    PRIMARY KEY (idProject, idDescriptor),
    FOREIGN KEY(idProject) REFERENCES project(idProject),
    FOREIGN KEY(idDescriptor) REFERENCES descriptor(idDescriptor)
);

CREATE TABLE project_subDescriptor (
    idProject INTEGER NOT NULL,
    idSubDescriptor INTEGER NOT NULL,
    PRIMARY KEY (idProject, idSubDescriptor),
    FOREIGN KEY(idProject) REFERENCES project(idProject),
    FOREIGN KEY(idSubDescriptor) REFERENCES subDescriptor(idSubDescriptor)
);

CREATE TABLE project_organization (
    idProject INTEGER NOT NULL,
    idOrganization INTEGER NOT NULL,
    PRIMARY KEY (idProject, idOrganization),
    FOREIGN KEY(idProject) REFERENCES project(idProject),
    FOREIGN KEY(idOrganization) REFERENCES organization(idOrganization)
);

CREATE TABLE beneficiary (
    idBeneficiary INTEGER PRIMARY KEY,
    code TEXT NOT NULL UNIQUE,
    name TEXT NOT NULL
);

CREATE TABLE project_beneficiary (
    idProject INTEGER NOT NULL,
    idBeneficiary INTEGER NOT NULL,
    PRIMARY KEY (idProject, idBeneficiary),
    FOREIGN KEY(idProject) REFERENCES project(idProject),
    FOREIGN KEY(idBeneficiary) REFERENCES beneficiary(idBeneficiary)
);

CREATE TABLE participation_type (
    idParticipationType INTEGER PRIMARY KEY,
    code TEXT NOT NULL UNIQUE,
    name TEXT NOT NULL
);

CREATE TABLE participation (
    idProject INTEGER NOT NULL,
    idOrganization INTEGER NOT NULL,
    idParticipationType INTEGER NOT NULL,
    PRIMARY KEY (idProject, idOrganization, idParticipationType),
    FOREIGN KEY(idProject) REFERENCES project(idProject),
    FOREIGN KEY(idOrganization) REFERENCES organization(idOrganization),
    FOREIGN KEY(idParticipationType) REFERENCES participation_type(idParticipationType)
);

CREATE TABLE initiative_type (
    idInitiativeType INTEGER PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE project_initiative (
    idProject INTEGER NOT NULL,
    idInitiativeType INTEGER NOT NULL,
    PRIMARY KEY (idProject, idInitiativeType),
    FOREIGN KEY(idProject) REFERENCES project(idProject),
    FOREIGN KEY(idInitiativeType) REFERENCES initiative_type(idInitiativeType)
);

CREATE TABLE population_type (
    idPopulationType INTEGER PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE population_segment (
    idPopulationSegment INTEGER PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE project_population (
    idProject INTEGER NOT NULL,
    idPopulationType INTEGER NOT NULL,
    idPopulationSegment INTEGER NOT NULL,
    PRIMARY KEY (idProject, idPopulationType, idPopulationSegment),
    FOREIGN KEY(idProject) REFERENCES project(idProject),
    FOREIGN KEY(idPopulationType) REFERENCES population_type(idPopulationType),
    FOREIGN KEY(idPopulationSegment) REFERENCES population_segment(idPopulationSegment)
);

CREATE TABLE population_participation_type (
    idPopulationParticipationType INTEGER PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE population_participation (
    idProject INTEGER NOT NULL,
    idPopulationParticipationType INTEGER NOT NULL,
    PRIMARY KEY (idProject, idPopulationParticipationType),
    FOREIGN KEY(idProject) REFERENCES project(idProject),
    FOREIGN KEY(idPopulationParticipationType) REFERENCES population_participation_type(idPopulationParticipationType)
);

CREATE TABLE staff_job_type (
    idStaffJobType INTEGER PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE project_staff (
    idProject INTEGER NOT NULL,
    idStaffJobType INTEGER NOT NULL,
    isForeign BOOLEAN NOT NULL,
    isVolunteer BOOLEAN NOT NULL,
    quantity INTEGER NOT NULL,
    PRIMARY KEY (idProject, idStaffJobType, isForeign, isVolunteer),
    FOREIGN KEY(idProject) REFERENCES project(idProject),
    FOREIGN KEY(idStaffJobType) REFERENCES staff_job_type(idStaffJobType)
);

CREATE TABLE exchange (
    idProject INTEGER PRIMARY KEY,
    projectName TEXT,
    organization TEXT,
    representative TEXT,
    address TEXT,
    city TEXT,
    idCountry INTEGER,
    phone TEXT,
    fax TEXT,
    email TEXT,
    webpage TEXT,
    exchangeType TEXT,
    contactExists BOOLEAN NOT NULL,
    noContactReason TEXT,
    FOREIGN KEY(idProject) REFERENCES project(idProject),
    FOREIGN KEY(idCountry) REFERENCES country(idCountry)
);
