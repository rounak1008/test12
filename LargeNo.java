import java.util.Scanner;
public class LargeNo{

    public static void main(String[] args){
        int a,b;

        Scanner sc=new Scanner(System.in);

        System.out.print("Enter a no : ");
        a=sc.nextInt();

        System.out.print("Enter another no : ");
        b=sc.nextInt();

        if(a>b){
            System.out.println(a+" is greater than "+b);
        }else{
            System.out.println(b+" is greater than "+a);
        }
    }
}