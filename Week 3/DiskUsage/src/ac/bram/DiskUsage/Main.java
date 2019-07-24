package ac.bram.DiskUsage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	Scanner input = new Scanner(System.in);

	public Main() {
		System.out.println("Which directory :");
		String dir = input.nextLine();
		dir = "C:\\Users\\Abram\\Documents\\MSwDev 2019\\SWEN 501\\Week 1";
		this.sizeCal(dir);
	}

	private void sizeCal(String dir) {
		long size = 0;
		if(new File(dir).isDirectory()) {
			File[] fileList = new File(dir).listFiles();

			for(File f : fileList) {
				if(!f.isDirectory()) {
					size = f.length() + size;
					String pathName = f.getPath();
					System.out.println("File Path: " + pathName + " ," + f.length());
				}
				else
				{
					String newPath = f.getPath();
					System.out.println("THIS is an directory ************* " + f.getPath());
					this.sizeCal(newPath);
				}
			}
		}
	}

public static void main(String[] args) {
	new Main();
}

}
