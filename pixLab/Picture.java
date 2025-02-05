import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to keep only the blue*/
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
        pixelObj.setRed(0);
      }
    }
  }
  
  /** Method to negate pic*/
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int green = pixelObj.getGreen();
        int red = pixelObj.getRed();
        int blue = pixelObj.getBlue();
        pixelObj.setBlue(255 - blue);
        pixelObj.setRed(255 - red);
        pixelObj.setGreen(255 - green);
      }
    }
  }
  
  /** Method to grayscale pic*/
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int green = pixelObj.getGreen();
        int red = pixelObj.getRed();
        int blue = pixelObj.getBlue();
        int avg = (green + red + blue) / 3;
        pixelObj.setBlue(avg);
        pixelObj.setRed(avg);
        pixelObj.setGreen(avg);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("images/flower1.jpg");
    Picture flower2 = new Picture("images/flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("images/collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
	
	/** To pixelate by dividing area into size x size.
	* @param size Side length of square area to pixelate.
	*/
	public void pixelate(int size) {
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 0; row < pixels.length; row += size) {
			for (int col = 0; col < pixels[0].length; col += size) {
				int redSum = 0; int blueSum = 0; int greenSum = 0;
				int endRow = Math.min(pixels.length - 1, row + size - 1);
				int endCol = Math.min(pixels[0].length - 1, col + size - 1);
				for (int currRow = row; currRow <= endRow; currRow++) {
					for (int currCol = col; currCol <= endCol; currCol++) {
						Pixel pixObj = pixels[currRow][currCol];
						redSum += pixObj.getRed();
						blueSum += pixObj.getBlue();
						greenSum += pixObj.getGreen();
					}
				}
				int actRowSize = endRow - row + 1;
				int actColSize = endCol - col + 1;
				int avgRed = redSum / (actRowSize * actColSize);
				int avgBlue = blueSum / (actRowSize * actColSize);
				int avgGreen = greenSum / (actRowSize * actColSize);
				for (int currRow = row; currRow <= endRow; currRow++) {
					for (int currCol = col; currCol <= endCol; currCol++) {
						Pixel currPix = pixels[currRow][currCol];
						currPix.setRed(avgRed);
						currPix.setGreen(avgGreen);
						currPix.setBlue(avgBlue);
					}
				}
			}
		}
	}
	
	/** Method that blurs the picture
	* @param size Blur size, greater is more blur
	* @return Blurred picture
	*/
	public Picture blur(int size) {
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				int rowIdxStart = Math.max(0, row - size / 2);
				int rowIdxEnd = Math.min(pixels.length - 1, row + size / 2);
				int colIdxStart = Math.max(0, col - size / 2);
				int colIdxEnd = Math.min(pixels[0].length - 1, col + size / 2);
				int redSum = 0; int blueSum = 0; int greenSum = 0;
				for (int resRow = rowIdxStart; resRow <= rowIdxEnd; resRow++) {
					for (int resCol = colIdxStart; resCol <= colIdxEnd; resCol++) {
						Pixel pixObj = pixels[resRow][resCol];
						redSum += pixObj.getRed();
						blueSum += pixObj.getBlue();
						greenSum += pixObj.getGreen();
					}
				}
				int actRowSize = rowIdxEnd - rowIdxStart + 1;
				int actColSize = colIdxEnd - colIdxStart + 1;
				int avgRed = redSum / (actRowSize * actColSize);
				int avgBlue = blueSum / (actRowSize * actColSize);
				int avgGreen = greenSum / (actRowSize * actColSize);
				Pixel currPix = resultPixels[row][col];
				currPix.setRed(avgRed);
				currPix.setGreen(avgGreen);
				currPix.setBlue(avgBlue);
			}
		}
		return result;
	}
	
	/** Method that enhances a picture by getting average Color around
	* a pixel then applies the following formula:
	*
	* pixelColor <- 2 * currentValue - averageValue
	*
	* size is the area to sample for blur.
	*
	* @param size Larger means more area to average around pixel
	* and longer compute time.
	* @return enhanced picture
	*/
	public Picture enhance(int size) {
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				Pixel currPix = pixels[row][col];
				int currRed = currPix.getRed(); 
				int currBlue = currPix.getBlue(); 
				int currGreen = currPix.getGreen();
				int rowIdxStart = Math.max(0, row - size / 2);
				int rowIdxEnd = Math.min(pixels.length - 1, row + size / 2);
				int colIdxStart = Math.max(0, col - size / 2);
				int colIdxEnd = Math.min(pixels[0].length - 1, col + size / 2);
				int redSum = 0; int blueSum = 0; int greenSum = 0;
				for (int resRow = rowIdxStart; resRow <= rowIdxEnd; resRow++) {
					for (int resCol = colIdxStart; resCol <= colIdxEnd; resCol++) {
						Pixel pixObj = pixels[resRow][resCol];
						redSum += pixObj.getRed();
						blueSum += pixObj.getBlue();
						greenSum += pixObj.getGreen();
					}
				}
				int actRowSize = rowIdxEnd - rowIdxStart + 1;
				int actColSize = colIdxEnd - colIdxStart + 1;
				int avgRed = redSum / (actRowSize * actColSize);
				int avgBlue = blueSum / (actRowSize * actColSize);
				int avgGreen = greenSum / (actRowSize * actColSize);
				int setRed = Math.max(0, Math.min(255, 2 * currRed - avgRed));
				int setBlue = Math.max(0, Math.min(255, 2 * currBlue - avgBlue));
				int setGreen = Math.max(0, Math.min(255, 2 * currGreen - avgGreen));
				Pixel currPixel = resultPixels[row][col];
				currPixel.setRed(setRed);
				currPixel.setGreen(setGreen);
				currPixel.setBlue(setBlue);
			}
		}
		return result;
	}
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
