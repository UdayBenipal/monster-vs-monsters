import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.media.AudioClip;

public class MiniGame extends Application {
    boolean gameStart = false;
    boolean gameStop = false;
    ArrayList<Fire> fires = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();
    Player p = new Player();
    Text enemyCount = new Text(1055, 595, "");
    Level level = new Level();

    Group welcome() {
        Image background = new Image("resources/img/background2.png", 1280, 598, true, true);
        ImageView backgroundView = new ImageView(background);
        backgroundView.setPreserveRatio(true);
        Group root = new Group(backgroundView);
        //////////////////////////////////////////
        Image logo = new Image("resources/img/logo.png", 600, 600, true, true);
        ImageView logoView = new ImageView(logo);
        logoView.setPreserveRatio(true);
        logoView.setX(320);
        logoView.setY(10);
        root.getChildren().add(logoView);
        //////////////////////////////////////////
        Text controls = new Text(530, 340, "Controls");
        controls.setFont(Font.font("Showcard Gothic", 40));
        controls.setFill(Color.WHITE);
        root.getChildren().add(controls);
        /////////////////////////////////////////
        Text press = new Text(440, 390, "Press " + Character.toString(0x2190) + " Or " +
                Character.toString(0x2192) + " To Fire");
        press.setFont(Font.font("Showcard Gothic", 40));
        press.setFill(Color.WHITE);
        root.getChildren().add(press);
        /////////////////////////////////////////
        Text startgame = new Text(450, 480, "Start Game - Enter");
        startgame.setFont(Font.font("Showcard Gothic", 40));
        startgame.setFill(Color.WHITE);
        root.getChildren().add(startgame);
        ////////////////////////////////////////
        Text quitgame = new Text(560, 530, "Quit - Q");
        quitgame.setFont(Font.font("Showcard Gothic", 40));
        quitgame.setFill(Color.WHITE);
        root.getChildren().add(quitgame);
        ////////////////////////////////////////
        Text name = new Text(5, 545, "UDAY SINGH BENIPAL\nUW ID: 20813206");
        name.setFont(Font.font(40));
        name.setFill(Color.WHITE);
        root.getChildren().add(name);
        ////////////////////////////////////////
        return root;
    }

    Group intermediate(int lev, boolean won) {
        Group root = new Group(gameroot());
        if(won) {
            String sound = getClass().getResource("/resources/sound/won.mp3").toString();
            AudioClip clip = new AudioClip(sound);
            clip.play();
        } else {
            String sound = getClass().getResource("/resources/sound/fail.wav").toString();
            AudioClip clip = new AudioClip(sound);
            clip.play();
        }
        //////////////////////////////////////////
        Rectangle box = new Rectangle(240, 130, 800, 400);
        box.setFill(Color.BEIGE);
        root.getChildren().add(box);
        ///////////////////////////////////////////
        if(!won || level.lev==3) {
            Text msg = new Text(450, 300, "You  WON!");
            if (!won) msg.setText("You  LOOSE");
            msg.setFont(Font.font("Showcard Gothic", 70));
            msg.setFill(Color.RED);
            root.getChildren().add(msg);
        } else {
            Text msg = new Text(300, 240, "LEVEL-" + lev + " COMPLETED!");
            msg.setFont(Font.font("Showcard Gothic", 70));
            msg.setFill(Color.RED);
            root.getChildren().add(msg);
            ///////////////////////////////////////
            Text continueGame = new Text(400, 380, "Press Enter To Continue");
            continueGame.setFont(Font.font("Showcard Gothic", 40));
            continueGame.setFill(Color.RED);
            root.getChildren().add(continueGame);
        }
        ///////////////////////////////////////////
        Text menu = new Text(360, 440, "Press Esc To Return To Menu");
        menu.setFont(Font.font("Showcard Gothic", 40));
        menu.setFill(Color.RED);
        root.getChildren().add(menu);
        return root;
    }

    Group gameroot() {
        Image background = new Image("resources/img/background2.png", 1280, 598, true, true);
        ImageView backgroundView = new ImageView(background);
        backgroundView.setPreserveRatio(true);
        Group root = new Group(backgroundView);
        return root;
    }

