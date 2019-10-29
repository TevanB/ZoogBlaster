import java.util.*;
public class MonsterDNA {
	ArrayList<Integer> dnaArray;
	double upChance=0.25;
	double downChance=0.25;
	double leftChance=0.25;
	double rightChance=0.25;
	MonsterDNA(){
		initDNA();
	}
	public void initDNA() {
		dnaArray= new ArrayList<Integer>();
		for(int i=0; i<5; i++) {
			int randomInt = (int)(4*Math.random());
			dnaArray.add(randomInt);
			//System.out.println(randomInt);
		}
	}	
	public Integer getDNA(int pos) {
		Integer f = new Integer(dnaArray.get(pos));
		int converted = f.intValue();
		return converted;
	}	
	public void chanceReset() {
		upChance=0.25;
		downChance=0.25;
		leftChance=0.25;
		rightChance=0.25;
	}	
	public void geneticMixup(int moveCounter, int locX, int locY) {
		locationChanceFactor(moveCounter, locX, locY);
	}
	public void locationChanceFactor(int moveCounter, int locX, int locY) {
		moveCounter=0;
		//code to implement random system
		if(locX<300) {
			//System.out.println("RIGHT");
			upChance=0.15;
			downChance=0.15;
			leftChance=0.15;
			rightChance=0.55;
			//System.out.print(" "+rightChance);
		}
		else if(locX>700) {
			//System.out.println("LEFT");
			upChance=0.15;
			downChance=0.15;
			leftChance=0.55;
			rightChance=0.15;
		}
		else if(locY<300) {
			//System.out.println("DOWN");
			upChance=0.15;
			downChance=0.55;
			leftChance=0.15;
			rightChance=0.15;
		}
		else if(locY>700) {
			//System.out.println("UP");
			upChance=0.55;
			downChance=0.15;
			leftChance=0.15;
			rightChance=0.15;
		}
		else  {
			upChance=0.25;
			downChance=0.25;
			leftChance=0.25;
			rightChance=0.25;
		}
		for(int i=0; i<5; i++) {
			double randomInt = Math.random();
			//System.out.println("Random Num is "+randomInt);
			if(upChance==0.55) {
				
				if(randomInt>=0.45) {
					dnaArray.remove(i);
					dnaArray.add(i, 0);
				}
				else if(randomInt>=0.30) {
					dnaArray.remove(i);
					dnaArray.add(i, 1);
				}
				else if(randomInt>=0.15) {
					dnaArray.remove(i);
					dnaArray.add(i, 2);
				}
				else if(randomInt>=0) {
					dnaArray.remove(i);
					dnaArray.add(i, 3);
				}
			}
			if(downChance==0.55) {
			
				if(randomInt>=0.45) {
					dnaArray.remove(i);
					dnaArray.add(i, 2);
				}
				else if(randomInt>=0.30) {
					dnaArray.remove(i);
					dnaArray.add(i, 1);
				}
				else if(randomInt>=0.15) {
					dnaArray.remove(i);
					dnaArray.add(i, 0);
				}
				else if(randomInt>=0) {
					dnaArray.remove(i);
					dnaArray.add(i, 3);
				}
			}
			if(leftChance==0.55) {
				if(randomInt>=0.45) {
					dnaArray.remove(i);
					dnaArray.add(i, 3);
				}
				else if(randomInt>=0.30) {
					dnaArray.remove(i);
					dnaArray.add(i, 1);
				}
				else if(randomInt>=0.15) {
					dnaArray.remove(i);
					dnaArray.add(i, 2);
				}
				else if(randomInt>=0) {
					dnaArray.remove(i);
					dnaArray.add(i, 0);
				}
			}
			if(rightChance==0.55) {
				//System.out.println("second stage");
				if(randomInt>=0.45) {
					dnaArray.remove(i);
					dnaArray.add(i, 1);
				}
				else if(randomInt>=0.30) {
					dnaArray.remove(i);
					dnaArray.add(i, 0);
				}
				 else if(randomInt>=0.15) {
					dnaArray.remove(i);
					dnaArray.add(i, 2);
				}
				 else  if(randomInt>=0) {
					dnaArray.remove(i);
					dnaArray.add(i, 3);
				}
			}
		
		}
	}
	
	public int priorityCheck(int locX, int locY) {
		if (locX<locY) {
			return 2;
		}
		if (locY<locX) {
			return 2; //figure it out
		}
		return 2;
	}
}
