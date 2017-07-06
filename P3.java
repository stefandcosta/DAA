
import java.util.*;
import java.io.*;

public class P3
{

    public static void main(String[] args)
    {
        Scanner src=new Scanner(System.in);
        System.out.println();
        int N=-1,M=-1;
        try
        {
            N=src.nextInt();
            M=src.nextInt();
        }
        catch(NoSuchElementException exp)
        {
            System.out.println("Empty File Is Given As Input or Invalid Input");
            System.exit(0);
        }

        LinkedList adj[]=new LinkedList[N];

        for(int i=0;i<M;i++)
        {
            int a=-1,b=-1;
            try
            {
                a=src.nextInt();
                b=src.nextInt();
            }
            catch(NoSuchElementException exp)
            {
                System.out.println("Missing Elements in File");
                System.exit(0);
            }

            if(adj[a-1]==null)
            {
                adj[a-1]=new LinkedList();
                adj[a-1].insert(b);
            }
            else
            {
                adj[a-1].insert(b);
            }

        }

        for(int i=1;i<=adj.length;i++)
        {
            System.out.print(i+"---->   ");
            try
            {
                adj[i-1].display();
            }
            catch(NullPointerException exp)
            {
                System.out.print("NULL");
                adj[i-1]=new LinkedList();
            }
            System.out.println();
        }

        if(M==0)
        {
            System.out.println("Graph is Empty, No Further Operations can be performed");
            System.exit(0);
        }

        LinkedList rev[]=LinkedList.reverse(adj);

        
        int order[]=new int[N];
        for(int i=1;i<=N;i++)
            order[i-1]=i;

        int u=0;
        while(u!=order.length)
        {
            if(adj[order[u]-1].color=="WHITE")
            {
                LinkedList.DFS_VISIT(adj,order[u],false);
                u++;
            }
            else
            {
                u++;
            }
        }


        for(int i=1;i<=N;i++)
        {
            order[i-1]=adj[i-1].f;
        }
        
        

        order=LinkedList.order_sort(adj,order);


        System.out.println();
        System.out.println();
        int cnt=0;
        System.out.println("Strongly Connected Components are:-");
        u=0;
        LinkedList.time=0;
        while(u!=order.length)
        {
            if(rev[order[u]-1].color=="WHITE")
            {
		cnt++;
                LinkedList.DFS_VISIT(rev,order[u],true);
                System.out.println();
                u++;
            }
            else
            {
                u++;
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Number of Strongly Connected Components are:-	"+cnt);
	System.out.println();
	System.out.println();

    }

}

class Node
{
    public int edgec;
    public Node next;

    public Node(int edgec)
    {
        this.edgec=edgec;
    }

    public String toString()
    {
        return(""+edgec);
    }

}

class LinkedList
{
    public Node first;
    public int d,f,p;
    static int time=0;
    public String color;
    public LinkedList()
    {
        first=null;
        d=0;
        f=0;
        p=-1;
        color="WHITE";
    }
    public boolean isEmpty()
    {
        return(first==null);
    }
    public void insert(int edgec)
    {
        Node nnode=new Node(edgec);
        nnode.next=first;
        first=nnode;
    }
    public void display()
    {
        Node nodes=first;
        while(nodes!=null)
        {
            System.out.print(nodes+"    ");
            nodes=nodes.next;
        }
    }
    public boolean find(int edgec)
    {
        Node nnode=first;
        if(isEmpty())
        {
            return false;
        }
        else
        {
            while(nnode.edgec!=edgec)
            {
                if(nnode.next==null)
                {
                    return false;
                }
                else
                {
                    nnode=nnode.next;
                }
            }
            return true;
        }
    }
    public static LinkedList[] reverse(LinkedList adj[])
    {
        LinkedList reverse[]=new LinkedList[adj.length];
        for(int i=1;i<=reverse.length;i++)
        {

            Node temp=adj[i-1].first;
            while(temp!=null)
            {
                if(reverse[temp.edgec-1]==null)
                {
                    reverse[temp.edgec-1]=new LinkedList();
                    reverse[temp.edgec-1].insert(i);
                }
                else
                {
                    reverse[temp.edgec-1].insert(i);
                }
                temp=temp.next;
            }
        }

        return reverse;
    }

    public static void DFS_VISIT(LinkedList adj[], int u,boolean print)
    {
        time++;
        adj[u-1].d=time;
        adj[u-1].color="GRAY";

        Node temp=adj[u-1].first;
        while(temp!=null)
        {
            if(adj[temp.edgec-1].color=="WHITE")
            {
                adj[temp.edgec-1].p=u;
                LinkedList.DFS_VISIT(adj,temp.edgec,print);
            }
            
            temp=temp.next;
        }
        if(print)
        {
            System.out.print(u+"    ");
        }

        adj[u-1].color="BLACK";
        time++;
        adj[u-1].f=time;
    }

    public static void insertionsort(int order[])
    {
        for(int i=1;i<order.length;i++)
        {
            int key=order[i];
            int j;
            for(j=i-1;j>=0 && key > order[j];j--)
            {
                order[j+1]=order[j];
            }
            order[j+1]=key;
        }
    }

    public static int[] order_sort(LinkedList adj[], int order[])
    {
        LinkedList.insertionsort(order);
        int temp[]=new int[order.length];
        for(int i=0;i<order.length;i++)
        {
            for(int j=0;j<adj.length;j++)
            {
                if(adj[i].f==order[j])
                    temp[j]=i+1;
            }
        }
        return(temp);
    }

}
