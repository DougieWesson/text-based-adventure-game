import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    private JFrame frame;

    public ImagePanel(String imageFileName) {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        File file = new File("resources/" + imageFileName);
        try {
            this.image = ImageIO.read(new File(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.repaint();
        this.frame.add(this);
        this.frame.pack();
        this.frame.setSize(this.image.getWidth(), this.image.getHeight());
        this.frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, this);
    }

    public static void main(String[] args) {
        ImagePanel imagePanel = new ImagePanel("guybrush.jpg");
    }
}
