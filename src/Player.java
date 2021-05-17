import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Player {
    int lives=3;
    int n_score=0;
    String heart = Character.toString(0x2764);
    Text hearts = new Text(580, 595, heart+heart+heart);
    Text score = new Text(5, 595, "SCORE: " + n_score);
    Image monster_idle = new Image("resources/img/idle.png", 300, 300, true, true);
    Image monster_fire = new Image("resources/img/fire.png", 300, 300, true, true);
    ImageView monsterView;

    void createPlayer(Group root) {
        monsterView = new ImageView(monster_idle);
        monsterView.setPreserveRatio(true);
        monsterView.setX(510);
        monsterView.setY(250);
        root.getChildren().remove(monsterView);
        root.getChildren().add(monsterView);
        //////////////////////////////////////////
        score.setFont(Font.font(30));
        score.setFill(Color.WHITE);
        root.getChildren().remove(score);
        root.getChildren().add(score);
        //////////////////////////////////////////
        hearts.setFont(Font.font(40));
        hearts.setFill(Color.WHITE);
        root.getChildren().remove(hearts);
        root.getChildren().add(hearts);
    }

    void left(Group root, boolean flag) {
        root.getChildren().remove(monsterView);
        if(flag) {
            monsterView = new ImageView(monster_fire);
        } else {
            monsterView = new ImageView(monster_idle);
        }
        monsterView.setPreserveRatio(true);
        monsterView.setX(510);
        monsterView.setY(250);
        root.getChildren().add(monsterView);
    }

    void right(Group root, boolean flag) {
        left(root, flag);
        monsterView.setScaleX(-1);
    }
}
