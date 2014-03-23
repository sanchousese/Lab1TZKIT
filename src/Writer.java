import java.io.*;

class Logo{
	private byte [] canva = {(byte)27, (byte)'K', (byte)52, (byte)0};
	private byte [] diagonal = {(byte) 129, (byte) 161, (byte) 145, (byte) 137, (byte) 133, (byte) 129};
	
	public void write(FileOutputStream f) throws IOException{
		for(byte s : canva)
			f.write(s);
		for(int i = 0; i < 15; i++)
			f.write((byte)8);
		
		f.write((byte)255);
		f.write((byte)129);
		
		for(int i = 0; i < 3; i++)
			for(byte s : diagonal)
				f.write(s);
		
		f.write((byte)129);
		f.write((byte)255);
		
		for(int i = 0; i < 15; i++)
			f.write((byte)8);
		f.write((byte)20);
	}
}

class Table{
	MyStream f;
	
	Table(MyStream f) throws IOException{
		this.f = f;
		f.write('\t');
		f.write("Comand sequence");
		for(int i = 0; i<5; i++)
			f.write('\t');

		f.write("Font");
		
		f.write('\n');
		
		f.write("Symbolic");
		
		f.write('\t');
		f.write('\t');
		
		f.write("Num");
		
		f.write('\n');
	}
	
	void nextRow(String s, int [] mas, String s2) throws IOException{
		int [] cancel = {27, 18, 27, 70, 27, 72, 27, 84, 27, 45, 0};
		for(int i : cancel)
			f.write((byte)i);
		
		f.write(s);
		
		for(int i = 0; i<3; i++)
			f.write('\t');
		
		for(int i : mas)
			f.write(((Integer) i).toString()+" ");

		
		int N = 3;
		if(mas.length == 3) N=2;
		for(int i = 0; i<N; i++)
			f.write('\t');
		
		for(int i : mas)
			f.write((byte)i);
		f.write(s2);
		f.write('\n');
	}
}

class MyStream extends FileOutputStream{

	public MyStream(File file) throws FileNotFoundException {super(file);}
	public void write(String s) throws IOException{
		for(char i : s.toCharArray())
			write(i);
	}
}

public class Writer {
	
	public static void main(String arg[]) throws IOException{
		File file = new File("out.prn");
		MyStream f = new MyStream(file);
		
		Table table = new Table(f);
		String [] sym = { "ESC SO", "DC4", "ESC SI", "DC2", "ESC E",
				"ESC F", "ESC G", "ESC H", "ESC M", "ESC P", "ESC S 0",
				"ESC S 1", "ESC T", "ESC 1", "ESC 0"};
		
		int [][] num = {
				{27,14}, {20}, {27,15}, {18}, {27,69}, {27, 70},
				{27,71}, {27,72}, {27,77}, {27,80}, {27,83,0},
				{27,83,1}, {27,84}, {27, 45, 1}, {27,45,0}
		};
		
		String [] names = {"Wide font", "Cancel wide font", "Brief font", "Cancel brief font",
				"Bold", "Cancel bold", "Sided printing", "Cancel double print", "Font \"elite\"", "Font \"peak\"",
				"Print in the top half of the line", "Print in the lower row" , "Cancel at the top or bottom",
				"Daily underscores", "Cancel daily underscores"};
		for(int i = 0; i < sym.length; i++)
			table.nextRow(sym[i], num[i], names[i]);
		
		f.write("Sutula");
		
		Logo log = new Logo();
		
		f.close();
	}
}