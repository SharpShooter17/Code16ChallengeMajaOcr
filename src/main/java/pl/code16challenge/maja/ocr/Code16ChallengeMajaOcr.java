package pl.code16challenge.maja.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.lang.System.out;

public class Code16ChallengeMajaOcr {
    public static void main(String[] args) throws IOException, TesseractException, URISyntaxException {
        Files.copy(
                new URL("https://github.com/tesseract-ocr/tessdata/raw/master/pol.traineddata").openStream(),
                Paths.get("pol.traineddata"),
                StandardCopyOption.REPLACE_EXISTING
        );
        InputStream code16ChallengeImage = Code16ChallengeMajaOcr.class.getClassLoader().getResourceAsStream("code16challenge.png");
        ITesseract instance = new Tesseract();
        instance.setLanguage("pol");

        out.println(instance.doOCR(ImageIO.read(code16ChallengeImage)));
        Desktop.getDesktop().browse(new URL("https://www.siepomaga.pl/code16challenge").toURI());
    }

}