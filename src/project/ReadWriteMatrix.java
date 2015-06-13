package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class ReadWriteMatrix {
	
	private ReadWriteMatrix(){
		
	}
	
	public static void readMatrixAsynch(Matrix left, Matrix right){
		Matrix[] matrixArr = {left, right};
		Thread[] threads = new Thread[matrixArr.length];
		for (int i = 0; i < matrixArr.length; i++) {
			final int matrixNumber = i;
			threads[i] = new Thread(){
				public void run(){
					try {
						readMatrix(matrixArr[matrixNumber]);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			threads[i].start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void readMatrix(Matrix matrix) throws IOException{
		
		DataInputStream	dataInput = new DataInputStream(new FileInputStream(matrix.getPath()));
	        int row = dataInput.readInt();
	        int col = dataInput.readInt();
	        double arr[][] = new double[row][col];
	        for(int i=0;i<row;i++){
	        	for(int j=0;j<col;j++){
	        		arr[i][j]=dataInput.readDouble();
	        	}
	        }
	      dataInput.close();
	      matrix.setArr(arr);
	}
	
	public static void writeMatrix(Matrix matrixToSave) throws IOException{
		
		DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream(matrixToSave.getPath()));
		dataOutput.writeInt(matrixToSave.getRow());
		dataOutput.writeInt(matrixToSave.getCol());
		for (int i = 0; i < matrixToSave.getRow(); i++) {
			for (int j = 0; j < matrixToSave.getCol(); j++) {
				dataOutput.writeDouble(matrixToSave.getArr()[i][j]);
			}
		}
		dataOutput.close();
		
	}
}
