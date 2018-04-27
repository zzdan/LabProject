package com;



public class Threaduservisit {
	
	public static void main(String[] args)
	{
		for (int i = 0; i < 1; i++) {
			Thread thread = new Thread(new TestRunnable());
			thread.start();
		}		
	
	}

}