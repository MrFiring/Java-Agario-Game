
package javaagario.entitys;

import java.util.Random;
import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.graphics.FloatRect;
import java.lang.Math;
/**
 *
 * @author MrFiring
 */
public class Ball implements Drawable{
    CircleShape m_ball;
    
    int mass = 40;
    int hungry = 0;
    int speed = 1;
    
    public Ball(){
       m_ball = new CircleShape();
       
       m_ball.setFillColor(getRandomColor());
       m_ball.setRadius(mass);
       m_ball.setPosition(800.f, 600.f);
       m_ball.setOrigin(mass/2, mass/2);
       
    }
  
    @Override
    public void draw(RenderTarget target, RenderStates states) {
        target.draw(m_ball);
    }
    
    public Vector2f getPosition(){
        return this.m_ball.getPosition();
    } //Даёт позицию в пространстве;
    
    public FloatRect getBounds(){
        return m_ball.getGlobalBounds();
    } //Даёт размеры в пространстве;
    
    public void increaseMass(int mass){
        this.mass += mass;
    } //Добавляет массу;
    
    public void Move(float x, float y, float dt){
        this.m_ball.move(x / dt * speed, y / dt * speed);
    } //Двигает объект с учетом deltaTime и Speed
    
    
    public int getMass(){
        return this.mass;
    }
    
    public void Update(float dt){
        this.hungry = this.mass / 100;
        this.speed -= this.mass / 2000;
        
        this.m_ball.setRadius(mass);
        this.m_ball.setOrigin(mass/2, mass/2);
    } //Ф-ция обновления.
    
    public static Color getRandomColor(){
        Random rand = new Random();
        
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
      
        if(r == 0 && g == 0 && b == 0)
            return getRandomColor();
       
     return new Color(r , g ,b);   
    } //Даёт рандомный цвет.
   
    public  boolean Contains(Ball c_ball){
        float rad = this.m_ball.getRadius();
        float x = this.getPosition().x + rad;
        float y = this.getPosition().y + rad;
        
        float rad2 = c_ball.getRadius();
        float x2 = c_ball.getPosition().x + rad2;
        float y2 = c_ball.getPosition().y + rad2;
        
        float radii = rad + rad2;
        float dx =  x2 - x;
        float dy = y2 - y;
       
        return Math.sqrt((dx * dx) + (dy * dy)) <= radii;
    }
        
    public enum Eated{
        NONE ,
        FIRST ,
        SECOND
    }
        
    public static Eated eat(Ball f_ball, Ball s_ball){
        if( (f_ball.getMass() - s_ball.getMass()) > 100 && f_ball.getMass() > s_ball.getMass() ) {
        
            if(f_ball.Contains(s_ball)){
                f_ball.increaseMass(s_ball.getMass()/3);
                return Eated.SECOND;
            }
        }
        else if( (s_ball.getMass() - f_ball.getMass()) > 100 && s_ball.getMass() > f_ball.getMass()) {
            if(s_ball.Contains(f_ball)) {
                s_ball.increaseMass(f_ball.getMass()/3);
                return Eated.FIRST;
            }
        }
        else if( s_ball instanceof Food){
            if(f_ball.Contains(s_ball)) {
            f_ball.increaseMass(s_ball.getMass()/3);
            return Eated.SECOND;
            }
        }
        else if(f_ball instanceof Food){
            if(s_ball.Contains(f_ball)) {
            s_ball.increaseMass(f_ball.getMass()/3);
            return Eated.FIRST;
            }
        }
        
        
        return Eated.NONE;
        
    }
    

    
    public float getRadius(){
        return this.m_ball.getRadius();
    }
}
