
# CapStone Assessment Proposal

## Gameplay
![Gameplay GIF](fullstack-web-game-phaser-dle.gif)

## Problem Statement
___
- In the realm of gaming, timeless classics like Mega Man have left an 
    indelible mark on the hearts of players across generations. Nevertheless, 
    the gaming landscape lacks accessible titles within the beloved platformer 
    shooter genre that pay homage to the nostalgia and thrill of those legendary 
    titles of yesteryear. This presents a compelling opportunity to craft a simple 
    platformer shooter game, evoking a sense of nostalgia while offering a fresh and 
    engaging gaming experience for the masses. 
___
## Technical Solution:
___
Create a web based platform shooter video game to bring awareness to the platform shooter genre.

Build a web game app using either phaser.js or Kaboom.js. backend can have tables to handle 
    stuff like:

- User
  - Store user information like username, email, password (hashed), and any other relevant user details.
  - This table can be used for user authentication and to associate game data with specific users.
- GameSession
  - Track information about individual game sessions, such as start time, end time, and duration.
  - Store the user who played the session and any relevant session statistics.
- Leaderboard
  - A leaderboard table to store player scores and rankings.
  - Include columns for user ID, score, and a timestamp to track when the score was achieved.
- Player Progress
  - Keep track of the player's progress in the game, including their level, collected items, and achievements. 
  - This table might be used to restore the player's progress when they log in.
- User-Game Statistics
  - For more detailed game statistics, you can create a table to record various in-game actions and events, like the number of enemies defeated, shots fired, levels completed, etc.
- Items
  - If your game includes power-ups, weapons, or collectibles, you can have a table to store information about these items.
  - Include attributes like item name, description, and any in-game effects.
- User Inventory
  - If the game involves an inventory system for items and equipment, you can have a table to track what items each user possesses.

    
- The game is designed to provide players with responsive controls, formidable 
    adversaries, and an immersive world that calls back to the essence of what made 
    Mega Man an unforgettable adventure while also being accessible in the form
    of a web-based game. In essence, this game meets the demand 
    for an approachable yet exhilarating platformer shooter that resonates with
    both long-time enthusiasts and newcomers to this cherished genre.

Example: 
> ### Scenario 1
> In a suburban household, a young adult named Alex, born in the 1990s, 
> cherished the classic platform shooter games from that era, like Contra 
> and Mega Man. Wanting to introduce his much younger sibling, Emily, to the 
> joy of these games, Alex faced a challenge. The old retro games were either 
> rare or expensive, making them inaccessible. To bridge the generational gap,
> Alex searched for an accessible web-based platform shooter video game that 
> pays homage to the classics. He introduced Emily to this found web game, 
> and her eyes lit up with excitement as she experienced the simple yet 
> exhilarating gameplay, falling in love with the genre that had enchanted 
> her older sibling.
> 
>  ### Scenario 2
> A charity organization, "Gamers for Good," decided to host a gaming 
> marathon for a noble cause. Their goal was to raise funds for underprivileged
> children's education. To capture the attention of a wide audience, they sought
> a game that was not only fun but also evoked a sense of community. The web-based
> platform shooter emerged as the perfect choice. Gamers from all walks of life 
> joined the marathon, played the game, and donated generously. The event garnered 
> widespread attention, both for the charity and the classic genre it celebrated.
> Gamers worldwide got on board, bringing platform shooters back into the limelight.
> 
> ### Scenario 3
> A young student named Alex, facing the pressures of school, discovered a 
> web-based platform shooter game which not only provided enjoyment 
> but also improved his problem-solving and hand-eye coordination skills. The game's
> soothing atmosphere acted as a stress reliever, offering a peaceful escape from 
> his schoolwork. "JumpQuest's" global leaderboard added a competitive element,
> motivating Alex to excel and prove himself among players worldwide. His 
> proficiency in the game had a positive impact on his academic life, improving 
> his confidence and problem-solving abilities. Alex's experience highlighted how 
> this platform shooter game and other similar games can offer both fun and 
> valuable skills,while connecting players worldwide in a sense of shared 
> competition and community.
> 

___
## Glossary
- User: Model representing user who has an account. can display some information on the user gathered from when the user in the game.
  - playerId/userId
  - username (player name (String name))
  - email
  - password
  - Favorite color (String color)
  - gender (String gender || char gender)
