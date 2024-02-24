package CodeWars.engine.client;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Utilities
{
    public static int getFileSize(File file)throws IOException
    {
        Scanner input = new Scanner(new FileReader(file));
        int size=0;
        while (input.hasNextLine())				//while there is another line in the file
        {
            size++;										//add to the size
            input.nextLine();							//go to the next line in the file
        }
        input.close();									//always close the files when you are done
        return size;
    }

    //pre:  "fileName" is the name of a real file containing lines of text - the first line intended to be unused
    //post:returns a String array of all the elements in <filename>.txt, with index 0 unused (heap) O(n)
    public static String[] readFile(File file)throws IOException
    {
        int size = getFileSize(file);        //holds the # of elements in the file
        String[] list = new String[size];        //a heap will not use index 0;
        Scanner input = new Scanner(new FileReader(file));
        int i = 0;                                            //index for placement in the array
        String line;
        while (input.hasNextLine())                //while there is another line in the file
        {
            line = input.nextLine();                    //read in the next Line in the file and store it in line
            list[i] = line;                                //add the line into the array
            i++;                                            //advance the index of the array
        }
        input.close();
        return list;
    }

    public static String[] readFile(String filePath)throws IOException
    {
        File file = new File(filePath);
        int size = getFileSize(file);        //holds the # of elements in the file
        String[] list = new String[size];        //a heap will not use index 0;
        Scanner input = new Scanner(new FileReader(file));
        int i = 0;                                            //index for placement in the array
        String line;
        while (input.hasNextLine())                //while there is another line in the file
        {
            line = input.nextLine();                    //read in the next Line in the file and store it in line
            list[i] = line;                                //add the line into the array
            i++;                                            //advance the index of the array
        }
        input.close();
        return list;
    }
    public static void pauseExecution(int millis)
    {
        long finalTime = System.currentTimeMillis() + millis;
        long currentTimeMillis;
        do
        {
            currentTimeMillis = System.currentTimeMillis();
        }while(currentTimeMillis < finalTime);
    }
}
