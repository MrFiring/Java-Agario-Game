
package javaagario;
import java.util.Random;
import org.jsfml.graphics.*;
import org.jsfml.window.*;
import org.jsfml.system.*;
import org.jsfml.window.event.Event;
import javaagario.entitys.*;
import java.util.Vector;
/**
 *
 * @author MrFiring
 */
public class jGame {
    private RenderWindow window;
    private Event evt;
    private Clock clock;
    float dt;
    Ball ball;
    Vector<Food> foods;
    View view;
    public jGame(){
        this.Init();
    }
    
    private void Event(){            
        
        evt = this.window.pollEvent();

        if(evt != null)
        switch(this.evt.type){
        
            case CLOSED:
                window.close();
                break;
            case KEY_PRESSED:
                
                if(evt.asKeyEvent().key == Keyboard.Key.LEFT){
                    ball.Move(-0.1f ,0.0f , dt);
                     view.move(-0.1f / dt ,0.0f / dt);
                }
                if(evt.asKeyEvent().key == Keyboard.Key.RIGHT){
                    ball.Move(0.1f, 0.0f, dt);
                   view.move(0.1f / dt ,0.0f / dt);
                     
                }
                if(evt.asKeyEvent().key == Keyboard.Key.UP){
                    ball.Move(0.0f, -0.1f, dt);
                    view.move(0.0f / dt ,-0.1f / dt);
                }
                if(evt.asKeyEvent().key == Keyboard.Key.DOWN){
                    ball.Move(0.0f, 0.1f, dt);
                    view.move(0.0f / dt ,0.1f / dt);
                }
                
                break;
        }
    }
    
    
    private void Loop(){
        clock = new Clock();
        while(this.window.isOpen()){
            
            this.Event();
            this.Draw();
            this.Update(dt);
            dt = clock.restart().asSeconds();
        }
        
    }
    
    private void Draw(){
        window.clear(Color.WHITE);
        window.setView(view);
        for(int i = 0; i < foods.size(); i++){
            window.draw(foods.get(i));
        }
        
        window.draw(ball);
        
        window.display();
    }
    
    private void Update(float dt){
        ball.Update(dt);
        for(Food food : foods) {
            
            
            if(Ball.eat(ball, food) == Ball.Eated.SECOND){
            foods.remove(food);
            System.out.println("EAT [" + String.valueOf(ball.getPosition().x) + " , " +  String.valueOf(ball.getPosition().y) + "]");
            break;
            }
        }
        
    }
    
    public void Init(){
        window = new RenderWindow(new VideoMode(860, 640),"Java Agario", WindowStyle.TITLEBAR | WindowStyle.CLOSE);
        window.setVerticalSyncEnabled(true);
        
        ball = new Ball();
        view = new View();
        foods = new Vector<Food>();
        
        
        Vector2f lastPos = null;
        
        for(int i = 0; i < 200; i++){
            Food fd = new Food();
            Vector2f pos = null;
        
            if( (pos = generatePos()) != lastPos){
                fd.setPosition(pos);
            }
            
           
            
            foods.add(fd);
            
        }
        float rad = ball.getRadius();
        Vector2f vRad = new Vector2f(rad, rad);
        
        view.setCenter(Vector2f.add(ball.getPosition(), vRad));
        
        
        this.Loop();
              
    }
    

    
    private static Vector2f generatePos(){
        Random rand = new Random();
        
        return new Vector2f( (float)rand.nextInt(860), (float)rand.nextInt(640));
    }
    
}
