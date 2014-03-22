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
	FileOutputStream f;
	private byte [] canva = {(byte)27, (byte)'K'};
	private byte end = 20;
	
	Table(FileOutputStream f) throws IOException{
		this.f = f;
		for(byte s : canva)
			f.write(s);
		f.write((byte)224);
		f.write((byte)1);
		for(int i = 0; i < 480; i++)
			f.write((byte)1);
		f.write((byte)end);
		f.write((byte)'\n');
	}
	
	void firstColumn(String s) throws IOException{
		for(byte i : canva)
			f.write(i);
		f.write((byte)1);
		f.write((byte)0);
		f.write((byte)255);
		f.write((byte)20);
		
		f.write((byte)27);
		f.write((byte)14);
		
		for(char i : s.toCharArray())
			f.write((byte)i);
		for(int i = 0; i < 20 - s.length()-1; i++)
			f.write((byte)' ');
		f.write(end);
		
		for(byte i : canva)
			f.write(i);
		f.write((byte)1);
		f.write((byte)0);
		f.write((byte)255);
		f.write((byte)20);
		
	}
}

public class Writer {
	public static void main(String arg[]) throws IOException{
		File file = new File("out.prn");
		FileOutputStream f = new FileOutputStream(file);
		
		Table table = new Table(f);
		table.firstColumn("Sutula");
		
		Logo log = new Logo();
		
		f.close();
	}
}