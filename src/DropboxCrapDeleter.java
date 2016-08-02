
import java.io.File;
import java.util.Scanner;

/**
 * Delete everything in this directory and subdirectories with "._" at the beginning of the name. 
**/
public class DropboxCrapDeleter {

	public static void main(String[] args) {

		File file = new File(args[0]);

		if (file.isDirectory()) {

			System.out.println("These are the subfiles:");

			for(File subFiles : file.listFiles()) {
				System.out.println(subFiles.getName());
			}

			System.out.print("Is this the correct Directory? Yes or No: ");
			
			Scanner scanInput = new Scanner(System.in);
			String data = scanInput.nextLine();

			if (data.equals("Yes") || data.equals("yes")) {
				deleter(file);
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

	private static void deleter(File curDir) {
		for (File subFile : curDir.listFiles()) {

			if (subFile.isDirectory()) {
				deleter(subFile);
			}

			else {
				try {
					if (subFile.getName().charAt(0) == '.' && subFile.getName().charAt(1) == '_') {
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