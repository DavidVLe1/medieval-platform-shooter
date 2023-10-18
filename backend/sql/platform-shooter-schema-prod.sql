drop database if exists platform_shooter;
create database platform_shooter;
use platform_shooter;

-- create tables and relationships

create table `user` (
    user_id int primary key auto_increment,
    first_name varchar(200) not null,
    last_name varchar(200) not null,
    username varchar (200) not null,
    email varchar(200) not null,
    password varchar(200) not null,
    favorite_color varchar (200) not null,
    gender varchar (200) not null
);

-- player character can have no skills (references by skill_id) similar with storage (referenced player_storage_id)
create table player_character(
    player_character_id int primary key auto_increment,
    user_id int not null,
    time_played_in_seconds int not null,
    characters_level double not null,
    max_health double not null,
    health double not null,
    damage double not null,
    speed double not null,
    healing_potions int,
    foreign key (user_id) references user(user_id)
);
create table leaderboard (
    leaderboard_id int primary key auto_increment,
    user_id int,
    username VARCHAR(200),
    score int not null,
    foreign key (user_id) references user(user_id)
);

create table item(
    item_id int primary key auto_increment,
    `name` varchar(200) not null,
    item_description TEXT not null,
    `type` varchar(200) not null,
    stat_increment double not null
);

create table world_stats(
    world_stats_id int primary key auto_increment,
    player_character_id int,
    enemies_killed int,
    item_used int,
    times_died int,
    foreign key (player_character_id) references player_character(player_character_id)
);



create table enemy(
    enemy_id int primary key auto_increment,
    enemy_name varchar(200) not null,
    enemy_type varchar(200) not null,
    damage double not null,
    health double not null,
    speed double not null
);

create table npc(
    npc_id int primary key auto_increment,
    npc_name  varchar(200) not null,
    stat_increment_type varchar(200) not null, 
    stat_increment double not null
);


create table game_events(
    game_events_id int primary key auto_increment,
    player_character_id int not null,
    bosses_killed int not null,
    legendary_item_obtained boolean not null,
    game_completed boolean not null,
    foreign key (player_character_id) references player_character(player_character_id)
);
