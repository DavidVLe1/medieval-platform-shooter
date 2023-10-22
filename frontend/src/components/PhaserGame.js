import React, { useEffect } from 'react';
import Phaser from 'phaser';

const PhaserGame = () => {

  useEffect(() => {
    const config = {
      type: Phaser.AUTO,
      width: 1600,
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
    var playerBullets;
    let isFacingLeft = false;
    var healthpoints;
    var speed;
    var damage;
    var maxHealth;
    var enemy1;
    var enemy2;
    var Boss;

    var game = new Phaser.Game(config);

    function preload() {
      this.load.image('sky', 'assets/sky.png');
      this.load.image('ground', 'assets/platform.png');
      this.load.image('star', 'assets/star.png');
      this.load.image('bomb', 'assets/bomb.png');
      this.load.image('bullet', 'assets/bullet3.png');
      this.load.spritesheet('dude',
        'assets/dude.png',
        { frameWidth: 32, frameHeight: 48 }
      );
    }

    function create() {
      //simple background image for game
      this.add.image(400, 300, 'sky').setScale(6);
      // Set the background color to white
      this.cameras.main.setBackgroundColor('#ffffff'); // Hex color code for white
      //world gravity
      this.physics.world.gravity.y = 300;
      //  The platforms group contains the ground and the 2 ledges we can jump on
      platforms = this.physics.add.staticGroup();
      //  Here we create the ground.
      //  Scale it to fit the width of the game (the original sprite is 400x32 in size)
      platforms.create(800, 568, 'ground').setScale(6).refreshBody();
      //  Now let's create some ledges
      platforms.create(600, 400, 'ground');
      platforms.create(50, 250, 'ground');
      platforms.create(750, 220, 'ground');

      // The player and its settings
      player = this.physics.add.sprite(100, 450, 'dude');
      //  Player physics properties. Give the little guy a slight bounce.
      player.setBounce(0.2);
      player.setCollideWorldBounds(true);
      // Add 1 groups for Bullet objects
      playerBullets = this.physics.add.group({ classType: Bullet, runChildUpdate: true, allowGravity:false});
      //create score;
      scoreText = this.add.text(16, 16, 'Score: 0', { fontSize: '32px', fill: '#000', fontFamily: 'Arial' });
      //create time tracker;
      timeText = this.add.text(22, 56, 'Time: 0', { fontSize: '16px', fill: '#000' })

      //  Input Events
      cursors = this.input.keyboard.createCursorKeys();
      // Add a new event listener for the "X" key press:
      this.input.keyboard.on('keydown-X', () => shootBullet(player, player.flipX));

      // In the create function follow the player/dude
      this.cameras.main.startFollow(player);

      //  Our player animations, turning, walking left and walking right.
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
        }
      }

    }

    function update() {
      if (gameOver) {
        clearInterval(timer); // Stop the timer when the game is over
        return;
      }
      if (cursors.left.isDown) {
        player.setVelocityX(-160);

        player.anims.play('left', true);
        isFacingLeft = true; // Player is facing left
      }
      else if (cursors.right.isDown) {
        player.setVelocityX(160);

        player.anims.play('right', true);
        isFacingLeft = false; // Player is facing right
      }
      else {
        player.setVelocityX(0);

        player.anims.play('turn');
      }

      if (cursors.up.isDown && player.body.touching.down) {
        player.setVelocityY(-330);
      }

      // Update the position of the score and time text relative to the camera
      scoreText.x = this.cameras.main.scrollX + 16;
      scoreText.y = this.cameras.main.scrollY + 16;
      timeText.x = this.cameras.main.scrollX + 22;
      timeText.y = this.cameras.main.scrollY + 56;


    }


    function collectStar(player, star) {
      star.disableBody(true, true);
      //Add and update the score
      score += 10;
      scoreText.setText('Score: ' + score);

      if (stars.countActive(true) === 0) {
        //  A new batch of stars to collect
        stars.children.iterate(function (child) {

          child.enableBody(true, child.x, 0, true, true);

        });

        var x = (player.x < 400) ? Phaser.Math.Between(400, 800) : Phaser.Math.Between(0, 400);

        var bomb = bombs.create(x, 16, 'bomb');
        bomb.setBounce(1);
        bomb.setCollideWorldBounds(true);
        bomb.setVelocity(Phaser.Math.Between(-200, 200), 20);

      }
    }
    function hitBomb(player, bomb) {
      this.physics.pause();

      player.setTint(0xff0000);

      player.anims.play('turn');

      gameOver = true;
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
        if (this.x < 0 || this.x > this.config.width || this.y < 0 || this.y > this.config.height) {
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
  }, []);

  return (
    <div id="phaser-container">
      {/* Phaser game will be rendered here */}
    </div>
  );
};

export default PhaserGame;
