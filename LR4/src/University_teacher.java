import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.Objects;

import exception.CheckNull;
import exception.IndexOutOfArr;
import exception.NegativeCount;

public class University_teacher implements Teacherable, Serializable{
	private static final long serialVersionUID = 1L;
	private String[] lessons=new String[2];
	private String name;
	private int coutOfStudents;
	public University_teacher() {
		lessons[0]="Математика";
		lessons[1]="Физика";
		name="Альберт Эйнштейн";
		coutOfStudents=14;
	}
	public University_teacher(String[] les,String name, int count) {
		for(int i=0;i<lessons.length;i++)
		{
			lessons[i]=les[i];
		}
		this.name=name;
		coutOfStudents=count;
	}
	public void setLessons(String[] les)
	{
		for(int i=0;i<lessons.length;i++)
		{
			lessons[i]=les[i];
		}
	}
	public String[] getLessons()
	{
		return lessons;
	}
	public void setElemLessons(String str,int a) throws IndexOutOfArr
	{
		if(a<0||a>=lessons.length)
		{
			throw new IndexOutOfArr("Такого индекса нет");
		}
		lessons[a]=str;
	}
	public String getElemLessons(int a) throws IndexOutOfArr
	{
		if(a<0||a>=lessons.length)
		{
			throw new IndexOutOfArr("Такого индекса нет");
		}
		return lessons[a];
	}
	public void setName(String str)
	{
		name=str;
	}
	public String getName()
	{
		return name;
	}
	public void setCount(int a) throws NegativeCount
	{
		if(a<0||a>=50)
		{
			throw new NegativeCount("Неверный ввод числа");
		}
		coutOfStudents=a;
	}
	public int getCount()
	{
		return coutOfStudents;
	}
	public String getInformation() throws CheckNull
	{
		if(Objects.equals(name, null)|| Objects.equals(lessons, null))
		{
			throw new CheckNull("Поле было пустым");
		}
		String res="Преподаватель "+name+" преподает предметы: \n";
		for(int i=0;i<lessons.length;i++)
		{
			res=res+lessons[i]+" ";
		}
		return res;
	}
	@Override
	public String toString()
	{
		try
		{
			return "Информация о преподавателе.\n"+getInformation();
		}
		catch(CheckNull e)
		{
			return e.getMessage();
		}
	}
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
		{
			return true;
		}
		if(obj==null || obj.getClass()!=this.getClass())
		{
			return false;
		}
		University_teacher check=(University_teacher)obj;
		if(Objects.equals(this.name, check.name)&&Objects.equals(this.coutOfStudents, check.coutOfStudents))
		{
			for(int i=0;i<this.lessons.length;i++)
			{
				if (!Objects.equals(this.lessons[i], check.lessons[i]))
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}
	@Override
	public int hashCode()
	{
		int res=coutOfStudents*11;
		if(!(name==null))
		{
			res=res+name.hashCode();
		}
		for(int i=0;i<this.lessons.length;i++)
		{
			if(!(lessons[i]==null))
			{
				res=res+lessons[i].hashCode();
			}
		}
		return res;
	}
	public void output(OutputStream out) throws IOException{
		DataOutputStream dataOutputStream = new DataOutputStream(out);
		dataOutputStream.writeBoolean(true);
		dataOutputStream.writeUTF(name);
		dataOutputStream.writeInt(coutOfStudents);
		dataOutputStream.writeInt(lessons.length);
		for (String lesson : lessons)
			dataOutputStream.writeUTF(lesson);
		}

		public void write(Writer out){
			PrintWriter printWriter = new PrintWriter(out);
			String result = "";
			result = true + " " + name + " " + coutOfStudents + " " + lessons.length + " ";
			for (String lesson : lessons)
				result += lesson + " ";
				printWriter.println(result);
		}
	
	
}
