package com.myscreen.terminal;

public class MyScreenTerminal {
  
  public static void main(String[] args) {
    Subscriber s = new Subscriber();
    s.subscribe();

    while (true) {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        System.out.println("InterruptedException!");    
        s.unSubscribe();
      }
    }    
  }
}
