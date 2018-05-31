create table if not exists resume
(
  uuid      char(36)     not null constraint resume_pkey primary key,
  full_name varchar(100) not null
);
create table if not exists contact
(
  id          serial,
  resume_uuid char(36)     not null references resume on delete cascade,
  type        varchar(50)  not null,
  value       varchar(100) not null
);

create unique index if not exists contact_uuid_type_index
  on contact (resume_uuid, type);

create table if not exists section
(
  id          serial,
  resume_uuid char(36)    not null references resume on delete cascade,
  type        varchar(20) not null,
  value       varchar     not null
 );

create unique index if not exists section_uuid_type_index
  on section (resume_uuid, type);

create unique index section__pkey
  on section (id)