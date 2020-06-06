package cn.edu.zzh.tank.collider;

import cn.edu.zzh.tank.GameModel;
import cn.edu.zzh.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zzh
 * @date 2020-06-05 16:41
 * @description
 */
public class ColliderChain implements Collider {
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
        add(new BulletBulletCollider());
        add(new BulletWallCollider());
    }


    public void add(Collider c) {
        colliders.add(c);
    }


    public boolean collide(GameObject o1, GameObject o2) {
        for(int i=0; i<colliders.size(); i++) {
            if(colliders.get(i).collide(o1, o2)) {

                return true;
            }
        }

        return false;
    }
}
