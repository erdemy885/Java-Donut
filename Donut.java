public class Donut {
	static final int screen_size = 100;

	static final double theta_spacing = 0.07;
	static final double phi_spacing = 0.02;
	static final double a_spacing = 0.01;
	static final double b_spacing = 0.005;

	static final int r1 = 10;
	static final int r2 = 20;
	static final int K2 = 500;

	static double lfac = 12/Math.sqrt(3);
	static final double K1 = (double)(3*screen_size*K2)/(8*(r1+r2));

	static double x, y, x2, y2, z, cosTheta, sinTheta, cosPhi, sinPhi, sinA, sinB, cosA, cosB, sx, sy, luminance;
	static double A = 0;
	static double B = 0;
	static double invz = 0;

	public static void main(String[] args) {
		Window win = new Window(screen_size, screen_size);
		for(;;) {
			win.clear();
			sinA = Math.sin(A);
			sinB = Math.sin(B);
			cosA = Math.cos(A);
			cosB = Math.cos(B);

			for(double theta = 0; theta <= 6.28; theta += theta_spacing) {
				cosTheta = Math.cos(theta);
				sinTheta = Math.sin(theta);

				x2 = r2 + r1*cosTheta;
				y2 = r1*sinTheta;

				for(double phi = 0; phi <= 6.28; phi += phi_spacing) {
					cosPhi = Math.cos(phi);
					sinPhi = Math.sin(phi);

					x = (x2 * cosPhi) * cosB - sinB * (y2 * cosA - sinA * (-sinPhi * x2));
					y = (y2 * cosA - sinA * (-sinPhi * x2)) * cosB + (x2 * cosPhi) * sinB;
					z = K2 + (-sinPhi * x2) * cosA + y2 * sinA;

					invz = 1/z;

					sx = K1 * x * invz;
					sy = K1 * y * invz;

					luminance = -(cosTheta * cosPhi * cosB - sinB * (sinTheta * cosA + cosTheta * sinPhi * sinA)) + (cosTheta * cosPhi * sinB + cosB * (sinTheta * cosA + cosTheta * sinPhi * sinA)) - (sinTheta * sinA - cosTheta * sinPhi * cosA);

					win.set(sx, sy, invz, (int)(luminance*lfac));
				}
			}
			A += a_spacing;
			B += b_spacing;
			win.draw(0);
		}
	}
}