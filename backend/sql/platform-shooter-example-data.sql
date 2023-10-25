use platform_shooter;

-- select user_id, first_name, last_name, email, password, favorite_color, gender  
-- from user
-- where email = "johndoe@example.com" and password = "password123"

    insert into `user` (first_name, last_name, username, email, `password`, favorite_color, gender)
    values
        ('John', 'Doe', 'johndoe', 'johndoe@example.com', 'password123', 'blue', 'male'),
        ('Jane', 'Smith', 'janesmith', 'janesmith@example.com', 'mypassword', 'red', 'female'),
        ('Cake', 'Cat', 'CakeCat', 'CakeCat@example.com', 'adventure', 'white', 'female'),
        ('Marceline', 'Abadeer', 'Marcee', 'MarceAba@example.com', 'vampire', 'red', 'female');
insert into leaderboard (user_id, username, score)
    values
        (1, 'johndoe', 100),
        (2, 'janesmith', 150),
        (3,"CakeCat",200);
insert into enemy (enemy_name, enemy_type, damage, health, speed)
    values
        ('Monkey', 'Small', 10, 50, 5),
        ('Knight', 'Boss', 30, 150, 8),
        ('Elf', 'Small', 20, 100, 6);

    insert into player_character (user_id, time_played_in_seconds, characters_level, max_health, health, damage, speed, healing_potions)
    values
        (1, 0, 1.0,50, 50, 15, 8, 5),
        (2, 0, 1.0,100, 100, 30, 6, 3),
        (3, 0, 1.0,120, 90, 10, 3, 1),
        (4, 0, 1.0,120, 120, 100, 10, 1);
            -- Sample Game Events Data
    insert into game_events (player_character_id, bosses_killed, legendary_item_obtained, game_completed)
    values
        (1, 0, true, true),
        (2, 1, false, true),
		(3, 1, true, true),
        (4, 1, false, false);
    -- Sample World stats Data
    insert into world_stats (player_character_id, enemies_killed, item_used, times_died)
    values
        (1, 1, 1, 5),
        (2, 0, 0, 6),
		(3, 0, 0, 7),
        (4, 1, 1, 8);