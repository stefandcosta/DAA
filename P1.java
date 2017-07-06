import java.util.*;
import java.io.*;
public class P1
{
    public static void main(String[] args) throws IOException
    {
        Scanner scr=new Scanner(System.in);
        double x_file=scr.nextDouble();
        System.out.println("%Output will be in the form of: x on 1st line, Maximum value of N on 2nd line & all x^N on corresponding lines%");
        System.out.print(Double.parseDouble(String.format("%20.13e", x_file)));
        long N_file[]=new long[100];
        String dump=scr.nextLine();
        N_file[0]=scr.nextLong();
        int i=1;
        while(true)
        {
            try
            {
            if("=".equals(scr.next()))
            {
                dump=scr.next();
                N_file[i]=scr.nextLong();
                i++;
            }
            }
            catch(NoSuchElementException exp)
            {
                N_file[i]=-1;
                break;
            }
        }
        if(!check(x_file))
         {
             System.out.println("                  x <- Out of range");
             System.exit(0);
         }
        System.out.println();
        double max=max(x_file);
        System.out.println(Double.parseDouble(String.format("%20.13e", max)));
        for(i=0;N_file[i]!=-1;i++)
        {
            
        if(max<N_file[i])
        {
        System.out.println(N_file[i]+"                  N <- Overflow");
        continue;
        }
        if(N_file[i]==0)
        {
            System.out.println(Double.parseDouble(String.format("%20.13e", 1.0)));
            continue;
        }
        double answer=powers(x_file,N_file[i]);
        System.out.println(Double.parseDouble(String.format("%20.13e", answer)));       
     }
    }
     public static boolean check(double x_file)
     {
         if(x_file>=1.00001 && x_file<=1.1)
         return true;
         return false;
     }
     public static double max(double x)
     {
         double max=0;
         double result=x;
         while(result!=Double.POSITIVE_INFINITY)
         {
             result=result*x;
             max++;
         }
         return max;
     }
     public static double powers(double x, long N)
     {
         if(N==0)
         return 1;
         double temp=powers(x,Math.round(N/2));
         if(N%2==0)
         return temp*temp;
         else
         return x*temp*temp;
     }     
}
