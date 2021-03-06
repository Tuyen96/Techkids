package controllers.Bombs;

import controllers.Behavior.ZigzagFlyBehavior;
import controllers.Colliable;
import controllers.CollsionPool;
import controllers.Behavior.FlyBehavior;
import controllers.PlaneController;
import controllers.SingleController;
import models.Lock;
import views.GameDrawer;
import views.ImageDrawer;

/**
 * Created by qhuydtvt on 8/10/2016.
 */
public class LockController extends SingleController implements Colliable {
    private FlyBehavior flyBehavior;
    public LockController(Lock gameObject, GameDrawer gameDrawer, FlyBehavior flyBehavior) {
        super(gameObject, gameDrawer);
        this.flyBehavior = flyBehavior;
        CollsionPool.instance.add(this);
    }

    public static LockController create(int x, int y) {
        return new LockController(new Lock(x,y),
                new ImageDrawer("resources/lock.png"),
                new ZigzagFlyBehavior()
        );
    }
    public void run(){
        super.run();
        flyBehavior.doFly(this);
    }
    @Override
    public void onCollide(Colliable colliable) {
        if(colliable instanceof PlaneController) {
            NotificationCenter.instance.onFrezze(
                    gameObject.getX(),
                    gameObject.getY()
            );
            gameObject.destroy();
        }
    }
}
