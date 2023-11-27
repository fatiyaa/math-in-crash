package com.mathincrash.state;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import com.mathincrash.math.CountObject;
import com.mathincrash.math.CountValue;
import com.mathincrash.math.Operator;
import com.mathincrash.ui.GamePanel;

public class MathState extends State {
	private ArrayList <CountObject> leftSide, rightSide, 
	toBeRemovedFromLeft, toBeRemovedFromRight, toBeAddToRight, toBeAddToLeft;
	private int leftValue, rightValue;
	private CountValue leftEval, rightEval;
	private int side = gp.tileSize;
	private Random rand;
	private Operator operator;
	
	public MathState(GamePanel gp) {
		super(gp);
		leftSide = new ArrayList<CountObject>();
		rightSide = new ArrayList<CountObject>();
		toBeRemovedFromLeft = new ArrayList<CountObject>();
		toBeRemovedFromRight = new ArrayList<CountObject>();
		toBeAddToRight = new ArrayList<CountObject>();
		toBeAddToLeft = new ArrayList<CountObject>();
		rand = new Random();
		leftValue = rand.nextInt(-1,1);
		rightValue = rand.nextInt(-1,1);
		leftEval = new CountValue(side + side/2, 4*side+side/2, side, side/2, 0);
		rightEval = new CountValue(gp.screenWidth - (2*side+ side/2) , 4*side+side/2, side, side/2, 0);
		setValue();
		leftSide = setList(leftValue);
		side += 3*gp.tileSize;
		rightSide = setList(rightValue);
		operator = new Operator(gp);
		
		// TODO Auto-generated constructor stub
	}
	private void setValue() {
		if(leftValue == 0) leftValue++;
		if(rightValue == 0) rightValue++;
	}
	
	private ArrayList<CountObject> setList(int value){
		int n = rand.nextInt(1,21);
		ArrayList<CountObject> list = new ArrayList<CountObject>();
		if(list.isEmpty()) {
			list.add(new CountObject(gp, side+10, 2*gp.tileSize, value));
		}
		for(int i = 0; i<n; i++) {
			int xco = rand.nextInt(side+10, side+2*gp.tileSize-30);
			int yco = rand.nextInt(2*gp.tileSize, 4*gp.tileSize);
			CountObject co = new CountObject(gp, xco, yco,value);
			boolean crash = false;
			for(CountObject cobj : list) {
				if(cobj.crashed(co))crash = true;
			}
			if(!crash) {
				list.add(co);
			}
		}
		return list;
	}
	
	public void crashingCase() {
		for (CountObject col : leftSide) {
		    col.update();
		    for (CountObject cor : rightSide) {
		        cor.update();
		        if (col.crashed(cor)) {
		            if (operator.stringOperator.equals("+")) {
		                if (col.val != cor.val) {
		                    toBeRemovedFromLeft.add(col);
		                    toBeRemovedFromRight.add(cor);
		                    break;
		                } else {
		                	if (cor.status) {
			                	cor.val = col.val;
			                	this.toBeAddToLeft.add(cor);
			                	toBeRemovedFromRight.add(cor);
			                	break;
		                	}else if (col.status) {
		                        col.val = cor.val;
		                        this.toBeAddToRight.add(col);
		                        toBeRemovedFromLeft.add(col);
		                        break;
		                    }
		                }
		            } else if (operator.stringOperator.equals("-")) {
		                if (col.val == cor.val) {
		                    toBeRemovedFromLeft.add(col);
		                    toBeRemovedFromRight.add(cor);
		                    break;
		                } else {
		                	if (cor.status) {
			                	cor.val = col.val;
			                	this.toBeAddToLeft.add(cor);
			                	toBeRemovedFromRight.add(cor);
			                	break;
		                	} else if (col.status) {
		                        col.val = cor.val;
		                        this.toBeAddToRight.add(col);
		                        toBeRemovedFromLeft.add(col);
		                        break;
		                    }
		                }
		            }
		        }
		    }
		}
		addAll();
	}
	
	private void addAll() {
		for(CountObject co : this.toBeAddToRight) {
			rightSide.add(co);
		}
		for(CountObject co : this.toBeAddToLeft) {
			leftSide.add(co);
		}
		removeAll();
		this.toBeAddToRight.clear();
		this.toBeAddToLeft.clear();
	}
	
	private void removeAll() {
		for(CountObject co : this.toBeRemovedFromLeft) {
			leftSide.remove(co);
		}
		for(CountObject co : this.toBeRemovedFromRight) {
			rightSide.remove(co);
		}
		this.toBeRemovedFromLeft.clear();
		this.toBeRemovedFromRight.clear();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		leftEval.setValue(leftValue*leftSide.size());
		rightEval.setValue(rightValue*rightSide.size());
		crashingCase();
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawRect(gp.tileSize, gp.tileSize, gp.tileSize*5, gp.tileSize*6);
		for(CountObject co : leftSide) {
			co.draw(g);
		}
		for(CountObject co : rightSide) {
			co.draw(g);
		}
		rightEval.draw(g);
		leftEval.draw(g);
		operator.draw(g);
	}

}
