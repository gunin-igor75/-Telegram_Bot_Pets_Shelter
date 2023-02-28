create table owner(
                      id bigint primary key generated by default as identity,
                      chat_id bigint unique not null,
                      name varchar(255),
                      registered_at timestamp not null,
                      last_action varchar(255),
                      pet_id bigint constraint pet_id references pet
);
