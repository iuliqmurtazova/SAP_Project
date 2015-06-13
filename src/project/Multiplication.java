package project;

public class Multiplication {
	
	public double[][] linearMultiplication(Matrix left, Matrix right){
		
	    double[][] result = new double[left.getRow()][right.getCol()];
	    for (int i = 0; i < left.getRow(); i++) {
              for (int k = 0; k < left.getCol(); k++) {
            	  for (int j = 0; j < right.getCol(); j++) {
            	  result[i][j] += left.getArr()[i][k] * right.getArr()[k][j];
              }
           }
	    }
	    return result;
	    //return asynchMultiplication(left, right, 1);
	    
	}
	
	public double[][] asynchMultiplication(Matrix left, Matrix right){
		return asynchMultiplication(left, right, left.getRow());
	}
	
	public double[][] asynchMultiplicationWithDefaultCores(Matrix left, Matrix right) {
		return asynchMultiplication(left, right, Runtime.getRuntime().availableProcessors());
	}
	
	public double[][] asynchMultiplication(Matrix left, Matrix right, int threadNumber){
	    double[][] result = new double[left.getRow()][right.getCol()];
	    Thread[] thread = new Thread[threadNumber];
	    for (int t = 0; t < thread.length; t++) {
		    final int t2 = t;	
	    	thread[t] = new Thread(){
		    		public void run(){
		    			for (int i = t2; i < left.getRow(); i+= threadNumber) {
		    				for (int k = 0; k < left.getCol(); k++) {
		    					for (int j = 0; j < right.getCol(); j++) {
		    						result[i][j] += left.getArr()[i][k] * right.getArr()[k][j];
		    					}
		    				}	    			
		    			}
		    		}
		    	};
	    }
	    for (Thread thread2 : thread) {
			thread2.start();
		}
	    for (Thread thread2 : thread) {
			try {
				thread2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	    return result;
	    
	}
}
