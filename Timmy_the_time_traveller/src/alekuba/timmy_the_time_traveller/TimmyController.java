package alekuba.timmy_the_time_traveller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
/**
 * Class to control all Timmy related actions.
 * @author Aleks, Kuba
 *
 */
public class TimmyController {
	private Timmy timmy;
	private boolean buttonPressed, jumping, harmed, collidedFloor, collidedLeft, collidedRight, surpressJump, depleted;
	private State state;
	private int maxJumpHeight = 30, jumpHeight = 0, maxHp = 3, hp = 3, punish = 0, maxPunish = 5;

	public TimmyController(Bitmap bmp) {
		this.timmy = new Timmy(bmp, 100, 150, 5, false);
		timmy.setCurrentRow(Timmy.timmy_right);
		timmy.setStopped(true);
		buttonPressed = false;
		this.state = State.NOT_MOVING;
		depleted = false;
	}
	
	private void fall(){
		timmy.setY(timmy.getY()+Timmy.speed);
	}
	/**
	 * Call this to move Timmy to the left.
	 */
	public void goLeft() {
		buttonPressed = true;
		state = State.MOVE_LEFT;
		timmy.setStopped(false);
		timmy.setCurrentRow(Timmy.timmy_left);
		timmy.setX(timmy.getX()-Timmy.speed);
	}
	/**
	 * Call this to move Timmy to the right.
	 */
	public void goRight() {
		buttonPressed = true;
		state = State.MOVE_RIGHT;
		timmy.setStopped(false);
		timmy.setCurrentRow(Timmy.timmy_right);
		timmy.setX(timmy.getX()+Timmy.speed);
	}
	/**
	 * Call this to make Timmy jump.
	 */
	public void jump(){
			jumping = true;
			if(jumpHeight < maxJumpHeight){
				timmy.setY(timmy.getY()-Timmy.speed);
				jumpHeight ++;
			}
			else
				jumping = false;
	}
	/**
	 * Call this in update() to keep Timmy moving.
	 */
	public void calculateMovements() {
		if(!collidedFloor && !jumping){
			surpressJump = true; //surpress jumping when falling
			fall();
			jumpHeight = 0;
		}
		else if(jumping && !surpressJump)
			jump();
		if(harmed){
			if(state.equals(State.MOVE_LEFT))
				timmy.setX(timmy.getX()+10);
			else
				timmy.setX(timmy.getX()-10);
			timmy.setY(timmy.getY()-10);
			punish ++;
			if(punish == maxPunish){
				harmed = false;
				punish = 0;
			}
		}
		if(buttonPressed) {
			if(state.equals(State.MOVE_LEFT) && !collidedLeft) {
				goLeft();
				collidedRight = false;
			} 
			if(state.equals(State.MOVE_RIGHT) && !collidedRight) {
				goRight();
				collidedLeft = false;
			}
		}
		collidedFloor = false;
	}
	/**
	 * Back in Time button trigger.
	 */
	public void goBackInTime(){
		depleted = true;
		timmy.setX(100);
		timmy.setY(100);
	}
	/**
	 * Call this to let controller know, arrow buttons have been released.
	 */
	public void buttonReleased() {
		buttonPressed = false;
		state = State.NOT_MOVING;
		timmy.setStopped(true);
	}
	/**
	 * Call this when Timmy hurts himself.
	 * @param r collided Rect
	 */
	public void harm(Rect r){
		hp-=1;
		if(Math.abs(timmy.getHitbox().right - r.left) > Math.abs(timmy.getHitbox().left - r.right))
			state=State.MOVE_LEFT;
		else
			state=State.MOVE_RIGHT;
		harmed = true;
	}
	/**
	 * Call this when Timmy falls into a void.
	 */
	public void harmFall(){
		hp-=1;
		//respawn
		timmy.setX(100);
		timmy.setY(150);
	}
	/**
	 * Call this when Timmy collides with anything
	 * @param r collided Rect
	 */
	public void collision(Rect r){
		int top, bottom, left, right;
		top = Math.abs(r.bottom - timmy.getHitbox().top);
		bottom = Math.abs(r.top - timmy.getHitbox().bottom);
		left = Math.abs(r.right - timmy.getHitbox().left);
		right = Math.abs(r.left - timmy.getHitbox().right);
		if(top < 5){
			jumping = false;
		}
		if(bottom < 5){
			this.collidedFloor = true;
			surpressJump = false;
		}
		if(right < 5){
			this.collidedRight = true;
		}
		if(left < 5){
			this.collidedLeft = true;
		}
	}
	/**
	 * Call this if Timmy didn't collide.
	 */
	public void noCollision(){
		this.collidedFloor = false;
		this.collidedLeft = false;
		this.collidedRight = false;
	}
	/**
	 * Used for setting sprite's direction.
	 *
	 */
	public enum State {
		MOVE_LEFT,
		MOVE_RIGHT,
		NOT_MOVING;
	}
	
	public State getState() {
		return this.state;
	}
	
	public boolean getSurpressJump(){
		return this.surpressJump;
	}
	public boolean isCollidedFloor() {
		return collidedFloor;
	}
	
	public Rect getHitbox(){
		return timmy.getHitbox();
	}	

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	public boolean isHarmed(){
		return this.harmed;
	}
	public boolean isDepleted(){
		return this.depleted;
	}
	public void drawTimmy(Canvas c){
		this.timmy.draw(c);
	}
	public Timmy getTimmy(){
		return this.timmy;
	}
	public int getX(){
		return this.timmy.getX();
	}
	public int getY(){
		return this.timmy.getY();
	}
	public void setX(int i){
		this.timmy.setX(i);
	}
	public void setY(int i){
		this.timmy.setY(i);
	}
}
