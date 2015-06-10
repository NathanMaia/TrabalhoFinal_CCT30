package control;

import java.awt.image.*;

/**
 * A filter which applies Gaussiano  to an image. This is a subclass of ConvolveFilter
 * which simply creates a kernel with a Gaussiano distribution for ring.
 * @author Jerry Huxtable
 */
public class FiltroGaussianoo extends ConvolveFilter {

	static final long serialVersionUID = 5377089073023183684L;

	protected float raio;
	protected Kernel kernel;
	
	/**
	 * Construct a Gaussiano filter
	 */
	public FiltroGaussianoo() {
		this(2);
	}

	

	/**
	 * Construct a Gaussiano filter
	 * @param raio  raio in pixels
	 */
	public FiltroGaussianoo(float raio) {
		setraio(raio);
	}

	/**
	 * Set the raio of the kernel, and hence the amount of . The bigger the raio, the longer this filter will take.
	 * @param raio the raio of the  in pixels.
	 */
	public void setraio(float raio) {
		this.raio = raio;
		kernel = criaKernel(raio);
	}
	
	/**
	 * Get the raio of the kernel.
	 * @return the raio
	 */
	public float getraio() {
		return raio;
	}

    public BufferedImage filter( BufferedImage src, BufferedImage dst ) {
        int width = src.getWidth();
        int height = src.getHeight();

        if ( dst == null )
            dst = createCompatibleDestImage( src, null );

        int[] inPixels = new int[width*height];
        int[] outPixels = new int[width*height];
        src.getRGB( 0, 0, width, height, inPixels, 0, width );

		convolucionarETranspor(kernel, inPixels, outPixels, width, height, alpha, CLAMP_EDGES);
		convolucionarETranspor(kernel, outPixels, inPixels, height, width, alpha, CLAMP_EDGES);

        dst.setRGB( 0, 0, width, height, inPixels, 0, width );
        return dst;
    }

	public static void convolucionarETranspor(Kernel kernel, int[] inPixels, int[] outPixels, int width, int height, boolean alpha, int edgeAction) {
		float[] matriz = kernel.getKernelData( null );
		int cols = kernel.getWidth();
		int cols2 = cols/2;

		for (int y = 0; y < height; y++) {
			int index = y;
			int ioffset = y*width;
			for (int x = 0; x < width; x++) {
				float r = 0, g = 0, b = 0, a = 0;
				int moffset = cols2;
				for (int col = -cols2; col <= cols2; col++) {
					float f = matriz[moffset+col];

					if (f != 0) {
						int ix = x+col;
						if ( ix < 0 ) {
							if ( edgeAction == CLAMP_EDGES )
								ix = 0;
							else if ( edgeAction == WRAP_EDGES )
								ix = (x+width) % width;
						} else if ( ix >= width) {
							if ( edgeAction == CLAMP_EDGES )
								ix = width-1;
							else if ( edgeAction == WRAP_EDGES )
								ix = (x+width) % width;
						}
						int rgb = inPixels[ioffset+ix];
						a += f * ((rgb >> 24) & 0xff);
						r += f * ((rgb >> 16) & 0xff);
						g += f * ((rgb >> 8) & 0xff);
						b += f * (rgb & 0xff);
					}
				}
				int ia = alpha ? PixelUtils.clamp((int)(a+0.5)) : 0xff;
				int ir = PixelUtils.clamp((int)(r+0.5));
				int ig = PixelUtils.clamp((int)(g+0.5));
				int ib = PixelUtils.clamp((int)(b+0.5));
				outPixels[index] = (ia << 24) | (ir << 16) | (ig << 8) | ib;
                index += height;
			}
		}
	}

	//Kernel do filtro
	public static Kernel criaKernel(float raio) {
		int r = (int)Math.ceil(raio);
		int rows = r*2+1;
		float[] matriz = new float[rows];
		float sigma = raio/3;
		float sigma22 = 2*sigma*sigma;
		float sigmaPi2 = 2*ImageMath.PI*sigma;
		float sqrtSigmaPi2 = (float)Math.sqrt(sigmaPi2);
		float raio2 = raio*raio;
		float total = 0;
		int index = 0;
		for (int row = -r; row <= r; row++) {
			float distancia = row*row;
			if (distancia > raio2)
				matriz[index] = 0;
			else
				matriz[index] = (float)Math.exp(-(distancia)/sigma22) / sqrtSigmaPi2;
			total += matriz[index];
			index++;
		}
		for (int i = 0; i < rows; i++)
			matriz[i] /= total;

		return new Kernel(rows, 1, matriz);
	}

	public String toString() {
		return "/Gaussiano ...";
	}
}