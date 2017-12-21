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

    /**
     * Method to set Green & Red values to 0, keeping only 0
     */
    public void keepOnlyBlue()
    {
        Pixel[][] pixels = this.getPixels2D();

        for (Pixel[] pRow : pixels)
        {
            for (Pixel pColumn : pRow)
            {
                pColumn.setRed(0);
                pColumn.setGreen(0);
            }
        }
    }

    /**
     * Negate all the pixels
     */
    public void negate()
    {
        Pixel[][] pixels = this.getPixels2D();

        for (Pixel[] pRow : pixels)
        {
            for (Pixel pColumn : pRow)
            {
                pColumn.setRed(255 - pColumn.getRed());
                pColumn.setGreen(255 - pColumn.getGreen());
                pColumn.setBlue(255 - pColumn.getBlue());
            }
        }
    }

    /**
     * Grayscale all the pixels
     */
    public void grayscale()
    {
        Pixel[][] pixels = this.getPixels2D();

        for (Pixel[] pRow : pixels)
        {
            for (Pixel pColumn : pRow)
            {
                int iAvg = (int) (pColumn.getRed() + pColumn.getGreen() + pColumn.getBlue()) / 3;

                pColumn.setRed(iAvg);
                pColumn.setGreen(iAvg);
                pColumn.setBlue(iAvg);
            }
        }
    }

    /**
     * Fix pixels for "water.jpg"
     */
    public void fixUnderwater()
    {
        Pixel[][] pixels = this.getPixels2D();

        for (Pixel[] pRow : pixels)
        {
            for (Pixel pColumn : pRow)
            {
                int iBlue = pColumn.getBlue();
                int iRed = pColumn.getRed();
                int iGreen = pColumn.getGreen();

                if (iBlue < 155 || iBlue > 180 || iRed < 10 || iRed > 25 || iGreen < 155 || iGreen > 165)
                {
                    pColumn.setRed(0);
                    pColumn.setGreen(0);
                    pColumn.setBlue(0);
                }
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
  
  /**
   * Mirror vertical from right to left
   */
  public void mirrorVerticalRightToLeft()
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
              leftPixel.setColor(rightPixel.getColor());
            }
        } 
  }

    /**
     * Mirror horizontal from top to bottom
     */
    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;

        for (int row = 0; row < height; row++)
        {
            for (int col = 0; col < pixels[0].length; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    /**
     * Mirror horizontal from bottom to top
     */
    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;

        for (int row = 0; row < height; row++)
        {
            for (int col = 0; col < pixels[0].length; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                topPixel.setColor(bottomPixel.getColor());
            }
        }
    }

    /**
     * Mirror diagonal
     */
    public void mirrorDiagonal()
    {
        Pixel[][] pixels = this.getPixels2D();

        Pixel topPixel = null;
        Pixel bottomPixel = null;

        int iLength = pixels.length < pixels[0].length ? pixels.length : pixels[0].length;

        for (int row = 0; row < iLength; row++)
        {
            for (int col = 0; col < iLength; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[col][row];
                bottomPixel.setColor(topPixel.getColor());
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
        rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
        count++;
      }
    }

    System.out.println(count);
  }

    /**
     * Mirrors arms of the snowman vertically
     */
    public void mirrorArms()
    {
        Pixel[][] pixels = this.getPixels2D();

        Pixel topPixel = null;
        Pixel bottomPixel = null;

        for (int row = 160; row < 200; row++)
        {
            for (int col = 105; col < 170; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[195 - row + 195][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }

        for (int row = 170; row < 200; row++)
        {
            for (int col = 239; col < 295; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[200 - row + 200][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    /**
     * Mirrors the seagual horizontally
     */
    public void mirrorGull()
    {
        Pixel[][] pixels = this.getPixels2D();

        Pixel rightPixel = null;
        Pixel leftPixel = null;

        for (int row = 235; row < 325; row++)
        {
            for (int col = 240; col < 350; col++)
            {
                rightPixel = pixels[row][col];
                leftPixel = pixels[row][350 - col + 350/3];
                leftPixel.setColor(rightPixel.getColor());
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
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
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
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
