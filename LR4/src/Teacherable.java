import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import exception.CheckNull;
import exception.IndexOutOfArr;
import exception.NegativeCount;

public interface Teacherable {
	public void setLessons(String[] les);
	public String[] getLessons();
	public void setElemLessons(String str,int a) throws IndexOutOfArr;
	public String getElemLessons(int a)throws IndexOutOfArr;
	public void setName(String str);
	public String getName();
	public void setCount(int a) throws NegativeCount;
	public int getCount();
	public String getInformation()throws CheckNull;
	public void output(OutputStream out) throws IOException;
	public void write(Writer out) throws IOException;
}
