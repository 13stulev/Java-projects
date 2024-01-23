import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.*;

import exception.CheckNull;
import exception.EmptyFile;
import exception.IndexOutOfArr;
import exception.NegativeCount;

public class Main {
	static void printClassesTeachers(ArrayList<Teacherable> arr)
	{
		School_teacher test=new School_teacher();
		ArrayList<Teacherable> teachersUn =new ArrayList<>(); 
		ArrayList<Teacherable> teachersSch =new ArrayList<>(); 
		for(int i=0;i<arr.size();i++)
		{
			if(arr.get(i).getClass()!=test.getClass())
			{
				teachersUn.add(arr.get(i));
			}
			else
			{
				teachersSch.add(arr.get(i));
			}
		}
		System.out.println("�������� ������������� ");
		printInfo(teachersSch);
		System.out.println("��������������� ������������� ");
		printInfo(teachersUn);
		
	}
	static void printInfo(ArrayList<Teacherable> arr)
	{
		for(int i=0;i<arr.size();i++)
		{
			Teacherable teach=arr.get(i);
			System.out.println(i+1 + ". " + "��� ������������� - " + teach.getName());
			System.out.println("���������� ��� ��������� - " + teach.getCount());
			System.out.println("��� ��������: ");
			
			for (int j = 0; j < teach.getLessons().length ; j++) {
				while(true)
				{
					try
					{
						System.out.println((j+1) + ". " + teach.getElemLessons(j));
						break;
					}
					catch(IndexOutOfArr e){
						j--;
						System.out.println(e.getMessage()+" \n��������� ����");
					}
				}

			}
		}
	}
	static void printEqualTeachers(ArrayList<Teacherable> arr1)
	{
		ArrayList<Teacherable> arr=new ArrayList<>();
		for(int i=0;i<arr1.size();i++)
		{
			arr.add(arr1.get(i));
		}
		ArrayList<ArrayList<Teacherable>> res =new ArrayList<>(); 
		
		int k=0;
		for(int j=0;j<arr.size();j++)
		{
			ArrayList<Teacherable> test=new ArrayList<>();
			test.add(arr.get(j));
			arr.remove(j);
			for(int i=0;i<arr.size();i++)
			{
				try
				{
					if(Objects.equals(test.get(0).getInformation(), arr.get(i).getInformation()))
					{
						test.add(arr.get(i));
						arr.remove(i);
					}
				}
				catch(CheckNull e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(test.size()!=1)
			{
				res.add(test);
				k++;
			}
		}
		if(k!=0)
		{
			for(int i=0;i<res.size();i++)
			{
				for(Teacherable teach :res.get(i))
				{
					try {
						System.out.println(teach.getInformation());
					}
					catch(CheckNull e)
					{
						System.out.println(e.getMessage());
					}
				}
			}
		}
		else
		{
			System.out.println("���������� ���");
		}
		
	}
	private static Teacherable FillTeacher(Teacherable teacher, Scanner in){
			System.out.println("������� ��� �������������: ");
			teacher.setName(in.next());
			System.out.println("������� ���������� ��������� � ����: ");
			while(true) {
				try {
					teacher.setCount(in.nextInt());
					break;
				}
				catch (NegativeCount err) {
					System.out.println(err.getMessage() + "\n��������� ����:");
				}
			}
			for (int i = 0; i < teacher.getLessons().length; i++) {
				System.out.println("������� �������: ");
				while (true) {
					try{
						teacher.setElemLessons( in.next(),i);;
						break;
					}
					catch (IndexOutOfArr err) {
						System.out.println(err.getMessage() + "\n��������� ����:");
						i--;
					}
				}
			}
			return teacher;
		}
	private static void outputStreamInformation(ArrayList<Teacherable> teachers, Scanner in) {
			int flag;
			while (true) {
			System.out.println("\n�������� ��������:" +
			"\n 1. �������� ����� � ���� " +
			"\n 2. �������� ���� �� ����� " +
			"\n 3. ���������� ����� � ���� " +
			"\n 4. ���������� ���� �� ����� " +
			"\n 5. ������������ ������� � ���� " +
			"\n 6. �������������� ������� �� ����� " +
			"\n 7. ���������� ��������� ����� � ���� " +
			"\n 8. ���������� ��������� ���� �� ����� " +
			"\n 0. �����");
			flag = in.nextInt();
			while (flag != 1 && flag != 2 && flag != 3 && flag != 4 && flag != 5 && flag != 6 && flag != 7 && flag !=8 && flag != 0) {
				System.out.println("�������� ����! ��������� ����: ");
				flag = in.nextInt();
			}
			switch(flag){
			case 1:
				try {
					FileOutputStream fileOutputStream = new FileOutputStream("teachers.bin");
					for (Teacherable teacher: teachers) {
					WorkWithFile.outputTeacherable(teacher,fileOutputStream);
					}
					System.out.println("�������� ����� � ���� ������� ��������");
					fileOutputStream.close();
				}
				catch (IOException err){
					System.out.println(err.getMessage());
				}
			break;
			case 2:
				try {
					FileInputStream fileInputStream = new FileInputStream("teachers.bin");
					ArrayList<Teacherable> teachersFromBin = new ArrayList<>();
				while (fileInputStream.available() > 0) {
					teachersFromBin.add(WorkWithFile.inputTeacherable(fileInputStream));
				}
				printInfo(teachersFromBin);
				fileInputStream.close();
				}
				catch (IOException | NegativeCount | IndexOutOfArr err){
				System.out.println(err.getMessage());
				}
			break;
			case 3:
				try {
					FileWriter fileWriter = new FileWriter("teachers.txt");
					for (Teacherable teacher: teachers)
					WorkWithFile.writeTeacherable(teacher, fileWriter);
					System.out.println("���������� ����� � ���� ������� ��������");
					fileWriter.close();
				}
				catch (IOException err){
					System.out.println(err.getMessage());
				}
			break;
			case 4:
				try {
				BufferedReader reader = new BufferedReader(new FileReader("teachers.txt"));
				ArrayList<Teacherable> teachersFromTxt = new ArrayList<>();
				boolean endFile = false;
				while(!endFile) {
					try {
						teachersFromTxt.add(WorkWithFile.readTeacherable(reader));
					}
					catch (EmptyFile err){
						System.out.println(err.getMessage());
						endFile = true;
					}
				}
				printInfo(teachersFromTxt);
				reader.close();
				}
				catch (IOException | NegativeCount | IndexOutOfArr err){
					System.out.println(err.getMessage());
				}
			break;
			case 5:
				try {
					FileOutputStream fileOutputStream = new FileOutputStream("ser.bin");
					for (Teacherable teacher: teachers)
						WorkWithFile.serializeTeacherable(teacher, fileOutputStream);
					System.out.println("������������ ������� � ���� ������� ���������");
					fileOutputStream.close();
				}
				catch (IOException exp){
					System.out.println(exp.getMessage());
				}
			break;
			case 6:
				try {
					FileInputStream fileInputStream = new FileInputStream("ser.bin");
					ArrayList<Teacherable> teachersFromSerializedBin = new ArrayList<>();
					while (fileInputStream.available() != 0)
						teachersFromSerializedBin.add(WorkWithFile.deserializeTeacherable(fileInputStream));
					printInfo(teachersFromSerializedBin);
					fileInputStream.close();
				}
				catch (IOException | ClassNotFoundException err){
					System.out.println(err.getMessage());
				}
			break;
			case 7:
				try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("teachers_format.txt"))) {
					for (Teacherable teacher: teachers)
						WorkWithFile.writeFormatTeacherable(teacher, bufferedWriter);
					System.out.println("���������� ����� � ���� ������� ��������");
				}
				catch (IOException | IndexOutOfArr | NegativeCount err ){
					System.out.println(err.getMessage());
				}
			break;
			case 8:
				try {
					FileReader fileReader = new FileReader("teachers_format.txt");
					Scanner scan = new Scanner(fileReader);
					ArrayList<Teacherable> teachersFromTxt = new ArrayList<>();
				while(scan.hasNextLine()) {
					try {
						teachersFromTxt.add(WorkWithFile.readFormatTeacherable(scan));
					}
					catch (EmptyFile err){
						System.out.println(err.getMessage());
					}
				}
					printInfo(teachersFromTxt);
					fileReader.close();
					}
					catch (IOException | IndexOutOfArr | NegativeCount err){
						System.out.println(err.getMessage());
					}
			break;
			case 0:
			return;
			}
			}
		}
	public static void main(String[] args) {
		ArrayList<Teacherable> teachers = new ArrayList<>();
		int flag;
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.println("\n�������� �������������: " +
			"\n 1. �������� �������������" +
			"\n 2. ��������������� �������������" +
			"\n 0. ������� � ��������� � ������������ ��������");
			flag = in.nextInt();
			while (flag != 1 && flag != 2 && flag != 0) {
				System.out.println("�������� ����! ���������: ");
				flag = in.nextInt();
			}
			if (flag == 1)
				teachers.add(FillTeacher(new School_teacher(), in));
			else if (flag == 2)
				teachers.add(FillTeacher(new University_teacher(), in));
			else if (flag == 0){
				if (teachers.size() != 0){
					printInfo(teachers);
					outputStreamInformation(teachers, in);
				}
				else
					System.out.println("\n������ �������� ����");
				break;
			}
		}
	}

}
