package test;

public class Spiral {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 4;
		int arr[][] = new int[n][n];
		int i = 0;
		int j = 0;
		int rightBound = n-1;
		int leftBound = 0;
		int upperBound = 0;
		int lowerBound = 0;
		int count = 1;
		boolean right = true;
		boolean left = false;
		boolean down = false;
		boolean up = false;
		while (count <= n * n) {
			arr[i][j] = count;
			count++;
			if (right) {
				j++;
				if (j == n - 1) {
					right = false;
					down = true;
				}
			}
			if (left) {
				j--;
				if(j==0) {
					left = false;
					up = true;
				}
			}
			if (down) {
				i++;
				if (i == n - 1) {
					down = false;
					left = true;
				}
			}
			if (up) {
				i--;
				
			}
		}

	}

}
