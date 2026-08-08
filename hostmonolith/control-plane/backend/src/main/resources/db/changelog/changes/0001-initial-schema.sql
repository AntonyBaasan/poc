--liquibase formatted sql

--changeset ant:001-enable-pgcrypto
CREATE
EXTENSION IF NOT EXISTS pgcrypto;

--changeset ant:002-create-users
CREATE TABLE users
(
    id         UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
    username   VARCHAR(50)              NOT NULL,
    email      VARCHAR(100)             NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_users_username UNIQUE (username),
    CONSTRAINT uk_users_email UNIQUE (email)
);


--changeset ant:003-create-projects
CREATE TABLE projects
(
    id          UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
    name        VARCHAR(100)             NOT NULL,
    description TEXT,
    status      VARCHAR(20)              NOT NULL DEFAULT 'initialized',
    version     INTEGER                  NOT NULL DEFAULT 1,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_projects_version_positive
        CHECK (version > 0)
);


--changeset ant:004-create-project-metadata
CREATE TABLE project_metadata
(
    id         UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
    project_id UUID                     NOT NULL,
    key        VARCHAR(100)             NOT NULL,
    value      TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_project_metadata_project
        FOREIGN KEY (project_id)
            REFERENCES projects (id)
            ON DELETE CASCADE,

    CONSTRAINT uk_project_metadata_project_key
        UNIQUE (project_id, key)
);


--changeset ant:005-create-project-members
CREATE TABLE project_members
(
    id         UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
    project_id UUID                     NOT NULL,
    user_id    UUID                     NOT NULL,
    role       VARCHAR(50)              NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_project_members_project
        FOREIGN KEY (project_id)
            REFERENCES projects (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_project_members_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE,

    CONSTRAINT uk_project_members_project_user
        UNIQUE (project_id, user_id)
);


--changeset ant:006-create-project-indexes
CREATE INDEX idx_project_metadata_project_id
    ON project_metadata (project_id);

CREATE INDEX idx_project_members_project_id
    ON project_members (project_id);

CREATE INDEX idx_project_members_user_id
    ON project_members (user_id);