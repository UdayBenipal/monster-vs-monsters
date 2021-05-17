import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy {
    Image enemy = new Image("resources/img/enemy.png", 200, 200, true, true);
    public ImageView enemyView;
    public int n_bump;
    boolean left;
    boolean bump = false;
    double speed;

    Enemy(double lev, float x, Group root) {
        n_bump = 0;
        speed = (lev / 10) * 5;
        enemyView = new ImageView(enemy);
        enemyView.setPreserveRatio(true);
        enemyView.setY(320);
        enemyView.setX(x);
        if(x<0) {
            left=true;
            enemyView.setScaleX(-1);
        } else {
            left=false;
        }
        root.getChildren().add(enemyView);
    }

    public void move(Group root) {
        double x, dx;
        x = enemyView.getX();
        if(left) {
            dx = 2 + speed;
        } else {
            dx = -2 - speed;
        }
        if(bump) {
            dx*=-1;
            if(x < 250 || x > 840) bump = false;
        }
        enemyView.setX(x+dx);
        if(!bump && 360 < x && x < 730) {
            n_bump += 1;
            bump = true;
        }
    }
}
