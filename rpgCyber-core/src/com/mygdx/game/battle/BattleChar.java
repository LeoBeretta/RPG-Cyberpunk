package com.mygdx.game.battle;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.animate.Animator;
import com.mygdx.game.animate.Animax;
public class BattleChar extends Animator {
	
	private Animax wait, attack;	
	private boolean atacando = false;
	private int x = -200;

	public BattleChar(String personagem) {
		super("characters/" + personagem + "Battle.png");
		
		String file = "characters/" + personagem + "Battle.png";
		
		width = Gdx.app.getGraphics().getWidth()/13;
    	height = Gdx.app.getGraphics().getHeight()/8;
		
		/* Animacao de parado */
		wait = new Animax();
		
		wait.setTexture(file); 
        wait.setTextureRegions(FRAME_ROWS,FRAME_COLS,1,1,1,3);
        wait.setAnimation(1/3f);      
        wait.setSpriteBatch();                
        wait.setStateTime(0f);
        
        /* Animacao de atacando */
        attack = new Animax();
        
        attack.setTexture(file); 
        attack.setTextureRegions(FRAME_ROWS,FRAME_COLS,4,1,4,3);
        attack.setAnimation(1/3f);      
        attack.setSpriteBatch();                
        attack.setStateTime(0f);
		
	}
	
	@Override
	public void act () {
		if(atacando) {
			attack = animate(attack);
		}
		else
			wait = animate(wait);
	}
	
	@Override
	public Animax animate(Animax play){
    	play.addStateTime( Gdx.graphics.getDeltaTime());           // #15
        play.setTextureRegion( play.getAnimation().getKeyFrame(play.getStateTime(), true));  // #16
        play.getSpriteBatch().begin(); 
        play.getSpriteBatch().draw(play.getTextureRegion(), x+Gdx.app.getGraphics().getWidth()/2, Gdx.app.getGraphics().getHeight()/2, width, height);// #17
        play.getSpriteBatch().end();
        return play;
    }
	
	public void setPositionX(int x) {
		this.x = x;
	}
	
	public void setAtacando(boolean atacando) {
		this.atacando = atacando;
	}

}