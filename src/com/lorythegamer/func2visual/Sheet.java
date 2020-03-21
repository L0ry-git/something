package com.lorythegamer.func2visual;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Sheet extends Canvas implements Runnable {
	private static final long serialVersionUID = -6725945092254432152L;

	public static final int WIDTH = 200;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	public Dimension SIZE = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	public static final Dimension SIZE_S = Toolkit.getDefaultToolkit().getScreenSize();

	public static final String NAME = "Func2Visual";
	public BufferedImage pixels = new BufferedImage(SIZE.width, SIZE.height, BufferedImage.TYPE_INT_RGB);

	private JFrame frame;
	
	//parameters
	float k = 1f;
	boolean flag = false;
	
	private static boolean running = false;
	public int timer = 0;

	public Sheet() {
		frame = new JFrame(NAME);
		//frame.setResizable(false);

		setMinimumSize(SIZE);
		setMaximumSize(SIZE);
		setPreferredSize(SIZE);		
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		//frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		//FOR TESTING
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}

	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}

	public synchronized void stop() {
		running = false;
	}

	@Override
	public void run() {
		System.out.println("Visualizer started");
		requestFocus();

		long lastTime = System.nanoTime();
		double nanoPerTick = 1000000000D / 60D;

		int currentFPS = 0;
		int ticks = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		while(running) {
			long now = System.nanoTime();
			boolean isTimeToRender = true;

			delta += (now - lastTime) / nanoPerTick;
			lastTime = now;

			while(delta >= 1) {
				ticks++;
				tickUpdate();

				delta--;
				isTimeToRender = true;
			}

			if(isTimeToRender) {
				currentFPS++;
				render();
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if(System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;

				String frameTitle = NAME + ", " + currentFPS + " FPS, " + 
						ticks + " ticks, " + timer + " updates from start";				
				frame.setTitle(frameTitle);

				currentFPS = 0;
				ticks = 0;
			}

		}
	}

	private void tickUpdate() {
		timer++;
		if(flag) k /= 1.01f;
		else k *= 1.01f;
		
		if(k < 1) flag = false;
		if(k > 45) flag = true;
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if(bs == null) {
			this.createBufferStrategy(3); 
			return;
		}

		Graphics2D gameGraphics = (Graphics2D) bs.getDrawGraphics();

		//Rendering
		TwoVariableFunction func = Functions::testFunc;
		SIZE = getSize();
		pixels = new BufferedImage(SIZE.width, SIZE.height, BufferedImage.TYPE_INT_RGB);
		
		for(int x = 0; x < pixels.getWidth(); x++) {
			for(int y = 0; y < pixels.getHeight(); y++) {	
				float funcValue = func.calculate(x, y, k);
				try {
					Color c = new Color(funcValue > 0 ? funcValue / 2000 : 0, 0, funcValue < 0 ? -funcValue / 2000: 0);			
					setRGB(x, y, c);
				} catch(IllegalArgumentException e) {
					//System.out.println(funcValue);
				}
			}
		}

		gameGraphics.drawImage(pixels, 0, 0, null);

		gameGraphics.dispose();
		bs.show();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setRGB(int x, int y, Color c) {
		this.pixels.setRGB(x, y, c.getRGB());
	}

}
