import java.io.IOException;
import java.util.Scanner;


public class MainControl {
	public static void main(String[] args) throws IOException{
		start();
	}
	public static void start() throws IOException{
		System.out.print("«Î ‰»Î√¸¡Ó: ");
		Scanner sc=new Scanner(System.in);
		if(sc.next().equals("dir")){
			sc.reset();
			Dir d=new Dir(sc.nextLine());
			d.execute();
		}
	}
}