- Guest: Model for representing users who are not logged in, thus have no user information.
- World Stats: Model for tracking game world statistics such as...
  - playerId (link to player) 
  - World Stat Details:
    - enemies killed
    - items used
    - times died
    - character's level
- Playable Character: model representing the player's character
  - playerId
  - Player Stats:
    - Level
    - health
    - damage
    - speed
    - skills
    - storage
    - time passed (Int timeInSeconds) (enemies get stronger over time).
- Items: Model for game items.
  - Item name
  - Description
  - Item Type (weapons, healing potions, stat potions, misc)
  - Stat_increment (Optional)
- Enemies: Model for enemy characters.
  - Enemy Type
  - Damage 
  - Health
  - Skills
  - speed
- NPCs: Model for non-playable characters. Are interactable objects that has dialogue or gives health or items.
  - NPC Name
  - Dialogue
  - Rewards
  - stat boost (boost's stats upon interaction)
- Leaderboards: Model for tracking high scores. Players are able to compare their scores with eachother looking at this data.
  - playerId
  - UserName
  - Score
- GameEvents: Model for recording in-game events.
  - playerId (linked to player id) 
  - bosses killed (int num) (change in audio, visual and enemies get stronger)

  - legendary item obtained (boolean) (character is modified visually, audibly)
  - game completed (boolean)
- Achievements: Model for player achievements. Tracks whether certain actions have been done in the  game and awards the player for it.
  - playerId (linked to player id)
  - killed 1 enemy (boolean)
  - killed 10 enemies (boolean)
  - used first item (boolean)
  - killed first boss (boolean)
  - killed second boss (boolean)
  - killed third boss (boolean)
  - killed final boss (boolean)
  - first death (boolean)
___
## High Level Requirements
- Sign up for an account/ Log in(authenticated).
- View own user information.(User).
- Access the game.(Guest, User).
- Edit own user information. (User)
- Has a playable Character (Guest, User).
- Can view their player progression (User). maybe guest too
- Can track GameData such as their game progression and game state (User).
- Can track world stats to see what they've done to the world (User).
- Store and retrieve game progress, Save and load game settings. (User).
- Playable Character Can store and use items. (Guest, User).
- Playable Character can interact with npc and enemies. (Guest, User).
- Can view leaderboard and Compete for top positions on leaderboards, View high scores of other players. (User) maybe also (Guest).
- Playable Character can gain experience points from defeating enemies. (Guest, User).
- Interact with NPCs, Receive quests,items or exp from NPCs. (Guest, User).
- Capture in-game events and achievements, Display event history. (Anyone)
- Unlock and earn achievements, Showcase earned achievements to others. (User)

### User Stories/Scenarios
___
- As a casual user, I want to play the game and see my personal records displayed.
- As an authenticated user, I want to see the leaderboard and my profile.
    - Depends on: as an unauthenticated user, I want to login in order to view my profile and the leaderboard.
    - Which depends on: As a new user, I want to make an account so that I can see my score on the leaderboard and view my profile.


> ### Play the game
>  If the player wants to play the game
>
> **Precondition**: None
>
> **Post-condition**: None
>
> ### Sign Up/Create an account
> If the user wants to track their stats or profile data, they can sign up.
> 
> Suggested data:
> - username/email (String name)
> - password (String name)
> - player name (String name)
> - color (String color)
> - gender (String gender || char gender)
> 
> **Precondition**: None
> 
> **Post-condition**: None
>
> ### View World Stats
>  If the player wants to view their stats
>
> suggested data: 
> - enemies killed (int number)
> - items used (int number)
> - times died (int number)
> - character's level (double number|| int number)
> - time played (time days/hours/minutes)
> 
> **Precondition**: None, Player can be logged in as User or Guest
>
> **Post-condition**: None
>
> ### View own User
>  Player can view their profile to see what information is available to them on their account. 
>  
> suggested data to view:
> - player name (String name)
> - color (String color)
> - gender (String gender || char gender)
>
> **Precondition**: Player must be logged in as User
>
> **Post-condition**: None 
> 
> ### Edit own User 
>  User can Edit their profile to change their character name, color, gender to affect their character's appearance
> 
> suggested data in User to edit:
> - player name (String name)
> - color (String color)
> - gender (String gender || char gender)
>
> **Precondition**: Player must be logged in as User
>
> **Post-condition**: None 
> 
>  ### View Player Progression//Character Profile Sheet
> Can view their Character Profile Sheet to see what information is available. 
> 
> suggested data in Character Profile Sheet (could be character profile/Character Profile Sheet)
> - Level
> - health
> - damage
> - speed
> - 
> **Precondition**: None
>
> **Post-condition**: None 
>  
>  ### Save Game Data
> Once the player is logged in, they are able to save their game to their account for later.
> 
> suggested data:
> - Visual Feedback: health bar, level completion message, changes in characters/objects/environment.
> - Auditory feedback: sound effects and music to give information on game state. ominous music for boss fight, beeping noises for low health.
> - Interactive Feedback: way players can interact with game giving sense of game state. ex. responsiveness of character movement and actions, and feedback from controls.
>
> **Precondition**: Player must be logged in as User
>
> **Post-condition**: None
>  
>  ### Load Game Data
> Once the player is logged in, they have the ability to browse previous game data if they saved any and can load it.
> 
> suggested data:
> - Visual Feedback: health bar, level completion message, changes in characters/objects/environment.
> - Auditory feedback: sound effects and music to give information on game state. ominous music for boss fight, beeping noises for low health.
> - Interactive Feedback: way players can interact with game giving sense of game state. ex. responsiveness of character movement and actions, and feedback from controls.
>
> **Precondition**: Player must be logged in as User
>
> **Post-condition**: None 
>
> ### View LeaderBoard
>  Once the player wants to view the leaderboard and compare their scores to other players.
>
> suggested data:
> - UserName
> - Score
> - Time Played
>
> **Precondition**: None, player has to choose to submit their score to the database || player submits score automatically on game over(exiting, or dying).
>
> **Post-condition**: player's score is uploaded to leaderboard.
> 
>  ### Interact with enemies and NPC
> Once the user wants to engage with the world to get items, skills, or experience
> 
>  suggested data for the character's progression:
> - Level is increased
> - health changed
> - damage changed
> - speed changed
> - new skills added 
> - storage updated
> 
> > **Precondition**: player interacts with enemies/ NPCs.
> 
> **Post-condition**: data above is updated.
>  
>  
>  ### Achieved an Achievement
> Once the user has made certain actions in the game and activated a notice that they receieved an achievement.
> 
> suggested data:
> - Achievement Name
> - Achievement Description
>
> > **Precondition**: performed an action activating the achievement.
>
> **Post-condition**: achievement is now listed on their character profile sheet.
> 
>  ### Activated an Event change to the world.
>  
> suggested data:
> - bosses killed (int number) (change in audio, visual and enemies get stronger)
> - time passed (int number) (enemies get stronger over time).
> - legendary item obtained  (boolean unlocked) (character is modified visually, audibly)
>  
> **Precondition**: performed an action activating the world change.
>
> **Post-condition**: World is affected somehow/ data above is updated.
> 
> 

## Technical Requirements
-Manage 4-7 database tables (entities) that are independent concepts. A simple bridge table doesn't count.
-Relational database for data management
-Spring Boot, MVC (@RestController), JdbcTemplate, Testing
-An HTML and CSS UI built with React
-Sensible layering and pattern choices
-A full test suite that covers the domain and data layers.

## Summarized tasks
___
### FrontEnd
- Focuses on using Phaser.js or Kaboom.js to create a playable video game.
- stores data locally initially and then posts to backend upon save.
- is a platform shooter.
- [x] Create Sign Up Component. (2.5 Hours)
  - [x] Send Data to Backend Database
  - [x] Become Authenticated after signup.
- [x] Create Login Component. (1.5 Hours)
  - [x] Get User Data from Backend to authenticate.
  - [x] Load data into frontend.
  - [x] Become Authenticated.
  - [x] Navigate to Home.
- [x] Create LogOut Component. (1.0 Hours)
  - [x] Remove authentication.
  - [x] navigate to Home.
  - [x] Ensure routes are now blocked if not authenticated.
- [x] Create ScoreBoard Component. (2.0 Hours)
  - [x] Read Scores from Leaderboard Table.
  - [x] Display scores from up to 10, from highest to lowest.
  - [x] Displays new Scores from the user when they are logged in once completing their game.
- [x] Create Home Component. (2.5 Hours)
  - [x] Stylized Home to welcome user or guest.
    - [x] welcomes the user by name if authenticated.
    - [x] prompt guest to log in or sign up.
- [x] Create Nav Component. (3.5 Hours)
  - [x] handle routes depending on user being authenticated.
    - [x] guests are shown sign up, sign in, and home.
    - [x] users are shown log out, home, game, scoreboard/leaderboard.
- [x] Secure routing in frontend. (2.0 Hours)
  - [x] use React Context so that components can check if authenticated or not.
  - [x] prevent guest from accessing routes they shouldn't and send them to error page.
- [x] Connect to Backend and pass/receive Data. (3.0 Hours)
  - [x] ensure data from tables are being received as well as checking that data is being populated onto database.
    - [x] populate user on sign up which then populates the rest of the connected tables.
    - [x] ensure other tables that are used are being read correctly by frontend.
- [x] Create platforms in game Component. (1.5 Hours)
  - [x] Ensure they are physical colliders with player and enemies and not invisible.
- [x] Create playable Character. (2.0 Hours)
  - [x] Ensure player can fight, move, jump.
    - [x] Ensure animations are working for these actions.
    - [x] Ensure Stats are being populated from the database and affect the game.
      - [x] health being zero or below will result in player death animation and game over screen.
  - [x] Ensure game doesn't break.
- [x] Create enemies(Boss, minions). (4.0 Hours)
  - [x] Ensure enemies deal damage.
  - [x] Enemy stats are populated by data from the backend.
  - [x] Ensure enemies can die.
  - [x] Ensure game doesn't break.
- [x] Add Sound Effects. (1.5 Hours)
  - [x] Winning game sound effects.
  - [x] Losing game Sound effects.
  - [x] sound effects from stars being picked up.
  - [x] sound effects for player dying.
  - [x] sound effects for player attacks.
  - [x] Ensure game doesn't break.
- [x] Implement Animations to sprites. (4.0 Hours)
  - [x] Ensure game doesn't break.
  - [x] attach animation to player.
  - [x] attach animation to enemies.
  - [x] attach animation to Boss.
- [x] Populate variables in Phaser.js with data from User and backend Database. (2.0 Hours)
  - [x] Ensure there is no empty/null data pushed or received.
- [x] Implement Time tracker, score tracker, and health tracker in game and display them. (2.0 Hours)
- [x] Implement Win Condition in Phaser.js Game component. (1.0 Hours)
  - [x] Triggered on boss death which spawns after collecting stars and killing enemy.
- [x] Implement Lose Condition in Phaser.js Game component. (1.5 Hours)
  - [x] Triggered by Health going to zero.

### BackEnd
-  If user is a guest, don't save any data or store any data as it will be held temporarily, similar with a user, but they can save whenever they want. 
- at least 4 tables: LeaderBoard, Items, User, World Stats, PlayableCharacter, Enemies, NPCs, GameEvents
  - enemies table is for grabbing enemy data to construct an enemy on frontend same with npcs
  -  items table will be pulled from database into game to generate items and can be loaded into the player_character's inventory.
  - player storage stores items user picked up
  - skills table holds skills that enemies and user can use.

- [x] Build Database Schema test and Production Schema (#3.3 Hours)
  - [x] Test communication between Database and Backend.
  - [x] Create Example Schema to insert Data into Production database.
- [x] Build Model Layer of required tables (#1.5 Hours).
  - [x] Populate models with appropriate fields and functions.
- [x] Build Data Layer for required tables (#3.0 Hours)
  - [x] implement CRUD capability and query to database.
- [x] Fully Test Data Layer (#3.0 Hours);
  - [x] Test CRUD capability in Data Layer
- [x] Build Domain Layer for required tables (#4.0 Hours).
- [x] Fully Test for Domain Layer (#4.0 Hours)
  - [x] Test CRUD capability in Domain Layer.
- [x] Build Controller Layer for required tables (#3.0 Hours).
- [x] Test Controller Layer (#1.0 Hours).
- [x] Connect Backend to FrontEnd (#2.5 Hours);


