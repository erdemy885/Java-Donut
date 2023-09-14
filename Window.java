public class Window {
	private char output[][];
	private double zbuffer[][];
	private final String brightness = ".,-~:;=!*#$@@";
	private double xoffset, yoffset;
	public Window (int width, int height) {
		output = new char[height/2][width];
		zbuffer = new double[height/2][width];
		xoffset = output[0].length*0.5;
		yoffset = output.length*0.5;
		clear();
	}

	public void clear() {
		for(int i = 0; i < output.length; i++) {
			for(int j = 0; j < output[0].length; j++) {
				output[i][j] = ' ';
				zbuffer[i][j] = 0;
			}
		}
	}

	public void draw(int x) {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		for(int i = 0; i < output.length; i++) {
			for(int j = 0; j < output[0].length; j++) {
				System.out.print(output[i][j]);
			}
			System.out.println();
		}
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void set(double x, double y, double z, int luminance) {
		int width = (int)(xoffset + x);
		int height = (int)(yoffset - y * 0.5);
		if(z > zbuffer[height][width]) {
			if(luminance < 0) {
				luminance = 0;
			}
			zbuffer[height][width] = z;
			output[height][width] = brightness.charAt(luminance);
		}
	}
}

