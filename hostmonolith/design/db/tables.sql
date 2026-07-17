# PostgreSQL database schema for a hostmonolith application

create table users (
    id uuid primary key default gen_random_uuid(),
    username varchar(50) not null unique,
    email varchar(100) not null unique,
    created_at timestamp default current_timestamp
);

create table projects (
    id uuid primary key default gen_random_uuid(),
    name varchar(100) not null,
    description text,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    status varchar(20) default 'initialized'
    version integer default 1
);

create table project_metadata (
    id uuid primary key default gen_random_uuid(),
    project_id uuid references projects(id),
    key varchar(100) not null,
    value text,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table project_members (
    id uuid primary key default gen_random_uuid(),
    project_id uuid references projects(id),
    user_id uuid references users(id),
    role varchar(50) not null,
    created_at timestamp default current_timestamp
);
