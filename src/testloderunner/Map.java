package testloderunner;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class Map {
	
	private int blocklen = 50; //side of a block length, its a square
	private int maplen = 10; //side of a map in numbers of blocks, its a square
	
	public class MapBlock {
		public Rectangle2D myrect;
		public BLOCK_TYPE mytype;
		
		public MapBlock(int placeX, int placeY, int blocklen, BLOCK_TYPE blocktype) {
			myrect = new Rectangle2D.Double(placeX,placeY,blocklen,blocklen);
			mytype = blocktype;
		}
	}
	
	private MapBlock[][] myblocks;
	
	public Map() {
		myblocks = new MapBlock[maplen][maplen]; //xx, yy
	}
	
	public int GetMapLen() {
		return maplen;
	}
	
	public int GetBlockLen() {
		return blocklen;
	}
	
	public Rectangle2D GetRect(int xx, int yy) {
		return myblocks[xx][yy].myrect;
	}
	
	public BLOCK_TYPE GetBlockType(int xx, int yy) {
		return myblocks[xx][yy].mytype;
	}
	
	public boolean LoadMap(String filename) {
		try {

			File  mapfile = new File(filename);
			Scanner myreader = new Scanner(mapfile);
			int placeY = 30;
			for (int yy=0; yy < maplen; yy++)
			{
				int placeX = 30;

				String line = myreader.nextLine();
				for (int xx=0; xx < maplen; xx++)
				{
					BLOCK_TYPE blocktype;
					if (line.charAt(xx) == 'A')
						blocktype = BLOCK_TYPE.AIR;
					else if (line.charAt(xx) == 'L')
						blocktype = BLOCK_TYPE.LADDER;
					else if (line.charAt(xx) == 'B')
						blocktype = BLOCK_TYPE.BRICK;
					else if (line.charAt(xx) == 'S')
						blocktype = BLOCK_TYPE.STEEL;
					else {
						throw new Exception("Bad blocktype letter found in given mapfile!");
					}
					
					myblocks[xx][yy] = new MapBlock(placeX,placeY,blocklen,blocktype);
					placeX += blocklen;
				}
				placeY += blocklen;
			}
		}
		catch (Exception ee) {
			System.out.println("Error in class Map method LoadMap!");
			String currentDirectory = System.getProperty("user.dir");
		    System.out.println("The current working directory is " + currentDirectory);
			ee.printStackTrace();
			return false;
		}
		
		return true;
	}
}
