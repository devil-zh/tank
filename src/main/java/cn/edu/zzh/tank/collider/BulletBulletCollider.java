package cn.edu.zzh.tank.collider;

import cn.edu.zzh.tank.*;

/**
 * @author zzh
 * @date 2020-06-06 15:09
 * @description
 */
public class BulletBulletCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Bullet) {
            Bullet bullet1 = (Bullet)o1;
            Bullet bullet2 = (Bullet)o2;
            if (bullet1.getGroup() == bullet2.getGroup()) return false;
            if(bullet1.getRectangle().intersects(bullet2.getRectangle())) {
                bullet1.die();
                bullet2.die();
                return true;
            }

        }

        return false;

    }
}
