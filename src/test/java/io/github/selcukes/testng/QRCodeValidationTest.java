package io.github.selcukes.testng;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import io.github.selcukes.wdb.WebDriverBinary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class QRCodeValidationTest {
    @Test
    public void testQRCode() throws Exception {
        WebDriverBinary.chromeDriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://cdn.qrstuff.com/");
        String qrCodeURL = driver.findElement(By.id("qrcode-preview")).getAttribute("src");
        URL url = new URL(qrCodeURL);
        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("Sample-QR.png")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException ignored) {

        }
        File file = new File("Sample-QR.png");
        validateQRCode(file);
        Assert.assertEquals(validateQRCode(file), "http://www.qrstuff.com");
    }

    public String validateQRCode(File qrCodeImage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeImage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            System.out.println("There is no QR code in the image");
            return null;

        }
    }

}
