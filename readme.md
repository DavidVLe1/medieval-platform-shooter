
# CapStone Assessment Proposal

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
- User: Model representing user who has an account.
- Guest: Model for representing users who are not logged in, thus have no user information.
- User Profile: Model for user profiles. displays information on the user gathered from when the user signed up. // could be displayed in game.
- GameData: Model for representing game-specific data such as game state, game progression.
  - game state details:
    - Visual Feedback: health bar, level completion message, changes in characters/objects/environment.
    - Auditory feedback: sound effects and music to give information on game state. ominous music for boss fight, beeping noises for low health.
    - Interactive Feedback: way players can interact with game giving sense of game state. ex. responsiveness of character movement and actions, and feedback from controls.
- World Stats: Model for tracking game world statistics such as...
  - World Stat Details:
    - enemies killed
    - items used
    - times died
    - character's level
    - time played
- Playable Character: model representing the player's character
  - Player Stats:
    - Level
    - health
    - damage
    - speed
    - skills
    - storage
- Items: Model for game items.
  - Item name
  - Description
  - Item Type
  - Stat Bonus (Optional)
- Enemies: Model for enemy characters.
  - Enemy Type
  - Damage 
  - Health
  - Skills
  - speed
- NPCs: Model for non-playable characters. Are interactable objects that has dialogue or gives health or items.
- Leaderboards: Model for tracking high scores. Players are able to compare their scores with eachother looking at this data.
  - UserName
  - Score
  - Time Played
- GameEvents: Model for recording in-game events.
  - bosses killed (change in audio, visual and enemies get stronger)
  - time passed (enemies get stronger over time).
  - legendary item obtained (character is modified visually, audibly)
- Achievements: Model for player achievements. Tracks whether certain actions have been done in the  game and awards the player for it.
___
## High Level Requirements
- Sign up for an account/ Log in(authenticated).
- View own user profile.(User).
- Access the game.(Guest, User).
- Edit own profile. (User)
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
> ### View User Profile
>  Player can view their profile to see what information is available to them on their account. 
>  
> suggested data in profile:
> - player name (String name)
> - color (String color)
> - gender (String gender || char gender)
>
> **Precondition**: Player must be logged in as User
>
> **Post-condition**: None 
> 
> ### Edit User Profile
>  User can Edit their profile to change their character name, color, gender to affect their character's appearance
> 
> suggested data in profile to edit:
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
> - skills
> - storage
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
>  ### Store game Items
>  Once the player wants to store an item for later use upon finding an item.
>  
> suggested data:
> - Item name
> - Description
> - Item Type
> - Stat Bonus (Optional)
>  
> **Precondition**: None, player has to interact with the item and choose to store it.
>
> **Post-condition**: None
>  
> ### Store game Items
>  Once the player wants to store an item for later use upon finding an item.
>
> suggested data:
> - Item name
> - Description
> - Item Type
> - Stat Bonus (Optional)
>
> **Precondition**: None, player has to interact with the item and choose to store it.
>
> **Post-condition**: Removed item from user's storage.
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

## Summarized 
___
### FrontEnd
- Focuses on using Phaser.js or Kaboom.js to create a playable video game.
- stores data locally initially and then posts to backend upon save.
- is a platform shooter.
-
-

### BackEnd
-  If user is a guest, don't save any data or store any data as it will be held temporarily, similar with a user but they can save whenever they want. 
- 
- 
- 
- 


