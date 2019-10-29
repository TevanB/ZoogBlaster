import java.awt.Color;
public class Rectangle {
 int x, y, width, height, speed;
 Color color;
 Rectangle (int x, int y, int width, int height,int speed, Color color){
  this.x = x;
  this.y =y;
  this.width =width;
  this. height = height;
  this.color = color;
  this.speed=speed;
 }
 public void setColor(Color newColor){
   color = newColor;
 }
 public Color getColor( ){
   return color;
 }
 public void moveLeft( ){
	   x-=speed;
	 }
	 public void moveRight( ){
	   x+=speed;
	 }
	 public void moveUp( ){
	   y-=speed;
	 }
	 public void moveDown( ){
	   y+=speed;
	 }
	 boolean intersects (Rectangle other){
	  return ((x < other.x + other.width)&& (y < other.y + other.height) && 
	          (x + width > other.x ) && (y + height > other.y));
	 }
	}