package it.univr.GenThreadsCasualArray;

import java.util.Random;

public class GenThreadsCasualArray{
	Random random = new Random();
	public int array[][];
	int y=0;
		
	public GenThreadsCasualArray(int numOfThreads, int y, int x){
			array = new int[y][x];
			SingleThread[] slaves = createThreads(numOfThreads);
			waitForThreadsToFinish(slaves);
		}
	public int[][] getArray(){
		return this.array;
	}
	public SingleThread[] createThreads(int numOfThreads){
			SingleThread[] slaves = new SingleThread[numOfThreads];
			for(int pos=0; pos < numOfThreads; pos++){
				(slaves[pos] = new SingleThread()).start();
			}
			return slaves;
		}
	private void waitForThreadsToFinish(SingleThread[] slaves) {
			// aspettiamo che abbiano finito di lavorare
			for (SingleThread slave: slaves)
				try {
					slave.join();
				}
				catch (InterruptedException e) {
					// qualcuno ci ha interrotti mentre aspettavamo
				}
		}
	public class SingleThread extends Thread{
			int yThread;
			@Override
			public void run(){
				do{
					synchronized(GenThreadsCasualArray.this){
						yThread=y;
						y++;
					}
					if(yThread < array.length)
						for(int x=0; x < array[0].length;x++){
							array[yThread][x] = random.nextInt();
					}
				}
				while(yThread < array.length);
			}
		}
	
	public static void main(String[] args) {
		new GenThreadsCasualArray(2, 1000, 1000);	
		
	}
	
}