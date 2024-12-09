package barscanner;

//java imports
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//zxing imports
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//misc imports
import javax.imageio.ImageIO;
import util.TextUI;

public class Barscanner {
    //code source: https://www.geeksforgeeks.org/how-to-generate-and-read-qr-code-with-java-using-zxing-library/
    //minorly modified for usage
    // Function to read the QR file
    public static String readCodeFromImage(BufferedImage image) {
        try {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

            Result result = new MultiFormatReader().decode(binaryBitmap);

            return result.getText();
        }
        catch (NotFoundException e){
            TextUI.displayMsg("error, Code not found:" + e.getMessage());
        }
        return null;
    }

    public static String readCodeFromPath(String path){
        try {
            return readCodeFromImage(ImageIO.read(new FileInputStream(path)));
        }
        catch (FileNotFoundException e){
            TextUI.displayMsg("File not found:" + e.getMessage());
        }
        catch (IOException e){
            TextUI.displayMsg("IO exception:" + e.getMessage());
        }
        return null;
    }
}
