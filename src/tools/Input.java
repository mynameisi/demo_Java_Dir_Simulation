package tools;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public final class Input {
	private String str = null;
	public Input(String f) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(f));
		str = sc.next();
	}
	public void getUDOE() {
		if (str.charAt(0) == 'u' || str.charAt(0) == 'd') {
			Constants.ud = str.charAt(0);
		}else if(str.charAt(0) != 'u' && str.charAt(0) != 'd'){
			Msg.errorMessage("请确保文件中内容第一个字符串是 u[金字塔向上] 或者是 d[金字塔向下]");
		}
		String temp="";
		if (str.charAt(1) == 'o' || str.charAt(1) == 'e') {
			Constants.ud = str.charAt(1);
			temp=str.substring(2, str.length());
		}else{
			temp=str.substring(1, str.length());
		}
		try{
			Constants.level=Integer.parseInt(temp);
		}catch(Exception e){
			Msg.errorMessage("请输入合理的金字塔层数");
		}
	}
}
