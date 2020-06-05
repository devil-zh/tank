package cn.edu.zzh.tank.collider;


import cn.edu.zzh.tank.GameModel;
import cn.edu.zzh.tank.GameObject;

public interface Collider {
	boolean collide(GameObject o1, GameObject o2, GameModel gameModel);
}