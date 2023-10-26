import React, { useContext, useEffect } from 'react';
import Phaser from 'phaser';
import AuthContext from './context/AuthContext';

const PhaserGame = ({enemies}) => {
  const {userData} = useContext(AuthContext);
  console.log(" userData: "+userData.username + ","+userData.userId);
  useEffect(() => {
    const config = {
      type: Phaser.AUTO,
      width: 2000,
      height: 600,
      physics: {
        default: 'arcade',
        arcade: {
          debug: false
        }
      },
      scene: {
        preload: preload,
        create: create,
        update: update
      }
    };
    var player;
    var platforms;
    var cursors;
    var stars;
    var score = 0;
    var scoreText;
    var time = 0;
    var timeText;
    var bombs;
    var gameOver = false;
    var timer;
    let bossIdleTimer;
    var playerBullets;
    let isFacingLeft = false;
    let sfx;
    let stage = 1;
    let isAttacking = false;
    var healthText;
    var playerSpeed = 5;
    var playerDamage = 10;
    var maxHealth = 100;
    var healthPoints = maxHealth;
    var enemy1; //for adding as sprite
    var bossActive=false;
    var enemy1Obj=enemies[0];//for holding data
    // enemies.forEach((enemy, index) => { //tests whats inside the enemies prop.
    //   console.log(`Enemy ${index + 1}:`, enemy);
    // });
    let enemy1Direction = 'right'; // To keep track of enemy's movement direction
    let enemy1Jumping = false; // To keep track of enemy's jump state
    var enemy1Damage = enemy1Obj.damage;
    var enemy1Health = enemy1Obj.health;
    var enemy1Speed = enemy1Obj.speed;
    var enemy1Killed;
    var bossObj=enemies[1];
    var bossKilled=false;
    var winMessage;
    var loseMessage;
    var greyOverlay;
    // var enemy2;
    var boss;
    var bossHealth = bossObj.health;
    var bossDamage = bossObj.damage;
    var bossDashSpeed = bossObj.speed;
    // Define a variable to track whether the boss has dashed
    let hasDashed = false;
    // Add a variable to keep track of invincibility state
    let isInvincible = false;
    let invincibilityTimer;
    const colors = {
      redTint: 0xff0000,
      whiteTint: 0xffffff,
    };
    const purpleTint = 0x800080;

    var scorePosted=false;

    var game = new Phaser.Game(config);

    function preload() {
      this.load.audio('theme', 'assets/music/theme.mp3');
      this.load.audio('pickUpStar','assets/music/pickUp.mp3')
      this.load.audio('hurt', 'assets/music/hurt.mp3');
      this.load.audio('evilLaugh', 'assets/music/gameOver.mp3');
      this.load.audio('swordWhoosh','/assets/music/swordWhoosh.mp3');
      this.load.audio('wonGame','/assets/music/winGame.mp3');
      this.load.audio('spellHit','/assets/music/swordSpell.mp3');
      this.load.image('sky', 'assets/sky.png');
      this.load.image('ground', 'assets/platform.png');
      this.load.image('background', 'assets/Background.png')
      this.load.image('ground1', 'assets/Tile_28.png')
      this.load.image('dirt', 'assets/Tile_12.png')
      this.load.image('star', 'assets/star.png');
      this.load.image('bomb', 'assets/bomb.png');
      this.load.image('bullet', 'assets/bullet9.png');
      this.load.image('stopSign', 'assets/Bush1_1.png');
      this.load.atlas('knight', 'assets/animations/knight.png', 'assets/animations/knight.json');
      this.load.atlas('bossKnight', 'assets/animations/knight.png', 'assets/animations/knight.json');
      this.load.spritesheet('dude',
        'assets/dude.png',
        { frameWidth: 32, frameHeight: 48 }
      );
    }

    function create() {
      const background = this.add.tileSprite(-400, 0, config.width * 2, 600, 'background');
      background.setOrigin(0, 0); // Set the origin to the top-left corner
      background.setScale(1, 2);
      //set keyboard control x to shoot and z to slash
      this.keyX = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.X);
      // Set the background color to white

      this.cameras.main.setBackgroundColor('#ffffff'); // Hex color code for white
      this.physics.world.gravity.y = 300;
      this.physics.world.setBounds(5, 0, config.width, config.height, true, true, true, false);
      //  The platforms group contains the ground and the 2 ledges we can jump on
      platforms = this.physics.add.staticGroup();
      //  Here we create the ground.
      // Add the dirt tiles first( first as tiles)
      const dirt = this.add.tileSprite(-400, 915, config.width * 3, 600, 'dirt');
      const ground = this.add.tileSprite(-400, 615, config.width * 3, 30, 'ground1');
      //now as actual ground.
      for (let i = 0; i < 2000; i += 30) {
        platforms.create(i, 610, 'ground1');
      }
      //  Scale it to fit the width of the game (the original sprite is 400x32 in size)
      // platforms.create(800, 680, 'ground').setScale(6, 6).refreshBody();
      //  Now let's create some ledges
      platforms.create(600, 450, 'ground');
      platforms.create(50, 300, 'ground');
      platforms.create(750, 270, 'ground');
      //stop sign
      // Create stop sign images and position them at the left and right sides
      this.add.image(0, 555, 'stopSign'); // Adjust the position as needed
      this.add.image(1990, 555, 'stopSign'); // Adjust the position as needed

      // The player and its settings
      // player = this.physics.add.sprite(100, 450, 'dude');
      player = this.physics.add.sprite(50, 50, 'knight');
      player.setSize(15, 60, 15, 0); // Set the width and height of the physics body and fix offset of x and y axis of sprite hitbox.

      player.setCollideWorldBounds(true);
      //enemy
      enemy1 = this.physics.add.sprite(500, 350, 'dude');
      enemy1.setSize(50, 50);
      enemy1.setCollideWorldBounds(true);

      // Add 1 groups for Bullet objects
      playerBullets = this.physics.add.group({ classType: Bullet, runChildUpdate: true, allowGravity: false });
      this.physics.add.collider(enemy1, playerBullets, enemyHitCallback);
      //create score;
      scoreText = this.add.text(16, 16, 'Score: 0', { fontSize: '32px', fill: '#fff', fontFamily: 'Arial' });
      //create time tracker;
      timeText = this.add.text(22, 56, 'Time: 0', { fontSize: '16px', fill: '#fff' })
      // Create a text element for player health
      healthText = this.add.text(16, 52, 'Health: ' + healthPoints, { fontSize: '16px', fill: '#fff' });
      //  ****Input Events*****
      cursors = this.input.keyboard.createCursorKeys();

      // Load and add audio

      sfx = this.sound.add('theme');
      sfx.setVolume(0.05); // Adjust the volume as needed (0.5 means 50% volume)
      sfx.play();



      //---------------  Our player animations, turning, walking left and walking right.---------------------------------------------------
      // Define idle animation
      const idleConfig = {
        key: 'idle',
        frames: this.anims.generateFrameNames('knight', { prefix: 'idle/frame', start: 0, end: 5, zeroPad: 4 }),
        frameRate: 12,
        repeat: -1
      };
      this.anims.create(idleConfig);

      const runConfig = {
        key: 'run',
        frames: this.anims.generateFrameNames('knight', { prefix: 'run/frame', start: 0, end: 7, zeroPad: 4 }),
        frameRate: 12,
        repeat: -1
      };
      this.anims.create(runConfig);

      const jumpConfig = {
        key: 'jump',
        frames: this.anims.generateFrameNames('knight', { prefix: 'fall_loop/frame', start: 0, end: 1, zeroPad: 4 }),
        frameRate: 12,
        repeat: -1
      };
      this.anims.create(jumpConfig);
      const dieConfig = {
        key: 'die',
        frames: this.anims.generateFrameNames('knight', { prefix: 'die/frame', start: 0, end: 9, zeroPad: 4 }),
        frameRate: 9,
        repeat: 0
      };
      this.anims.create(dieConfig);

      const spellConfig = {
        key: 'spell',
        frames: this.anims.generateFrameNames('knight', { prefix: 'attack_A/frame', start: 0, end: 12, zeroPad: 4 }),
        frameRate: 24,

      };
      this.anims.create(spellConfig);
      //---------------------enemy movement------------------------------------------------------------------------------------
      this.anims.create({
        key: 'left',
        frames: this.anims.generateFrameNumbers('dude', { start: 0, end: 3 }),
        frameRate: 10,
        repeat: -1
      });

      this.anims.create({
        key: 'turn',
        frames: [{ key: 'dude', frame: 4 }],
        frameRate: 20
      });

      this.anims.create({
        key: 'right',
        frames: this.anims.generateFrameNumbers('dude', { start: 5, end: 8 }),
        frameRate: 10,
        repeat: -1
      });
      //----------------------------boss animation--------------------------------------------------------------
      const idleBossConfig = {
        key: 'idleBoss',
        frames: this.anims.generateFrameNames('knight', { prefix: 'idle/frame', start: 0, end: 5, zeroPad: 4 }),
        frameRate: 6,
        repeat: -1
      };
      this.anims.create(idleBossConfig);

      const bossDashAttackConfig = {
        key: 'bossDashAttack',
        frames: this.anims.generateFrameNames('knight', { prefix: 'attack_C/frame', start: 0, end: 12, zeroPad: 4 }),
        frameRate: 24,
      }
      this.anims.create(bossDashAttackConfig);
      const bossJumpConfig = {
        key: 'bossJump',
        frames: this.anims.generateFrameNames('knight', { prefix: 'fall_loop/frame', start: 0, end: 1, zeroPad: 4 }),
        frameRate: 12,
        repeat: -1
      };
      this.anims.create(bossJumpConfig);
      //--------------------------------------------------------------------------
      // Add a new event listener for the "X" key press:
      this.input.keyboard.on('keydown-X', () => {
        // Check if the player is not already attacking
        if (!isAttacking) {
          isAttacking = true;

          // Play the attack animation and listen for animation complete
          player.anims.play('spell', true);
          player.on('animationcomplete', () => {
            const swordWhooshSound = this.sound.add('swordWhoosh');
            swordWhooshSound.play();
            // The animation has completed; fire the bullet
            shootBullet(player, player.flipX);

            // Reset the flag so that the player can attack again
            isAttacking = false;
          });
        }
      });

      //bombs obj.
      bombs = this.physics.add.group();
      //star obj
      stars = this.physics.add.group({
        key: 'star',
        repeat: 11,
        setXY: { x: 12, y: 0, stepX: 70 }
      });
      stars.children.iterate(function (child) {

        child.setBounceY(Phaser.Math.FloatBetween(0.4, 0.8));

      });

      //  Collide the player and the stars with the platforms
      this.physics.add.collider(player, platforms);
      this.physics.add.collider(stars, platforms);
      this.physics.add.overlap(player, stars, collectStar, null, this);
      //collide enemy with platform stars and player.
      this.physics.add.collider(enemy1, platforms);
      // Collide player with enemy
      this.physics.add.collider(player, enemy1, handlePlayerEnemyCollision, null, this);

      //  Checks to see if the player overlaps with any of the stars, if he does call the collectStar function
      this.physics.add.collider(bombs, platforms);

      this.physics.add.collider(player, bombs, hitBomb, null, this);
      //colider for bullets and platform
      this.physics.add.collider(playerBullets, platforms, (bullet) => {
        // Destroy the bullet when it collides with a platform
        bullet.destroy();
      });
      // Set up a timer to call the incrementTime function every 1000 milliseconds (1 second)
      timer = setInterval(incrementTime, 1000);
      //CAMERA MANAGEMENT
      // Adjust the camera's bounds and position
      this.cameras.main.setBounds(0, 0, background.displayWidth, background.displayHeight + 200); // Adjust the additional 200 as needed
      this.cameras.main.startFollow(player, true, 0.5, 0.5);
      //BULLET MANAGEMENT

      function shootBullet(player) {
        if (player.active === false) {
          return;
        }
        // Get a bullet from the playerBullets group
        const bullet = playerBullets.get().setActive(true).setVisible(true);
        if (bullet) {
          // Adjust the bullet's direction and spawn offset based on the player's direction
          bullet.fire(player, isFacingLeft, 20);
          // Add a collision event to handle damage to the enemy
        }
      }
      //-------------------------Win Message-----------------------------
      winMessage = this.add.text(config.width / 2, config.height / 2, 'You Won!', {
        fontSize: '64px',
        fill: '#ffffff',
        fontFamily: 'Arial'
      });
      winMessage.setOrigin(0.5);
      winMessage.setScrollFactor(0);
      winMessage.setDepth(2); // Ensure the message appears on top of other elements

      // Hide the win message initially
      winMessage.setVisible(false);
      //-------------------------LOSE Message-----------------------------
      loseMessage = this.add.text(config.width / 2, config.height / 2, 'You Lose', {
        fontSize: '64px',
        fill: '#ff0000', // Red color for a lose message
        fontFamily: 'Arial'
      });
      loseMessage.setOrigin(0.5);
      loseMessage.setScrollFactor(0);
      loseMessage.setDepth(2);
      loseMessage.setVisible(false); // Initially, hide the "You Lose" message
      //-------------------------------------GameOVer greyed Screen--------------------------------
      greyOverlay = this.add.rectangle(0, 0, config.width, config.height, 0x000000, 0.7);
      greyOverlay.setOrigin(0);
      greyOverlay.setScrollFactor(0);
      greyOverlay.setDepth(1); // Place it below the win message but above the game elements
      // Hide the grey overlay initially
      greyOverlay.setVisible(false);
    }

    function update() {
      healthText.setText('Health: ' + healthPoints);//update health.
      this.physics.world.overlap(playerBullets, enemy1, enemyHitCallback, null, this); //handle enemy hit by bullet
      this.physics.world.overlap(playerBullets, boss, enemyHitCallback, null, this); //handle boss hit by bullet

      if(enemy1Killed===true && !boss && stage===3 && stars.countActive(true) === 0){
        bossActive=true;
        createBoss.call(this);
      }
      //---------------------------check if player is invincible-----------------------
      if (isInvincible) {
        // Call the function to make the player flicker between red and white tints
        flickerPlayerTint();

        // Add any other invincibility-related logic here
      }
      //---------------------check gameover --------------------------------------
      if (gameOver) {
        sfx.stop();
        this.physics.pause();
        clearInterval(timer); // Stop the timer when the game is over
        if(!scorePosted){
          postNewScore();
          scorePosted=true;
        }

        return;
      }
      //arrow key controls.-------------------------------------------------------------------------------
      if(!gameOver){
      if (cursors.left.isDown) {
        player.setVelocityX(-playerSpeed * 32);
        // Flip the character horizontally when moving left
        player.setScale(-1, 1);
        player.anims.play('run', true);
        // player.anims.play('left', true);
        isFacingLeft = true; // Player is facing left
      }
      else if (cursors.right.isDown) {
        player.setVelocityX(playerSpeed * 32);
        // Reset the character's scale when moving right
        player.setScale(1, 1);
        player.anims.play('run', true);
        // player.anims.play('right', true);
        isFacingLeft = false; // Player is facing right
      }
      else {
        if (this.keyX.isDown) {
          player.anims.play('spell', true);
        } else {
          player.setVelocityX(0);
          player.anims.play('idle', true);
        }
      }
      // Simulate player touching down----------------------------------------------------------------
      if (player.y >= 565) {
        player.body.touching.down = true;
      }
      // Play the jump animation when the player is not touching down (jumping or falling)
      if (!player.body.touching.down) {
        player.anims.play('jump', true);

      }
      if (cursors.up.isDown && player.body.touching.down) {
        player.setVelocityY(-330);
      }
    }
      // Update the position of the score and time text relative to the camera-------------------------------
      scoreText.x = this.cameras.main.scrollX + 16;
      scoreText.y = this.cameras.main.scrollY + 16;
      timeText.x = this.cameras.main.scrollX + 22;
      timeText.y = this.cameras.main.scrollY + 56;
      healthText.x = this.cameras.main.scrollX + 22;
      healthText.y = this.cameras.main.scrollY + 86;
      if (this.cameras.main.scrollX < 0) {
        this.cameras.main.scrollX = 0;
      }

      // Set the camera's position to follow the player's X-axis while keeping the player centered
      this.cameras.main.scrollX = player.x - config.width / 2;


      // ---Enemy movement logic----------------------------------------------------
      if(enemy1 ){

 
      if (enemy1Direction === 'right' && enemy1Health > 0) {
        enemy1.setVelocityX(enemy1Speed * 25);
        enemy1.anims.play('right', true);
      } else if (enemy1Direction === 'left' && enemy1Health > 0) {
        enemy1.setVelocityX(-enemy1Speed * 25);
        enemy1.anims.play('left', true);
        enemy1.flipX = false;
      }

      // Add logic for enemy small jumps (you can adjust this to your needs)
      if (!enemy1Jumping) {
        if (Phaser.Math.Between(1, 100) === 1) { // Adjust the probability as needed
          enemy1.setVelocityY(-200);
          enemy1Jumping = true;
        }
      }
      //--------------------------------------------------------------------------
      // Check if the enemy has reached the edges of the platform
      if (enemy1.x <= 400) {
        enemy1Direction = 'right';
      } else if (enemy1.x >= 600) {
        enemy1Direction = 'left';
      }
    }
      //---------------------------------------------------BOSS LOGIC!--------------------------------------------------------
      // Detect player's position
      if (stage === 3 && enemy1Killed===true) {
        // Check if the boss object exists
        if (bossActive ) {
          const playerX = player.x;
          const bossX = boss.x;

          // Define the boss's movement logic here
          if(!gameOver ){
          if (bossActive && bossHealth > 0) {
            if (!hasDashed) {
              if (player.x < boss.x) {
                // Player is to the left of the boss, so make the boss dash left
                boss.anims.play('bossDashAttack', true);
                boss.setVelocityX(-bossDashSpeed * 30);
                boss.flipX = true;
              } else if (player.x > boss.x) {
                // Player is to the right of the boss, so make the boss dash right
                boss.anims.play('bossDashAttack', true);
                boss.setVelocityX(bossDashSpeed * 30);
                boss.flipX = false;
              } else {
                // Player is at the same position as the boss, so boss can perform other actions
                // For example, make the boss idle or jump
                boss.anims.play('idleBoss', true);
                boss.setVelocityX(0);
                boss.setVelocityY(0);
              }
              hasDashed = true;
              if (boss && bossActive) {
                // Start a timer to reset the boss's velocity and play idle animation
                bossIdleTimer = this.time.addEvent({
                    delay: 1000,
                    callback: () => {
                        // Check if the boss is still in the game
                        if (boss && boss.active) {
                            boss.setVelocityX(0);
                            boss.anims.play('idleBoss', true);
                        }
        
                        // Create a timer to allow the boss to dash again after a certain interval
                        bossIdleTimer = this.time.addEvent({
                            delay: 3000,
                            callback: () => {
                                hasDashed = false;
                            },
                            callbackScope: this,
                        });
                    },
                    callbackScope: this,
                });}
              // // Start a timer to reset the boss's velocity and play idle animation
              // this.time.addEvent({
              //   delay: 1000,
              //   callback: () => {
              //     boss.setVelocityX(0);
              //     boss.anims.play('idleBoss', true);

              //     // Create a timer to allow the boss to dash again after a certain interval
              //     this.time.addEvent({
              //       delay: 3000,
              //       callback: () => {
              //         hasDashed = false;
              //       },
              //       callbackScope: this,
              //     });
              //   },
              //   callbackScope: this,
              // });
            }
          }
        }
        }
      }
    }
    function createBoss() {
      //boss
      // Set properties for the boss (position, animations, etc.)
      boss = this.physics.add.sprite(1000, 50, 'bossKnight');
      boss.setTint(purpleTint);
      boss.setScale(2.2, 2.2);
      boss.setSize(75, 50);
      boss.setOffset(0, 12);
      boss.setCollideWorldBounds(true);

      this.physics.add.collider(boss, playerBullets, enemyHitCallback);
      //collide boss with platform
      this.physics.add.collider(boss, platforms);
      //collide boss with player
      this.physics.add.collider(player, boss, handlePlayerBossCollision, null, this);
      // You might want to set its initial position off-screen or hidden
      // so that it only appears after collecting the stars.
    }
    function collectStar(player, star) {
      const pickUpStarSound = this.sound.add('pickUpStar');
      pickUpStarSound.play();
      star.disableBody(true, true);
      // Add and update the score
      score += 10;
      scoreText.setText('Score: ' + score);
    
      if(stage<3){
        if (stars.countActive(true) === 0 ) {
          // A new batch of stars to collect
          stars.children.iterate(function (child) {
            child.enableBody(true, child.x, 0, true, true);
          });
      
          var x = (player.x < 400) ? Phaser.Math.Between(400, 800) : Phaser.Math.Between(0, 400);
          var bomb = bombs.create(x, 16, 'bomb');
          bomb.setBounce(1);
          bomb.setCollideWorldBounds(true);
          bomb.setVelocity(Phaser.Math.Between(-200, 200), 20);
          stage += 1;
        }
      }

    }
    function hitBomb(player, bomb) {
      // Play the "hurt" sound effect when the player gets hit
      const hurtSound = this.sound.add('hurt');
      hurtSound.play();


      // Reduce player health by the bomb's damage
      healthPoints -= 100;
      if (healthPoints <= 0) {
        // Player is defeated (game over)
        const sfxGameOver = this.sound.add('evilLaugh');
        sfxGameOver.setVolume(0.5);
        sfxGameOver.play();
        gameOver = true;
        player.anims.play('die', true);
        loseMessage.setVisible(true);
        greyOverlay.setVisible(true);
      }


    }
    // Create a function to increment the time variable
    function incrementTime() {
      time += 1;
      timeText.setText('Time: ' + formatTime(time));
    }
    // Create a function to format the time as minutes and seconds
    function formatTime(seconds) {
      const minutes = Math.floor(seconds / 60);
      const remainingSeconds = seconds % 60;
      return `${minutes}:${remainingSeconds < 10 ? '0' : ''}${remainingSeconds}`;
    }

    // Function to handle player-enemy collision
    function handlePlayerEnemyCollision(player, enemy) {
      if (!isInvincible) {
        // Play the "hurt" sound effect when the player gets hit
        const hurtSound = this.sound.add('hurt');
        hurtSound.play();
        // Reduce player health by the enemy's damage
        healthPoints -= enemy1Damage;

        if (healthPoints <= 0) {
          // Player is defeated (game over)

          const sfxGameOver = this.sound.add('evilLaugh');
          sfxGameOver.setVolume(0.5);
          sfxGameOver.play();
          gameOver = true;
          player.anims.play('die', true);
          loseMessage.setVisible(true);
          greyOverlay.setVisible(true);
        }

        // Set the player as invincible and start the timer
        isInvincible = true;

        // Define the duration of invincibility in milliseconds (e.g., 1000ms or 1 second)
        const invincibilityDuration = 1000;

        // Visual indication of invincibility (e.g., character blinking or changing color)
        invincibilityTimer = setInterval(flickerPlayerTint, 200);

        // Start a timer to reset invincibility
        setTimeout(() => {
          isInvincible = false;
          clearInterval(invincibilityTimer); // Stop the tint flicker animation
          player.setTint(); // Reset to the normal color
        }, invincibilityDuration);
      }
    }
    function handlePlayerBossCollision(player, boss) {
      if (!isInvincible) {
        // Play the "hurt" sound effect when the player gets hit
        const hurtSound = this.sound.add('hurt');
        hurtSound.play();
        // Reduce player health by the boss's damage
        healthPoints -= bossDamage;

        if (healthPoints <= 0) {
          // Player is defeated (game over)

          const sfxGameOver = this.sound.add('evilLaugh');
          sfxGameOver.setVolume(0.5);
          sfxGameOver.play();
          gameOver = true;
          player.anims.play('die', true);
          loseMessage.setVisible(true);
          greyOverlay.setVisible(true);
        }

        // Set the player as invincible and start the timer
        isInvincible = true;

        // Define the duration of invincibility in milliseconds (e.g., 1000ms or 1 second)
        const invincibilityDuration = 1000;

        // Visual indication of invincibility (e.g., character blinking or changing color)
        // Implement this part based on your game's graphics.

        // Start a timer to reset invincibility
        invincibilityTimer = setTimeout(() => {
          isInvincible = false;
          // Remove visual indication of invincibility here.
          player.setTint();
        }, invincibilityDuration);

      }
      // Prevent the boss from being pushed by the player
      boss.setVelocityX(0);
    }
    // Function to toggle between red and white tints
    function flickerPlayerTint() {
      if (player.tint === colors.redTint) {
        player.setTint(colors.whiteTint);
      } else {
        player.setTint(colors.redTint);
      }
    }
    function enemyHitCallback(targetHit, bulletHit) {
      if (bulletHit.active === true && targetHit.active === true) {
        if (targetHit === boss) {
          // Handle boss hit
          bossHealth -= playerDamage;

          if (bossHealth <= 0) {
            if(!bossKilled){
              score += 1500; // Add points for defeating the boss
            }
            bossKilled=true;
            // const gameWonSound = this.sound.add('wonGame');
            // gameWonSound.play();
            boss.destroy(); // Destroy the boss
            bossActive=false;
            boss.setActive(false).setVisible(false);
            // Show the win message and grey overlay
            winMessage.setVisible(true);
            greyOverlay.setVisible(true);
            gameOver = true;

          }
        } else {
          // Handle enemy hit
          enemy1Health -= playerDamage;

          if (enemy1Health <= 0) {
            if(!enemy1Killed){
              score += 500; // Add points for defeating an enemy

            }
            enemy1Killed = true;
            targetHit.destroy(); // Destroy the enemy

          }
        }

        // Update the score text
        scoreText.setText('Score: ' + score);

        // Destroy the bullet
        bulletHit.destroy();
      }
    }


    function postNewScore() {
      const requestData = {
        userId:userData.userId,
        username:userData.username,
        score: score,
      };
    
      fetch('http://localhost:8080/api/leaderboard', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData),
      })
        .then((response) => {
          if (response.ok) {
            // The leaderboard has been updated successfully.
            // You can handle this as needed (e.g., showing a success message).
            console.log('Leaderboard updated successfully');
            // return response.json();
          } else {
            // Handle any errors here.
            console.error('Failed to update leaderboard');
            // return response.json();
          }
        })
        .catch((error) => {
          console.error('An error occurred:', error);
        });
    }




    class Bullet extends Phaser.GameObjects.Image {
      constructor(scene, config) {
        super(scene, 0, 0, 'bullet');
        this.speed = 1;
        this.born = 0;
        this.direction = 0;
        this.xSpeed = 0;
        this.ySpeed = 0;
        this.setSize(12, 12, true);
        this.config = config;
      }
      fire(shooter, isFacingLeft, xOffset) {
        // Offset the spawn position based on the player's direction
        const spawnX = isFacingLeft ? shooter.x - xOffset : shooter.x + xOffset;
        this.setPosition(spawnX, shooter.y);

        // Calculate bullet direction based on the player's direction
        this.xSpeed = isFacingLeft ? -this.speed : this.speed;
        this.ySpeed = 0; // No vertical movement

        this.rotation = Math.atan2(this.ySpeed, this.xSpeed);

        this.born = 0;
      }

      update(time, delta) {
        this.x += this.xSpeed * delta;
        this.y += this.ySpeed;
        this.born += delta;

        // Check if the bullet goes out of bounds and hide it.
        if (this.x < 0 || this.x > this.config.width * 2 || this.y < 0 || this.y > this.config.height) {
          this.setActive(false);
          this.setVisible(false);
        }
        //if bullet lasts too long, they disappear.
        if (this.born > 1800) {
          this.setActive(false);
          this.setVisible(false);
        }
      }
    }
    return () => {
      // Clean up the Phaser game instance when the component unmounts
      if (game) {
        sfx.stop();
        clearInterval(timer); // Stop the timer when the game is over
        game.destroy(true, false);
      }
    };
  }, []);

  return (
    <div id="phaser-container">
      {/* Phaser game will be rendered here */}
    </div>
    
  );
};

export default PhaserGame;
