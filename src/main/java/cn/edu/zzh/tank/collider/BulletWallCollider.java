package cn.edu.zzh.tank.collider;

import cn.edu.zzh.tank.*;

/**
 * @author zzh
 * @date 2020-06-06 15:09
 * @description
 */
public class BulletWallCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet bullet1 = (Bullet)o1;
            Wall wall = (Wall)o2;
            if(bullet1.getRectangle().intersects(wall.rectangle)) {
                bullet1.die();
                return true;
            }

        }else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }

        return false;

    }
}
