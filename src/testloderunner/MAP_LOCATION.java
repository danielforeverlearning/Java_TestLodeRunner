package testloderunner;

public class MAP_LOCATION {
	public int START_XX;
	public int END_XX;
	public int START_YY;
	public int END_YY;
	
	public MAP_LOCATION() {
		START_XX = -1;
		END_XX = -1;
		START_YY = -1;
		END_YY = -1;
	}
	
	boolean equals (MAP_LOCATION other) {
		if (START_XX == other.START_XX && END_XX == other.END_XX && START_YY == other.START_YY && END_YY == other.END_YY)
			return true;
		else
			return false;
	}
	
	public void Copy(MAP_LOCATION src)
	{
		START_XX = src.START_XX;
		START_YY = src.START_YY;
		END_XX   = src.END_XX;
		END_YY   = src.END_YY;
	}
	
	public void DebugPrint() {
		String infostr = String.format("(%d,%d) (%d,%d)", START_XX, START_YY, END_XX, END_YY);
		System.out.println(infostr);
	}

}
