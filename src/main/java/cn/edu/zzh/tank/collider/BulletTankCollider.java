package cn.edu.zzh.tank.collider;

import cn.edu.zzh.tank.Bullet;
import cn.edu.zzh.tank.Expload;
import cn.edu.zzh.tank.GameObject;
import cn.edu.zzh.tank.Tank;

public class BulletTankCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Tank) {
			Bullet bullet = (Bullet)o1;
			Tank tank = (Tank)o2;
			if (tank.getGroup() == bullet.getGroup()) return false;
			if (tank.getRectangle().intersects(bullet.getRectangle())){
				bullet.die();
				tank.die();
				int eX = tank.getX() + Tank.WIDTH/2 - Expload.WIDTH/2;
				int eY = tank.getY() + Tank.HEIGHT/2 - Expload.HEIGHT/2;

				new Expload(eX, eY);
			}

			return true;

		} else if (o1 instanceof Tank && o2 instanceof Bullet) {
			return collide(o2, o1);
		} 
		
		return false;
		
	}

}