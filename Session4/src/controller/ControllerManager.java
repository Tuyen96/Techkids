package controller;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Nguyen on 7/31/2016.
 */
public class ControllerManager implements BaseController {
    public Vector<SingleController> singleControllerVector;
    public ControllerManager() {
        singleControllerVector = new Vector<SingleController>();
}
    public void add(SingleController singleController){
        singleControllerVector.add(singleController);
    }
    @Override
    public void draw(Graphics g) {
        synchronized (this.singleControllerVector) {
            Iterator<SingleController> singleControllerIterator =
                    this.singleControllerVector.iterator();
            while(singleControllerIterator.hasNext()) {
                SingleController singleController = singleControllerIterator.next();
                if(singleController.getGameObject().isAlive()) {
                    singleController.draw(g);
                }
            }
        }
    }
    @Override
    public void run() {
        synchronized (this.singleControllerVector) {
            Iterator<SingleController> singleControllerIterator =
                    this.singleControllerVector.iterator();
            while(singleControllerIterator.hasNext()) {
                SingleController singleController = singleControllerIterator.next();
                if(!singleController.getGameObject().isAlive()) {
                    singleControllerIterator.remove();
                } else {
                    singleController.run();
                }
            }
        }
    }
}
