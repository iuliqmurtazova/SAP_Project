package project;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		
		Matrix leftMatrix = new Matrix();
		Matrix rightMatrix = new Matrix();
		Matrix result = new Matrix();
		Multiplication multiply = new Multiplication();
		
		leftMatrix.setPath("C:\\Users\\User\\Downloads\\Java\\Ex3\\left");
		rightMatrix.setPath("C:\\Users\\User\\Downloads\\Java\\Ex3\\right");
		result.setPath("C:\\Users\\User\\Downloads\\Java\\Ex1\\result");
		
		long time = System.currentTimeMillis();
		ReadWriteMatrix.readMatrixAsynch(leftMatrix, rightMatrix);
		System.out.println("Time for asynchronous loading: " + (System.currentTimeMillis() - time));
		
		time = System.currentTimeMillis();
		for (int i = 0; i < 15; i++) {
			result.setArr(multiply.linearMultiplication(leftMatrix, rightMatrix));			
		}
		System.out.println("Time for linear multiplication: " + ((System.currentTimeMillis() - time)) / 15);
		
		Matrix resultasinch = new Matrix();
		time = System.currentTimeMillis();
		resultasinch.setArr(multiply.asynchMultiplication(leftMatrix, rightMatrix));
		System.out.println("Time for asynchronous multiplication: " + (System.currentTimeMillis() - time));
		
		Matrix resultasinch2 = new Matrix();
		time = System.currentTimeMillis();
		for (int i = 0; i < 15; i++) {
			resultasinch2.setArr(multiply.asynchMultiplication(leftMatrix, rightMatrix, 6));			
		}
		System.out.println("Time for asynchronous multiplication with 6 threads: " + ((System.currentTimeMillis() - time)) / 15);
		
		
		time = System.currentTimeMillis();
		for (int i = 0; i < 15; i++) {
			resultasinch2.setArr(multiply.asynchMultiplicationWithDefaultCores(leftMatrix, rightMatrix));			
		}
		System.out.println("Time for asynchronous multiplication with default threads: " + ((System.currentTimeMillis() - time)) / 15);
		
		try {
			ReadWriteMatrix.writeMatrix(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(result.equals(resultasinch));
		System.out.println(resultasinch.equals(resultasinch2));
	  }
}
