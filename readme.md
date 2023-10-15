
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

- Build a web game app using either phaser.js or Kaboom.js. backend can handle 
    stuff like user,Profile, GameData, stats, items,
    maybes( enemies,npcs, Leaderboards, game events, achievements) 
    that can be their own separate tables.

- The game is designed to provide players with responsive controls, formidable 
    adversaries, and an immersive world that calls back to the essence of what made 
    Mega Man an unforgettable adventure while also being accessible in the form
    of a web-based game. In essence, this game meets the demand 
    for an approachable yet exhilarating platformer shooter that resonates with
    both long-time enthusiasts and newcomers to this cherished genre.

Example
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

___
## Glossary
- User: Model representing user who has an account.
- Guest: Model for representing users who are not logged in, thus have no user information.
- Profile: Model for user profiles. displays information on the user gathered from when the user signed up.
- GameData: Model for representing game-specific data such as game state, game progression.
- Stats: Model for tracking player statistics such as how many enemies killed, items used, times died, characters level.
- Playable Character: model representing the player's character such as character stats, health, skills
- Items: Model for game items.
- Enemies: Model for enemy characters. Has their own stats such as damage, health, skills
- NPCs: Model for non-playable characters. Are interactable objects that has dialogue or gives health or items.
- Leaderboards: Model for tracking high scores. Players are able to compare their scores with eachother looking at this data.
- GameEvents: Model for recording in-game events.
- Achievements: Model for player achievements. Tracks whether certain actions have been done in the  game and awards the player for it.
___
## High Level Requirements
- Sign up for an account/ Log in(authenticated).
- View own user profile.(User).
- Access the game.(Anyone)
- Edit own profile. (User)
- Store and retrieve game progress, Save and load game settings. (User).
- Record and display player statistics, Compare stats with other players.(User)
- Collect and use game items, Manage in-game inventory. (Anyone)
- Encounter and defeat enemies, Gain experience points from defeating enemies. (Anyone)
- Interact with NPCs, Receive quests or missions from NPCs.(Anyone) 
- Compete for top positions on leaderboards, View high scores of other players. (Anyone)
- Capture in-game events and achievements, Display event history. (Anyone)
- Unlock and earn achievements, Showcase earned achievements to others. ( User)

### User Stories/Scenarios
___
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
> - username/email 
> - password 
> 
> **Precondition**: None
> 
> **Post-condition**: None
>
> ### View Stats
>  If the player wants to view their stats
>
> **Precondition**: Player must be logged in as admin or User
>
> **Post-condition**: None
>
> ### View Profile
>  If the player wants to view their profile
>
> **Precondition**: Player must be logged in as admin or User
>
> **Post-condition**: None 
> 
> ### Edit Profile 
> 
>  If the player wants to Edit their profile
>
> **Precondition**: Player must be logged in as admin or User
>
> **Post-condition**: None 
>

## Technical Requirements
-Manage 4-7 database tables (entities) that are independent concepts. A simple bridge table doesn't count.
-Relational database for data management
-Spring Boot, MVC (@RestController), JdbcTemplate, Testing
-An HTML and CSS UI built with React
-Sensible layering and pattern choices
-A full test suite that covers the domain and data layers.


### FrontEnd
- Focuses on using Phaser.js or Kaboom.js to create a playable video game.
-
-
-
-

### BackEnd
-  
- 
- 
- 
- 


