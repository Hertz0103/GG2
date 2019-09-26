import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class Book {

	public static String name;
	public static String author;
	public static String publish;
	public static String number;
	public static String block;
	public static int year;
	public static String originCode = "1234";
	public static int filecount;
	public static int max = 128;
	
	public static String getDateTime()
	{
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		//System.out.println(strDate);
		return strDate;
	}
	
	public static void countfilecount()
	{
		Scanner input = null;
		
		try
		{
			input = new Scanner(new FileInputStream("bookfile.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Ū�ɿ��~!�Хh�ˬdbookfile.txt�s���s�b...�^��D��椤...");
			menu();
		}
		
		String file = null;
		int count = 0;
		while (input.hasNextLine())
		{
			file = input.nextLine();
			count++;
		}
		filecount = count;
		input.close();
	}
	
	public static void LogIn()
	{
		System.out.println("�Х��n�J...");
		System.out.println("�п�J�b���K�X...");
		System.out.printf("�b��: ");
		Scanner scan = new Scanner(System.in);
		String user = scan.next();
		System.out.printf("�K�X: ");
		String code = scan.next();
		
		if (user.equals("cis") && code.equals(originCode))
		{
			System.out.println("�w��Y�{���˪����y�޲z�t��~~~");
			menu();
		}
		else 
		{
			System.out.println("�b���αK�X���~...");
			LogIn();
		}
	}
	public static void menu()
	{
		System.out.println("�Чi�D�ڧA�{�b�Q�n������...");
		System.out.println("(A ���s�W���y�AB ���ק���y�AC ���R�����y�AD ���d�ݮ��y��Ʈw�AE ���d�ݧڪ��̷R�AF ����ѥ[�J�̷R�AH ���榡�Ƹ�ơA\nK �����å\����AG ���ק�K�X�AT ���U����AV ���M�z�U����AU ���ק��ƤW���AZ �����s�n�J�AQ �������{��)");
		System.out.println("��ƤW����" + max + "����ơA�w��" + filecount + "���A�ٳѾl" + (max-filecount) + "���i��...");
		Scanner scan = new Scanner(System.in);
		String func = scan.next();
		switch (func.toUpperCase())
		{
			case "A": addbook(); break;
			case "B": changebook(); break;
			case "C": deletebookask(); break;
			case "D": lookfile(); break;
			case "E": looklove(); break;
			case "F": love(); break;
			case "H": deleteall(); break;
			case "K": hide(); break;
			case "G": changeUserCode(); break;
			case "T": trash(); break;
			case "U": changemax(); break;
			case "V": deletetrash(); break;
			case "Z": System.out.println("�n�X��..."); LogIn(); break;
			case "Q": System.out.println("�����{����..."); System.out.println("�ФU���A��~~~GoodBye~~"); System.exit(0); break;
			default: System.out.println("�п�J���T�����O!���n�ë�!"); menu(); break;
		}	
	}
	
	public static void addbook()
	{
		Scanner scan = new Scanner(System.in);
		if ((filecount + 1) > max)
		{			
			System.out.printf("�w�g�W�L��ƼƤW��!�ЧR���@�Ǹ�ƫ�A�s�W!");
			menu();
		}
		boolean check = false;
		while (!check)
		{
			System.out.printf("��J�ѦW(��15�Ӧr�H��): ");
			name = scan.next();
			if (name.substring(0, 1).equals("��"))
				System.out.println("�}�Y���o���P��!�Э��s��J���!");
			else if (name.length() > 15)
				System.out.println("�榡���~!�Э��s��J���!");
			else
				check = true;
		}
		while (check)
		{
			System.out.printf("��J�@��(��15�Ӧr�H��): ");
			author = scan.next();
			if (author.length() > 15)
				System.out.println("�榡���~!�Э��s��J���!");
			else
				check = false;
		}
		while (!check)
		{
			System.out.printf("��J�X����(��15�Ӧr�H��): ");
			publish = scan.next();
			if (publish.length() > 15)
				System.out.println("�榡���~!�Э��s��J���!");
			else
				check = true;
		}
		while (check)
		{
			System.out.printf("��J�s��(���}�Y�@�ӭ^��r���᭱��ۥ|�ӼƦr): ");
			number = scan.next();
			if (number.length() != 5 || (!Character.isLetter(number.charAt(0))))
				System.out.println("�榡���~!�Э��s��J���!");
			else
			{
				for (int i = 1; i <= 4; i++)
				{
					if (!Character.isDigit(number.charAt(i)))
					{	
						System.out.println("�榡���~!�Э��s��J���!");
						i = 5;
					}
					if (i == 4)
						check = false;
				}
			}
		}
		while (!check)
		{
			System.out.printf("��J����(��15�Ӧr�H��): ");
			block = scan.next();
			if (block.length() > 15)
				System.out.println("�榡���~!�Э��s��J���!");
			else
				check = true;
		}
		while (check)
		{
			System.out.printf("��J�X���~��(��4�ӼƦr�H�U): ");
			year = scan.nextInt();
			if (year > 9999)
				System.out.println("�榡���~!�Э��s��J���!");
			else
				check = false;
		}
		System.out.printf("�z��J%15s%15s%15s%9s%10s%8s\n", name, author, publish, number, block, year + "�~�X��");
		System.out.println("�T�w��?(�T�w��Y�A�Q���s�������N��A�T�w��ק�N�����q�D����ܭק�\��ק�)");
		String yn = scan.next();
		switch (yn)
		{
			case "Y": break;
			default: addbook();
		}
		readfile(name, author, publish, number, block, year);
	}
	
	public static void changeUserCode()
	{
		Scanner scan = new Scanner(System.in);
		System.out.printf("�п�J�±K�X: ");
		String oldcode = scan.next();
		if (oldcode.equals(originCode))
			System.out.printf("�п�J�s�K�X: ");
		else
		{
			System.out.println("�o���O�±K�X�A���o�ק�A�Э��s�A��...");
			menu();
		}
		String newCode1 = scan.next();
		System.out.printf("�ЦA�@����J�s�K�X: ");
		String newCode2 = scan.next();
		if (newCode1.equals(newCode2))
		{
			originCode = newCode1;
			System.out.println("�ק令�\!");
			menu();
		}
		else
		{	
			System.out.println("��J���~!!!!�ק�K�X����~~~�Э��s�A��~~~");
			menu();
		}
	}
	
	public static void readfile(String readname, String readauthor, String readpublish, String readnumber, String readblock, int readyear)
	{
		Scanner input = null;
		PrintWriter output = null;
		
		try
		{
			input = new Scanner(new FileInputStream("bookfile.txt"));
			output = new PrintWriter(new FileOutputStream("bookfile.txt", true));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Ū�ɿ��~!�Хh�ˬdbookflie.txt�s���s�b...�^��D��椤...");
			menu();
		}
		
		output.println(readname + "\t\t" + readauthor + "\t\t" + readpublish + "\t\t" + readnumber + "\t\t" + readblock + "\t\t" + readyear + "\t�Ыؤ��\t" + getDateTime());
		System.out.println("�g�J���\!�i�H��bookfile.txt�άd�ݮ��y�ˬd�o~");
		
		input.close();
		output.close();
		filecount++;
		menu();
	}
	
	public static void lookfile()
	{
		Scanner input = null;
		Scanner scan = new Scanner(System.in);
		
		try
		{
			input = new Scanner(new FileInputStream("bookfile.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Ū�ɿ��~!�Хh�ˬdbookfile.txt�s���s�b...�^��D��椤...");
			menu();
		}
		
		String file = null;
		int count = 0;
		while (input.hasNextLine())
		{
			file = input.nextLine();
			System.out.println(file);
			count++;
		}
		if (count == 0)
			System.out.println("�ثe��ƬO�Ū�...�����N��^�D���A��Q�����{��...");
		else
			System.out.println("�H�W�O�ثe�����(�`�@��" + filecount + "�����)...�����N��^�D���A��Q�����{��...");
		input.close();
		String B = scan.next();
		if (B.equals("Q"))
		{
			System.out.println("�����{����..."); System.out.println("�ФU���A��~~~GoodBye~~"); System.exit(0);
		}
		else
			menu();
	}
	
	public static void deletebookask()
	{
		if (filecount == 0)
		{
			System.out.println("��Ʈw�̭��w�g�O�Ū��F!�^��D��椤...");
			menu();
		}
		System.out.println("�z�T�w��?�p�G�R���F�N�L�k�A�_��F(��Y�T�w�A�����N��^��D���)");
		Scanner scan = new Scanner(System.in);
		String Y = scan.next();
		if (Y.equals("Y"))
			System.out.printf("�п�J�K�X: ");
		else
			menu();
		String code = scan.next();
		if (code.equals(originCode))
			delete();
		else
		{
			System.out.println("�K�X���~�A�^��D���...");
			menu();
		}
	}
	
	public static void delete()
	{
		if (filecount == 0)
		{
			System.out.println("��Ʈw�̭��w�g�O�Ū��F!�^��D��椤...");
			menu();
		}
		Scanner scan = new Scanner(System.in);
		System.out.printf("�z�n�R�����@����: ");
		int delete = scan.nextInt();
		
		Scanner input = null;
		PrintWriter output = null;
		PrintWriter outputt = null;
		try
		{
			input = new Scanner(new FileInputStream("bookfile.txt"));
			output = new PrintWriter(new FileOutputStream("bookfile1.txt"));
			outputt = new PrintWriter(new FileOutputStream("trashcan.txt", true));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Ū�ɿ��~!�Хh�ˬdbookfile.txt�s���s�b...�^��D��椤...");
			menu();
		}
		
		String file = null;
		int count = 1;
		while (input.hasNextLine())
		{
			file = input.nextLine();
			
			if (count != delete)
				output.println(file);
			else
			{	
				if (file.substring(0, 3).equals("������"))
				{	
					System.out.println("�o�ӬO�ڪ��̷R!�z�T�w�n�R����?(��Y�T�w�A�����N�䭫�s���)");
					String lovee = scan.next();
					if (!lovee.equals("Y"))
						delete();
				}	
				outputt.println(file + "\t�R�����\t" + getDateTime());
			}
			count++;
		}
		input.close();
		output.close();
		outputt.close();
		
		Scanner input1 = null;
		PrintWriter output1 = null;
		try
		{
			input1 = new Scanner(new FileInputStream("bookfile1.txt"));
			output1 = new PrintWriter(new FileOutputStream("bookfile.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Ū�ɿ��~!�Хh�ˬdbookfile.txt�s���s�b...�^��D��椤...");
			menu();
		}
		
		String file1 = null;
		while (input1.hasNextLine())
		{
			file1 = input1.nextLine();
			output1.println(file1);
		}
		input1.close();
		output1.close();
		
		filecount--;
		System.out.println("�w�R��!�^��D�������N��A�~��R����D");
		String str = scan.next();
		if (str.equals("D"))
			delete();
		else
			menu();
	}
	
	public static void love()
	{
		System.out.printf("�п�J�z�Q�n�[�J�̷R�����y(��J�ĴX��): ");
		Scanner scan = new Scanner(System.in);
		int lovebook = scan.nextInt();
		Scanner input = null;
		PrintWriter output = null;
		try
		{
			input = new Scanner(new FileInputStream("bookfile.txt"));
			output = new PrintWriter(new FileOutputStream("bookfile2.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Ū�ɿ��~!�Хh�ˬdbookfile.txt�s���s�b...�^��D��椤...");
			menu();
		}
		
		String file = null;
		int count = 1;
		while (input.hasNextLine())
		{
			file = input.nextLine();
			if (file.substring(0, 3).equals("������"))
			{	
				System.out.println("�o�w�g�O�ڪ��̷R�F!");
				love();
			}
			if (lovebook != count)
				output.println(file);
			else
				output.println("������" + file);
			count++;
		}
		input.close();
		output.close();
		
		Scanner input1 = null;
		PrintWriter output1 = null;
		try
		{
			input1 = new Scanner(new FileInputStream("bookfile2.txt"));
			output1 = new PrintWriter(new FileOutputStream("bookfile.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Ū�ɿ��~!�Хh�ˬdbookfile.txt�s���s�b...�^��D��椤...");
			menu();
		}
		
		String file1 = null;
		while (input1.hasNextLine())
		{
			file1 = input1.nextLine();
			output1.println(file1);
		}
		input1.close();
		output1.close();
		
		System.out.println("�w��o���ѥ[��̷R!�٭n�~��[��?��Y�~��A�����N��^�D���...");
		String str = scan.next();
		if (str.equals("Y"))
			love();
		else
			menu();
	}
	
	public static void looklove()
	{
		Scanner input = null;
		Scanner scan = new Scanner(System.in);
		
		try
		{
			input = new Scanner(new FileInputStream("bookfile.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Ū�ɿ��~!�Хh�ˬdbookfile.txt�s���s�b...�^��D��椤...");
			menu();
		}
		
		String file = null;
		int count = 0;
		while (input.hasNextLine())
		{
			file = input.nextLine();
			if (file.substring(0, 3).equals("������"))
			{
				System.out.println(file);
				count++;
			}
		}
		if (count == 0)
			System.out.println("�ثe�S���ڪ��̷R...�����N��^�D���A��Q�����{��...");
		else
			System.out.println("�H�W�O�ڪ��̷R...�����N��^�D���A��Q�����{��...");
		input.close();
		String B = scan.next();
		if (B.equals("Q"))
		{
			System.out.println("�����{����..."); System.out.println("�ФU���A��~~~GoodBye~~"); System.exit(0);
		}
		else
			menu();
	}
	
	public static void changebook()
	{
		System.out.printf("�п�J�z�Q�n�ק諸���y(��J���): ");
		Scanner scan = new Scanner(System.in);
		int change = scan.nextInt();
		Scanner input = null;
		PrintWriter output = null;
		try
		{
			input = new Scanner(new FileInputStream("bookfile.txt"));
			output = new PrintWriter(new FileOutputStream("bookfile3.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Ū�ɿ��~!�Хh�ˬdbookfile.txt�s���s�b...�^��D��椤...");
			menu();
		}
		
		String file = null;
		int count = 1;
		while (input.hasNextLine())
		{
			file = input.nextLine();
			if (change == count)
			{
				System.out.println("�o�ӬO�쥻�����: " + file);
				System.out.println("�п�J�Q�ק諸���...");
				boolean check = false;
				while (!check)
				{
					System.out.printf("��J�s�ѦW(��15�Ӧr�H��): ");
					name = scan.next();
					if (name.substring(0, 1).equals("��"))
						System.out.println("�}�Y���o���P��!�Э��s��J���!");
					else if (name.length() > 15)
						System.out.println("�榡���~!�Э��s��J���!");
					else
						check = true;
				}
				while (check)
				{
					System.out.printf("��J�s�@��(��15�Ӧr�H��): ");
					author = scan.next();
					if (author.length() > 15)
						System.out.println("�榡���~!�Э��s��J���!");
					else
						check = false;
				}
				while (!check)
				{
					System.out.printf("��J�s�X����(��15�Ӧr�H��): ");
					publish = scan.next();
					if (publish.length() > 15)
						System.out.println("�榡���~!�Э��s��J���!");
					else
						check = true;
				}
				while (check)
				{
					System.out.printf("��J�s�s��(���}�Y�@�ӭ^��r���᭱��ۥ|�ӼƦr): ");
					number = scan.next();
					if (number.length() != 5 || (!Character.isLetter(number.charAt(0))))
						System.out.println("�榡���~!�Э��s��J���!");
					else
					{
						for (int i = 1; i <= 4; i++)
						{
							if (!Character.isDigit(number.charAt(i)))
							{	
								System.out.println("�榡���~!�Э��s��J���!");
								i = 5;
							}
							if (i == 4)
								check = false;
						}
					}
				}
				while (!check)
				{
					System.out.printf("��J�s����(��15�Ӧr�H��): ");
					block = scan.next();
					if (block.length() > 15)
						System.out.println("�榡���~!�Э��s��J���!");
					else
						check = true;
				}
				while (check)
				{
					System.out.printf("��J�s�X���~��(��4�ӼƦr�H�U): ");
					year = scan.nextInt();
					if (year > 9999)
						System.out.println("�榡���~!�Э��s��J���!");
					else
						check = false;
				}
				if (file.substring(0, 3).equals("������"))
					output.println("������" + name + "\t\t" + author + "\t\t" + publish + "\t\t" + number + "\t\t" + block + "\t\t" + year + "\t�ק���\t" + getDateTime());
				else
					output.println(name + "\t\t" + author + "\t\t" + publish + "\t\t" + number + "\t\t" + block + "\t\t" + year + "\t�ק���\t" + getDateTime());
			}
			else
				output.println(file);
			count++;
		}
		input.close();
		output.close();
		
		Scanner input1 = null;
		PrintWriter output1 = null;
		try
		{
			input1 = new Scanner(new FileInputStream("bookfile3.txt"));
			output1 = new PrintWriter(new FileOutputStream("bookfile.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Ū�ɿ��~!�Хh�ˬdbookfile.txt�s���s�b...�^��D��椤...");
			menu();
		}
		
		String file1 = null;
		while (input1.hasNextLine())
		{
			file1 = input1.nextLine();
			output1.println(file1);
		}
		input1.close();
		output1.close();
		
		System.out.println("�ק粒��!�~��ק��F�A�^��D�������N��...");
		String str = scan.next();
		if (str.equals("F"))
			changebook();
		else
			menu();
	}
	
	public static void hide()
	{
		System.out.printf("�п�J���ñK�X: ");
		Scanner scan = new Scanner(System.in);
		int code = scan.nextInt();
		int hidecode = 123456789;
		if (code != hidecode)
		{	
			System.out.println("�K�X���~!�^��D���..."); menu();
		}
		System.out.println("�w��Ө����ÿ��~~~~~~~~");
		hidemenu();
	}
	
	public static void hidemenu()
	{
		System.out.println("�аݧA�Q�n���ƻ�?(A �����p�C���AC ���^��D���)");
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		switch (str)
		{
			case "A": game(); break;
			case "C": menu(); break;
			default: System.out.println("�п�J���T�����O!���n�ë�!"); hidemenu(); break;
		}
	}
	
	
	
	public static void game()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("�w��Ө����˪��C���@�ɡA�ثe���C���u���@�ءA���XA�XB���q�Ʀr�A�A�ǳƦn�F��?(��Y���ǳƦn�F�A����L��^��D���)");
		String yesno = scan.next();
		if (!yesno.equals("Y"))
			menu();
		System.out.println("���������(²���A�A���q��B�A�x����C�A�ƨg��D): ");
		int difficulty;
		String QQ = scan.next();
		switch (QQ)
		{
			case "A": difficulty = 10; break;
			case "B": difficulty = 7; break;
			case "C": difficulty = 5; break;
			case "D": difficulty = 4; break;
			default: System.out.println("���n�ë�!�@���g�@�A���������ܬ��a����!"); difficulty = 3; break;
		}
		System.out.println("�Ʀr���ͤ�...");
		Random ran = new Random();
		int[] num = new int[4];
		num[0] = 0; num[1] = 0; num[2] = 0; num[3] = 0;
		while (num[0] == num[1] || num[0] == num[2] || num[0] == num[3] || num[1] == num[2] || num[1] == num[3] || num[2] == num[3])
		{	
			num[0] = ran.nextInt(10);
			num[1] = ran.nextInt(10);
			num[2] = ran.nextInt(10);
			num[3] = ran.nextInt(10);
		}
		System.out.println("���ͦ��\!");
		int[] number0 = new int[4]; int number00 = 0; int count = 1;
		number0[0] = 1; number0[1] = 2; number0[2] = 3; number0[3] = 4;
		while (count <= difficulty)
		{
			int A = 0, B = 0;
			boolean right = true;
			while (right)
			{
				System.out.println("�вq�@�|���(�A�u��" + (difficulty + 1 - count) + "�����|): " );
				number00 = scan.nextInt();
				if (number00 < 100 || number00 > 9999)
					System.out.println("�п�J�|���!");
				else if (number00 < 1000)
				{
					number0[0] = 0;
					number0[1] = (number00 % 1000) / 100;
					number0[2] = ((number00 % 1000) % 100) / 10;
					number0[3] = ((number00 % 1000) % 100) % 10;
					if (number0[0] == number0[1] || number0[0] == number0[2] || number0[0] == number0[3] || number0[1] == number0[3] || number0[1] == number0[3] ||  number0[2] == number0[3])
						System.out.println("�A��J���Ʀr�O���ƪ�!");
					else
						right = false;
				}
				else
				{
					number0[0] = number00 / 1000;
					number0[1] = (number00 % 1000) / 100;
					number0[2] = ((number00 % 1000) % 100) / 10;
					number0[3] = ((number00 % 1000) % 100) % 10;
					if (number0[0] == number0[1] || number0[0] == number0[2] || number0[0] == number0[3] || number0[1] == number0[3] || number0[1] == number0[3] ||  number0[2] == number0[3])
						System.out.println("�A��J���Ʀr�O���ƪ�!");
					else
						right = false;
				}
			}
				
			for (int i = 0; i < 4; i++)
			{
				if (number0[i] == num[i])
					A++;
				if (number0[i] == num[0] || number0[i] == num[1] || number0[i] == num[2] || number0[i] == num[3])
					B++;
			}
			B = B - A;
			if (number00 < 1000)
				System.out.println(count + "  0" + number00 + "\t" + A + "A" + B + "B");
			else
				System.out.println(count + "  " + number00 + "\t" + A + "A" + B + "B");
			if (A == 4)
			{
				System.out.println("�����o~�A�ΤF" + count + "��");
				System.out.println("�ٷQ�~�򪱶�?�Q���ܫ�Y�A���Q�����N��");
				String keep = scan.next();
				if (keep.equals("Y"))
					game();
				else
					menu();
			}
			else
				count++;
		}
		System.out.println("�A�����|�w�g�Υ��F!���׬O" + num[0] + num[1] + num[2] + num[3]);
		System.out.println("�ٷQ�~�򪱶�?�Q���ܫ�Y�A���Q�����N��");
		String keep = scan.next();
		if (keep.equals("Y"))
			game();
		else
			hidemenu();
	}
	
	public static void deleteall()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("�z�T�w�n�榡�Ƹ�ƶ�?�Ҧ�����Ƴ��|�����A�åB�b�U����L�k���...(��Y�T�w�A���N��^��D���)");
		String yes = scan.next();
		if (!yes.equals("Y"))
			menu();
		System.out.printf("�п�J�K�X: ");
		String yes1 = scan.next();
		if (!yes1.equals(originCode))
			menu();
		PrintWriter output = null;

		try
		{
			output = new PrintWriter(new FileOutputStream("bookfile.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Ū�ɿ��~!�Хh�ˬdbookflie.txt�s���s�b...�^��D��椤...");
			menu();
		}
		output.close();
		
		filecount = 0;
		System.out.println("�w���Ʈw�榡��!�^��D��椤...");
		menu();
	}
	
	public static void trash()
	{
		Scanner input = null;
		Scanner scan = new Scanner(System.in);
		
		try
		{
			input = new Scanner(new FileInputStream("trashcan.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Ū�ɿ��~!�Хh�ˬdtrahscan.txt�s���s�b...�^��D��椤...");
			menu();
		}
		
		String file = null;
		int count = 0;
		while (input.hasNextLine())
		{
			file = input.nextLine();
			System.out.println(file);
			count++;
		}
		input.close();
		if (count == 0)
			System.out.println("�ثe�U����O�Ū�...�����N��^�D���A��Q�����{��...");
		else
			System.out.println("�H�W�O�z�R���L�����(�`�@��" + count + "��)...�����N��^�D���A��Q�����{��...");
		String B = scan.next();
		if (B.equals("Q"))
		{
			System.out.println("�����{����..."); System.out.println("�ФU���A��~~~GoodBye~~"); System.exit(0);
		}
		else
			menu();
	}
	
	public static void deletetrash()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("�z�T�w�n�M�z�U�����?(��Y�T�w�A���N��^��D���)");
		String yes = scan.next();
		if (!yes.equals("Y"))
			menu();
		System.out.printf("�п�J�K�X: ");
		String yes1 = scan.next();
		if (!yes1.equals(originCode))
			menu();
		PrintWriter output = null;

		try
		{
			output = new PrintWriter(new FileOutputStream("trashcan.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Ū�ɿ��~!�Хh�ˬdbookflie.txt�s���s�b...�^��D��椤...");
			menu();
		}
		output.close();
		
		System.out.println("�w�M�z�U����!!�^��D��椤...");
		menu();
	}
	
	public static void changemax()
	{
		System.out.printf("�ثe�W���O" + max + "�A�п�J�z�Q�諸�W��(�����O1024��): ");
		Scanner scan = new Scanner(System.in);
		int maxx = scan.nextInt();
		if (maxx > 1024)
		{	
			System.out.println("�w�W�L�t�η���!���n�å��Ʀr!");
			changemax();
		}
		max = maxx;
		System.out.println("�ק令�\!�^��D��椤...");
		menu();
	}
	
	public static void main(String[] args)
	{
		countfilecount();
		LogIn();
	}
}
