package cn.edu.zzh.tank;

/**
 * @author zzh
 * @date 2020-06-04 9:20
 * @description
 */
public class DefaultFireStrategy implements FireStrategy{
    private static DefaultFireStrategy instance = new DefaultFireStrategy();
    private DefaultFireStrategy(){}
    public static FireStrategy getInstance() {
        return instance;
    }

    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        new Bullet(bX, bY, tank.getDir(), tank.getGroup(), tank.gameModel);
        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
