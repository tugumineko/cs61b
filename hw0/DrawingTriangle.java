public class DrawingTriangle{
	public static void drawingTriangle(int N){
			for(int i=0;i<N;i++)
		{	for(int j=0;j<=i;j++)
			{System.out.print("*");}
			System.out.print("\n");}
		
	}
	public static void main(String[] args){
		drawingTriangle(10);
		
	}
	
}