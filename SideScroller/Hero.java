import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Hero {

    int heroX, heroY, aladdinCount = 0, count = 0, jumpCount = 0, originalY;
    int[][] locsAndDims, jumpLocsAndDims;
    boolean jumping = false, falling = false, onBox = false, right = false;

    public Hero (int heroX, int heroY, int[][] locsAndDims, int[][] jumpLocsAndDims) {
        this.heroX = heroX;
        this.heroY = originalY = heroY;
        this.locsAndDims = locsAndDims;
        this.jumpLocsAndDims = jumpLocsAndDims;
    }

    public boolean isOnBox(){
        return onBox;
    }

    public void setOnBox(boolean onBox){
        this.onBox = onBox;
    }

    public void updateAladdinCount(int aladdinCount) {
        this.aladdinCount = aladdinCount;
        if (aladdinCount == 13)
            this.aladdinCount = 1;
    }

    public boolean isJumping() {
        return jumping;
    }

    public int getOriginalY(){
        return originalY;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isAbove(Block block) {
    	return heroY+getHeight()-1 < block.getY();
    }
    
    public boolean sameLevel(Block block) {
    	return heroY+getHeight() == block.getY()+50;
    }

    public void zeroJumpCount() {
    	jumpCount = 0;
    }

    public void updateJumping() {
        heroY--;
        count++;

        if(count%25 == 0){
            jumpCount++;
            if(jumpCount==6){
                setJumping(false);
                setFalling(true);
            }
        }
    }

    public void updateFalling(){
        heroY++;
        count++;

        if (count%25 == 0){
            jumpCount++;
            if (jumpCount==12){
                jumpCount = 0;
            }
        }
    }

    public Rectangle2D collisionBelow() {
        return new Rectangle2D.Double(getX(), getY()+1, locsAndDims[0][2] * 2, locsAndDims[0][3] * 2);
    }

    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Double(getX(), getY(), locsAndDims[aladdinCount][2] * 2, locsAndDims[0][3] * 2);
    }
    
    public int getX() {return heroX;}
    public int getY() {return heroY;}
    public int getJumpCount() {return jumpCount;}
    public int getHeight() {return locsAndDims[0][3]*2;}
    public int getAladdinCount() { return aladdinCount; }

    
}
