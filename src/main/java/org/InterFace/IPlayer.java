package org.InterFace;

import org.Player.Player;

public interface IPlayer {
    public Player Player(String Name, String Movement, String Shape, String Weapon, int Health);
    public Object GetCircle(String Name,String Movement,String Shape,String Weapon,int Health);
    public Object GetRectangle(String Name,String Movement,String Shape,String Weapon,int Health);
    public Object GetEllipse(String Name,String Movement,String Shape,String Weapon,int Health);
}
