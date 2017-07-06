import java.util.*;
import java.io.*;
public class P2
{
    public static void main(String[] args) throws IOException
    {
        Scanner src=new Scanner(System.in);
        System.out.println();
        int n=-1;
        try
        {
        n=src.nextInt();
        }
        catch(NoSuchElementException exp)
        {
        System.out.println("Empty File Is Given As Input or Invalid Input");
        System.exit(0);
        }
        int dim[]=new int[n+1];
        TwoDArray A[]=new TwoDArray[n];
        int i=n;
        int c=0;
        while(i!=0)
        {
            try
            {
            int a=src.nextInt();
            int b=src.nextInt();
            if(c==0)
            {
            dim[c]=a;
            dim[c+1]=b;
            c=c+2;
            A[n-i]=new TwoDArray(a,b);
            for(int l=0;l<a;l++)
                for(int m=0;m<b;m++)
                {
                    A[n-i].x[l][m]=src.nextDouble();
                }
            i=i-1;
            }
            else
            {
             if(dim[c-1]==a)
             {
                 dim[c]=b;
                 c=c+1;
                 A[n-i]=new TwoDArray(a,b);
                 for(int l=0;l<a;l++)
                     for(int m=0;m<b;m++)
                     {
                         A[n-i].x[l][m]=src.nextDouble();
                     }
                 i=i-1;
             }
             else
             {
               System.out.println("Non Conformable Matrix");
               System.exit(0);
             }
            }
            }
            catch(InputMismatchException exp)
            {
                System.out.println("Input not properly given-> missing elements in matrix");
                System.exit(0);
            }
        }
        System.out.println("Dimension Matrix is:- ");
        for(i=0;i<=n;i++)
        {
            System.out.print(dim[i]+"   ");
        }
        System.out.println();
        System.out.println();
        int totalcost=0;
        if(dim.length==2)
        {
            
        }
        else
        {
            if(dim.length==3)
            {
                totalcost=dim[0]*dim[1]*dim[2];
            }
            else
            {
                for(int t=0;t<dim.length-1;t++)
                {
                    totalcost=totalcost+dim[0]*dim[t]*dim[t+1];
                }
            }
        }
        System.out.println("Total Cost of multiplying these matrices in order=  "+totalcost);
        System.out.println();
        Operations obj=new Operations();
        int S[][]=obj.calculate(dim);
        String order=obj.print(1,n,S);
        System.out.println("The optimal order to multiply is    ");
        System.out.println(order);
        System.out.println();
        System.out.println("The answer is =");


        TwoDArray ANSWER=new TwoDArray();
        ANSWER=ANSWER.MATRIX_CHAIN_MULTIPLY(A,S,1,n);

        System.out.println(ANSWER);

    }
}

class TwoDArray
{
    protected double x[][];
    public TwoDArray()
    {
    }
    public TwoDArray(int a, int b)
    {
        x=new double[a][b];
    }
    public String toString()
    {
        String s="";
        for(int i=0;i<x.length;i++)
        {
            for(int j=0;j<x[i].length;j++)
               s=s+Double.parseDouble(String.format("%20.13e", x[i][j]))+"   ";
            s=s+"\n";
        }
        return s;
    }
    public TwoDArray mul(TwoDArray a, TwoDArray b)
    {
        TwoDArray X=new TwoDArray(a.x.length,b.x[0].length);
        for(int i=0;i<X.x.length;i++)
            for(int j=0;j<X.x[0].length;j++)
            {
                X.x[i][j]=0;
                for(int k=0;k<a.x[0].length;k++)
                    X.x[i][j]=X.x[i][j]+a.x[i][k]*b.x[k][j];
            }
        return X;
    }

    public TwoDArray MATRIX_CHAIN_MULTIPLY(TwoDArray A[],int B[][],int i,int j)
    {
        if(i<j)
        {
            TwoDArray X=MATRIX_CHAIN_MULTIPLY(A,B,i,B[i][j]);
            TwoDArray Y=MATRIX_CHAIN_MULTIPLY(A,B,B[i][j]+1,j);
            return mul(X,Y);
        }
        else
            return A[i-1];
    }
}

class Operations
{
        public String print(int i, int j, int B[][])
        {
            String s="";
                if(i>j)
                {
                        s=s+i;
                        return s;
                }
                if(j-i == 1)
                {
                        s=s+"("+i+"*"+j+")";
                        return s;
                }
                else if(j-i ==0)
                {
                        s=s+i;
                        return s;
                }
                int k = B[i][j];
                s=s+"(";
                s=s+print(i,k,B);
                s=s+print(k+1,j,B);
                s=s+")";
                return s;
        }
        public int[][] calculate(int dim[])
        {
            int p[]=dim;
            int n=p.length;
            int M[][]=new int[n][n];
            int B[][]=new int[n][n];
            for(int l=2;l<n;l++)
                for(int i=1;i<n-l+1;i++)
                {
                    int j=i+l-1;
                    int min = Integer.MAX_VALUE;
                    for(int k=i;k<j;k++)
                    {
                        int current = M[i][k]+M[k+1][j]+p[i-1]*p[k]*p[j];
                        if(current < min)
                        {
                            min = current;
                            B[i][j]= k;
                        }
                    }
                    M[i][j] = min;
                }
            System.out.println("Optimal Cost of multiplying  =     "+M[1][n-1]);
            System.out.println();
            return(B);
        }
}
