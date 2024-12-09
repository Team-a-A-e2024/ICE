package barscanner;

//java imports
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
    public static String readQR(String path)

    {
        try {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));

            Result result = new MultiFormatReader().decode(binaryBitmap);

            return result.getText();
        }
        catch (FileNotFoundException e){
            TextUI.displayMsg("File not found");
        }
        catch (IOException e){
            TextUI.displayMsg("error");
        }
        catch (NotFoundException e){
            TextUI.displayMsg("error, Code not found");
        }
        return null;
    }

}
