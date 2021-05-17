import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fire {
    Image fire = new Image("resources/img/fireball2.gif", 50, 50, true, true);
    public ImageView fireView;
    boolean left;
    int boundry;
    public boolean exist = false;

    Fire(boolean flag, Group root, int lev) {
        left = flag;
        boundry = (lev-1)*30;
        exist = true;
        fireView = new ImageView(fire);
        fireView.setPreserveRatio(true);
        fireView.setY(420);
        if(left) {
            fireView.setScaleX(-1);
            fireView.setX(560);
        } else {
            fireView.setX(675);
        }
        root.getChildren().add(fireView);
    }

    public void move(Group root) {
        double x = fireView.getX();
        if(left) {
            x-=5;
        } else {
            x+=5;
        }
        fireView.setX(x);
        if(x < 440+boundry || x > 795-boundry) {
            exist = false;
            root.getChildren().remove(fireView);
        }
    }
}
