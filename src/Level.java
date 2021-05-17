import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;

public class Level {
    int lev;
    Text levelView = new Text(540, 60, "");

    Level() {
        lev = 1;
        levelView = new Text(540, 60, "");
        levelView.setFont(Font.font("Showcard Gothic", 50));
        levelView.setFill(Color.RED);
    }

    void create (int l, ArrayList<Enemy> enemies, Group root) {
        lev = l;
        enemies.clear();
        Random rand = new Random();
        float f;
        for(int i = 0; i < 20; ++i) {
            f = rand.nextFloat();
            f*=6000;
            if(i%2==0) {
                f*=-1;
                f-=200;
            } else {
                f+=1480;
            }
            enemies.add(new Enemy(lev, f, root));
        }
        levelView.setText("LEVEL - " + lev);
    }
}
