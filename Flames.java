import java.io.*;
import java.util.*;
class Flames
{
	public static void main(String args[]) throws IOException
	{
		System.out.println("Enter 1st name:");
		BufferedReader bfn = new BufferedReader(new InputStreamReader(System.in));
        	String name1 = bfn.readLine();
		System.out.println("Enter 2nd name:");
		String name2 = bfn.readLine();
		name1 = name1.toLowerCase();
		name2 = name2.toLowerCase();
		char[] n1 = strToChArray(name1);
		char[] n2 = strToChArray(name2);
		if((n1.length)>=(n2.length))
		{
			for(int i=0;i<n1.length;i++)
			{
				for(int j=0;j<n2.length;j++)
				{
					if(n1[i]==n2[j])
					{
						n1[i]=n2[j]='0';
					}
				}
			}
		}
		else
		{
			for(int i=0;i<n2.length;i++)
			{
				for(int j=0;j<n1.length;j++)
				{
					if(n2[i]==n1[j])
					{
						n2[i]=n1[j]='0';
					}
				}
			}
		}
		int num=0;
		num += returnsCount(n1);
		num += returnsCount(n2);
		char c = findFlames(num-1);
		switch(c)
		{
			case 'f':
				System.out.println(name1+" and "+name2+" are friends.");
				break;
			case 'l':
				System.out.println(name1+" and "+name2+" are lovers.");
				break;
			case 'a':
				System.out.println(name1+" and "+name2+" have attraction.");
				break;
			case 'm':
				System.out.println(name1+" and "+name2+" will get married.");
				break;
			case 'e':
				System.out.println(name1+" and "+name2+" are enemies.");
				break;
			case 's':
				System.out.println(name1+" and "+name2+" are sisters.");
				break;
			default:
				System.out.println("Sorry I can't say!");
		}
	}
	public static char[] strToChArray(String name)
	{
		char[] ch = name.toCharArray();
		return ch;
	}
	public static int returnsCount(char[] ch)
	{
		int i=0,count=0;
		for(i=0;i<ch.length;i++)
		{
			if(ch[i]!='0')
			{
				count+=1;
			}
		}
		return count;
	}
	public static char findFlames(int n)
	{ 
		List<Character> ch = new ArrayList<Character>();
		ch.add('f');
		ch.add('l');
		ch.add('a');
		ch.add('m');
		ch.add('e');
		ch.add('s');
		int i=0;
		for(i=0;i<5;i++)
		{
			if(n>=ch.size())
			{
				n = n%(ch.size());
				ch.remove(ch.get(n));
			}
			else
				ch.remove(ch.get(n));
		}
		return ch.get(0);
	}
}