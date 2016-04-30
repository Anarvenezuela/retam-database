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
    code TEXT UNIQUE,
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
    telex TEXT,
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
    FOREIGN KEY(idRepresentative) REFERENCES representative(idRepresentative)
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









