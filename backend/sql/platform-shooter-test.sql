drop database if exists platform_shooter_test;
create database platform_shooter_test;
use platform_shooter_test;

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

create table leaderboard (
    leaderboard_id int primary key auto_increment,
    user_id int,
    username VARCHAR(200),
    score int not null,
    foreign key (user_id) references user(user_id)
);

create table items(
    item_id int primary key auto_increment,
    name varchar(200) not null,
    item_description TEXT not null,
    type varchar(200) not null,
    stat_increment double not null
);

create table world_stats(
    world_stats_id int primary key auto_increment,
    user_id int,
    enemies_killed int,
    items_used int,
    times_died int,
    characters_level double,
    foreign key (user_id) references user(user_id)
);

-- player character can have no skills (references by skill_id) similar with storage (referenced player_storage_id)
create table player_character(
    player_character_id int primary key auto_increment,
    user_id int not null,
    time_played_in_seconds int,
    characters_level double not null,
    max_health double not null,
    health double not null,
    damage double not null,
    speed double not null,
    healing_potions int,
    foreign key (user_id) references user(user_id)
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
    stat_boost varchar(200)
);

create table achievements(
    achievements_id int primary key auto_increment,
    user_id int not null,
    killed_one_enemy boolean not null,
    killed_ten_enemies  boolean not null,
    used_first_potion boolean not null,
    killed_first_boss boolean not null,
    killed_final_boss boolean not null,
    first_death boolean not null,
    foreign key (user_id) references user(user_id)
);

create table game_events(
    game_events_id int primary key auto_increment,
    user_id int not null,
    bosses_killed int not null,
    legendary_item_obtained boolean not null,
    game_completed boolean not null,
    foreign key (user_id) references user(user_id)
);

delimiter //
create procedure set_known_good_state()
begin
	delete from player_character;
	alter table player_character auto_increment = 1;
	delete from game_events;
	alter table game_events auto_increment = 1;
	delete from world_stats;
	alter table world_stats auto_increment = 1;
	delete from achievements;
	alter table achievements auto_increment = 1;
	delete from leaderboard;
	alter table leaderboard auto_increment = 1;
	delete from `user`;
	alter table `user` auto_increment = 1;
	delete from enemy;
	alter table enemy auto_increment = 1;
	delete from items;
	alter table items auto_increment = 1;
	delete from npc;
	alter table npc auto_increment = 1;

    -- Sample User Data
    insert into `user` (first_name, last_name, username, email, password, favorite_color, gender)
    values
        ('John', 'Doe', 'johndoe', 'johndoe@example.com', 'password123', 'blue', 'male'),
        ('Jane', 'Smith', 'janesmith', 'janesmith@example.com', 'mypassword', 'red', 'female');

    -- Sample Leaderboard Data
    insert into leaderboard (user_id, username, score)
    values
        (1, 'johndoe', 100),
        (2, 'janesmith', 150);

    -- Sample Player Character Data
    insert into player_character (user_id, time_played_in_seconds, characters_level, max_health, health, damage, speed, healing_potions)
    values
        (1, 3600, 10.5,100, 100, 15, 8, 5),
        (2, 2700, 8.0,70, 80, 10, 6, 3);

    -- Sample Enemy Data
    insert into enemy (enemy_name, enemy_type, damage, health, speed)
    values
        ('Goblin', 'Small', 10, 50, 5),
        ('Dragon', 'Boss', 100, 500, 20);

    -- Sample NPC Data
    insert into npc (npc_name, stat_boost)
    values
        ('Vendor', '["Health +10", "Damage +5"]'),
        ('Guide', '["Speed +3", "Health +5"]');

    -- Sample Achievements Data
    insert into achievements (user_id, killed_one_enemy, killed_ten_enemies, used_first_potion, killed_first_boss, killed_final_boss, first_death, stayed_alive)
    values
        (1, true, true, false, true, false, true, true),
        (2, false, true, true, false, true, true, true);

    -- Sample Game Events Data
    insert into game_events (user_id, bosses_killed, legendary_item_obtained, game_completed)
    values
        (1, 2, true, true),
        (2, 1, false, true);
    -- Sample World stats Data
    insert into world_stats (user_id, enemies_killed, items_used, times_died, characters_level)
    values
        (1, 50, 10, 5, 15.5),
        (2, 100, 25, 8, 20.0);
    -- Sample items Data
    insert into items (name, item_description, type, stat_increment)
    values
        ('Health Potion', 'Restores health when consumed.', 'healing_potions', 20.0),
        ('Sword of Fire', 'A blazing sword that deals fire damage.', 'damage', 10.0),
        ('Speed Boots', 'Magical boots that enhance speed.', 'speed', 5.0),
        ('Health Booster', 'Increases max health.', 'max_health', 20.0); -- Increases max health by 20 points
end//
delimiter ;