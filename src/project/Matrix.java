package project;

import java.util.Arrays;

public class Matrix {
	
	private double[][] arr;
	private String path;
	
	
	public double[][] getArr() {
		return arr;
	}
	public void setArr(double[][] arr) {
		this.arr = arr;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public int getRow(){
		return arr.length;
	}
	
	public int getCol(){
		return arr[0].length;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matrix other = (Matrix) obj;
		if (!Arrays.deepEquals(arr, other.arr))
			return false;
		return true;
	}
}