    @Override
    public void start(Stage stage) {
        Scene welcomePage = new Scene(welcome(), 1280, 598);

        Group root = gameroot();
        Scene gamePage = new Scene(root, 1280, 598);

        p.createPlayer(root);
        enemyCount.setFont(Font.font(30));
        enemyCount.setFill(Color.WHITE);
        root.getChildren().add(enemyCount);
        root.getChildren().add(level.levelView);

        welcomePage.setOnKeyPressed(keyEvent -> {
            KeyCode key = keyEvent.getCode();
            if(key == KeyCode.Q) {
                stage.close();
            } else if(key == KeyCode.ENTER || key== KeyCode.DIGIT1) {
                p.n_score = 0;
                p.lives=3;
                level.lev=1;
                stage.setScene(gamePage);
                level.create(1, enemies, root);
                gameStart = true;
            } else if(key== KeyCode.DIGIT2) {
                p.n_score = 0;
                p.lives=3;
                level.lev=2;
                stage.setScene(gamePage);
                level.create(2, enemies, root);
                gameStart = true;
            } else if(key== KeyCode.DIGIT3) {
                p.n_score = 0;
                p.lives=3;
                level.lev=3;
                stage.setScene(gamePage);
                level.create(3, enemies, root);
                gameStart = true;
            }
        });

        gamePage.setOnKeyPressed(keyEvent -> {
            KeyCode key = keyEvent.getCode();
            if(key == KeyCode.LEFT) {
                p.left(root, true);
            } else if(key == KeyCode.RIGHT) {
                p.right(root, true);
            }
        });

        gamePage.setOnKeyReleased(keyEvent -> {
            KeyCode key = keyEvent.getCode();
            if(key == KeyCode.LEFT) {
                p.left(root, false);
                fires.add(new Fire(true, root, level.lev));
                String sound = getClass().getResource("/resources/sound/firecast.mp3").toString();
                AudioClip clip = new AudioClip(sound);
                clip.play();
            } else if(key == KeyCode.RIGHT) {
                p.right(root, false);
                fires.add(new Fire(false, root, level.lev));
                String sound = getClass().getResource("/resources/sound/firecast.mp3").toString();
                AudioClip clip = new AudioClip(sound);
                clip.play();
            }
        });



        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (int i = 0; i < fires.size(); i++) {
                    if (fires.get(i).exist) {
                        fires.get(i).move(root);
                    } else {
                        p.n_score -= 1;
                        fires.remove(i);
                        i -= 1;
                    }
                }

                int templives = p.lives;
                for (int i = 0; i < enemies.size(); i++) {
                    enemies.get(i).move(root);
                    templives -= enemies.get(i).n_bump;
                }

                interact(root);
                enemyCount.setText("MONSTERS: " + enemies.size());
                p.score.setText("SCORE: " + p.n_score);
                if(templives==3) {
                    p.hearts.setText(p.heart+p.heart+p.heart);
                } else if(templives==2) {
                    p.hearts.setText(p.heart+p.heart);
                } else if(templives==1) {
                    p.hearts.setText(p.heart);
                } else if(templives < 1 && !gameStop) {
                    p.hearts.setText("");
                    Scene IntermediatePage = new Scene(intermediate(level.lev, false), 1280, 598);
                    stage.setScene(IntermediatePage);
                    gameStop = true;
                    IntermediatePage.setOnKeyReleased(keyEvent -> {
                        KeyCode key = keyEvent.getCode();
                        if(key == KeyCode.ESCAPE) {
                            level.lev=1;
                            p.lives=3;
                            p.n_score=0;
                            for(int i = 0; i < enemies.size(); ++i) {
                                root.getChildren().remove(enemies.get(i).enemyView);
                            }
                            enemies.clear();
                            gameStart = false;
                            gameStop = false;
                            stage.setScene(welcomePage);
                        }
                    });
                }

                if(enemies.size()==0 && gameStart && !gameStop) {
                    Scene IntermediatePage = new Scene(intermediate(level.lev, true), 1280, 598);
                    stage.setScene(IntermediatePage);
                    gameStop = true;
                    IntermediatePage.setOnKeyReleased(keyEvent -> {
                        KeyCode key = keyEvent.getCode();
                        if(key == KeyCode.ENTER && level.lev < 3) {
                            level.lev+=1;
                            stage.setScene(gamePage);
                            level.create(level.lev, enemies, root);
                            gameStart = true;
                            gameStop = false;
                        } else if(key == KeyCode.ESCAPE) {
                            level.lev=1;
                            p.lives=3;
                            p.n_score=0;
                            for(int i = 0; i < enemies.size(); ++i) {
                                root.getChildren().remove(enemies.get(i).enemyView);
                            }
                            enemies.clear();
                            gameStart = false;
                            gameStop = false;
                            stage.setScene(welcomePage);
                        }
                    });
                }
            }
        };
        timer.start();

        stage.setTitle("Monster vs Monsters");
        stage.setScene(welcomePage);
        stage.setResizable(false);
        stage.show();
    }

    void interact(Group root) {
        for (int i = 0; i < enemies.size(); i++) {
            ImageView e = enemies.get(i).enemyView;
            for (int j = 0; j < fires.size(); j++) {
                ImageView f = fires.get(j).fireView;
                boolean left = fires.get(j).left;
                boolean eleft = enemies.get(i).left;
                if((left && eleft && (f.getX() < e.getX() + 190)) || (!left && !eleft && (f.getX() + 40 > e.getX()))) {
                    p.lives-=enemies.get(i).n_bump;
                    root.getChildren().remove(f);
                    root.getChildren().remove(e);
                    String sound = getClass().getResource("/resources/sound/kill_sound.mp3").toString();
                    AudioClip clip = new AudioClip(sound);
                    clip.play();
                    fires.remove(j);
                    enemies.remove(i);
                    p.n_score+=1;
                    i-=1;
                    j-=1;
                }
            }
        }
    }
}