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
    `password` varchar(200) not null,
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
    items_used int,
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


delimiter //
create procedure set_known_good_state()
begin

	delete from enemy;
	alter table enemy auto_increment = 1;
	delete from item;
	alter table item auto_increment = 1;
	delete from npc;
	alter table npc auto_increment = 1;
    
	delete from game_events;
	alter table game_events auto_increment = 1;
	delete from world_stats;
	alter table world_stats auto_increment = 1;
    
	delete from leaderboard;
	alter table leaderboard auto_increment = 1;
    
	delete from player_character;
	alter table player_character auto_increment = 1;
	delete from `user`;
	alter table `user` auto_increment = 1;


    -- Sample Enemy Data
    insert into enemy (enemy_name, enemy_type, damage, health, speed)
    values
        ('Goblin', 'Small', 10, 50, 5),
        ('Dragon', 'Boss', 100, 500, 20),
        ('Elf', 'Small', 20, 100, 6);

    -- Sample NPC Data
    insert into npc (npc_name,stat_increment_type, stat_increment)
    values
        ('Vendor', 'Health',10),
        ('Guide','Speed' ,3);
	-- Sample items Data
    insert into item (`name`, item_description, `type`, stat_increment)
    values
        ('Health Potion', 'Restores health when consumed.', 'healing_potions', 20.0),
        ('Sword of Fire', 'A blazing sword that deals fire damage.', 'damage', 10.0),
        ('Speed Boots', 'Magical boots that enhance speed.', 'speed', 5.0),
        ('Health Booster', 'Increases max health.', 'max_health', 20.0); -- Increases max health by 20 points
    -- Sample User Data
    insert into `user` (first_name, last_name, username, email, `password`, favorite_color, gender)
    values
        ('John', 'Doe', 'johndoe', 'johndoe@example.com', 'password123', 'blue', 'male'),
        ('Jane', 'Smith', 'janesmith', 'janesmith@example.com', 'mypassword', 'red', 'female'),
        ('Cake', 'Cat', 'CakeCat', 'CakeCat@example.com', 'adventure', 'white', 'female'),
        ('Marceline', 'Abadeer', 'Marcee', 'MarceAba@example.com', 'vampire', 'red', 'female');

    -- Sample Leaderboard Data
    insert into leaderboard (user_id, username, score)
    values
        (1, 'johndoe', 100),
        (2, 'janesmith', 150),
        (3,"CakeCat",200);

    -- Sample Player Character Data
    insert into player_character (user_id, time_played_in_seconds, characters_level, max_health, health, damage, speed, healing_potions)
    values
        (1, 3600, 10.5,100, 100, 15, 8, 5),
        (2, 2700, 8.0,80, 70, 10, 6, 3),
        (2, 5400, 12.5,120, 90, 18, 7, 1);

    -- Sample Game Events Data
    insert into game_events (player_character_id, bosses_killed, legendary_item_obtained, game_completed)
    values
        (1, 2, true, true),
        (2, 1, false, true);
    -- Sample World stats Data
    insert into world_stats (player_character_id, enemies_killed, items_used, times_died)
    values
        (1, 50, 10, 5),
        (2, 100, 25, 8);

end//
delimiter ;