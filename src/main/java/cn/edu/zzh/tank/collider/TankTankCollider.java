package cn.edu.zzh.tank.collider;

import cn.edu.zzh.tank.Expload;
import cn.edu.zzh.tank.GameModel;
import cn.edu.zzh.tank.GameObject;
import cn.edu.zzh.tank.Tank;

public class TankTankCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Tank && o2 instanceof Tank) {
			Tank tank1 = (Tank)o1;
			Tank tank2 = (Tank)o2;
			if (tank1.getGroup() == tank2.getGroup()) return false;
			if(tank1.getRectangle().intersects(tank2.getRectangle())) {
				tank1.die();
				tank2.die();
				int eX = tank1.getX() + Tank.WIDTH/2 - Expload.WIDTH/2;
				int eY = tank1.getY() + Tank.HEIGHT/2 - Expload.HEIGHT/2;

				GameModel.getInstance().add(new Expload(eX, eY));
				return true;
			}
			
		} 
		
		return false;
		
	}

}