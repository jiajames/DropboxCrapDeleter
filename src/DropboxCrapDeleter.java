
import java.io.File;
import java.util.Scanner;

/**
 * Delete everything in this directory with given input prefix string.
**/
public class DropboxCrapDeleter {

	public static void main(String[] args) {

		File file = new File(args[0]);

		if (file.isDirectory()) {

			System.out.println("These are the subfiles:");

			Scanner scanInput = new Scanner(System.in);
			
			for(File subFiles : file.listFiles()) {
				System.out.println(subFiles.getName());
			}
			
			System.out.print("Is this the correct Directory? Yes or No: ");
			
			String data = scanInput.nextLine();
			
			System.out.print("File prefix? ");
			String prefix = scanInput.nextLine();
			
			if (data.equals("Yes") || data.equals("yes")) {
				deleter(file, prefix);
			}

			else {
				System.out.println("Looks like wrong directory. Try again. " + data);
			}

			scanInput.close(); 
			
			System.out.println("Files deleted.");
			
			return;
		}

		else {
			System.out.println("Argument is not a directory.");
		}

		return;

	}

	private static void deleter(File curDir, String prefix) {
		for (File subFile : curDir.listFiles()) {

			if (subFile.isDirectory()) {
				deleter(subFile, prefix);
			}

			else {
				try {
					if (subFile.getName().substring(0, prefix.length()).equals(prefix)) {
						if(subFile.delete()) {
							System.out.println(subFile.getName() + " has been deleted.");
						}
					}
				} catch (IndexOutOfBoundsException e) {
					// do nothing
				}
			}
		}
	}
}