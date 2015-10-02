import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Inventory 
{
	//constructor
	public Inventory() {}
	
	//methods
	public boolean accessInventory(String databaseFile, List <Item> databaseItem)
	{
		boolean ableToOpen = true;
		
		String line = null;
		String[] lineSort;
		int numLine = 0;
		
		//Checks database file for the item		
		try {
			FileReader fileR = new FileReader(databaseFile);
			BufferedReader textReader = new BufferedReader(fileR);
			//reads the entire database
			while ((line = textReader.readLine()) != null)
			{
				lineSort = line.split(" "); //separates words
				databaseItem.add(new Item(Integer.parseInt(lineSort[0]),lineSort[1],Float.parseFloat(lineSort[2]),
						Integer.parseInt(lineSort[3])));
				numLine++;
			}
			
			textReader.close();
		}
		
		//catches exceptions
		 catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                		databaseFile + "'"); 
	            ableToOpen = false;
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + databaseFile + "'");  
	            ableToOpen = false;
	        }
		
		
		
		
		return ableToOpen;
	}
	
	public void updateInventory(String databaseFile, List <Item> transactionItem, List <Item> databaseItem)
	{
		int counter2;
		int newAmount; //stores new amount (number of items in database - number of items in transaction)
		
		
		//updates inventory list
		for (int counter = 0 ; counter < transactionItem.size(); counter++) //for every item on this transaction
		{
			for (counter2 = 0; counter2 < databaseItem.size(); counter2++) //for every item on the database
			{
				if (transactionItem.get(counter) == databaseItem.get(counter2)) //if itemIDs are equal, update new amount on the list
				{
					newAmount = databaseItem.get(counter2).getAmount() - transactionItem.get(counter).getAmount();
					databaseItem.get(counter2).updateAmount(newAmount);
					break; //breaks when item is found
				}
			}
		}
		
		
		//save into database.txt file (to implement)
	}
	
	
}