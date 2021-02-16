/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaagario.entitys;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;


/**
 *
 * @author Adminb
 */
public class Food extends Ball {

    
    
    public Food(){
        this.mass = 5;
        m_ball = new CircleShape();
        m_ball.setRadius(mass);
        m_ball.setFillColor(Ball.getRandomColor());
        m_ball.setPosition(Vector2f.ZERO);
       
        
    }
    
    public void setPosition(float x, float y){
        m_ball.setPosition(x, y);
    }
    
    public void setPosition(Vector2f pos){
        m_ball.setPosition(pos);
    }
    
    public Vector2f getOriginCoords(){
        return new Vector2f(m_ball.getPosition().x + m_ball.getRadius(), 
                            m_ball.getPosition().y + m_ball.getRadius());
    }
    
    
}
