--liquibase formatted sql

--changeset ant:001-insert-projects
INSERT INTO projects (name, description, status, version, created_at, updated_at)
VALUES
    ('Alpha Project', 'Initial platform project for customer onboarding.', 'initialized', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Beta Workspace', 'Operations workspace for internal tooling and automation.', 'initialized', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Gamma Launch', 'Customer launch project for rollout and monitoring.', 'initialized', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


