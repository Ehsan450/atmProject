create table accounts
(
    account_no   varchar(14) not null
        primary key,
    account_name varchar(75) not null,
    balance      double      not null
);

create table cards
(
    card_no    varchar(12) not null
        primary key,
    pin        varchar(4)  not null,
    account_no varchar(14) not null,
    constraint cards_ibfk_1
        foreign key (account_no) references accounts (account_no)
);

create index account_no
    on cards (account_no);

create table transactions
(
    id             int auto_increment
        primary key,
    transaction_on timestamp                                not null,
    trx_type       enum ('DEPOSIT', 'WITHDRAW', 'TRANSFER') not null,
    src_account    varchar(14)                              not null,
    dest_account   varchar(14)                              not null,
    constraint transactions_ibfk_1
        foreign key (src_account) references accounts (account_no),
    constraint transactions_ibfk_2
        foreign key (dest_account) references accounts (account_no)
);

create index dest_account
    on transactions (dest_account);

create index src_account
    on transactions (src_account);