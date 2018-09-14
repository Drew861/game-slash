import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import launch.pack.window.Display;

public class Game implements Runnable {

 private Display display;
 private int width;
 private int height;
 private Boolean running = false;
 public String title;

 private BufferStrategy bs;
 private Graphics g;


 private Thread thread;

 public Game(String title, int width, int height) {
  this.width = width;
  this.height = height;
  this.title = title;




 }

 private void init() {
  display = new Display(title, width, height);
 }

 private void tick() {

 }

 private void render() {
  bs = display.getCanvas().getBufferStrategy();
  if (bs == null) {
   display.getCanvas().createBufferStrategy(3);
   return;

  }

  g = bs.getDrawGraphics();

  //ClearScreen
  g.clearRect(0, 0, width, height);
  //DrawHere!


  g.drawRect(10, 50, 50, 70);

  //EndDraw!

  bs.show();
  g.dispose();

 }


 public void run() {
  init();


  while (running) {

   tick();
   render();
  }
  stop();
 }


 public synchronized void start() {
  if (running)
   return;
  running = true;
  thread = new Thread(this);
  thread.start();

 }

 public synchronized void stop() {
  if (!running)
   return;
  running = false;
  try {
   thread.join();
  } catch (InterruptedException e) {
   e.printStackTrace();
  }

 }

}
