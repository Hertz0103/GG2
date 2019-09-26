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
			System.out.println("讀檔錯誤!請去檢查bookfile.txt存不存在...回到主選單中...");
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
		System.out.println("請先登入...");
		System.out.println("請輸入帳號密碼...");
		System.out.printf("帳號: ");
		Scanner scan = new Scanner(System.in);
		String user = scan.next();
		System.out.printf("密碼: ");
		String code = scan.next();
		
		if (user.equals("cis") && code.equals(originCode))
		{
			System.out.println("歡迎蒞臨翊竹的書籍管理系統~~~");
			menu();
		}
		else 
		{
			System.out.println("帳號或密碼錯誤...");
			LogIn();
		}
	}
	public static void menu()
	{
		System.out.println("請告訴我你現在想要做什麼...");
		System.out.println("(A 為新增書籍，B 為修改書籍，C 為刪除書籍，D 為查看書籍資料庫，E 為查看我的最愛，F 為把書加入最愛，H 為格式化資料，\nK 為隱藏功能選單，G 為修改密碼，T 為垃圾桶，V 為清理垃圾桶，U 為修改資料上限，Z 為重新登入，Q 為結束程式)");
		System.out.println("資料上限為" + max + "筆資料，已用" + filecount + "筆，還剩餘" + (max-filecount) + "筆可用...");
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
			case "Z": System.out.println("登出中..."); LogIn(); break;
			case "Q": System.out.println("關閉程式中..."); System.out.println("請下次再來~~~GoodBye~~"); System.exit(0); break;
			default: System.out.println("請輸入正確的指令!不要亂按!"); menu(); break;
		}	
	}
	
	public static void addbook()
	{
		Scanner scan = new Scanner(System.in);
		if ((filecount + 1) > max)
		{			
			System.out.printf("已經超過資料數上限!請刪除一些資料後再新增!");
			menu();
		}
		boolean check = false;
		while (!check)
		{
			System.out.printf("輸入書名(限15個字以內): ");
			name = scan.next();
			if (name.substring(0, 1).equals("☆"))
				System.out.println("開頭不得為星號!請重新輸入資料!");
			else if (name.length() > 15)
				System.out.println("格式錯誤!請重新輸入資料!");
			else
				check = true;
		}
		while (check)
		{
			System.out.printf("輸入作者(限15個字以內): ");
			author = scan.next();
			if (author.length() > 15)
				System.out.println("格式錯誤!請重新輸入資料!");
			else
				check = false;
		}
		while (!check)
		{
			System.out.printf("輸入出版社(限15個字以內): ");
			publish = scan.next();
			if (publish.length() > 15)
				System.out.println("格式錯誤!請重新輸入資料!");
			else
				check = true;
		}
		while (check)
		{
			System.out.printf("輸入編號(限開頭一個英文字母後面跟著四個數字): ");
			number = scan.next();
			if (number.length() != 5 || (!Character.isLetter(number.charAt(0))))
				System.out.println("格式錯誤!請重新輸入資料!");
			else
			{
				for (int i = 1; i <= 4; i++)
				{
					if (!Character.isDigit(number.charAt(i)))
					{	
						System.out.println("格式錯誤!請重新輸入資料!");
						i = 5;
					}
					if (i == 4)
						check = false;
				}
			}
		}
		while (!check)
		{
			System.out.printf("輸入類型(限15個字以內): ");
			block = scan.next();
			if (block.length() > 15)
				System.out.println("格式錯誤!請重新輸入資料!");
			else
				check = true;
		}
		while (check)
		{
			System.out.printf("輸入出版年份(限4個數字以下): ");
			year = scan.nextInt();
			if (year > 9999)
				System.out.println("格式錯誤!請重新輸入資料!");
			else
				check = false;
		}
		System.out.printf("您輸入%15s%15s%15s%9s%10s%8s\n", name, author, publish, number, block, year + "年出版");
		System.out.println("確定嗎?(確定打Y，想重新打按任意鍵，確定後修改就必須從主選單選擇修改功能修改)");
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
		System.out.printf("請輸入舊密碼: ");
		String oldcode = scan.next();
		if (oldcode.equals(originCode))
			System.out.printf("請輸入新密碼: ");
		else
		{
			System.out.println("這不是舊密碼，不得修改，請重新再來...");
			menu();
		}
		String newCode1 = scan.next();
		System.out.printf("請再一次輸入新密碼: ");
		String newCode2 = scan.next();
		if (newCode1.equals(newCode2))
		{
			originCode = newCode1;
			System.out.println("修改成功!");
			menu();
		}
		else
		{	
			System.out.println("輸入錯誤!!!!修改密碼失敗~~~請重新再來~~~");
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
			System.out.println("讀檔錯誤!請去檢查bookflie.txt存不存在...回到主選單中...");
			menu();
		}
		
		output.println(readname + "\t\t" + readauthor + "\t\t" + readpublish + "\t\t" + readnumber + "\t\t" + readblock + "\t\t" + readyear + "\t創建日期\t" + getDateTime());
		System.out.println("寫入成功!可以到bookfile.txt或查看書籍檢查囉~");
		
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
			System.out.println("讀檔錯誤!請去檢查bookfile.txt存不存在...回到主選單中...");
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
			System.out.println("目前資料是空的...按任意鍵回主選單，按Q結束程式...");
		else
			System.out.println("以上是目前的資料(總共有" + filecount + "筆資料)...按任意鍵回主選單，按Q結束程式...");
		input.close();
		String B = scan.next();
		if (B.equals("Q"))
		{
			System.out.println("關閉程式中..."); System.out.println("請下次再來~~~GoodBye~~"); System.exit(0);
		}
		else
			menu();
	}
	
	public static void deletebookask()
	{
		if (filecount == 0)
		{
			System.out.println("資料庫裡面已經是空的了!回到主選單中...");
			menu();
		}
		System.out.println("您確定嗎?如果刪除了就無法再復原了(按Y確定，按任意鍵回到主選單)");
		Scanner scan = new Scanner(System.in);
		String Y = scan.next();
		if (Y.equals("Y"))
			System.out.printf("請輸入密碼: ");
		else
			menu();
		String code = scan.next();
		if (code.equals(originCode))
			delete();
		else
		{
			System.out.println("密碼錯誤，回到主選單...");
			menu();
		}
	}
	
	public static void delete()
	{
		if (filecount == 0)
		{
			System.out.println("資料庫裡面已經是空的了!回到主選單中...");
			menu();
		}
		Scanner scan = new Scanner(System.in);
		System.out.printf("您要刪除哪一行資料: ");
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
			System.out.println("讀檔錯誤!請去檢查bookfile.txt存不存在...回到主選單中...");
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
				if (file.substring(0, 3).equals("☆☆☆"))
				{	
					System.out.println("這個是我的最愛!您確定要刪除嗎?(按Y確定，按任意鍵重新選擇)");
					String lovee = scan.next();
					if (!lovee.equals("Y"))
						delete();
				}	
				outputt.println(file + "\t刪除日期\t" + getDateTime());
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
			System.out.println("讀檔錯誤!請去檢查bookfile.txt存不存在...回到主選單中...");
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
		System.out.println("已刪除!回到主選單按任意鍵，繼續刪除按D");
		String str = scan.next();
		if (str.equals("D"))
			delete();
		else
			menu();
	}
	
	public static void love()
	{
		System.out.printf("請輸入您想要加入最愛的書籍(輸入第幾行): ");
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
			System.out.println("讀檔錯誤!請去檢查bookfile.txt存不存在...回到主選單中...");
			menu();
		}
		
		String file = null;
		int count = 1;
		while (input.hasNextLine())
		{
			file = input.nextLine();
			if (file.substring(0, 3).equals("☆☆☆"))
			{	
				System.out.println("這已經是我的最愛了!");
				love();
			}
			if (lovebook != count)
				output.println(file);
			else
				output.println("☆☆☆" + file);
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
			System.out.println("讀檔錯誤!請去檢查bookfile.txt存不存在...回到主選單中...");
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
		
		System.out.println("已把這本書加到最愛!還要繼續加嗎?按Y繼續，按任意鍵回主選單...");
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
			System.out.println("讀檔錯誤!請去檢查bookfile.txt存不存在...回到主選單中...");
			menu();
		}
		
		String file = null;
		int count = 0;
		while (input.hasNextLine())
		{
			file = input.nextLine();
			if (file.substring(0, 3).equals("☆☆☆"))
			{
				System.out.println(file);
				count++;
			}
		}
		if (count == 0)
			System.out.println("目前沒有我的最愛...按任意鍵回主選單，按Q結束程式...");
		else
			System.out.println("以上是我的最愛...按任意鍵回主選單，按Q結束程式...");
		input.close();
		String B = scan.next();
		if (B.equals("Q"))
		{
			System.out.println("關閉程式中..."); System.out.println("請下次再來~~~GoodBye~~"); System.exit(0);
		}
		else
			menu();
	}
	
	public static void changebook()
	{
		System.out.printf("請輸入您想要修改的書籍(輸入行序): ");
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
			System.out.println("讀檔錯誤!請去檢查bookfile.txt存不存在...回到主選單中...");
			menu();
		}
		
		String file = null;
		int count = 1;
		while (input.hasNextLine())
		{
			file = input.nextLine();
			if (change == count)
			{
				System.out.println("這個是原本的資料: " + file);
				System.out.println("請輸入想修改的資料...");
				boolean check = false;
				while (!check)
				{
					System.out.printf("輸入新書名(限15個字以內): ");
					name = scan.next();
					if (name.substring(0, 1).equals("☆"))
						System.out.println("開頭不得為星號!請重新輸入資料!");
					else if (name.length() > 15)
						System.out.println("格式錯誤!請重新輸入資料!");
					else
						check = true;
				}
				while (check)
				{
					System.out.printf("輸入新作者(限15個字以內): ");
					author = scan.next();
					if (author.length() > 15)
						System.out.println("格式錯誤!請重新輸入資料!");
					else
						check = false;
				}
				while (!check)
				{
					System.out.printf("輸入新出版社(限15個字以內): ");
					publish = scan.next();
					if (publish.length() > 15)
						System.out.println("格式錯誤!請重新輸入資料!");
					else
						check = true;
				}
				while (check)
				{
					System.out.printf("輸入新編號(限開頭一個英文字母後面跟著四個數字): ");
					number = scan.next();
					if (number.length() != 5 || (!Character.isLetter(number.charAt(0))))
						System.out.println("格式錯誤!請重新輸入資料!");
					else
					{
						for (int i = 1; i <= 4; i++)
						{
							if (!Character.isDigit(number.charAt(i)))
							{	
								System.out.println("格式錯誤!請重新輸入資料!");
								i = 5;
							}
							if (i == 4)
								check = false;
						}
					}
				}
				while (!check)
				{
					System.out.printf("輸入新類型(限15個字以內): ");
					block = scan.next();
					if (block.length() > 15)
						System.out.println("格式錯誤!請重新輸入資料!");
					else
						check = true;
				}
				while (check)
				{
					System.out.printf("輸入新出版年份(限4個數字以下): ");
					year = scan.nextInt();
					if (year > 9999)
						System.out.println("格式錯誤!請重新輸入資料!");
					else
						check = false;
				}
				if (file.substring(0, 3).equals("☆☆☆"))
					output.println("☆☆☆" + name + "\t\t" + author + "\t\t" + publish + "\t\t" + number + "\t\t" + block + "\t\t" + year + "\t修改日期\t" + getDateTime());
				else
					output.println(name + "\t\t" + author + "\t\t" + publish + "\t\t" + number + "\t\t" + block + "\t\t" + year + "\t修改日期\t" + getDateTime());
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
			System.out.println("讀檔錯誤!請去檢查bookfile.txt存不存在...回到主選單中...");
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
		
		System.out.println("修改完成!繼續修改按F，回到主選單按任意鍵...");
		String str = scan.next();
		if (str.equals("F"))
			changebook();
		else
			menu();
	}
	
	public static void hide()
	{
		System.out.printf("請輸入隱藏密碼: ");
		Scanner scan = new Scanner(System.in);
		int code = scan.nextInt();
		int hidecode = 123456789;
		if (code != hidecode)
		{	
			System.out.println("密碼錯誤!回到主選單..."); menu();
		}
		System.out.println("歡迎來到隱藏選單~~~~~~~~");
		hidemenu();
	}
	
	public static void hidemenu()
	{
		System.out.println("請問你想要做甚麼?(A 為玩小遊戲，C 為回到主選單)");
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		switch (str)
		{
			case "A": game(); break;
			case "C": menu(); break;
			default: System.out.println("請輸入正確的指令!不要亂按!"); hidemenu(); break;
		}
	}
	
	
	
	public static void game()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("歡迎來到翊竹的遊戲世界，目前的遊戲只有一種，為幾A幾B的猜數字，你準備好了嗎?(按Y為準備好了，按其他鍵回到主選單)");
		String yesno = scan.next();
		if (!yesno.equals("Y"))
			menu();
		System.out.println("選擇難易度(簡單按A，普通按B，困難按C，瘋狂按D): ");
		int difficulty;
		String QQ = scan.next();
		switch (QQ)
		{
			case "A": difficulty = 10; break;
			case "B": difficulty = 7; break;
			case "C": difficulty = 5; break;
			case "D": difficulty = 4; break;
			default: System.out.println("不要亂按!作為懲罰你的難易度變為地獄級!"); difficulty = 3; break;
		}
		System.out.println("數字產生中...");
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
		System.out.println("產生成功!");
		int[] number0 = new int[4]; int number00 = 0; int count = 1;
		number0[0] = 1; number0[1] = 2; number0[2] = 3; number0[3] = 4;
		while (count <= difficulty)
		{
			int A = 0, B = 0;
			boolean right = true;
			while (right)
			{
				System.out.println("請猜一四位數(你只剩" + (difficulty + 1 - count) + "次機會): " );
				number00 = scan.nextInt();
				if (number00 < 100 || number00 > 9999)
					System.out.println("請輸入四位數!");
				else if (number00 < 1000)
				{
					number0[0] = 0;
					number0[1] = (number00 % 1000) / 100;
					number0[2] = ((number00 % 1000) % 100) / 10;
					number0[3] = ((number00 % 1000) % 100) % 10;
					if (number0[0] == number0[1] || number0[0] == number0[2] || number0[0] == number0[3] || number0[1] == number0[3] || number0[1] == number0[3] ||  number0[2] == number0[3])
						System.out.println("你輸入的數字是重複的!");
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
						System.out.println("你輸入的數字是重複的!");
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
				System.out.println("答對囉~你用了" + count + "次");
				System.out.println("還想繼續玩嗎?想的話按Y，不想按任意鍵");
				String keep = scan.next();
				if (keep.equals("Y"))
					game();
				else
					menu();
			}
			else
				count++;
		}
		System.out.println("你的機會已經用光了!答案是" + num[0] + num[1] + num[2] + num[3]);
		System.out.println("還想繼續玩嗎?想的話按Y，不想按任意鍵");
		String keep = scan.next();
		if (keep.equals("Y"))
			game();
		else
			hidemenu();
	}
	
	public static void deleteall()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("您確定要格式化資料嗎?所有的資料都會消失，並且在垃圾桶無法找到...(按Y確定，任意鍵回到主選單)");
		String yes = scan.next();
		if (!yes.equals("Y"))
			menu();
		System.out.printf("請輸入密碼: ");
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
			System.out.println("讀檔錯誤!請去檢查bookflie.txt存不存在...回到主選單中...");
			menu();
		}
		output.close();
		
		filecount = 0;
		System.out.println("已把資料庫格式化!回到主選單中...");
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
			System.out.println("讀檔錯誤!請去檢查trahscan.txt存不存在...回到主選單中...");
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
			System.out.println("目前垃圾桶是空的...按任意鍵回主選單，按Q結束程式...");
		else
			System.out.println("以上是您刪除過的資料(總共有" + count + "筆)...按任意鍵回主選單，按Q結束程式...");
		String B = scan.next();
		if (B.equals("Q"))
		{
			System.out.println("關閉程式中..."); System.out.println("請下次再來~~~GoodBye~~"); System.exit(0);
		}
		else
			menu();
	}
	
	public static void deletetrash()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("您確定要清理垃圾桶嗎?(按Y確定，任意鍵回到主選單)");
		String yes = scan.next();
		if (!yes.equals("Y"))
			menu();
		System.out.printf("請輸入密碼: ");
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
			System.out.println("讀檔錯誤!請去檢查bookflie.txt存不存在...回到主選單中...");
			menu();
		}
		output.close();
		
		System.out.println("已清理垃圾桶!!回到主選單中...");
		menu();
	}
	
	public static void changemax()
	{
		System.out.printf("目前上限是" + max + "，請輸入您想改的上限(極限是1024筆): ");
		Scanner scan = new Scanner(System.in);
		int maxx = scan.nextInt();
		if (maxx > 1024)
		{	
			System.out.println("已超過系統極限!不要亂打數字!");
			changemax();
		}
		max = maxx;
		System.out.println("修改成功!回到主選單中...");
		menu();
	}
	
	public static void main(String[] args)
	{
		countfilecount();
		LogIn();
	}
}
