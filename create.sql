create table authors (id bigint not null, name varchar(255), primary key (id)) engine=InnoDB;
create table authors_seq (next_val bigint) engine=InnoDB;
insert into authors_seq values ( 1 );
create table book_inventory (available_count integer, total_count integer, book_id bigint, id bigint not null, primary key (id)) engine=InnoDB;
create table book_inventory_seq (next_val bigint) engine=InnoDB;
insert into book_inventory_seq values ( 1 );
create table books (price float(23), author_id bigint, id bigint not null, genre varchar(255), title varchar(255), primary key (id)) engine=InnoDB;
create table books_seq (next_val bigint) engine=InnoDB;
insert into books_seq values ( 1 );
create table borrow_info (book_id bigint, id bigint not null, user_id bigint, status varchar(255), primary key (id)) engine=InnoDB;
create table borrow_info_seq (next_val bigint) engine=InnoDB;
insert into borrow_info_seq values ( 1 );
create table users (id bigint not null, name varchar(255), primary key (id)) engine=InnoDB;
create table users_seq (next_val bigint) engine=InnoDB;
insert into users_seq values ( 1 );
alter table books add constraint FKfjixh2vym2cvfj3ufxj91jem7 foreign key (author_id) references authors (id);