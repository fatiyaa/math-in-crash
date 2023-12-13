package com.mathincrash.state;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import com.mathincrash.math.CountObject;
import com.mathincrash.math.CountValue;
import com.mathincrash.math.Operator;
import com.mathincrash.ui.Button;
import com.mathincrash.ui.GamePanel;
import com.mathincrash.util.MakeImage;
import com.mathincrash.ui.InputField;

public class MathState extends State {
	private ArrayList<CountObject> leftSide, rightSide,
			toBeRemovedFromLeft, toBeRemovedFromRight, toBeAddToRight, toBeAddToLeft;
	private int leftValue, rightValue;
	private CountValue leftEval, rightEval;
	private int side = gp.tileSize;
	private Random rand;
	private Operator operator;
	private Image background;
	private Button button;
	public InputField input;
	private int answer;

	public MathState(GamePanel gp) {
		super(gp);
		leftSide = new ArrayList<CountObject>();
		rightSide = new ArrayList<CountObject>();
		toBeRemovedFromLeft = new ArrayList<CountObject>();
		toBeRemovedFromRight = new ArrayList<CountObject>();
		toBeAddToRight = new ArrayList<CountObject>();
		toBeAddToLeft = new ArrayList<CountObject>();

		rand = new Random();
		leftValue = rand.nextInt(-1, 1);
		rightValue = rand.nextInt(-1, 1);
		leftEval = new CountValue(side + side / 2, 4 * side + side / 2, side, side / 2, 0);
		rightEval = new CountValue(gp.screenWidth - (2 * side + side / 2), 4 * side + side / 2, side, side / 2, 0);
		setValue();

		leftSide = setList(leftValue);
		rightSide = setList(rightValue);
		side += 3 * gp.tileSize;
		operator = new Operator(gp);
		evaluate();

		String imagePath = "assets/math/Board.png";
		this.background = new MakeImage(imagePath, gp.tileSize * 5, gp.tileSize * 6).getImage();

	}

	private void setValue() {
		if (leftValue == 0)
			leftValue++;
		if (rightValue == 0)
			rightValue++;
	}

	private ArrayList<CountObject> setList(int value) {
		int n = rand.nextInt(1, 21);
		ArrayList<CountObject> list = new ArrayList<CountObject>();
		if (list.isEmpty()) {
			list.add(new CountObject(gp, side + 20, 2 * gp.tileSize - 10, value));
		}
		for (int i = 0; i < n; i++) {
			int xco = rand.nextInt(side + 10, side + 2 * gp.tileSize - 40);
			int yco = rand.nextInt(2 * gp.tileSize, 4 * gp.tileSize);
			CountObject co = new CountObject(gp, xco, yco, value);
			boolean crash = false;
			for (CountObject cobj : list) {
				if (cobj.crashed(co))
					crash = true;
			}
			if (!crash) {
				list.add(co);
			}
		}
		return list;
	}

	private void evaluate() {
		if (operator.operator == true) {
			answer = (this.leftSide.size() * this.leftValue) + (this.rightSide.size() * this.rightValue);
		} else {
			answer = (this.leftSide.size() * this.leftValue) - (this.rightSide.size() * this.rightValue);
		}
	}

	public void crashingCase() {
		for (CountObject col : leftSide) {
			if (gp.mouseH.mouseDragged==false) col.update();
			else if (col.status)col.update();
			for (CountObject cor : rightSide) {
				if (gp.mouseH.mouseDragged==false) cor.update();
				else if (cor.status)cor.update();
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
							} else if (col.status) {
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
		for (CountObject co : this.toBeAddToRight) {
			rightSide.add(co);
		}
		for (CountObject co : this.toBeAddToLeft) {
			leftSide.add(co);
		}
		removeAll();
		this.toBeAddToRight.clear();
		this.toBeAddToLeft.clear();
	}

	private void removeAll() {
		for (CountObject co : this.toBeRemovedFromLeft) {
			leftSide.remove(co);
		}
		for (CountObject co : this.toBeRemovedFromRight) {
			rightSide.remove(co);
		}
		this.toBeRemovedFromLeft.clear();
		this.toBeRemovedFromRight.clear();
	}

	@Override
	public void update() {
		leftEval.setValue(leftValue * leftSide.size());
		rightEval.setValue(rightValue * rightSide.size());
		// input.update();
		crashingCase();
		if (button.state == Button.submitted) {
			if (input.getAnswer() == this.answer) {
				gp.gameState = GamePanel.playState;
				gp.resetContinue(50);
			} else {
				gp.gameState = GamePanel.endState;
			}
		}
		button.update();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(background, gp.tileSize, gp.tileSize, null);
		// g.drawRect(gp.tileSize, gp.tileSize, gp.tileSize*5, gp.tileSize*6);
		for (CountObject co : leftSide) {
			co.draw(g);
		}
		for (CountObject co : rightSide) {
			co.draw(g);
		}
		rightEval.draw(g);
		leftEval.draw(g);
		button.draw(g);
		input.draw(g);
		operator.draw(g);
	}

	public void reset() {
		leftSide.clear();
		rightSide.clear();
		leftSide = new ArrayList<CountObject>();
		rightSide = new ArrayList<CountObject>();
		leftValue = rand.nextInt(-1, 1);
		rightValue = rand.nextInt(-1, 1);
		// leftEval = new CountValue(side + side/2, 4*side+side/2, side, side/2, 0);
		// rightEval = new CountValue(gp.screenWidth - (2*side+ side/2) , 4*side+side/2,
		// side, side/2, 0);
		setValue();
		side -= 3 * gp.tileSize;
		leftSide = setList(leftValue);
		side += 3 * gp.tileSize;
		rightSide = setList(rightValue);
		operator.generate();
		button = new Button(gp, "submit", 3 * gp.tileSize + gp.tileSize / 4, 6 * gp.tileSize, gp.tileSize / 2,
				gp.tileSize / 2);
		input = new InputField(gp, "Jawaban", 3 * gp.tileSize, 5 * gp.tileSize + gp.tile, gp.tileSize, gp.tileSize / 2);
		evaluate();
	}

}
