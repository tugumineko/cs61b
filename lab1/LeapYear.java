public class LeapYear{
	/** whether is LeapYear straghtly with syntax"return"
	*    and in java,the  type boolean only  have 'true' or 'false'*/
	public static boolean isLeapYear(int year)
	{
			return ((year%100!=0&&year%4==0)||(year%400==0));
				}
	public static void checkLeapYear(int year)
	{	
		if(isLeapYear(year))
			System.out.println(year+" is a leap year.");
		else
			System.out.println(year+" is not a leap year.");
	}
	public static void main(String[] args)
	{	if(args.length<1){
			System.out.println("Please enter command line arguments.");
			System.out.println("e.g. java Year 2000");
	}
	for(int i=0;i<args.length;i++)
		try{
			int year = Integer.parseInt(args[i]);
			checkLeapYear(year);
			}catch(NumberFormatException e){
			System.out.printf("%s is not a valid number.\n",args[i]);}
		
		
	}
}


