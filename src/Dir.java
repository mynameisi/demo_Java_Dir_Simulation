import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;
import tools.Msg;


public class Dir {
	
	public static int indent=0;
	private String path=null;
	private boolean hide_readOnly=true;
	private boolean alList=false;
	
	private static int fileCount=0;
	private static int dirCount=0;
	
	
	
	
	public boolean isHide_readOnly() {
		return hide_readOnly;
	}
	public void setHide_readOnly(boolean hide_readOnly) {
		this.hide_readOnly = hide_readOnly;
	}
	public boolean isAlList() {
		return alList;
	}
	public void setAlList(boolean alList) {
		this.alList = alList;
	}

	
	private boolean pathDone=false;
	public Dir(String args){
		Scanner sc=new Scanner(args);
		while(sc.hasNext()){
			String str=sc.next();
			if(str.equals("-all")){
				this.setAlList(true);
			}else if(str.equalsIgnoreCase("-a")){
				this.setHide_readOnly(false);
			}else{
				File f=new File(str);
				if(!f.isDirectory() && !f.isFile()){
					Msg.errorMessage("参数 \""+str+"\" 不合法");
				}
				if(f.isDirectory() && !pathDone){
					this.path=str;
					pathDone=true;
				}else if(f.isDirectory() && pathDone){
					Msg.errorMessage("不能接收两个以上的文件夹地址");
				}
			}
		}
		if(!pathDone){
			Msg.errorMessage("请输入一个文件夹地址");
		}
	}
	
	public void execute() throws IOException{
		File f=new File(path);
		if(alList){
			System.out.println(" "+f.getCanonicalPath()+" 的所有内容");
			controler(f);
		}else{
			System.out.println(" "+f.getCanonicalPath()+" 的目录");
			File[] fList=f.listFiles();
			Arrays.sort(fList);
			for(File file:fList){
				if(hide_readOnly && (file.isHidden() || !file.canWrite())){
					continue;
				}
				print(file,false,null);
			}
		}
		System.out.printf("%16d 个文件\n",fileCount);
		System.out.printf("%16d 个目录\n",dirCount);
	}
	
	private void print(File file, boolean ident, String type){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dataStr = df.format(file.lastModified());
		if (file.isDirectory()) {
            System.out.printf("%-22s %-15s ",dataStr,"<DIR>"); 
            dirCount++;
        }else{
        	System.out.printf("%-22s %15s ",dataStr,getFormatString(file.length())); 
        	fileCount++;
        }
		if(ident){
			System.out.print(type);
			printIndent();
			System.out.println(file.getName());
		}else{
			System.out.println(file.getName());
		}
       
	}
	
	 //格式化函数
    public String getFormatString(long l) {
    	NumberFormat numberFormat = NumberFormat.getInstance();
    	numberFormat.setGroupingUsed(true);
    	return numberFormat.format(l);
    }
	
	private static boolean start=true;
	public void controler(File file){
		if(!start && hide_readOnly && (file.isHidden() || !file.canWrite())){
			start=false;//use start, so that "." is ignored
			return;
		}
		indent++;
		if(file.isFile()){
			print(file,true,"-");
		}else if(file.isDirectory()){
			printDirectory(file);
		}
		indent--;
	}
	public void printFile(File f){
		printIndent();
		System.out.println("-"+f.getName());
	}
	public void printDirectory(File d){
		print(d,true,"+");
		for(File f:d.listFiles()){
			controler(f);
		}
	}
	public void printIndent(){
		for(int i=1;i<indent;i++){
			System.out.print("   ");
		}
	}
}
